package com.skilldistillery.services;

import java.util.List;

import com.skilldistillery.entities.Episode;

public interface EpisodeService {

	List <Episode> index();
	
	Episode findById(int id);
	
	Episode createEpisode (Episode episode);
	
	Episode updateEpisode (Episode episode, int id);
	
	boolean deleteEpisode (int id);
	
	List<Episode> findByKeyword(String keyword);
	
	List<Episode> findByPodcastId (int idn);


		
}