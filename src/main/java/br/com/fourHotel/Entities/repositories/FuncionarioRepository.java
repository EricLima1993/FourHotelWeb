package br.com.fourHotel.Entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fourHotel.Entities.models.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {

}
