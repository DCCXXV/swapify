package es.ucm.fdi.iw.model;

import lombok.*;
import jakarta.persistence.*;

/**
 * An authorized user of the system.
 */
@Entity
@Data
@NoArgsConstructor
public class Review implements Transferable<Review.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private Long id;

    @Lob
    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private Long swapId;

    @ManyToOne
    private User userA;

    @ManyToOne
    private User userB;

    @ManyToOne
    private Skill skillA;

    @ManyToOne
    private Skill skillB;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
		private Long id;
		private Long swapId;
		private Double rating;
		private String text;
		private Long userAId;
		private Long userBId;
		private String skillA;
		private String skillB;
    }

	@Override
    public Transfer toTransfer() {
		return new Transfer(id, swapId, rating, text, userA.getId(), userB.getId(), skillA.getName(), skillB.getName());
	}
}

