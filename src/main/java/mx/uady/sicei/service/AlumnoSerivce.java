package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;

import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.repository.AlumnoRepository;

@Service
public class AlumnoSerivce {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> getAlumnos() {

        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add); // SELECT(id, nombre)
        
        return alumnos;
    }

    public Alumno crearAlumno(AlumnoRequest request) {
        Alumno alumno = new Alumno();

        alumno.setNombre(request.getNombre());
        alumno.setLicenciatura(request.getLicenciatura());
        alumno = alumnoRepository.save(alumno); // INSERT

        return alumno;
    }

    public Alumno getAlumno(Integer AlumnoID) {

        return alumnoRepository.findById(AlumnoID)
            .orElseThrow(() -> new NotFoundException("La entidad alumno no pudo ser encontrada."));
    }

    public Alumno editarAlumno(Integer AlumnoID, AlumnoRequest request) {

        return alumnoRepository.findById(AlumnoID)
        .map(alumno -> {
            alumno.setNombre(request.getNombre());
            alumno.setLicenciatura(request.getLicenciatura());
            return alumnoRepository.save(alumno);
        })
        .orElseThrow(() -> new NotFoundException("La entidad alumno no pudo ser encontrada."));
    }

    public void borrarAlumno(Integer AlumnoID) {

        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add);
        if(alumnos.size() < AlumnoID || AlumnoID <= 0){
            throw new NotFoundException("La entidad alumno no pudo ser encontrada.");
        }
        
        alumnoRepository.deleteById(AlumnoID);
    }
    
}
