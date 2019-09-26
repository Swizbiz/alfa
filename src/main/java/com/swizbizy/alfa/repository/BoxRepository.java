package com.swizbizy.alfa.repository;

import com.swizbizy.alfa.model.Box;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoxRepository extends CrudRepository<Box, Long> {
    @Query("SELECT box from Box box WHERE box.containedIn=:containedIn")
    List<Box> getBoxesByContainedIn(@Param("containedIn") Long containedIn);
}
