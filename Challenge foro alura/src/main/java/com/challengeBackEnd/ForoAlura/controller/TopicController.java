package com.challengeBackEnd.ForoAlura.controller;

import com.challengeBackEnd.ForoAlura.dto.TopicDto;
import com.challengeBackEnd.ForoAlura.dto.TopicResponseDto;
import com.challengeBackEnd.ForoAlura.model.TopicStatus;
import com.challengeBackEnd.ForoAlura.model.Author;
import com.challengeBackEnd.ForoAlura.model.Course;
import com.challengeBackEnd.ForoAlura.model.Topic;
import com.challengeBackEnd.ForoAlura.repository.AuthorRepository;
import com.challengeBackEnd.ForoAlura.repository.CourseRepository;
import com.challengeBackEnd.ForoAlura.repository.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/topics")

public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    @Transactional
    public Topic createTopic(@Valid @RequestBody TopicDto topicDto) {

        // Verificar si el tópico ya existe
        Optional<Topic> existingTopic = topicRepository.findByTitleAndMessage(topicDto.getTitle(), topicDto.getMessage());
        if (existingTopic.isPresent()) {
            throw new IllegalArgumentException("Ya existe un topico con el mismo titulo y mensaje");
        }

        // Buscar o crear el autor
        Author author = authorRepository.findByName(topicDto.getAuthor())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(topicDto.getAuthor());
                    return authorRepository.save(newAuthor);
                });

        // Buscar o crear el curso
        Course course = courseRepository.findByName(topicDto.getCourse())
                .orElseGet(() -> {
                    Course newCourse = new Course();
                    newCourse.setName(topicDto.getCourse());
                    return courseRepository.save(newCourse);
                });

        // Crear y guardar el tópico
        Topic topic = new Topic();
        topic.setTitle(topicDto.getTitle());
        topic.setMessage(topicDto.getMessage());
        topic.setAuthor(author);
        topic.setCourse(course);
        topic.setStatus(TopicStatus.ACTIVE);
        topic.setCreationDate(LocalDateTime.now());

        return topicRepository.save(topic);

    }
        @GetMapping
        public List<TopicResponseDto> getAllTopics() {
            List<Topic> topics = topicRepository.findAll();
            return topics.stream().map(topic -> new TopicResponseDto(
                    topic.getTitle(),
                    topic.getMessage(),
                    topic.getCreationDate(),
                    topic.getStatus(),
                    topic.getAuthor().getName(),
                    topic.getCourse().getName()
            )).collect(Collectors.toList());
        }

        @GetMapping("/{id}")
        public TopicResponseDto getTopicById(@PathVariable Long id) {
            Topic topic = topicRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Topic with ID " + id + " not found."));

            return new TopicResponseDto(
                    topic.getTitle(),
                    topic.getMessage(),
                    topic.getCreationDate(),
                    topic.getStatus(),
                    topic.getAuthor().getName(),
                    topic.getCourse().getName()
        );
    }
    @PutMapping("/{id}")
    @Transactional
    public TopicResponseDto updateTopic(@PathVariable Long id, @Valid @RequestBody TopicDto topicDto) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Topic with ID " + id + " not found."));

        // Verificar si el título y el mensaje ya existen en otro tópico
        Optional<Topic> existingTopic = topicRepository.findByTitleAndMessage(topicDto.getTitle(), topicDto.getMessage());
        if (existingTopic.isPresent() && !existingTopic.get().getId().equals(id)) {
            throw new IllegalArgumentException("A topic with the same title and message already exists.");
        }

        Author author = authorRepository.findByName(topicDto.getAuthor()).orElseGet(() -> {
            Author newAuthor = new Author();
            newAuthor.setName(topicDto.getAuthor());
            return authorRepository.save(newAuthor);
        });

        Course course = courseRepository.findByName(topicDto.getCourse()).orElseGet(() -> {
            Course newCourse = new Course();
            newCourse.setName(topicDto.getCourse());
            return courseRepository.save(newCourse);
        });

        topic.setTitle(topicDto.getTitle());
        topic.setMessage(topicDto.getMessage());
        topic.setAuthor(author);
        topic.setCourse(course);
        topic.setStatus(TopicStatus.ACTIVE);

        Topic updatedTopic = topicRepository.save(topic);

        return new TopicResponseDto(
                updatedTopic.getTitle(),
                updatedTopic.getMessage(),
                updatedTopic.getCreationDate(),
                updatedTopic.getStatus(),
                updatedTopic.getAuthor().getName(),
                updatedTopic.getCourse().getName()
        );
    }
        @DeleteMapping("/{id}")
        @Transactional
        public void eliminarTopico(@PathVariable Long id) {
            Topic topico = topicRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Tópico con ID " + id + " no encontrado."));

            topicRepository.deleteById(id);
        }
    }


