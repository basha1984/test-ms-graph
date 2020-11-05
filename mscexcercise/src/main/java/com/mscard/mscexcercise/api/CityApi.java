package com.mscard.mscexcercise.api;

import com.mscard.mscexcercise.exception.DataNotFoundException;

public interface CityApi {
	String findCity(String origin, String destination) throws DataNotFoundException;

}
