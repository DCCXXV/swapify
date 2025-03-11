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
	private long id;

    @Lob
    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private double rating;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
		private long id;
    }

	@Override
    public Transfer toTransfer() {
		return new Transfer(id);
	}
}

