package com.mscard.mscexcercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.mscard.mscexcercise.entity.Graph;
import com.mscard.mscexcercise.service.CityRouteService;

@SpringBootTest
class MscexcerciseApplicationTests {

	@Autowired
	CityRouteService cityRouteService;

	@Mock
	private RestTemplate restTemplate;

	@Test
	void findRouteTest() {

		Set<String> routes = cityRouteService.findRoute(createGraph(), "Boston");
		boolean found = routes.contains("Boston");
		assertEquals(true, found);
		boolean notFound = routes.contains("Bos");
		assertEquals(false, notFound);
	}

	@Test
	void findRoute() {
		Graph routeGraph = createGraph();
		Mockito.when(restTemplate.getForEntity("http://localhost:8080/connected?origin=Boston&destination=Philadelphia",
				String.class));
		Set<String> traversal = cityRouteService.findRoute(routeGraph, "Philadelphia");

	}

	Graph createGraph() {
		Graph graph = new Graph();
		graph.addVertex("Boston");
		graph.addVertex("New York");
		graph.addVertex("Philadelphia");
		graph.addVertex("Newark");
		graph.addVertex("Trenton");
		graph.addVertex("Albany");
		graph.addEdge("Boston", "New York");
		graph.addEdge("Philadelphia", "Newark");
		graph.addEdge("Newark", "Boston");
		graph.addEdge("Trenton", "Albany");
		return graph;
	}
}
