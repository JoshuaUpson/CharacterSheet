package com.tus.charactersheet.service;

import com.tus.charactersheet.model.*;
import com.tus.charactersheet.model.Character;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CharacterSheetService {
    
    private List<Character> characters = new ArrayList<>();

    public List<Character> getAllCharacters() {
        return characters;
    }

    public Character getCharacterById(Long id) {
        for (Character character : characters) {
            if (character.getId().equals(id)) {
                return character;
            }
        }
        return null;
    }

    public com.tus.charactersheet.model.Character saveOrUpdateCharacter(com.tus.charactersheet.model.Character character) {
        if (character.getId() == null) {
            character.setId(generateNextId());
        }
        characters.removeIf(c -> c.getId().equals(character.getId()));
        characters.add(character);
		return character;
    }

    public boolean deleteCharacterById(Long id) {
        return characters.removeIf(c -> c.getId().equals(id));
    }

    private Long generateNextId() {
        Long nextId = 1L;
        if (!characters.isEmpty()) {
            nextId = characters.get(characters.size() - 1).getId() + 1;
        }
        return nextId;
    }
}
