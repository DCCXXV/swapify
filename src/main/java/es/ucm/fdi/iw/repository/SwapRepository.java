package es.ucm.fdi.iw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ucm.fdi.iw.model.Swap;

@Repository
public interface SwapRepository extends JpaRepository<Swap, Long> {
    Swap findById(long id);
}
