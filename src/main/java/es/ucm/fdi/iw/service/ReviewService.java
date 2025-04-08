package es.ucm.fdi.iw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.Review;
import es.ucm.fdi.iw.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review.Transfer saveReview(Review review) {
        return reviewRepository.save(review).toTransfer();
    }
}
