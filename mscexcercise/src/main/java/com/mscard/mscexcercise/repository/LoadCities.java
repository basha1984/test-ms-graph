package com.mscard.mscexcercise.repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import com.mscard.mscexcercise.entity.Graph;
import com.mscard.mscexcercise.exception.DataLoadException;

@Component
public class LoadCities {
	private static final Logger LOG = Logger.getLogger(LoadCities.class.getName());

	private Map<String, String> cityMap = new HashMap<>();

	Graph graph = new Graph();

	@PostConstruct
	public void init() throws DataLoadException {
		String delimiter = ",";

		try {
			InputStream ins = new DefaultResourceLoader().getResource("classpath:/city.txt").getInputStream();
			if (ins.available() > 0) {
				
				List<String> lines = IOUtils.readLines(ins, StandardCharsets.UTF_8);
				
				lines.stream().filter(line -> line.contains(delimiter))
						.forEach(line -> cityMap.putIfAbsent(line.split(delimiter)[0], line.split(delimiter)[1]));
				createGraph(cityMap);
			} else {
				throw new DataLoadException("Unable to load cities....");
			}
		} catch (Exception e) {
			throw new DataLoadException("Unable to load cities ! ");
		}
	}

	private void createGraph(Map<String, String> routeMap) {

		for (Map.Entry<String, String> entry : routeMap.entrySet()) {
			graph.addVertex(entry.getKey());
			graph.addVertex(entry.getValue());
		}
		
		for (Map.Entry<String, String> entry : routeMap.entrySet()) {
			graph.addEdge(entry.getKey(), entry.getValue());
		}
	}

	public Map<String, String> getCityMap() {
		return cityMap;
	}

	public Graph getGraph() {
		return graph;
	}

}
