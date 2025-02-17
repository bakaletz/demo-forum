package com.example.forum.controller;

import com.example.forum.dto.message.MessageDetailsDTO;
import com.example.forum.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MessageController {

    public final MessageService messageService;

    @GetMapping("/api/v1/posts/{id}/messages")
    public ResponseEntity<List<MessageDetailsDTO>> findMessageByPostId(@PathVariable long id) {
        List<MessageDetailsDTO> messageDetailsDTOS = messageService.findByPostId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageDetailsDTOS);
    }
}
