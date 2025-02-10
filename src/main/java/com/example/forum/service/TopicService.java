package com.example.forum.service;

import com.example.forum.dto.topic.TopicDetailsDTO;
import com.example.forum.dto.topic.TopicInListDTO;

import java.util.List;

public interface TopicService {

    List<TopicInListDTO> findAll();

    TopicDetailsDTO findById(long id);

}
