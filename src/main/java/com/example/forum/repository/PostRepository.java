package com.example.forum.repository;

import com.example.forum.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "WITH RECURSIVE topic_hierarchy AS (" +
            "SELECT id FROM topic WHERE id = :topicId " +
            "UNION ALL " +
            "SELECT t.id FROM topic t " +
            "JOIN topic_hierarchy th ON t.parent_topic_id = th.id) " +
            "SELECT COUNT(p.id) FROM post p " +
            "JOIN topic_hierarchy th ON p.topic = th.id", nativeQuery = true)
    long countByTopicIdIncludingChildren(@Param("topicId") long topicId);
}