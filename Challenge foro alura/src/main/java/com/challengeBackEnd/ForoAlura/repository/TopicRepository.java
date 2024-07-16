package com.challengeBackEnd.ForoAlura.repository;

import com.challengeBackEnd.ForoAlura.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitleAndMessage(String title, String message);
}