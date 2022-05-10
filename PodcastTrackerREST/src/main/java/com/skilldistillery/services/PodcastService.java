package com.skilldistillery.services;

import java.util.List;

import com.skilldistillery.entities.Episode;
import com.skilldistillery.entities.Podcast;

public interface PodcastService {

		List <Podcast> index();
		
		Podcast addPodcast (Podcast newpodcast);
		
		Podcast updatePodcast (Podcast podcast, int id);
		
		boolean deletePodcast (int id);
		
	}