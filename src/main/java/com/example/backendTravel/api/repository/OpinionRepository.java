package com.example.backendTravel.api.repository;
import com.example.backendTravel.api.model.City;
import com.example.backendTravel.api.model.Opinion;
import com.example.backendTravel.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpinionRepository  extends JpaRepository<Opinion, Integer>{

    @Query("SELECT opinion FROM Opinion opinion WHERE opinion.city.cityId = :cityId")
    List<Opinion> findByCityId(@Param("cityId") Long cityId);

    @Query("SELECT o.user FROM Opinion o GROUP BY o.user.userId ORDER BY COUNT(o.user.userId) DESC limit 1")
    User findMostActiveUser();

}
