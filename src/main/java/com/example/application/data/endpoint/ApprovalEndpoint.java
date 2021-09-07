package com.example.application.data.endpoint;

import java.util.List;
import java.util.Arrays;
import com.example.application.data.service.StudentRepository;
import com.example.application.data.entity.Auditorium;
import com.example.application.data.entity.Status;
import com.example.application.data.entity.Student;
import com.example.application.data.service.AuditoriumRepository;
import com.example.application.data.service.StatusRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.connect.Endpoint;


@Endpoint
@AnonymousAllowed
public class ApprovalEndpoint {
	private StudentRepository studentRepository;
	private AuditoriumRepository auditoriumRepository;
	private StatusRepository statusRepository;
	
	public ApprovalEndpoint(StudentRepository studentRepository, AuditoriumRepository auditoriumRepository, StatusRepository statusRepository) {
		this.studentRepository = studentRepository;
		this.auditoriumRepository = auditoriumRepository;
		this.statusRepository = statusRepository;
	}
	
	public static class Approval{
		public List<Student> students;
		public List<Auditorium> auditoriums;
		public List<Status> statuses;
	}
	
	public Approval getApproval() {
		Approval approval = new Approval();
		approval.students = studentRepository.findAll();
		approval.auditoriums = auditoriumRepository.findAll();
		approval.statuses = statusRepository.findAll();
		return approval;
	}
	
	public Student saveStudent(Student student) {
		student.setAuditorium(auditoriumRepository.findById(student.getAuditorium().getId())
				.orElseThrow(() -> new RuntimeException("Cound not find Auditorium with id" + student.getAuditorium().getId())));
		student.setStatus(statusRepository.findById(student.getStatus().getId())
				.orElseThrow(() -> new RuntimeException("Could not find Status with id" + student.getStatus().getId())));
		return studentRepository.save(student);
	}
	
	public void deleteStudent(Integer studentId) {
		studentRepository.deleteById(studentId);
		
	}

}
