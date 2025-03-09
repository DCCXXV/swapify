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
import es.ucm.fdi.iw.repository.DesiredSkillRepository;
import es.ucm.fdi.iw.repository.SkillRepository;

@Service
public class DesiredSkillService {

    @Autowired
    private DesiredSkillRepository desiredSkillRepository;

    @Autowired
    private SkillRepository skillRepository;
    /*
     * public List<Skill> getAllNameById(long id){
     * List<DesiredSkill> listDesiredSkills =
     * desiredSkillRepository.findAllByUserId(id);
     * List<Skill> resultado = new ArrayList<>();
     * for(DesiredSkill c: listDesiredSkills){
     * Skill skill = skillRepository.findById(c.getSkill().getId());
     * resultado.add(skill);
     * }
     * return resultado;
     * }
     * 
     * public List<DesiredSkill> getAllById(long id){
     * return desiredSkillRepository.findAllByUserId(id);
     * }
     */

    public List<Map<String, Object>> getAllById(long id) {
        List<Map<String, Object>> combinedList = new ArrayList<>();
        List<DesiredSkill> listDesiredSkills = desiredSkillRepository.findAllByUserId(id);

        for (DesiredSkill c : listDesiredSkills) {
            Map<String, Object> desiredSkill = new HashMap<>();
            desiredSkill.put("name", c.getSkill().getName()); // Nombre de la habilidad
            desiredSkill.put("description", c.getDescription());
            combinedList.add(desiredSkill);
        }

        return combinedList;
    }

}
