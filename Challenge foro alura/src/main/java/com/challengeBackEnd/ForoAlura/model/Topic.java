package com.challengeBackEnd.ForoAlura.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
    public class Topic {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        @Lob
        private String message;

        private LocalDateTime creationDate;

        @Enumerated(EnumType.STRING)
        private TopicStatus status;

        @ManyToOne
        @JoinColumn(name = "author_id")
        private Author author;

        @ManyToOne
        @JoinColumn(name = "course_id")
        private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
