package com.castgroup.rest.service;

import com.castgroup.rest.enuns.TipoDiff;
import com.castgroup.rest.exception.ValidationException;
import com.castgroup.rest.model.Diff;



public interface DiffService {

	Diff save(Diff diff) throws ValidationException;

	Diff buscarDiffByCodigoHashAndTipoDiff(Long codigoHash, TipoDiff tipoDiff);
}
