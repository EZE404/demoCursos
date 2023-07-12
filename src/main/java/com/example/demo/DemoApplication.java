package com.example.demo;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import com.example.demo.domain.Curso;
import com.example.demo.domain.EstadoConverter;
import com.example.demo.domain.Estudiante;
import com.example.demo.domain.Inscripcion;
import com.example.demo.exception.WrongIdException;
import com.example.demo.repository.CursoRepo;
import com.example.demo.repository.EstudianteRepo;
import com.example.demo.repository.InscripcionRepo;
import com.example.demo.service.InscripcionService;
import com.example.demo.tools.CollectionsFormatter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Para usarlo en consultas del módulo 3
	@Autowired
	CursoRepo cursoRepo;

	@Autowired
	EstudianteRepo estudianteRepo;

	@Autowired
	InscripcionRepo inscripcionRepo;

	// Para la parte de armar service

	@Autowired
	InscripcionService inscripcionService;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
		return args -> {
			// CARGAR DATOS DE PRUEBA

			cargarDatosPrueba();

			// PROBANDO SERVICES Y CONSULTAS DE MOD3

			probarConsultas();
			agregarInscripcion();

		};

	}

	void agregarInscripcion() {
		try {
			inscripcionService.save(1L, 2L);
		} catch (WrongIdException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void cargarDatosPrueba() {
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
				"eze@correo", LocalDate.of(2010, Month.SEPTEMBER, 16));

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
	}

	private void probarConsultas() {
		EstadoConverter estadoConverter = new EstadoConverter();
		PageRequest pageable = PageRequest.of(0, 2);

		// CURSOS
		System.out.println("cursoRepo.findAllByQuery()");
		System.out.println(CollectionsFormatter.toStringList(cursoRepo.findAllByQuery()));
		System.out.println("cursoRepo.findAllWhereInicioIsAfter01022020()");
		System.out.println(CollectionsFormatter.toStringList(cursoRepo.findAllWhereInicioIsAfter01022020()));
		System.out.println("cursoRepo.findByInicioGreaterThan(LocalDate.of(2020, Month.FEBRUARY, 1))");
		System.out.println(CollectionsFormatter
				.toStringList(cursoRepo.findByInicioGreaterThan(LocalDate.of(2020, Month.FEBRUARY, 1))));

		// ESTUDIANTES
		System.out.println("estudianteRepo.findAllByQuery()");
		System.out.println(CollectionsFormatter.toStringList(estudianteRepo.findAllByQuery()));
		System.out.println("estudianteRepo.findAllWhereDniGreaterThan20000000AndApellidoIsRomero()");
		System.out.println(CollectionsFormatter
				.toStringList(estudianteRepo.findAllWhereDniGreaterThan20000000AndApellidoIsRomero()));
		System.out.println("estudianteRepo.findByDniGreaterThanAndApellidoIsIgnoreCase(\"20000000\", \"romero\")");
		System.out.println(CollectionsFormatter
				.toStringList(estudianteRepo.findByDniGreaterThanAndApellidoIsIgnoreCase("20000000", "romero")));
		System.out.println("estudianteRepo.findByOrderByDniAsc(pageable).toList()");
		System.out.println(CollectionsFormatter.toStringList(estudianteRepo.findByOrderByDniAsc(pageable).toList()));

		// INSCRIPCIONES
		System.out.println("inscripcionRepo.findAllWhereEstadoIsPendienteOrRechazada");
		System.out
				.println(CollectionsFormatter.toStringList(
						inscripcionRepo.findAllWhereEstadoIsPendienteOrRechazada()));

		System.out.println(
				"inscripcionRepo.findByEstadoIsOrEstadoIs(Inscripcion.Estado.PENDIENTE, Inscripcion.Estado.RECHAZADA)");
		System.out.println(CollectionsFormatter.toStringList(
				inscripcionRepo.findByEstadoIsOrEstadoIs(Inscripcion.Estado.PENDIENTE,
						Inscripcion.Estado.RECHAZADA)));

		System.out.println("inscripcionRepo.findAllWhereEstadoIs(Inscripcion.Estado.ACEPTADA)");
		System.out.println(
				CollectionsFormatter.toStringList(
						inscripcionRepo.findAllWhereEstadoIs(Inscripcion.Estado.ACEPTADA)));

		System.out.println("inscripcionRepo.findByEstadoIs(Inscripcion.Estado.PENDIENTE)");
		System.out.println(
				CollectionsFormatter.toStringList(
						inscripcionRepo.findByEstadoIs(Inscripcion.Estado.PENDIENTE)));

		System.out.println(
				"inscripcionRepo.findAllWhereEstadoIsUsingNative(estadoConverter.convertToDatabaseColumn(Inscripcion.Estado.ACEPTADA))");
		System.out.println(CollectionsFormatter.toStringList(inscripcionRepo.findAllWhereEstadoIsUsingNative(
				estadoConverter.convertToDatabaseColumn(Inscripcion.Estado.ACEPTADA))));

	}
}
