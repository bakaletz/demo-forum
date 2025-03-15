package com.example.forum.controller;

import com.example.forum.dto.topic.TopicDetailsDTO;
import com.example.forum.dto.topic.TopicInListDTO;
import com.example.forum.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/topics")
    public ResponseEntity<List<TopicInListDTO>> findAll(){
        List<TopicInListDTO> topicDTOList = topicService.findAll();
        if (topicDTOList == null || topicDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(topicDTOList);
    }


    @GetMapping("/topics/{id}")
    public ResponseEntity<TopicDetailsDTO> findTopicById(@PathVariable long id){
        TopicDetailsDTO topicDetailsDTO = topicService.findById(id);
        if (topicDetailsDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(topicDetailsDTO);
    }
}