package test.kotlin

import main.kotlin.URLService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class URLServiceTDD {

    @Test
    fun test1() {
        val longURL = "https://www.example.com/config/tipolinux/url"
        val shortURL = URLService.shorten(longURL)

        assertNotNull(shortURL)
    }

    @Test
    fun test2() {
        val longURL = "https://www.example.com/config/awesomeuml/url"
        val shortURL = URLService.shorten(longURL)

        assertTrue(shortURL.length < longURL.length)
    }

    @Test
    fun test3() {
        val emptyURL = ""
        assertThrows<IllegalArgumentException> {
            URLService.shorten(emptyURL)
        }
    }

    @Test
    fun test4() {
        val longURL = "https://tipourl.com"
        val shortURL = URLService.shorten(longURL)
        val original = URLService.getOriginal(shortURL)
        assertEquals(longURL, original)
    }

    @Test
    fun test5() {
        val original = URLService.getOriginal("не существует")
        assertNull(original)
    }
}
