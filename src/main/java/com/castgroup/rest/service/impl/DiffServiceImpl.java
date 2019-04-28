package com.castgroup.rest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castgroup.rest.enuns.TipoDiff;
import com.castgroup.rest.exception.ValidationException;
import com.castgroup.rest.model.Diff;
import com.castgroup.rest.repository.DiffRepository;
import com.castgroup.rest.service.DiffService;

@Service
public class DiffServiceImpl implements DiffService {

	
	@Autowired
    private DiffRepository diffRepository;

    public DiffServiceImpl(DiffRepository diffRepository) {
        this.diffRepository = diffRepository;
    }
    
    public Diff save(Diff diff) throws ValidationException {
        return diffRepository.save(diff);
    }


    public Diff buscarDiffByCodigoHashAndTipoDiff(Long codigoHash, TipoDiff tipoDiff) {
        Optional<Diff> diff = diffRepository.findByCodigoHashAndTipoDiff(codigoHash, tipoDiff);
        return diff.orElse(null);
    }
}
