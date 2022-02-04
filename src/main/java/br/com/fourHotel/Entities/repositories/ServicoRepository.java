package br.com.fourHotel.Entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fourHotel.Entities.models.ServicoModel;

@Repository
public interface ServicoRepository extends JpaRepository<ServicoModel, Integer> {

}
