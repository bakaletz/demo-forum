package com.example.forum.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Message extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
