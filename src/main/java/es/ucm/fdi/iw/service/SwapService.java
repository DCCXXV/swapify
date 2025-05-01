package es.ucm.fdi.iw.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Swap.Transfer updateStatus(Long id, Swap.Status status) {
        Swap swap = swapRepository.findById(id.longValue());
        swap.setSwapStatus(status);
        swapRepository.save(swap);
        return swap.toTransfer();
    }

    public Swap.Transfer getById(Long id) {
        return swapRepository.findById(id)
            .map(Swap::toTransfer)
            .orElseThrow(() -> new NoSuchElementException(
                "Swap no encontrado con id: " + id
            ));
    }

    public List<Swap.Transfer> getActiveByUsername(String username) {
        return swapRepository.findByUserA_UsernameOrUserB_Username(username, username)
            .stream()
            .map(Swap::toTransfer)
            .filter(swap -> swap.getSwapStatus().equals("ACTIVE"))
            .toList();
    }

    public List<Swap.Transfer> getFinishedByUsername(String username) {
        return swapRepository.findByUserA_UsernameOrUserB_Username(username, username)
            .stream()
            .map(Swap::toTransfer)
            .filter(swap -> swap.getSwapStatus().equals("FINISHED"))
            .toList();
    }

    public List<Swap.Transfer> getPendingByUsername(String username) {
        return swapRepository.findByUserA_UsernameOrUserB_Username(username, username)
            .stream()
            .map(Swap::toTransfer)
            .filter(swap -> swap.getSwapStatus().equals("PENDING"))
            .toList();
    }

    public Skill getSkillById(long skillId) {
        return skillRepository.findById(skillId);
    }
    
    public Skill getSkillByName(String skillName) {
        return skillRepository.findByName(skillName);
    }

    public boolean isReviewSubmitted(Long swapId, long userId) {
        Swap swap = swapRepository.findById(swapId).orElse(null);
        if (swap == null) {
            return false;
        }

        // Si user es userA y ha hecho una review
        if (swap.getUserA() != null && swap.getUserA().getId() == userId) {
            return swap.getReviewA() != null;
        }

        // Si user es userB y ha hecho una review
        if (swap.getUserB() != null && swap.getUserB().getId() == userId) {
            return swap.getReviewB() != null;
        }
        return false;
    }


    public Swap getSwapByID(Long id) {
        return swapRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Swap no encontrado con id: " + id));
    }

    public long countActiveSwaps() {
        return swapRepository.countBySwapStatus(Swap.Status.ACTIVE);
    }

    public List<Swap.Transfer> getAllSwaps() {
        return swapRepository.findAll()
            .stream()
            .map(Swap::toTransfer)
            .collect(Collectors.toList());
    }
}
