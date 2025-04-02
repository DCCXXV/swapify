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
}
