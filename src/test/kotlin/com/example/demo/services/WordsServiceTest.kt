package com.example.demo.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WordsServiceTest {
    private val requiredNumberOfWords = 5
    private val wordsService = WordsService()

    @Test
    fun `should return five random words`() {
        val result = wordsService.getRandomWords(requiredNumberOfWords)

        assertThat(result).size().isEqualTo(5)
    }
}
