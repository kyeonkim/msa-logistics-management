package com.sparta.logistics.ai.controller;

import com.sparta.logistics.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    @PostMapping
    public ResponseEntity<String> create(
        @RequestParam(value = "question") String question,
        @RequestParam(value = "userId") Long userId
    ) {
        return ResponseEntity.ok(aiService.create(userId, question));
    }
}
