package com.ald.exchangegenerator.app.services;

import java.util.List;

import com.ald.exchangegenerator.app.models.domain.Contestant;

public interface ContestantService {
	public void clear();
	public void add(Contestant contestant);
	public void removeIf(String id);
	public void update(Contestant contestant);
	public Contestant findById(String id);
	public List<Contestant> list();
	public void randomSort();
	public int size();
}
