package com.tus.charactersheet.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tus.charactersheet.model.Character;
import com.tus.charactersheet.repos.CharacterRepository;

@RestController
@RequestMapping("/characterapi/v1")
public class CharacterSheetController {
	
	@Autowired
	CharacterRepository  repo;
	
	@RequestMapping(value = "/characters", method = RequestMethod.POST)
	public Character create(@RequestBody Character character) {
		return repo.save(character);
	}
	
	@RequestMapping(value = "/characters/{id}", method = RequestMethod.GET)
	public Optional<Character> getId(@PathVariable("id") Long id) {
		return repo.findById(id);
	}
	@RequestMapping(value = "/characters", method = RequestMethod.GET)
	public List<Character> getAllCharacters(){
		return repo.findAll();		
	}
	@RequestMapping(value = "/characters/{name}", method = RequestMethod.GET)
	public Character getName(@PathVariable("name") String name) {
		return repo.findByName(name);		
	}
	@RequestMapping(value = "/characters/{stats}", method = RequestMethod.GET)
	public Character getStats(@PathVariable("stats") String stats) {
		return repo.findByStats(stats);		
	}
}