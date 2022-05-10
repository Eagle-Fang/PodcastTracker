package com.skilldistillery.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.entities.Episode;

public interface EpisodeRepository extends JpaRepository <Episode, Integer>{

	List<Episode> findByTitleContaining(String keyword);

	List<Episode> findByPodcastId(int pId);


}
