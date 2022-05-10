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
import com.skilldistillery.services.EpisodeService;

@RequestMapping("api")
@RestController
public class EpisodeController {
	
	@Autowired
	EpisodeService eServ;
	
	@GetMapping("episodes")
	public List<Episode> index()  {
		return eServ.index();
	}

	@GetMapping("episodes/{id}")
	public Episode show(HttpServletResponse resp, @PathVariable Integer id) {
		Episode episode = eServ.findById(id);
		if(episode == null) {
			resp.setStatus(404);
		}
		return episode;
	}
	
	@PostMapping("episodes")
	public Episode addEpisode(
			@RequestBody Episode episode, 
			HttpServletRequest req, 
			HttpServletResponse res
			)  {
		try {
			episode = eServ.createEpisode(episode);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(episode.getId());
			res.setHeader("Location", url.toString());
			return episode;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			episode = null;
		
		}
		return null;
	}
	
	
	
	@PutMapping("episodes/{id}")
	public Episode updateEpisode(
		@RequestBody Episode episode,
		@PathVariable int id,  
		HttpServletRequest req,
		HttpServletResponse res
		)  {
		Episode updatedEpisode = null;
		
			try {
				updatedEpisode = eServ.updateEpisode(episode, id);
				if (updatedEpisode == null) {
					res.setStatus(404);
					return null;
				}
				res.setStatus(200);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(updatedEpisode.getId());
				res.setHeader("Location", url.toString());
				
				
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
				updatedEpisode = null;
			}
			return updatedEpisode;
		}
	

	@DeleteMapping("episodes/{id}")
	public boolean deleteEpisode(
			@PathVariable Integer id,
			HttpServletResponse res
			) {
		try {		
		if (eServ.deleteEpisode(id)) {
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
	

	//Search for Episodes by keyword
		@GetMapping("episodes/search/{keyword}")
		public List<Episode> episodesByKeyword(
				@PathVariable String keyword) {
			return eServ.findByKeyword(keyword);
		}
	
	
	
		// Find episodes by Podcast Id
		@GetMapping("podcasts/{podId}/episodes")
		public List<Episode> findByPodcastId (
				@PathVariable int podId) {
			return eServ.findByPodcastId (podId);
		}
		
		
		
		
	}
	