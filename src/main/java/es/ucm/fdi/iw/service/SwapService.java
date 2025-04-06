package es.ucm.fdi.iw.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.Swap;
import es.ucm.fdi.iw.repository.SwapRepository;

@Service
public class SwapService {

    private final SwapRepository swapRepository;

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
}
