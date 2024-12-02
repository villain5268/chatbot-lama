package com.boldest.chatbot_lama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boldest.chatbot_lama.entities.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
}