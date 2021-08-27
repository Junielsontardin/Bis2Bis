package bis2bis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bis2bis.models.Tecnologia;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long>{
	
	Optional<Tecnologia> findByTecnologia(String tecnologia);
	
}
