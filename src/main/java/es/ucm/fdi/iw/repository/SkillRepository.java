package es.ucm.fdi.iw.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.ucm.fdi.iw.model.Skill;
import es.ucm.fdi.iw.model.User;

@Repository
public interface SkillRepository extends JpaRepository<User, Long>{
    
    Skill findByName(String name);
    List<Skill> findByNameContainingIgnoreCase(String partialName);
} 
