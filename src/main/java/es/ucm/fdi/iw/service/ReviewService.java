package es.ucm.fdi.iw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.fdi.iw.model.CurrentSkill;
import es.ucm.fdi.iw.model.Review;
import es.ucm.fdi.iw.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CurrentSkillService currentSkillService;

    @Transactional
    public void saveReview(Review review) {
        reviewRepository.save(review);
        
        // Encontrar el CurrentSkill correspondiente y añadir la review
        // El usuario que es valorado (userB) es quien enseñó la habilidad (skillB)
        List<CurrentSkill> skills = currentSkillService.getAllByUserId(review.getUserB().getId());
        for (CurrentSkill cs : skills) {
            if (cs.getSkill().getId() == review.getSkillB().getId()) {
                cs.getReviews().add(review);
                currentSkillService.saveCurrentSkill(cs);
                break;
            }
        }
    }

    /**
     * Obtiene todas las reviews en las que el usuario especificado es evaluado (userB)
     * @param username Nombre de usuario
     * @return Lista de reviews transferibles
     */
    public List<Review.Transfer> getAllByUsername(String username) {
        // Solo devolver reviews donde el usuario es userB (el evaluado)
        return reviewRepository.findByUserB_Username(username)
            .stream()
            .map(Review::toTransfer)
            .toList();
    }

    /**
     * Obtiene todas las reviews escritas por el usuario especificado
     * @param username Nombre de usuario
     * @return Lista de reviews transferibles
     */
    public List<Review.Transfer> getAllWrittenByUsername(String username) {
        return reviewRepository.findByUserA_Username(username)
            .stream()
            .map(Review::toTransfer)
            .toList();
    }
}
