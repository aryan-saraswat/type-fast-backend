package com.example.demo.services

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class WordsService {
    fun getRandomWords(requiredNumberOfWords: Int): List<String> {
        val resource = ClassPathResource("words.json")
        val inputStream = resource.inputStream

        // Use Jackson ObjectMapper to parse JSON
        val mapper = jacksonObjectMapper()
        val allWords: List<String> = inputStream.use { mapper.readValue(it) }
        val totalNumberOfWords = allWords.size

        check(requiredNumberOfWords <= totalNumberOfWords) {
            throw ResponseStatusException(HttpStatusCode.valueOf(500), "Too many words to return.")
        }

        val randomWords: MutableList<String> = mutableListOf()
        for (i in 1..requiredNumberOfWords) {
            val randomIndex = Math.random().times(totalNumberOfWords).toInt()
            randomWords.add(allWords[randomIndex])
        }
        return randomWords
    }
}
