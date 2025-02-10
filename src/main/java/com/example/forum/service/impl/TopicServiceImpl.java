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

    @Override
    public List<TopicInListDTO> findAll() {
        List<Topic> topicList = topicRepository.findAll();
        return getDTOListFromList(topicList);
    }


    private List<TopicInListDTO> getDTOListFromList(List<Topic> topicList) {
        List<TopicInListDTO> topicDTOList = new ArrayList<>();
        for (Topic topic : topicList) {
            TopicInListDTO topicInListDTO = TopicMapper.toInListDTO(topic);
            topicDTOList.add(topicInListDTO);

        }
        return topicDTOList;
    }

    @Override
    public TopicDetailsDTO findById(long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        TopicDetailsDTO topicDetailsDTO = TopicMapper.toDetailsDTO(topic);

        // Без перевірки на null, якщо getChildren() завжди повертає список:
        topicDetailsDTO.getChildren()
                .forEach(tpc -> tpc.setPostCount(postRepository.countByTopicIdIncludingChildren(tpc.getId())));

        return topicDetailsDTO;
    }

}