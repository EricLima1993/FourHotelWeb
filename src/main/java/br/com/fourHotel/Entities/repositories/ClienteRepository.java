package br.com.fourHotel.Entities.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fourHotel.Entities.models.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer>{

	@Query("SELECT DISTINCT obj FROM ClienteModel obj WHERE obj.login LIKE %:login% AND obj.senha LIKE %:senha%")
	Optional<ClienteModel> search(@Param("login")String login, @Param("senha")String senha);
}
