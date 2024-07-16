package com.challengeBackEnd.ForoAlura.dto;

import com.challengeBackEnd.ForoAlura.model.TopicStatus;

import java.time.LocalDateTime;

public class TopicResponseDto {
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private TopicStatus status;
    private String author;
    private String course;

    // Constructor, Getters y Setters

    public TopicResponseDto(String title, String message, LocalDateTime creationDate, TopicStatus status, String author, String course) {
        this.title = title;
        this.message = message;
        this.creationDate = creationDate;
        this.status = status;
        this.author = author;
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public void setStatus(TopicStatus status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
