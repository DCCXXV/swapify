package es.ucm.fdi.iw.model;

import lombok.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * An authorized user of the system.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name="Skill")
public class Skill implements Transferable<Skill.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
		private long id;
        private String name;
    }

	@Override
    public Transfer toTransfer() {
		return new Transfer(id,	name);
	}
	
	@Override
	public String toString() {
		return name;
	}

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CurrentSkill> currentUsers = new ArrayList<>();

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DesiredSkill> aspirantUsers = new ArrayList<>();

}

