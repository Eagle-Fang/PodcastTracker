package com.skilldistillery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.entities.Episode;
import com.skilldistillery.entities.Podcast;
import com.skilldistillery.repositories.EpisodeRepository;
import com.skilldistillery.repositories.PodcastRepository;

@Service
public class EpisodeServiceImpl implements EpisodeService {

	@Autowired
	EpisodeRepository eRepo;

	@Autowired
	PodcastRepository pRepo;
	
	@Override
	public List<Episode> index() {
		return eRepo.findAll();
	}

	@Override
	public Episode findById(int id) {
		Optional<Episode> op = eRepo.findById(id);
		Episode j = null;
		if (op.isPresent()) {
			j = op.get();
		}
		return j;
	}

	@Override
	public Episode createEpisode (Episode episode) {
		Podcast p= new Podcast();
		p.setId(1);
		episode.setPodcast(p);
		return eRepo.saveAndFlush(episode);
	}

	@Override
	public Episode updateEpisode(Episode episode, int id) {
		Optional <Episode> episodeOpt = eRepo.findById(id);
		if (episodeOpt.isPresent())  {
			Episode managedEpisode = episodeOpt.get();
			managedEpisode.setSummary(episode.getSummary());
			managedEpisode.setTitle(episode.getTitle());
			managedEpisode.setImageUrl(episode.getImageUrl());
			managedEpisode.setLink(episode.getLink());
			managedEpisode.setExplicit(episode.isExplicit());
			managedEpisode.setReleased(episode.getReleased());
			managedEpisode.setEpNum(episode.getEpNum());
			managedEpisode.setSeason(episode.getSeason());
			managedEpisode.setCopyright(episode.getCopyright());
			managedEpisode.setLength(episode.getLength());
			
			if(episode.getPodcast().getId() > 3) {
				managedEpisode.setPodcast(episode.getPodcast());
			} else {
				Podcast podcast = pRepo.getById(1);
				managedEpisode.setPodcast(podcast);
			}
			return eRepo.saveAndFlush(managedEpisode);
		}
		return null;

	}

	
	@Override
	public boolean deleteEpisode (int id)  {	
				if (id <= 1) {
				return false;
			}
			
			Optional <Episode> episodeOpt = eRepo.findById(id);
			if (episodeOpt.isPresent())  {
				Episode episode = episodeOpt.get();
				eRepo.delete(episode);
			
				return true;
			}
			else {
	
				return false;
			}
	
		}

	@Override
	public List<Episode> findByKeyword(String keyword) {
		return eRepo.findByTitleContaining(keyword);
	}

	@Override
	public List<Episode> findByPodcastId(int pId) {
		return eRepo.findByPodcastId (pId);
		
	}
			




}	
	

