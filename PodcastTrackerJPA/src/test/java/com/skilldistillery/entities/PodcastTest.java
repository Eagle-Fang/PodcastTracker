package com.skilldistillery.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PodcastTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Podcast podcast;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	emf = Persistence.createEntityManagerFactory("PodcastTrackerJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
	em = emf.createEntityManager();
	podcast = em.find(Podcast.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		podcast = null;
	}

	@Test
	@DisplayName("testing company entity")
	void test1() {
		assertNotNull(podcast);
		assertEquals("WeHireAll", podcast.getDescription());
	}
	
}
