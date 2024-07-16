package com.challengeBackEnd.ForoAlura.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicDto {
    @NotBlank(message = "El titulo es obligatorio")
    private String title;

    @NotBlank(message = "El mensaje es obligatorio")
    private String message;

    @NotBlank(message = "El autor es obligatorio")
    private String author;

    @NotBlank(message = "El tema es obligatorio")
    private String course;

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
