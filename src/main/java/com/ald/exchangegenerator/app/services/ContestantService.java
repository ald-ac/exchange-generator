package com.ald.exchangegenerator.app.services;

import java.util.ArrayList;

import com.ald.exchangegenerator.app.models.domain.Contestant;

public interface ContestantService {
	public void clear();
	public void add(Contestant contestant);
	public void removeIf(String id);
	public ArrayList<Contestant> list();
	public void randomSort();
	public int size();
}
