package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uady.sicei.exception.NotFoundException;

import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.Tutoriallave;
import mx.uady.sicei.repository.TutoriaRepository;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.ProfesorRepository;
import mx.uady.sicei.model.request.TutoriaRequest;

@Service
public class TutoriaService {

    @Autowired
    private TutoriaRepository tutoriaRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Tutoria> getTutorias() {

        List<Tutoria> tutorias = new LinkedList<>();
        tutoriaRepository.findAll().iterator().forEachRemaining(tutorias::add);

        return tutorias;
    }


    public Tutoria crearTutoria(TutoriaRequest request) {
        Tutoria tutoria = new Tutoria();
        Tutoriallave tutoriallave = new Tutoriallave();
        Alumno alumno = new Alumno();
        Profesor profesor = new Profesor();
        
        
        alumno = alumnoRepository.findById(request.getIdAlumno())
            .orElseThrow(() -> new NotFoundException("El alumno no se encuentra registrado en el sistema."));

        profesor = profesorRepository.findById(request.getIdProfesor())
            .orElseThrow(() -> new NotFoundException("El profesor no se encuentra registrado en el sistema."));

        tutoriallave.setIdAlumno(request.getIdAlumno());
        tutoriallave.setIdProfesor(request.getIdProfesor());

        tutoria.setHoras(request.getHoras());
        tutoria.setId(tutoriallave);
        tutoria.setAlumno(alumno);
        tutoria.setProfesor(profesor);
        tutoria = tutoriaRepository.save(tutoria); // INSERT

        return tutoria;
    }

    public Tutoria getTutoria(Integer idalumno, Integer idprofesor) {
        Tutoriallave tutoriallave = new Tutoriallave();

        alumnoRepository.findById(idalumno)
            .orElseThrow(() -> new NotFoundException("El alumno no se encuentra registrado en el sistema."));

        profesorRepository.findById(idprofesor)
            .orElseThrow(() -> new NotFoundException("El profesor no se encuentra registrado en el sistema."));

        tutoriallave.setIdAlumno(idalumno);
        tutoriallave.setIdProfesor(idprofesor);

        return tutoriaRepository.findById(tutoriallave)
            .orElseThrow(() -> new NotFoundException("La entidad tutoria no pudo ser encontrada."));
    }

    public Tutoria editarTutorias(Integer idalumno, Integer idprofesor, TutoriaRequest request) {
        Tutoriallave tutoriallave = new Tutoriallave();

        alumnoRepository.findById(idalumno)
            .orElseThrow(() -> new NotFoundException("El alumno no se encuentra registrado en el sistema."));

        profesorRepository.findById(idprofesor)
            .orElseThrow(() -> new NotFoundException("El profesor no se encuentra registrado en el sistema."));

        tutoriallave.setIdAlumno(idalumno);
        tutoriallave.setIdProfesor(idprofesor);

        return tutoriaRepository.findById(tutoriallave)
        .map(tutoria -> {
            tutoria.setHoras(request.getHoras());
            return tutoriaRepository.save(tutoria);
        })
        .orElseThrow(() -> new NotFoundException("La entidad tutoria no pudo ser encontrada."));
    }

    public void borrarTutoria(Integer idalumno, Integer idprofesor) {

        Tutoriallave tutoriallave = new Tutoriallave();

        alumnoRepository.findById(idalumno)
            .orElseThrow(() -> new NotFoundException("El alumno no se encuentra registrado en el sistema."));

        profesorRepository.findById(idprofesor)
            .orElseThrow(() -> new NotFoundException("El profesor no se encuentra registrado en el sistema."));

        tutoriallave.setIdAlumno(idalumno);
        tutoriallave.setIdProfesor(idprofesor);
        
        
        tutoriaRepository.deleteById(tutoriallave);
    }
    
}
