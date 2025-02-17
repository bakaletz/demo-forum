package com.example.forum.controller;

import com.example.forum.dto.post.PostDetailsDTO;
import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class PostController {

    private final PostService postService;

    @GetMapping("/api/v1/posts")
    public ResponseEntity<List<PostInListDTO>> findAll(){
        List<PostInListDTO> postDTOList = postService.findAll();
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(postDTOList);
    }


    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDetailsDTO> findById(@PathVariable long id){
        PostDetailsDTO postDetailsDTO = postService.findById(id);
        if (postDetailsDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postDetailsDTO);
    }
}
