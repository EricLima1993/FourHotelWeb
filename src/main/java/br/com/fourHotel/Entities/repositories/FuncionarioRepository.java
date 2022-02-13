package br.com.fourHotel.Entities.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fourHotel.Entities.models.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {

	@Query("SELECT obj FROM FuncionarioModel obj WHERE obj.login LIKE %:login% AND obj.senha LIKE %:senha%")
	Optional<FuncionarioModel> search(@Param("login")String login, @Param("senha")String senha);
}
