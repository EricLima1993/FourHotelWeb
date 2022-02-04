package br.com.fourHotel.Entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fourHotel.Entities.models.QuartoModel;

@Repository
public interface QuartoRepository extends JpaRepository<QuartoModel, Integer> {

}
