package es.ucm.fdi.iw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="currentSkill")
public class CurrentSkill{

    public CurrentSkill(String description, float rating, float points){
        this.description = description;
        this.rating = rating;
        this.points = points;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;

    //las columnas son description,rating,points

    @Column(name="description")
    private String description;

    @Column(name="rating")
    private float rating;

    @Column(name="points")
    private float points;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

}