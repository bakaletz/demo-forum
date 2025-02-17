package com.example.forum.service.impl;

import com.example.forum.dto.topic.TopicDetailsDTO;
import com.example.forum.dto.topic.TopicInListDTO;
import com.example.forum.entity.Topic;
import com.example.forum.mapper.TopicMapper;
import com.example.forum.repository.PostRepository;
import com.example.forum.repository.TopicRepository;
import com.example.forum.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;
    private final TopicMapper topicMapper;

    @Override
    public List<TopicInListDTO> findAll() {
        List<Topic> topicList = topicRepository.findAll();
        return getDTOListFromList(topicList);
    }


    private List<TopicInListDTO> getDTOListFromList(List<Topic> topicList) {
        List<TopicInListDTO> topicDTOList = new ArrayList<>();
        for (Topic topic : topicList) {
            TopicInListDTO topicInListDTO = topicMapper.toInListDTO(topic);
            topicDTOList.add(topicInListDTO);

        }
        return topicDTOList;
    }

    @Override
    public TopicDetailsDTO findById(long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        TopicDetailsDTO topicDetailsDTO = topicMapper.toDetailsDTO(topic);

        return topicDetailsDTO;
    }

}