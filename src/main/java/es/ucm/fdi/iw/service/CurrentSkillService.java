package es.ucm.fdi.iw.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.CurrentSkill;
import es.ucm.fdi.iw.repository.CurrentSkillRepository;

@Service
public class CurrentSkillService {

    @Autowired
    private CurrentSkillRepository currentSkillRepository;

    public List<CurrentSkill> getAllById(long id){
        return currentSkillRepository.findAllByUserId(id);
    }

    public void saveCurrentSkill(CurrentSkill cs) {
        currentSkillRepository.save(cs);
    }
    
}
