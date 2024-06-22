package com.example.demo.controller

import com.example.demo.inbound.WordsRequest
import com.example.demo.services.WordsService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest

@ExtendWith(MockKExtension::class)
@SpringBootTest()
class WordsControllerTest {
    @MockK private lateinit var wordsService: WordsService

    lateinit var wordsController: WordsController

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        every { wordsService.getRandomWords(5) } returns listOf("aa", "bb", "cc", "dd", "ee")
        wordsController = WordsController(wordsService)
    }

    @Test
    fun `should return random words`() {
        val wordsRequest = WordsRequest(5)

        val words = wordsController.randomWords(wordsRequest)

        assertThat(words).size().isEqualTo(5)
        assertThat(words).containsExactly("aa", "bb", "cc", "dd", "ee")
    }
}
