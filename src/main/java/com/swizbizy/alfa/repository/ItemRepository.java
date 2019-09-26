package com.swizbizy.alfa.repository;

import com.swizbizy.alfa.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("SELECT item.id from Item item WHERE item.containedIn=:containedIn AND item.color=:color ")
    List<Long> getItemIdByContainedInAndColor(@Param("containedIn") Long containedIn, @Param("color") String color);
}
