package com.example.forum.service;

import com.example.forum.dto.post.PostDetailsDTO;
import com.example.forum.dto.post.PostInListDTO;

import java.util.List;


public interface PostService {
    List<PostInListDTO> findAll();

    PostDetailsDTO findById(long id);
}
