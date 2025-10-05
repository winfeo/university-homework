package main.kotlin

object URLService {
    private val urlMap = mutableMapOf<String, String>()
    private var counter = 0

    fun shorten(longURL: String): String {
        if (longURL.isEmpty()) throw IllegalArgumentException("URL не может быть пустым")
        if (!longURL.startsWith("http")) throw IllegalArgumentException("Некорректный URL")

        val existingShort = urlMap.entries.find { it.value == longURL }?.key
        if (existingShort != null) return existingShort

        counter++
        val shortURL = generateHash(longURL)
        urlMap[shortURL] = longURL
        return shortURL
    }

    private fun generateHash(url: String): String {
        return "h${url.hashCode().toString().replace("-", "x")}"
    }

    fun getOriginal(shortURL: String): String? = urlMap[shortURL]
}
