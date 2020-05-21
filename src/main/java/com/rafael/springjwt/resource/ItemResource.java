package com.rafael.springjwt.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.springjwt.event.ResourceCreateEvent;
import com.rafael.springjwt.model.Item;
import com.rafael.springjwt.repository.ItemRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/items")
@Api("REST API Items")
public class ItemResource {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@ApiOperation("Returns a list of all ITEMS")
	@PreAuthorize("hasAuthority('LST_ITEM') and #oauth2.hasScope('read')")
	public List<Item> list() {
		return itemRepository.findAll();
	}
	
	@PostMapping
	@ApiOperation("ADD an ITEM")
	@PreAuthorize("hasAuthority('ADD_ITEM') and #oauth2.hasScope('write')")
	public ResponseEntity<Item> add(@Valid @RequestBody Item item, HttpServletResponse response) {
		Item savedItem = itemRepository.save(item);
		publisher.publishEvent(new ResourceCreateEvent(this, response, savedItem.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Find one ITEM by ID")
	@PreAuthorize("hasAuthority('LST_ITEM') and #oauth2.hasScope('read')")
	public Item findById(@PathVariable Long id) {
		return itemRepository.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Delete a ITEM by ID")
	@PreAuthorize("hasAuthority('DEL_ITEM') and #oauth2.hasScope('delete')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemRepository.deleteById(id);
	}
	
}
