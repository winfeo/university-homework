package main.kotlin

object URLService {
    private val urlMap = mutableMapOf<String, String>()
    private var counter = 0

    fun shorten(longURL: String): String {
        if (longURL.isEmpty()) throw IllegalArgumentException("URL не может быть пустым")

        counter++
        val shortURL = "s$counter"
        urlMap[shortURL] = longURL
        return shortURL
    }

    fun getOriginal(shortURL: String): String? {
        return urlMap[shortURL]
    }
}