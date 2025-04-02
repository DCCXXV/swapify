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

    public enum Status {
        PENDING,  // Swap propuesto, a falta de aceptar por userB
        ACTIVE,   // Swap accepted, en curso
        FINISHED, // Swap completao
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;

    @ManyToOne
    @JoinColumn(name = "user_a_id", nullable = false)
    private User userA;

    @ManyToOne
    @JoinColumn(name = "user_b_id", nullable = false)
    private User userB;

    @ManyToOne
    @JoinColumn(name = "skill_a_id", nullable = false)
    private Skill skillA;
    
    @ManyToOne
    @JoinColumn(name = "skill_b_id", nullable = false)
    private Skill skillB;

    @OneToOne
    @JoinColumn(name = "review_a_id", nullable = true)
    private Review reviewA;

    @OneToOne
    @JoinColumn(name = "review_b_id", nullable = true)
    private Review reviewB;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) 
    private Status status = Status.PENDING;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private Long id;
        private User userA;
        private User userB;
        private Skill skillA;
        private Skill skillB;
        private String status;
    }
    
	@Override
    public Swap.Transfer toTransfer() {
		return new Transfer(id, userA, userB, skillA, skillB, status.toString());
	}
}

