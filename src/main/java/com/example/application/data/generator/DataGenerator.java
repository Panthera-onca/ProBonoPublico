package com.example.application.data.generator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.application.data.entity.Student;
import com.example.application.data.entity.Status;
import com.example.application.data.entity.Auditorium;
import com.example.application.data.service.StudentRepository;
import com.example.application.data.service.StatusRepository;
import com.example.application.data.service.AuditoriumRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {

  @Bean
  public CommandLineRunner loadData(StudentRepository studentRepository, StatusRepository statusRepository, AuditoriumRepository auditoriumRepository) {

    return args -> {
      Logger logger = LoggerFactory.getLogger(getClass());
      if (studentRepository.count() != 0L) {
        logger.info("Using existing database");
        return;
      }
      int seed = 123;
      
      statusRepository.saveAll(Stream.of("Clear", "Unclear")
              .map(Status::new).collect(Collectors.toList()));

      logger.info("Generating demo data");
      if (auditoriumRepository.count() == 0) {
          ExampleDataGenerator<Auditorium> auditoriumGenerator = new ExampleDataGenerator<>(Auditorium.class, LocalDateTime.now());
          auditoriumGenerator.setData(Auditorium::setName, DataType.COMPANY_NAME);
          auditoriumRepository.saveAll(auditoriumGenerator.create(5, seed));
        }
      logger.info("... generating 50 Student entities...");
      ExampleDataGenerator<Student> studentGenerator = new ExampleDataGenerator<>(Student.class, LocalDateTime.now());
      studentGenerator.setData(Student::setFirstName, DataType.FIRST_NAME);
      studentGenerator.setData(Student::setLastName, DataType.LAST_NAME);

      Random r = new Random(seed);
      List<Status> statuses = statusRepository.findAll();
      List<Student> students = studentGenerator.create(50, seed).stream().map(student -> {
        student.setStatus(statuses.get(r.nextInt(statuses.size())));
        return student;
      }).collect(Collectors.toList());

      studentRepository.saveAll(students);

      logger.info("Generated demo data");
    };
  }

}
