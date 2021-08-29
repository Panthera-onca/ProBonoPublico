package com.example.application.data.entity;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;

import com.example.application.data.AbstractEntity;

@Entity
public class Status extends AbstractEntity{
	
	private boolean clear = false;
	
	@NotBlank 
	@OneToMany(mappedBy = "auditorium")
	private String name;
	public Status(String name) {
		   this.name = name;
		 }

	public boolean isClear() {
		   return clear;
		 }

	public void setClear(boolean clear) {
		   this.clear = clear;
		 }

	public String getName() {
		   return name;
		 }

	public void setName(String name) {
		   this.name = name;
		 }

}
