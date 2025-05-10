package es.ucm.fdi.iw.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.Skill;
import es.ucm.fdi.iw.repository.SkillRepository;

@Service
public class SkillService {
    
    @Autowired
    private SkillRepository skillRepository;

    public Skill getOrCreateSkill(String name) {
        Skill skill = skillRepository.findByName(name);
        if (skill == null) {
            skill = new Skill();
            skill.setName(name);
            skill = skillRepository.save(skill);
        }
        return skill;
    }
    
    public List<Skill.Transfer> getSkillsByKeyword(String keyword) {
        return skillRepository.findByNameContainingIgnoreCase(keyword)
            .stream()
            .map(Skill::toTransfer)
            .collect(Collectors.toList());
    }

    public List<Skill.Transfer> getCommon() {
        return skillRepository.findAll()
            .stream()
            .map(Skill::toTransfer)
            .sorted(Comparator.comparingInt(Skill.Transfer::getCurrentUsers))
            .collect(Collectors.toList());    
    }

    public List<Skill.Transfer> getDesired() {
        return skillRepository.findAll()
            .stream()
            .map(Skill::toTransfer)
            .sorted(Comparator.comparingInt(Skill.Transfer::getAspirantUsers))
            .collect(Collectors.toList());    
    }
}