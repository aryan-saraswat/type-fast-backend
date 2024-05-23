package com.example.demo.controller

import com.example.demo.inbound.WordsRequest
import com.example.demo.services.WordsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(val wordsService: WordsService) {
    @PostMapping("/hello")
    fun randomWords(@RequestBody wordsRequest: WordsRequest): List<String> {
        return wordsService.getRandomWords(wordsRequest.numberOfWords)
    }
}
