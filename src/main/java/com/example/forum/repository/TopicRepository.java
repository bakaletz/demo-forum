package com.example.forum.repository;

import com.example.forum.entity.Message;
import com.example.forum.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findAllByCategoryId(Long categoryId);


    @Query(value = "WITH RECURSIVE topic_hierarchy AS (" +
                "SELECT id FROM topic WHERE id = :topicId " +
                "UNION ALL " +
                "SELECT t.id FROM topic t " +
                "JOIN topic_hierarchy th ON t.parent_topic_id = th.id) " +
                "SELECT COUNT(p.id) FROM post p " +
                "JOIN topic_hierarchy th ON p.topic = th.id", nativeQuery = true)
        long countPostsInTopicAndChildren(@Param("topicId") long topicId);

    @Query(value = "WITH RECURSIVE topic_hierarchy AS (" +
            "SELECT id FROM topic WHERE id = :topicId " +
            "UNION ALL " +
            "SELECT t.id FROM topic t " +
            "JOIN topic_hierarchy th ON t.parent_topic_id = th.id) " +
            "SELECT COUNT(m.id) FROM message m " +
            "JOIN post p ON m.post_id = p.id " +
            "JOIN topic_hierarchy th ON p.topic = th.id", nativeQuery = true)
    long countMessagesInTopicAndChildren(@Param("topicId") long topicId);

    @Query(value = "WITH RECURSIVE topic_hierarchy AS (" +
            "SELECT id FROM topic WHERE id = :topicId " +
            "UNION ALL " +
            "SELECT t.id FROM topic t " +
            "JOIN topic_hierarchy th ON t.parent_topic_id = th.id) " +
            "SELECT m.* FROM message m " +
            "JOIN post p ON m.post_id = p.id " +
            "JOIN topic_hierarchy th ON p.topic = th.id " +
            "ORDER BY m.created_at DESC " +
            "LIMIT 1", nativeQuery = true)
    Message findLatestMessageInTopicAndChildren(@Param("topicId") Long topicId);
}
