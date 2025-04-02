package es.ucm.fdi.iw.model;

import lombok.*;

import java.sql.Date;
import java.util.List;
import jakarta.persistence.*;

/**
 * An authorized user of the system.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name="Swap")
public class Swap implements Transferable<Swap.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;

    @ManyToOne
    private User userA;

    @ManyToOne
    private User userB;

    @ManyToOne
    private Skill skillA;
    
    @ManyToOne
    private Skill skillB;

    @OneToOne
    private Review reviewA;

    @OneToOne
    private Review reviewB;

    List<Date> schedule;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private Long id;
        private User userA;
        private User userB;
        private Skill skillA;
        private Skill skillB;
        private List<Date> schedule;
    }
    
	@Override
    public Swap.Transfer toTransfer() {
		return new Transfer(id, userA, userB, skillA, skillB, schedule);
	}
}

