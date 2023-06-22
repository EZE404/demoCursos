package com.example.demo;

import java.time.LocalDate;
import java.time.Month;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Curso;
import com.example.demo.domain.Estudiante;
import com.example.demo.domain.Inscripcion;
import com.example.demo.repository.CursoRepo;
import com.example.demo.repository.EstudianteRepo;
import com.example.demo.repository.InscripcionRepo;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	CursoRepo cursoRepo;

	@Autowired
	EstudianteRepo estudianteRepo;

	@Autowired
	InscripcionRepo inscripcionRepo;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
		return args -> {
			// CURSO
			Curso unCurso = new Curso();
			unCurso.setNombre("SpringBoot AP");
			unCurso.setDescripcion("Curso para aprender a levantar un API Rest");
			unCurso.setInicio(LocalDate.now());
			unCurso.setFin(LocalDate.of(2023, Month.DECEMBER, 10));

			cursoRepo.save(unCurso);

			// ESTUDIANTE
			Estudiante unEstudiante = new Estudiante();
			unEstudiante.setNombre("Franco");
			unEstudiante.setApellido("Albornoz");
			unEstudiante.setDni("33111222");
			unEstudiante.setEmail("eze@correo.com");
			unEstudiante.setNacimiento(LocalDate.of(1991, Month.SEPTEMBER, 16));

			estudianteRepo.save(unEstudiante);

			// INSCRIPCIÓN
			Inscripcion unaInscripcion = new Inscripcion();
			unaInscripcion.setCurso(unCurso);
			unaInscripcion.setEstudiante(unEstudiante);
			unaInscripcion.setFecha(LocalDate.now());
			unaInscripcion.setEstado(Inscripcion.Estado.PENDIENTE);
			
			inscripcionRepo.save(unaInscripcion);


			System.out.println(inscripcionRepo.findAll());
		};

	}

}
