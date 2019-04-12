package com.alten.challenge.db.repo;

import com.alten.challenge.db.entity.Vehicle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wailm.yousif on 4/5/19.
 */

@Repository
public interface VehicleRepo extends PagingAndSortingRepository<Vehicle, Long> {

    public Vehicle findByVin(String vin);
}
