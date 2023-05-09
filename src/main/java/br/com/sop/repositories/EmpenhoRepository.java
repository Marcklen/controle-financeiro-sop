package br.com.sop.repositories;

import br.com.sop.entities.EmpenhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpenhoRepository extends JpaRepository<EmpenhoEntity, Integer> {
}
