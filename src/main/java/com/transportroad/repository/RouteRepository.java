package com.transportroad.repository;

import com.transportroad.model.domain.Route;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RouteRepository extends PagingAndSortingRepository<Route, Long> {

}
