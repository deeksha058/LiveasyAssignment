package com.LiveasyAssignment.LiveasyAssignment.Repository;

import com.LiveasyAssignment.LiveasyAssignment.Model.Loads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoadsRepository extends JpaRepository<Loads , Integer> {

    @Query(value = "SELECT * FROM loads limit :limit_value offset :offset_value" ,nativeQuery = true)
    public List<Loads> loadsData(Integer limit_value , Integer offset_value);

    @Query(value = "SELECT * FROM loads where shipper_id = :shipperId " ,nativeQuery = true)
    public List<Loads> loadsWithTheShipperId(UUID shipperId);

}