package es.ucm.fdi.iw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ucm.fdi.iw.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
