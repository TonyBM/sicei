package mx.uady.sicei.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.Tutoriallave;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria, Tutoriallave> {
    List<Tutoria> findByIdIdalumno(Integer idalumno);
    List<Tutoria> findByIdIdprofesor(Integer idprofesor);
}