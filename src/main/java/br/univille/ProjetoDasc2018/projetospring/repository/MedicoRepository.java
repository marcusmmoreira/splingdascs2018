package br.univille.ProjetoDasc2018.projetospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.ProjetoDasc2018.projetospring.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

}
