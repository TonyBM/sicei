package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;

import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.repository.ProfesorRepository;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> getProfesores() {

        List<Profesor> profesores = new LinkedList<>();
        profesorRepository.findAll().iterator().forEachRemaining(profesores::add); // SELECT(id, nombre)
        
        return profesores;
    }

    public Profesor crearProfesor(ProfesorRequest request) {
        Profesor profesor = new Profesor();

        profesor.setNombre(request.getNombre());
        profesor.setHoras(request.getHoras());
        profesor = profesorRepository.save(profesor); // INSERT

        return profesor;
    }

    public Profesor getProfesor(Integer ProfesorID) {

        return profesorRepository.findById(ProfesorID)
            .orElseThrow(() -> new NotFoundException("La entidad profesor no pudo ser encontrada."));
    }

    public Profesor editarProfesor(Integer ProfesorID, ProfesorRequest request) {

        return profesorRepository.findById(ProfesorID)
        .map(profesor -> {
            profesor.setNombre(request.getNombre());
            profesor.setHoras(request.getHoras());
            return profesorRepository.save(profesor);
        })
        .orElseThrow(() -> new NotFoundException("La entidad profesor no pudo ser encontrada."));
    }

    public void borrarProfesor(Integer ProfesorID) {

        List<Profesor> profesores = new LinkedList<>();
        profesorRepository.findAll().iterator().forEachRemaining(profesores::add);
        if(profesores.size() < ProfesorID || ProfesorID <= 0){
            throw new NotFoundException("La entidad profesor no pudo ser encontrada.");
        }
        
        profesorRepository.deleteById(ProfesorID);
    }
    
}
