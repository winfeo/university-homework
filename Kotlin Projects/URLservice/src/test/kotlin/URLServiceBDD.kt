package test.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import main.kotlin.URLService

class URLServiceBDDTest {

    @Test
    fun test() {
        println()
        //Дано: Введён длинный URL
        val longURL = "https://www.example.com/config/tipolinux/url"
        println("Введён длинный URL - $longURL")

        //Когда: Пользователь нажимает на кнопку сокращения
        val shortURL = URLService.shorten(longURL)
        println("Нажата кнопка сокращения")

        //Тогда: Пользователь получает короткий URL, который работает
        println("Получен короткий URL - $shortURL")

        //Проверка что URL работает
        assertNotNull(shortURL, "Короткий URL не должен быть null")
        assertTrue(shortURL.isNotEmpty(), "Короткий URL не должен быть пустым")

        val originalURL = URLService.getOriginal(shortURL)
        assertEquals(longURL, originalURL, "Короткий URL должен вести на оригинальный")
        assertTrue(shortURL.length < longURL.length, "Короткий URL должен быть короче оригинального")

        println("Сценарий выполнен")
    }
}