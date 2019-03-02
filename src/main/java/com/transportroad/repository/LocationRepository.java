package com.transportroad.repository;

import com.transportroad.model.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: 02.03.19 we don't need repository here
// TODO: 02.03.19 user PagingAndSortingRepository
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> getLocationsByIdIn(List<Long> ids);
}
