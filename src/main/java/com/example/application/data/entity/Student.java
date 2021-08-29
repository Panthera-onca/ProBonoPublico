package com.example.application.data.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.application.data.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Student extends AbstractEntity {

  @NotEmpty
  private String firstName = "";

  @NotEmpty
  private String lastName = "";
  
  @ManyToOne
  @JoinColumn(name = "auditorium_id")
  @NotNull
  @JsonIgnoreProperties({ "participants" })
  private Auditorium auditorium;


  public Auditorium getAuditorium() {
	  return auditorium;
}

  public void setAuditorium(Auditorium auditorium) {
	  this.auditorium = auditorium;
}

  @NotNull
  @ManyToOne
  private Status status;

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Status getStatus() {
    return  status;
  }

  public void setStatus(Status status) {
	    this.status = status;
	  }




}