package test.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import main.kotlin.URLService

class URLServiceSDDTest {

    @Test
    fun test1() {
        val inputURL = "https://example.com/biba"
        val shortURL = URLService.shorten(inputURL)

        assertTrue(shortURL.startsWith("h"), "Короткий URL должен начинаться с 'h'")
        assertTrue(shortURL.length in 2..15, "URL должен быть коротким")
        assertTrue(shortURL.all { it.isLetterOrDigit() }, "Короткий URL должен содержать только буквы/цифры")

        val originalURL = URLService.getOriginal(shortURL)
        assertEquals(inputURL, originalURL,
            "Короткий URL '$shortURL' должен вести на '$inputURL'")
    }

    @Test
    fun test2() {
        val inputURL = "https://example.com/boba"
        val shortURL = URLService.shorten(inputURL)

        assertTrue(shortURL.startsWith("h"), "Короткий URL должен начинаться с 'h'")
        assertTrue(shortURL.length in 2..15, "URL должен быть коротким")
        assertTrue(shortURL.all { it.isLetterOrDigit() }, "Короткий URL должен содержать только буквы/цифры")

        // Проверяем перенаправление
        val originalURL = URLService.getOriginal(shortURL)
        assertEquals(inputURL, originalURL,
            "Короткий URL '$shortURL' должен вести на '$inputURL'")
    }

    @Test
    fun test3() {
        val inputURL = "invalid/url"

        try {
            URLService.shorten(inputURL)
            fail("Исключение для некорректного URL")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message!!.contains("Некорректный URL"),
                "Для URL '$inputURL'. Некорректный URL, получено: '${e.message}'")
        }
    }
}