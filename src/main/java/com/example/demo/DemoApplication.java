package com.example.demo;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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
			Curso unCurso = new Curso(
					null,
					"SpringBoot",
					"Curso para aprender a levantar un API Rest",
					LocalDate.of(2019, Month.APRIL, 1),
					LocalDate.of(2023, Month.DECEMBER, 10));

			Curso otroCurso = new Curso(
					null,
					"React",
					"Curso de React Native",
					LocalDate.now(),
					LocalDate.of(2025, 1, 12));

			unCurso = cursoRepo.saveAndFlush(unCurso);
			otroCurso = cursoRepo.saveAndFlush(otroCurso);

			// ESTUDIANTE

			Estudiante franco = new Estudiante(null,
					"Franco", "Albornoz", "11222333",
					"eze@correo", LocalDate.of(1991, Month.SEPTEMBER, 16));

			Estudiante pablo = new Estudiante(null,
					"Pablo", "Cabrera", "11222444",
					"pablo@correo", LocalDate.of(1989, Month.JUNE, 19));
			estudianteRepo.save(franco);

			Estudiante lauta = new Estudiante(null,
					"Lautaro", "Romero", "55777333",
					"lau@correo", LocalDate.of(1995, Month.MAY, 8));

			Estudiante sofia = new Estudiante(null,
					"Sofía", "Cabrera", "77222333",
					"sofi@correo", LocalDate.of(1985, Month.DECEMBER, 23));

			franco = estudianteRepo.saveAndFlush(franco);
			pablo = estudianteRepo.saveAndFlush(pablo);
			lauta = estudianteRepo.saveAndFlush(lauta);
			sofia = estudianteRepo.saveAndFlush(sofia);

			// INSCRIPCIÓN
			Inscripcion inscripcion1 = new Inscripcion(null, unCurso, franco, LocalDate.now(),
					Inscripcion.Estado.PENDIENTE);
			Inscripcion inscripcion2 = new Inscripcion(null, otroCurso, franco, LocalDate.now(),
					Inscripcion.Estado.ACEPTADA);
			Inscripcion inscripcion3 = new Inscripcion(null, unCurso, pablo, LocalDate.now(),
					Inscripcion.Estado.RECHAZADA);
			Inscripcion inscripcion4 = new Inscripcion(null, unCurso, lauta, LocalDate.now(),
					Inscripcion.Estado.PENDIENTE);
			Inscripcion inscripcion5 = new Inscripcion(null, unCurso, sofia, LocalDate.now(),
					Inscripcion.Estado.RECHAZADA);
			Inscripcion inscripcion6 = new Inscripcion(null, otroCurso, sofia, LocalDate.now(),
					Inscripcion.Estado.ACEPTADA);

			inscripcionRepo.save(inscripcion1);
			inscripcionRepo.save(inscripcion2);
			inscripcionRepo.save(inscripcion3);
			inscripcionRepo.save(inscripcion4);
			inscripcionRepo.save(inscripcion5);
			inscripcionRepo.save(inscripcion6);

			System.out.println(inscripcionRepo.findAll());
			System.out.println(cursoRepo.findById(2L));

		};

	}

}
