package es.ucm.fdi.iw.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.Swap;
import es.ucm.fdi.iw.model.Skill;
import es.ucm.fdi.iw.repository.SwapRepository;
import es.ucm.fdi.iw.repository.SkillRepository;

@Service
public class SwapService {

    private final SwapRepository swapRepository;

    @Autowired
    private SkillRepository skillRepository;

    public SwapService(SwapRepository swapRepository) {
        this.swapRepository = swapRepository;
    }

    public Swap.Transfer saveSwap(Swap swap) {
        return swapRepository.save(swap).toTransfer();
    }

    public List<Swap.Transfer> getAllByUsername(String username) {
        return swapRepository.findByUserA_UsernameOrUserB_Username(username, username)
            .stream()
            .map(Swap::toTransfer)
            .toList();
    }

    public Swap.Transfer getById(Long id) {
        return swapRepository.findById(id)
            .map(Swap::toTransfer)
            .orElseThrow(() -> new NoSuchElementException(
                "Swap no encontrado con id: " + id
            ));
    }

    public List<Swap.Transfer> getAll() {
        return swapRepository.findAll()
            .stream()
            .map(Swap::toTransfer)
            .toList();
    }

    public Skill getSkillById(long skillId) {
        return skillRepository.findById(skillId);
    }
    
    public Skill getSkillByName(String skillName) {
        return skillRepository.findByName(skillName);
    }
}
