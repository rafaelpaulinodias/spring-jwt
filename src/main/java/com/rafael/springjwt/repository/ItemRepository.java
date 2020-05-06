package com.rafael.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.springjwt.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
