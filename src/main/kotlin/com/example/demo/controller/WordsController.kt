package com.example.demo.controller

import com.example.demo.inbound.WordsRequest
import com.example.demo.services.WordsService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WordsController(val wordsService: WordsService) {
    @CrossOrigin(origins = ["http://localhost:4200"])
    @PostMapping("/hello")
    fun randomWords(@RequestBody wordsRequest: WordsRequest): List<String> {
        return wordsService.getRandomWords(wordsRequest.numberOfWords)
    }
}
