package com.skilldistillery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.entities.Podcast;

public interface PodcastRepository extends JpaRepository <Podcast, Integer> {

}
