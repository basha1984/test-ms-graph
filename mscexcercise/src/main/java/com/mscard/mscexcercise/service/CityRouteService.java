package com.mscard.mscexcercise.service;

import java.util.Set;

import com.mscard.mscexcercise.entity.Graph;

public interface CityRouteService {
	Set<String> findRoute(Graph route, String city);
}
