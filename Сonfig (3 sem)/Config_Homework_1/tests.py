import unittest
from unittest.mock import patch, MagicMock
from shell_emulator import ShellEmulator
import tkinter as tk

class TestShellEmulator(unittest.TestCase):
    def setUp(self):
        root = tk.Tk()
        self.shell = ShellEmulator(root)

    @patch('shell_emulator.ShellEmulator.ls')
    def test_ls_command(self, mock_ls):
        # Тест ls без аргументов
        self.shell.execute_command(command="ls")
        mock_ls.assert_called_once_with([])

        # Тест ls с флагом "-l"
        self.shell.execute_command(command="ls -l")
        mock_ls.assert_called_with(["-l"])

        # Тест ls с флагом "-h"
        self.shell.execute_command(command="ls -h")
        mock_ls.assert_called_with(["-h"])

    @patch('shell_emulator.ShellEmulator.cd')
    def test_cd_command(self, mock_cd):
        # Тест cd без аргументов
        self.shell.execute_command(command="cd")
        mock_cd.assert_called_once_with([])

        # Тест cd с аргументов "директория"
        self.shell.execute_command(command="cd folder")
        mock_cd.assert_called_with(["folder"])

        # Тест cd c аргументом ".."
        self.shell.execute_command(command="cd ..")
        mock_cd.assert_called_with([".."])

    @patch('shell_emulator.ShellEmulator.echo')
    def test_echo_command(self, mock_echo):
        # Тест echo с 1 словом
        self.shell.execute_command(command="echo Hello")
        mock_echo.assert_called_once_with(["Hello"])

        # Тест echo с несколькими словами
        self.shell.execute_command(command="echo Hello World!")
        mock_echo.assert_called_with(["Hello", "World!"])

        # Тест echo без текста
        self.shell.execute_command(command="echo")
        mock_echo.assert_called_with([])

    @patch('shell_emulator.ShellEmulator.mv')
    def test_mv_command(self, mock_mv):
        # Тест mv с источником или назначением
        self.shell.execute_command(command="mv file1.txt folder/")
        mock_mv.assert_called_once_with(["file1.txt", "folder/"])

        # Тест mv с несколькими источниками и одним назначением
        self.shell.execute_command(command="mv file1.txt file2.txt folder/")
        mock_mv.assert_called_with(["file1.txt", "file2.txt", "folder/"])

        # Тест mv без указания назначения
        self.shell.execute_command(command="mv file1.txt")
        mock_mv.assert_called_with(["file1.txt"])

    @patch('shell_emulator.ShellEmulator.find')
    def test_find_command(self, mock_find):
        # Тест find без аргументов
        self.shell.execute_command(command="find")
        mock_find.assert_called_once_with([])

        # Тест find с указанием директории и типом
        self.shell.execute_command(command="find /folder -name *.txt")
        mock_find.assert_called_with(["/folder", "-name", "*.txt"])

        # Тест find с указанием размера
        self.shell.execute_command(command="find -size 1M")
        mock_find.assert_called_with(["-size", "1M"])

if __name__ == '__main__':
    unittest.main()
