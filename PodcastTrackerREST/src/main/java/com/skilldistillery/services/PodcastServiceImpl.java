package com.skilldistillery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.entities.Podcast;
import com.skilldistillery.repositories.PodcastRepository;

@Service
public class PodcastServiceImpl implements PodcastService {
	
	@Autowired
	PodcastRepository pRepo;

//	@Autowired
//	EpisodeRepository jRepo;
	
	@Override
	public List<Podcast> index() {
		return pRepo.findAll();
	}

	@Override
	public Podcast addPodcast(Podcast newpodcast) {
		return pRepo.saveAndFlush(newpodcast);
	}

	@Override
	public Podcast updatePodcast(Podcast podcast, int id) {
		Optional<Podcast> compOpt = pRepo.findById(id);
		if (compOpt.isPresent()) {
			Podcast managedPodcast = compOpt.get();
			managedPodcast.setDescription(podcast.getDescription());
			managedPodcast.setCompany(podcast.getCompany());
			managedPodcast.setCreator(podcast.getCreator());
			managedPodcast.setImageUrl(podcast.getImageUrl());
			managedPodcast.setWebsite(podcast.getWebsite());			
			managedPodcast.setPeriodicity(podcast.getPeriodicity());
			managedPodcast.setEpisodes(podcast.getEpisodes());
			managedPodcast.setCreator (podcast.getCreator());
			
			pRepo.saveAndFlush(managedPodcast);
			return managedPodcast;
		}

		return null;
	}

	@Override
	public boolean deletePodcast(int id) {
		Optional <Podcast> comOpt = pRepo.findById(id);
		if (comOpt.isPresent())  {
			Podcast podcast = comOpt.get();
			pRepo.delete(podcast);
		
			return true;
		}
		else {

			return false;
		}

	}

}
