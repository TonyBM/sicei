package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uady.sicei.exception.NotFoundException;

import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.repository.EquipoRepository;
import mx.uady.sicei.model.request.EquipoRequest;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> getEquipos() {

        List<Equipo> equipos = new LinkedList<>();
        equipoRepository.findAll().iterator().forEachRemaining(equipos::add);

        return equipos;
    }


    public Equipo crearEquipo(EquipoRequest request) {
        Equipo equipo = new Equipo();

        equipo.setModelo(request.getmodelo());
        equipo.setAlumnos(null);
        equipo = equipoRepository.save(equipo); // INSERT

        return equipo;
    }

    public Equipo getEquipo(Integer EquipoID) {

        return equipoRepository.findById(EquipoID)
            .orElseThrow(() -> new NotFoundException("La entidad equipo no pudo ser encontrada."));
    }

    public Equipo editarEquipos(Integer EquipoID, EquipoRequest request) {

        return equipoRepository.findById(EquipoID)
        .map(equipo -> {
            equipo.setModelo(request.getmodelo());
            return equipoRepository.save(equipo);
        })
        .orElseThrow(() -> new NotFoundException("La entidad equipo no pudo ser encontrada."));
    }

    public void borrarEquipo(Integer EquipoID) {

        List<Equipo> equipos = new LinkedList<>();
        equipoRepository.findAll().iterator().forEachRemaining(equipos::add);
        if(equipos.size() < EquipoID || EquipoID <= 0){
            throw new NotFoundException("La entidad equipo no pudo ser encontrada.");
        }
        
        equipoRepository.deleteById(EquipoID);
    }
    
}
