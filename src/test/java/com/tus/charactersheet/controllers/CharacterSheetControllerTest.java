package com.tus.charactersheet.controllers;

import com.tus.charactersheet.model.Character;
import com.tus.charactersheet.repos.CharacterRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CharacterSheetControllerTest {
	@Autowired
    CharacterSheetController characterSheetController;
	
	@MockBean
	CharacterRepository  repo;
	
	@Captor
	ArgumentCaptor<Character> captor;
   

    @Test
    public void testCreateCharacter() {
    	Character character = makeCharacter();
    	Character characterSaved=makeCharacter();
    	characterSaved.setId(1L);
    	when(repo.save(any())).thenReturn(characterSaved);
    	Character characterReturn = characterSheetController.create(character);
    	assertEquals(characterReturn.getId(),1L);
    	assertEquals(characterReturn.getName(),"Gandalf");
    	assertEquals(characterReturn.getRace(),"Human");
    	assertEquals(characterReturn.getStats(),"Intellect");
    	verify(repo, new Times(1)).save(captor.capture());
    }
    
    @Test
    void testGetCharacterByName() {
    	Character characterSaved=makeCharacter();
    	characterSaved.setId(1L);
    	when(repo.findByName("Gandalf")).thenReturn(characterSaved);
    	Character characterReturn = characterSheetController.getName("Gandalf");
    	assertEquals(characterReturn.getId(),1L);
    	assertEquals(characterReturn.getName(),"Gandalf");
    	assertEquals(characterReturn.getRace(),"Human");
    	assertEquals(characterReturn.getStats(),"Intellect");
    	verify(repo, new Times(1)).findByName("Gandalf");
    }
    
    @Test 
    void testGetCharacterByStats(){
    	Character characterSaved=makeCharacter();
    	characterSaved.setId(1L);
    	when(repo.findByStats("Intellect")).thenReturn(characterSaved);
    	Character characterReturn = characterSheetController.getStats("Intellect");
    	assertEquals(characterReturn.getId(),1L);
    	assertEquals(characterReturn.getName(),"Gandalf");
    	assertEquals(characterReturn.getRace(),"Human");
    	assertEquals(characterReturn.getStats(),"Intellect");
    	verify(repo, new Times(1)).findByStats("Intellect");
    }
    
    @Test
    void testFindAllCharacter() {
    	Character characterSaved=makeCharacter();
    	characterSaved.setId(1L);
    	ArrayList<Character> characters = new ArrayList<>();
    	characters.add(characterSaved);
    	when(repo.findAll()).thenReturn(characters);
    	List<Character> charactersFound = characterSheetController.getAllCharacters();
    	Character characterFound = charactersFound.get(0);
    	assertEquals(characterFound.getId(),1L);
    	assertEquals(characterFound.getName(),"Gandalf");
    	assertEquals(characterFound.getRace(),"Human");
    	assertEquals(characterFound.getStats(),"Intellect");
    	verify(repo, new Times(1)).findAll();
    }
    private Character makeCharacter() {
    	Character character = new Character();
    	character.setName("Gandalf");
    	character.setRace("Human");
    	character.setStats("Intellect");
    	return character;
    }
}
