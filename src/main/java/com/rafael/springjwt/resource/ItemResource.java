package com.rafael.springjwt.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.springjwt.model.Item;
import com.rafael.springjwt.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemResource {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('LST_ITEM') and #oauth2.hasScope('read')")
	public List<Item> list() {
		return itemRepository.findAll();
	}
	
}
