package test.kotlin

import main.kotlin.URLService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class URLServiceATDD {

    @Test
    fun test1(){ //Обработочка
        val longURL = "https://www.example.com/config/tipolinux/url"
        val shortURL = URLService.shorten(longURL)
        assertNotNull(shortURL)
        assertTrue(shortURL.length <= 10, "Обработанный URL должен быть коротким")
        assertTrue(shortURL.all { it.isLetterOrDigit() }, "URL должен содержать только буквы и цифры")
    }

    @Test
    fun test2(){ //Перенаправ
        val longURL = "https://www.example.com/config/awesomeuml/url"
        val shortURL = URLService.shorten(longURL)
        val redirectResult = URLService.getOriginal(shortURL)
        assertEquals(longURL, redirectResult, "Перенаправление должно быть точным")
    }

    @Test
    fun test3(){ //Ашибка
        val invalidURL = "not-a-valid-url"

        val exception = assertThrows<IllegalArgumentException> {
            URLService.shorten(invalidURL)
        }

        assertTrue(exception.message!!.contains("URL", ignoreCase = true),
            "Сообщение об ошибке")
    }

    @Test
    fun test4(){ //Одинаков исход ссылки
        val longURL = "https://www.example.com/config/awesomeuml/url"
        val shortURL1 = URLService.shorten(longURL)
        val shortURL2 = URLService.shorten(longURL)

        assertEquals(shortURL1, shortURL2, "Сокращение одной и той же ссылки")
    }
}
