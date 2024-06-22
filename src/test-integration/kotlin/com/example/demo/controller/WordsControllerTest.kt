package com.example.demo.controller

import com.example.demo.inbound.WordsRequest
import com.example.demo.services.WordsService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForObject
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.web.client.RestClientException

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WordsControllerTest {
    @Autowired private lateinit var wordsService: WordsService

    private lateinit var wordsController: WordsController

    @Autowired private lateinit var restTemplate: TestRestTemplate

    @LocalServerPort private var serverPort: Int = 0

    @BeforeEach
    fun setUp() {
        wordsController = WordsController(wordsService)
    }

    @Test
    fun `should return random words`() {
        val wordsRequest = WordsRequest(5)
        val randomWords =
            this.restTemplate.postForObject<List<String>>(
                "http://localhost:$serverPort/get-words",
                wordsRequest,
            )

        assertThat(randomWords).size().isEqualTo(5)
    }

    @Test
    fun `should throw error 500 when too many words are required`() {
        val wordsRequest = WordsRequest(6000)
        assertThrows<RestClientException> {
            this.restTemplate.postForEntity(
                "http://localhost:$serverPort/get-words", wordsRequest, List::class.java)
        }
    }
}
