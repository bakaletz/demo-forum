package com.example.forum.service.impl;

import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.entity.Post;
import com.example.forum.mapper.PostMapper;
import com.example.forum.repository.PostRepository;
import com.example.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostInListDTO> findAll() {
        List<Post> postList = postRepository.findAll();
        List<PostInListDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            postDTOList.add(PostMapper.toDTO(post));
        }
        return postDTOList;
    }
}
