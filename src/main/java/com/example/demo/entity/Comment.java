package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Comment {
    @Id
    int commentId;
    @ManyToOne
    Member memberId;
    @ManyToOne
    Product productId;
    String message;
}
