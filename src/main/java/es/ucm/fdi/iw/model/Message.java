package es.ucm.fdi.iw.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;


import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NamedQueries({
    @NamedQuery(name="Message.countUnread",
    query="SELECT COUNT(m) FROM Message m "
            + "WHERE m.recipient.id = :userId AND m.dateRead = null")
})
@Data
public class Message implements Transferable<Message.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User recipient;
    private String text;

    private LocalDateTime dateSent;
    private LocalDateTime dateRead;

    @ManyToOne
    private Swap swap;

    private boolean reportFlag;

    /**
     * Objeto para persistir a/de JSON
     * @author mfreire
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Transfer {
        private String from;
        private String to;
        private Long swapId;
        private LocalDateTime sent;
        private LocalDateTime received;
        private String text;
        long id;
    }

    @Override
    public Transfer toTransfer() {
        return new Transfer(
            this.sender != null ? this.sender.getUsername() : null,
            this.recipient != null ? this.recipient.getUsername() : null,
            this.swap != null ? this.swap.getId() : null,
            this.dateSent,
            this.dateRead,
            this.text,
            this.id
        );
    }
}
