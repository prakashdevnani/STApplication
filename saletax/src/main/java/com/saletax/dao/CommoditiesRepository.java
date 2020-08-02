package com.saletax.dao;

import com.saletax.model.Commodities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommoditiesRepository extends JpaRepository<Commodities,Long> {
    //Check if item given input is present or not
    @Query("select cd from Commodities cd where cd.item=:itemInSen OR cd.item like CONCAT('%',:itemInSen,'%')")
    Optional<Commodities> getByItem(@Param("itemInSen") String itemInSen);
}
