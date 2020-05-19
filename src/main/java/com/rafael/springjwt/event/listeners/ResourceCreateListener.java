package com.rafael.springjwt.event.listeners;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafael.springjwt.event.ResourceCreateEvent;

@Component
public class ResourceCreateListener implements ApplicationListener<ResourceCreateEvent>{
	
	public void onApplicationEvent(ResourceCreateEvent event) {
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();
		addLocation(response, id);
	}

	private void addLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
