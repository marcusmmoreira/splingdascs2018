package br.univille.ProjetoDasc2018.projetospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.ProjetoDasc2018.projetospring.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}