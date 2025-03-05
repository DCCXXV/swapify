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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
}
