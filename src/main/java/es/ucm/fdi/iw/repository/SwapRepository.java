package es.ucm.fdi.iw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ucm.fdi.iw.model.Swap;

@Repository
public interface SwapRepository extends JpaRepository<Swap, Long> {
    Swap findById(long id);
    List<Swap> findByUserA_UsernameOrUserB_Username(String usernameA, String usernameB);
    
    long countBySwapStatus(Swap.Status status);
}
