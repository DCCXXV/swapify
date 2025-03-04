package es.ucm.fdi.iw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="desiredSkill")
public class DesiredSkill {

    public DesiredSkill(String description){
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;


    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
}
