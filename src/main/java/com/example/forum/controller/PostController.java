package com.example.forum.controller;

import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("/api/v1/posts")
    public ResponseEntity<List<PostInListDTO>> findAll(){
        List<PostInListDTO> postDTOList = postService.findAll();
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(postDTOList);
    }
}
