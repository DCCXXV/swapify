package es.ucm.fdi.iw.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import es.ucm.fdi.iw.model.CurrentSkill;

@Repository
public interface CurrentSkillRepository extends JpaRepository<CurrentSkill, Long> {
    List<CurrentSkill> findAllByUserId(Long id);
}
