package com.skilldistillery.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.entities.Episode;
import com.skilldistillery.entities.Podcast;
import com.skilldistillery.services.EpisodeService;
import com.skilldistillery.services.PodcastService;

@RequestMapping("api")
@RestController
public class PodcastController {

		@Autowired
		PodcastService pserv;
		
		@Autowired
		EpisodeService eserv;
		
		@GetMapping("podcasts")
		public List<Podcast> index()  {
			List<Podcast> podcasts = pserv.index();
			return podcasts;
		}

		
		@PostMapping("podcasts")
		public Podcast addPodcast (
				@RequestBody Podcast newPodcast, 
				HttpServletRequest req, 
				HttpServletResponse res
				)  {
			try {
				newPodcast = pserv.addPodcast(newPodcast);
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(newPodcast.getId());
				res.addHeader("Location", url.toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
				return null;
			
			}
			return newPodcast;
		}
		
		
		
		@PutMapping("podcasts/{id}")
		public Podcast updatePodcast(
			@RequestBody Podcast podcast,
			@PathVariable int id,  
			HttpServletResponse res,
			HttpServletRequest req
			)  {
			Podcast updatedPodcast = null;
			
				try {
					updatedPodcast = pserv.updatePodcast (podcast, id);
					if (updatedPodcast == null) {
						res.setStatus(404);
						return null;
					}
					res.setStatus(202);
					StringBuffer url = req.getRequestURL();
					url.append("/").append(updatedPodcast.getId());
					res.addHeader("Location",url.toString());
					
				} catch (Exception e) {
					e.printStackTrace();
					res.setStatus(400);
					return null;
				}
				return updatedPodcast;
			}
		
		
		@DeleteMapping("podcasts/{id}")
		public boolean deletePodcast(
				@PathVariable int id,
				HttpServletResponse res
				) {
			try {		
			if (pserv.deletePodcast(id)) {
				res.setStatus(204);
				return true;
		}
		else {
			res.setStatus(404);
			return false;
		}
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(400);
		return false;
	  }
	 }
		
		
		
		@PostMapping("podcasts/{id}/episodes")
		public Episode createEpisode(
				@PathVariable int id,
				@RequestBody Episode episode,
				HttpServletRequest req, 
				HttpServletResponse res
				)  {
			Episode episodeCreated = null;
			
			try {
				episodeCreated = eserv.createEpisode(episode);
				res.setStatus(202);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(episodeCreated.getId());
				res.addHeader("Location",url.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(404);
				return null;
			}
			return episodeCreated;
		}
		

	}

	

