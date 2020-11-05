package com.mscard.mscexcercise.api;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mscard.mscexcercise.exception.DataNotFoundException;
import com.mscard.mscexcercise.repository.LoadCities;
import com.mscard.mscexcercise.service.CityRouteService;

@RestController
public class CityApiController implements CityApi {
	
	/** LOGGER **/
	private static final Logger LOG = Logger.getLogger(CityApiController.class.getName());
	/** Repository **/
	private final LoadCities loadCitiesGraph;

	/** CityRouteService **/
	private CityRouteService cityRouteService;
	
	@Autowired
	public CityApiController(final LoadCities loadCitiesGraph, final CityRouteService cityRouteService) {
		this.loadCitiesGraph = loadCitiesGraph;
		this.cityRouteService = cityRouteService;
	}

	/** API to find the route from source to destination **/
	@GetMapping("/connected")
	@Override
	public String findCity(@NotNull @RequestParam("origin") String origin,
			@NotNull @RequestParam("destination") String destination) throws DataNotFoundException {
		LOG.info("Source..." + origin +".....Destination.."+ destination);
		if (StringUtils.isNotBlank(origin) && StringUtils.isNotBlank(destination)) {
			Set<String> traversal = cityRouteService.findRoute(getLoadCitiesGraph().getGraph(),origin);
			if(traversal.contains(destination))
				return "yes";
			else 
				return "no";
		} else {
			throw new DataNotFoundException("Unable to process this request due to source or destination missing");
		}
	}

	/** Getter method for load cities **/
	public LoadCities getLoadCitiesGraph() {
		return loadCitiesGraph;
	}

}
