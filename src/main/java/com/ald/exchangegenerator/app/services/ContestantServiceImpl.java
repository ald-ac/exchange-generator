package com.ald.exchangegenerator.app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ald.exchangegenerator.app.models.domain.Contestant;

@Service
public class ContestantServiceImpl implements ContestantService {

	private List<Contestant> contestants = new ArrayList<>();
	
	@Override
	public void clear() {
		contestants.clear();
	}

	@Override
	public void add(Contestant contestant) {
		contestants.add(contestant);
	}

	@Override
	public void removeIf(String id) {
		contestants.removeIf(item -> item.getId().equals(id));
	}

	@Override
	public void update(Contestant contestant) {
		contestants = contestants.stream().map(item -> item.getId().equals(contestant.getId()) ? contestant : item).collect(Collectors.toList());
	}
	
	@Override
	public Contestant findById(String id) {
		return contestants.stream().filter(item -> item.getId().equals(id)).findAny().orElse(null);
	}
	
	@Override
	public List<Contestant> list() {
		return contestants;
	}

	@Override
	public void randomSort() {
		Collections.shuffle(contestants);
		for(int i=0; i<contestants.size(); i++) {
			if(i==contestants.size()-1) {
				contestants.get(i).setSecretFriend(contestants.get(0));
			} else {
				contestants.get(i).setSecretFriend(contestants.get(i+1));
			}
		}
	}

	@Override
	public int size() {
		return contestants.size();
	}
}
