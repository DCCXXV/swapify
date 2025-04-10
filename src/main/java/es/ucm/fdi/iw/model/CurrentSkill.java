package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="currentSkill")
public class CurrentSkill{

    public CurrentSkill(Skill skill, String description, float rating, float points){
        this.skill = skill;
        this.description = description;
        this.rating = rating;
        this.points = points;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;

    @Column(length = 1000)
    private String description;
    
    private float rating;
    private float points;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return skill.getName();
    }
}