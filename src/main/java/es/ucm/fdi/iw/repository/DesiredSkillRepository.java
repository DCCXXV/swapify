package es.ucm.fdi.iw.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import es.ucm.fdi.iw.model.DesiredSkill;

@Repository
public interface DesiredSkillRepository extends JpaRepository<DesiredSkill, Long> {
    List<DesiredSkill> findAllByUserId(Long id);
}
