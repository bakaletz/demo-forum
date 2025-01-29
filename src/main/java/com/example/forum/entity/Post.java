package com.example.forum.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Post extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private boolean isLocked;
    private boolean isPinned;
    @ManyToOne
    @JoinColumn(name = "topic")
    private Topic topic;
}
