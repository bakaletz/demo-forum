package com.example.forum.repository;

import com.example.forum.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findMessagesByPostId(long postId);

    long countMessageByPostId(long id);
}
