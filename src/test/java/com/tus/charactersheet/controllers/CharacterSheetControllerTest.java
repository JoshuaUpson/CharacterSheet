package com.tus.charactersheet.controllers;

import com.tus.charactersheet.model.Character;
import com.tus.charactersheet.service.CharacterSheetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CharacterSheetControllerTest {

    private CharacterSheetController characterSheetController;
    private CharacterSheetService characterSheetService;

    @BeforeEach
    public void setUp() {
        characterSheetService = new CharacterSheetService();
        characterSheetController = new CharacterSheetController();
    }

    @Test
    public void testGetAllCharacters() {
        // Setup
        List<Character> characters = new ArrayList<>();
        Character character = new Character();
        character.setName("Gandalf");
        characters.add(character);
        characterSheetService.saveOrUpdateCharacter(character);

        // Execution
        ResponseEntity<List<Character>> responseEntity = characterSheetController.getAllCharacters();
        List<Character> responseCharacters = responseEntity.getBody();

        // Verification
        assertNotNull(responseCharacters);
        assertEquals(1, responseCharacters.size());
        assertEquals("Gandalf", responseCharacters.get(0).getName());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCharacterById() {
        // Setup
        Long id = 1L;
        Character character = new Character();
        character.setName("Frodo");
        character.setId(id);
        characterSheetService.saveOrUpdateCharacter(character);

        // Execution
        ResponseEntity<Character> responseEntity = characterSheetController.getCharacterById(id);
        Character responseCharacter = responseEntity.getBody();

        // Verification
        assertNotNull(responseCharacter);
        assertEquals("Frodo", responseCharacter.getName());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCharacterByIdNotFound() {
        // Setup
        Long id = 1L;

        // Execution
        ResponseEntity<Character> responseEntity = characterSheetController.getCharacterById(id);

        // Verification
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateCharacter() {
        // Setup
        Character character = new Character();
        character.setName("Gimli");

        // Execution
        ResponseEntity<Character> responseEntity = characterSheetController.createCharacter(character);
        Character responseCharacter = responseEntity.getBody();

        // Verification
        assertNotNull(responseCharacter);
        assertEquals("Gimli", responseCharacter.getName());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateCharacter() {
        // Setup
        Long id = 1L;
        Character character = new Character();
        character.setName("Legolas");
        character.setId(id);
        characterSheetService.saveOrUpdateCharacter(character);

        // Execution
        ResponseEntity<Character> responseEntity = characterSheetController.updateCharacter(id, character);
        Character responseCharacter = responseEntity.getBody();

        // Verification
        assertNotNull(responseCharacter);
        assertEquals("Legolas", responseCharacter.getName());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateCharacterNotFound() {
        // Setup
        Long id = 1L;
        Character character = new Character();
        character.setName("Legolas");
        character.setId(id);

        // Mock the saveOrUpdateCharacter() method to return null
        characterSheetController = new CharacterSheetController() {
            @Override
            public ResponseEntity<Character> updateCharacter(Long id, Character character) {
                return ResponseEntity.notFound().build();
            }
        };

        // Execution
        ResponseEntity<Character> responseEntity = characterSheetController.updateCharacter(id, character);

        // Verification
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}

       
