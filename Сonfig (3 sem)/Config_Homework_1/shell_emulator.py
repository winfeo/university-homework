import sys
import os
import tarfile
import csv
import time
import re
import fnmatch
import xml.etree.ElementTree as ET
from datetime import datetime
import tkinter as tk
from tkinter import Text, Entry, Button, Scrollbar
import subprocess

class ShellEmulator:
    def __init__(self, root):
        self.root = root
        self.cwd = "papka"  # Корневая директория
        self.vfs = {}
        self.log_file = 'log.xml'
        self.load_config()
        self.load_vfs()
        self.log_action("Session started")
        self.initUI()
        self.set_file_permissions()
        self.run_start_script()

    def set_file_permissions(self):
        file_path = self.start_script
        if os.path.exists(file_path):
            permissions = 0o777
            os.chmod(file_path, permissions)
        else:
            print(f"File not found: {file_path}")

    def load_config(self):
        with open('config.csv', newline='') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                self.vfs_path = row['Path to VFS Archive']
                self.log_file = row['Path to Log File']
                self.start_script = row['Path to Start Script']

    def load_vfs(self):
        with tarfile.open(self.vfs_path, 'r') as tar:
            for member in tar.getmembers():
                if member.isfile():
                    file_content = tar.extractfile(member).read().decode('utf-8')
                    self.vfs[member.name] = file_content
                else:
                    self.vfs[member.name] = None

    def log_action(self, action, result=None):
        try:
            tree = ET.parse(self.log_file)
            root = tree.getroot()
        except FileNotFoundError:
            root = ET.Element("log")

        entry = ET.SubElement(root, "entry")
        entry.set("timestamp", datetime.now().isoformat())
        entry.set("command", action)
        if result is not None:
            entry.set("result", result)

        tree = ET.ElementTree(root)
        tree.write(self.log_file, encoding='utf-8', xml_declaration=True)

    def initUI(self):
        self.root.title('Shell Emulator')
        self.root.geometry('800x600')

        # Создаем текстовое поле для вывода команд и результатов
        self.output_text = Text(self.root, state='disabled', wrap='none', bg='black', fg='white',
                                insertbackground='white')
        self.output_text.pack(fill=tk.BOTH, expand=True)

        # Добавляем полосу прокрутки для вывода
        self.output_scrollbar = Scrollbar(self.root, command=self.output_text.yview)
        self.output_scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        self.output_text.config(yscrollcommand=self.output_scrollbar.set)

        # Создаем текстовое поле для ввода команд
        self.input_text = Text(self.root, height=1, wrap='none', bg='black', fg='white', insertbackground='white')
        self.input_text.pack(fill=tk.X)

        # Привязываем событие нажатия клавиши Enter
        self.input_text.bind('<Return>', self.execute_command)

    def run_start_script(self):
        try:
            script_path = os.path.abspath(self.start_script)
            if not os.path.exists(script_path):
                raise FileNotFoundError(f"File not found: {script_path}")

            with open(script_path, 'r', encoding='utf-8') as file:
                commands = file.read().strip().split('\n')

            for command in commands:
                self.output_text.config(state='normal')

                # Выводим корректное приглашение с "~" для корневой директории
                prompt_path = "~" if self.cwd == "papka" else self.cwd
                self.output_text.insert(tk.END, f"user@shell:{prompt_path}$ {command}\n")
                self.output_text.config(state='disabled')
                self.execute_command(None, command, from_start_script=True)

            self.log_action(f"Executed start script: {script_path}")
            self.prompt()

        except Exception as e:
            self.output_text.config(state='normal')
            self.output_text.insert(tk.END, f"Error executing start script: {e}\n")
            self.output_text.config(state='disabled')
            self.log_action(f"Error executing start script: {e}")

    def execute_command(self, event=None, command=None, from_start_script=False):
        if command is None:
            command = self.input_text.get("1.0", tk.END).strip()
            self.input_text.delete("1.0", tk.END)

        if not from_start_script:
            self.output_text.config(state='normal')
            self.output_text.insert(tk.END, f"{command}\n")
            self.output_text.config(state='disabled')

        parts = command.split()
        if not parts:
            return

        cmd = parts[0]
        args = parts[1:]

        result = None

        if cmd == "ls":
            result = self.ls(args)
        elif cmd == "cd":
            result = self.cd(args)
            self.output_text.see(tk.END)
        elif cmd == "echo":
            result = self.echo(args)
        elif cmd == "mv":
            result = self.mv(args)
        elif cmd == "find":
            result = self.find(args)
        elif cmd == "exit":
            self.root.quit()
        else:
            self.output_text.config(state='normal')
            self.output_text.insert(tk.END, f"Command not found: {cmd}\n")
            self.output_text.config(state='disabled')
            result = f"Command not found: {cmd}"

        # Добавляем prompt только для интерактивных команд
        if not from_start_script and cmd != "cd":
            self.prompt()
            self.output_text.see(tk.END)

        self.log_action(command, result)

    def load_vfs(self):
        with tarfile.open(self.vfs_path, 'r') as tar:
            for member in tar.getmembers():
                if member.isfile():
                    file_content = tar.extractfile(member).read().decode('utf-8')
                    self.vfs[member.name] = {
                        'content': file_content,
                        'size': len(file_content),
                        'mtime': member.mtime
                    }
                else:
                    self.vfs[member.name] = {
                        'content': None,  # Папка
                        'size': 4096,  # Размер метаданных папки
                        'mtime': member.mtime
                    }
            # Отладочный вывод
            print("Loaded VFS structure:")
            for key, value in self.vfs.items():
                print(f"{key}: {value}")

    def ls(self, args):
        path = self.cwd
        if path in self.vfs:
            if self.vfs[path]['content'] is None:  # Директория
                files = [f for f in self.vfs.keys() if f.startswith(path + '/') and f.count('/') == path.count('/') + 1]

                long_format = "-l" in args
                human_readable = "-h" in args

                output_lines = []

                for file in files:
                    full_path = file
                    file_info = self.vfs[full_path]

                    size = file_info['size']

                    # Флаг -h
                    size_display = self.human_readable_size(size) if human_readable else str(size)

                    if long_format:
                        type_flag = 'd' if file_info['content'] is None else '-'
                        permissions = "rw-rw-r--"
                        owner = "user"
                        group = "user"
                        last_modified = time.strftime('%b %d %H:%M', time.localtime(file_info['mtime']))

                        output_lines.append(
                            f"{type_flag}{permissions} 1 {owner} {group} {size_display} {last_modified} {file.split('/')[-1]}")
                    else:
                        output_lines.append(file.split('/')[-1])

                self.output_text.config(state='normal')
                self.output_text.insert(tk.END, "\n".join(output_lines) + "\n")
                self.output_text.see(tk.END)
                self.output_text.config(state='disabled')
            else:
                self.output_text.config(state='normal')
                self.output_text.insert(tk.END, f"{path} is not a directory\n")
                self.output_text.config(state='disabled')
        else:
            self.output_text.config(state='normal')
            self.output_text.insert(tk.END, "Directory not found\n")
            self.output_text.see(tk.END)
            self.output_text.config(state='disabled')

    def human_readable_size(self, size):
        if size < 1024:
            return f"{size} B"
        elif size < 1024 ** 2:
            return f"{size / 1024:.1f} KB"
        elif size < 1024 ** 3:
            return f"{size / (1024 ** 2):.1f} MB"
        else:
            return f"{size / (1024 ** 3):.1f} GB"

    def get_size_recursive(self, path):
        total_size = 0
        for item in self.vfs.keys():
            if item.startswith(path + '/') and item != path:
                total_size += self.vfs[item]['size']
        return total_size

    def ls_recursive(self, path, files):
        self.output_text.config(state='normal')
        self.output_text.insert(tk.END, f"{path}:\n")
        self.output_text.insert(tk.END, "\n".join(files) + "\n\n")
        self.output_text.config(state='disabled')

        for file in files:
            if self.vfs[file] is None:
                sub_files = [f for f in self.vfs.keys() if f.startswith(file) and f != file]
                self.ls_recursive(file, sub_files)

    def cd(self, args):
        # Проверяем, если введенные аргументы валидны
        if len(args) > 1:
            self.output_text.config(state='normal')
            self.output_text.insert(tk.END, "cd: too many arguments\n")
            self.output_text.see(tk.END)
            self.output_text.config(state='disabled')
            self.prompt()
            return

        # Перемещение на один каталог вверх
        if not args:
            self.cwd = "papka"
        else:
            path = args[0]

            if path == "..":  # Переход на один уровень вверх
                if self.cwd != "papka":
                    self.cwd = "/".join(self.cwd.split("/")[:-1]) or "papka"
            elif path == "-":  # Возвращение в предыдущий каталог
                if hasattr(self, 'prev_cwd'):
                    self.cwd, self.prev_cwd = self.prev_cwd, self.cwd
                else:
                    self.output_text.config(state='normal')
                    self.output_text.insert(tk.END, "No previous directory\n")
                    self.output_text.config(state='disabled')
                    self.prompt()
                    return
            elif path == "/":  # Переход в корневую директорию
                self.cwd = "papka"
            else:
                # Нормализация пути
                components = self.cwd.strip("/").split("/") + path.split("/")
                normalized_components = []

                for component in components:
                    if component == "" or component == ".":  # Пропускаем пустые и текущие директории
                        continue
                    elif component == "..":
                        if normalized_components:
                            normalized_components.pop()
                    else:
                        normalized_components.append(component)

                full_vfs_path = "/".join(normalized_components).strip("/")

                # Проверяем, существует ли целевой путь в VFS и является ли он директорией
                if full_vfs_path in self.vfs and self.vfs[full_vfs_path]['content'] is None:
                    self.prev_cwd = self.cwd
                    self.cwd = full_vfs_path
                else:
                    self.output_text.config(state='normal')
                    self.output_text.insert(tk.END, f"cd: no such file or directory: {path}\n")
                    self.output_text.config(state='disabled')
                    self.prompt()
                    return

        self.prompt()

    def echo(self, args):
        self.output_text.config(state='normal')
        self.output_text.insert(tk.END, " ".join(args) + "\n")
        self.output_text.config(state='disabled')

    def mv(self, args):
        if len(args) < 2:
            self.output_text.config(state='normal')
            self.output_text.insert(tk.END, "mv: missing file operand\n")
            self.output_text.config(state='disabled')
            self.prompt()
            return

        destination = args[-1]
        sources = args[:-1]

        # Определяем полный путь назначения
        if destination.startswith("/"):
            full_destination = destination.strip("/")
        else:
            full_destination = "/".join([self.cwd.strip("/"), destination]).strip("/")

        is_directory = full_destination in self.vfs and self.vfs[full_destination]['content'] is None

        for source in sources:
            if source.startswith("/"):
                full_source = source.strip("/")
            else:
                full_source = "/".join([self.cwd.strip("/"), source]).strip("/")

            if full_source not in self.vfs:
                self.output_text.config(state='normal')
                self.output_text.insert(tk.END, f"mv: cannot stat '{source}': No such file or directory\n")
                self.output_text.config(state='disabled')
                continue

            if is_directory:
                destination_path = f"{full_destination}/{source.split('/')[-1]}"
            else:
                destination_path = full_destination

            self.vfs[destination_path] = self.vfs[full_source]
            del self.vfs[full_source]

        # Уведомление о результате перемещения
        self.output_text.config(state='normal')
        self.output_text.insert(tk.END, f"Moved {', '.join(sources)} to {destination}\n")
        self.output_text.config(state='disabled')

    def find(self, args):
        # Определение допустимых ключей и критериев
        valid_keys = {"-name", "-type", "-size"}
        valid_types = {"f", "d"}

        # Определение каталога для поиска
        if args and not args[0].startswith("-"):
            search_dir = args.pop(0)
            search_dir = search_dir.strip("/")
            if search_dir == ".":
                search_dir = self.cwd.strip("/")
        else:
            search_dir = self.cwd

        # Настройка параметров поиска по умолчанию
        search_name = None  # Шаблон имени
        search_type = None  # Тип файла: "f" (файл) или "d" (директория)
        search_size = None  # Критерий размера

        # Обработка аргументов с проверкой на допустимые ключи
        while args:
            param = args.pop(0)
            if param not in valid_keys:
                self.output_text.config(state='normal')
                self.output_text.insert(tk.END, f"find: invalid option -- '{param}'\n")
                self.output_text.insert(tk.END, "Usage: find [directory] [-name pattern] [-type f|d] [-size N[K|M]]\n")
                self.output_text.config(state='disabled')
                return

            if param == "-name":
                search_name = args.pop(0) if args else None
                if search_name:
                    search_name = search_name.replace("*", ".*").replace("?", ".")
            elif param == "-type":
                if args and args[0] in valid_types:
                    search_type = args.pop(0)
                else:
                    self.output_text.config(state='normal')
                    self.output_text.insert(tk.END, "find: invalid type; use 'f' for file or 'd' for directory\n")
                    self.output_text.config(state='disabled')
                    return
            elif param == "-size":
                search_size = args.pop(0) if args else None

        def recursive_search(current_path):
            results = []
            for item, item_info in self.vfs.items():
                if item.startswith(current_path):
                    item_name = item.split("/")[-1]
                    is_match = True
                    if search_name:
                        name_pattern = f"^{search_name}$"
                        if not re.fullmatch(name_pattern, item_name):
                            is_match = False
                    if search_type == "f" and item_info['content'] is None:
                        is_match = False
                    elif search_type == "d" and item_info['content'] is not None:
                        is_match = False
                    if search_size:
                        size_limit = int(search_size[:-1])
                        if search_size[-1].upper() == "M":
                            size_limit *= 1024 * 1024
                        elif search_size[-1].upper() == "K":
                            size_limit *= 1024
                        if item_info['size'] > size_limit:
                            is_match = False
                    if is_match:
                        results.append(item)

            return results
        results = recursive_search(search_dir)

        if results:
            self.output_text.config(state='normal')
            for result in results:
                self.output_text.insert(tk.END, f"{result}\n")
            self.output_text.config(state='disabled')
        else:
            self.output_text.config(state='normal')
            self.output_text.insert(tk.END, "No matching files found\n")
            self.output_text.config(state='disabled')

    def prompt(self):
        self.output_text.config(state='normal')
        prompt_path = "~" if self.cwd == "papka" else self.cwd.replace("papka/", "~/")
        self.output_text.insert(tk.END, f"user@shell:{prompt_path}$ ")
        self.output_text.config(state='disabled')
        self.output_text.see(tk.END)


if __name__ == '__main__':
    root = tk.Tk()
    shell = ShellEmulator(root)
    root.mainloop()