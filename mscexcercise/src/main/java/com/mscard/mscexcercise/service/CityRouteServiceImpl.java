package com.mscard.mscexcercise.service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.mscard.mscexcercise.entity.Graph;
import com.mscard.mscexcercise.entity.Graph.Vertex;
/** Serice class for finding route */
@Service
public class CityRouteServiceImpl implements CityRouteService {

	@Override
	public Set<String> findRoute(Graph route, String city) {
		Set<String> visited = new LinkedHashSet<>();
		Stack<String> stack = new Stack<>();
		stack.push(city);
		while (!stack.isEmpty()) {
			String vertex = stack.pop();
			if (!visited.contains(vertex)) {
				visited.add(vertex);
				for (Vertex v : route.getAdjVertices(vertex)) {
					stack.push(v.label);
				}
			}
		}
		return visited;
	}
}
