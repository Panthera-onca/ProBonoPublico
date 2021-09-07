package com.example.application.data.endpoint;

import java.util.List;

import com.example.application.data.entity.Status;
import com.example.application.data.service.StatusRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.connect.Endpoint;

@Endpoint
@AnonymousAllowed
public class StatusEndpoint {
	
	private StatusRepository repository;

	public StatusEndpoint(StatusRepository repository) { 
	   this.repository = repository;
	 }

	public List<Status> findAll() {
	   return repository.findAll();
	 }

	public Status save(Status status) {
	   return repository.save(status);
	 }

}
