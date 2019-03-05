package com.transportroad.repository;

import com.transportroad.model.domain.Location;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

    List<Location> getLocationsByIdIn(List<Long> ids);
}
