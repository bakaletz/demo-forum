package com.example.forum.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @OneToMany(mappedBy = "post",
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
    )
    private List<Message> messages;

}
