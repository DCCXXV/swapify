package es.ucm.fdi.iw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ucm.fdi.iw.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findBySwapId(Long id);
}
