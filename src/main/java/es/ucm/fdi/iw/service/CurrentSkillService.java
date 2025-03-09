package es.ucm.fdi.iw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.CurrentSkill;
import es.ucm.fdi.iw.model.DesiredSkill;
import es.ucm.fdi.iw.model.Skill;
import es.ucm.fdi.iw.repository.CurrentSkillRepository;
import es.ucm.fdi.iw.repository.SkillRepository;

@Service
public class CurrentSkillService {

    @Autowired
    private CurrentSkillRepository currentSkillRepository;

    @Autowired
    private SkillRepository skillRepository;
    /* 
    public List<Skill> getAllNameById(long id) {
        List<CurrentSkill> listCurrentSkills = currentSkillRepository.findAllByUserId(id);
        List<Skill> resultado = new ArrayList<>();
        for (CurrentSkill c : listCurrentSkills) {
            Skill skill = skillRepository.findById(c.getSkill().getId());
            resultado.add(skill);
        }
        return resultado;
    }*/

    public List<Map<String, Object>> getAllById(long id){
        List<Map<String, Object>> combinedList = new ArrayList<>();
        List<CurrentSkill> listCurrentSkills = currentSkillRepository.findAllByUserId(id);

        for (CurrentSkill c : listCurrentSkills) {
            Map<String, Object> currentSkill = new HashMap<>();
            currentSkill.put("name", c.getSkill().getName());  // Nombre de la habilidad
            currentSkill.put("description", c.getDescription());
            currentSkill.put("rating", c.getRating());
            combinedList.add(currentSkill);
        }

        return combinedList;
    }
    /* 
    public List<Map<String, Object>> getAllByIda(long id) {
        List<CurrentSkill> listCurrentSkills = currentSkillRepository.findByUserId(id);
        List<Skill> listCurrentSkillsName = skillRepository.findAll(); // Obtener nombres de habilidades

        List<Map<String, Object>> combinedList = new ArrayList<>();
    
        for (CurrentSkill c : listCurrentSkills) {
            Map<String, Object> skillData = new HashMap<>();
            skillData.put("name", c.getSkill().getName());  // Nombre de la habilidad
            skillData.put("description", c.getDescription());
            skillData.put("rating", c.getRating());
            combinedList.add(skillData);
        }
    
        return combinedList;
    } */  

    
}
