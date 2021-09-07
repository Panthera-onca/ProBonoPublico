package com.example.application.data.entity;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.example.application.data.AbstractEntity;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Entity
@AnonymousAllowed
public class Auditorium extends AbstractEntity{
	  @NotBlank
	  private String name;

	  @OneToMany(mappedBy = "auditorium")
	  @Nullable
	  private List<Student> participants = new LinkedList<>();

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public List<Student> getParticipants() {
	    return participants;
	  }

	  public void setParticipants(List<Student> participants) {
	    this.participants = participants;
	  }

}
