package com.example.demo.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.server.ResponseStatusException

@SpringBootTest
class WordsServiceTest {
    private val wordsService = WordsService()

    @Test
    fun `should return five random words`() {
        val numberOfWords = 5
        val result = wordsService.getRandomWords(numberOfWords)

        assertThat(result).size().isEqualTo(5)
    }

    @Test
    fun `should throw error if too many words are requested`() {
        val numberOfWords = 6000
        assertThrows<ResponseStatusException> { wordsService.getRandomWords(numberOfWords) }
    }
}
