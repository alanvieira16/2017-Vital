package br.ufes.dwws.vital.dashboard.medicine;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Medicine;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManageMedicinesService extends CrudService<Medicine>{}
