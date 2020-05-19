package com.rafael.springjwt.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.springjwt.event.ResourceCreateEvent;
import com.rafael.springjwt.model.Item;
import com.rafael.springjwt.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemResource {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('LST_ITEM') and #oauth2.hasScope('read')")
	public List<Item> list() {
		return itemRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Item> add(@Valid @RequestBody Item item, HttpServletResponse response) {
		Item savedItem = itemRepository.save(item);
		publisher.publishEvent(new ResourceCreateEvent(this, response, savedItem.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
	}
	
}
