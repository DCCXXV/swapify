package es.ucm.fdi.iw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.DesiredSkill;
import es.ucm.fdi.iw.repository.DesiredSkillRepository;

@Service
public class DesiredSkillService {

    @Autowired
    private DesiredSkillRepository desiredSkillRepository;

    public List<DesiredSkill> getAllByUserId(long id){
        return desiredSkillRepository.findAllByUserId(id);
    }

    public void saveDesiredSkill(DesiredSkill ds) {
        desiredSkillRepository.save(ds);
    }

}
