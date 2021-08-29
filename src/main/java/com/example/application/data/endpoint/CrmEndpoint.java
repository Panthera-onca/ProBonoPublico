package com.example.application.data.endpoint;

import java.util.List;

import com.example.application.data.entity.Auditorium;
import com.example.application.data.entity.Student;
import com.example.application.data.entity.Status;
import com.example.application.data.service.StudentRepository;
import com.example.application.data.service.StatusRepository;
import com.example.application.data.service.AuditoriumRepository;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;
import com.vaadin.flow.server.connect.Endpoint;

@Endpoint
@AnonymousAllowed
public class CrmEndpoint {

  private StudentRepository studentRepository;
  private StatusRepository statusRepository;
  private AuditoriumRepository auditoriumRepository;

  public CrmEndpoint(StudentRepository contactRepository, StatusRepository statusRepository, AuditoriumRepository auditoriumRepository) {
    this.studentRepository = contactRepository;
    this.statusRepository = statusRepository;
    this.auditoriumRepository = auditoriumRepository;
  }

  public static class CrmData {
    public List<Student> contacts;
    public List<Status> statuses;
    public List<Auditorium> auditoriums;
  }

  public CrmData getCrmData() {
    CrmData crmData = new CrmData();
    crmData.contacts = studentRepository.findAll();
    crmData.statuses = statusRepository.findAll();
    crmData.auditoriums = auditoriumRepository.findAll();
    return crmData;
  }

  public Student saveContact(Student student) {
	  student.setAuditorium(auditoriumRepository.findById(student.getAuditorium().getId())
		        .orElseThrow(() -> new RuntimeException("Could not find Company with id" + student.getAuditorium().getId())));
    student.setStatus(statusRepository.findById(student.getStatus().getId())
        .orElseThrow(() -> new RuntimeException("Could not find Status with id" + student.getStatus().getId())));
    return studentRepository.save(student);}
  
  
  
  public void deleteContact(Integer contactId) {
    studentRepository.deleteById(contactId);
  }
}