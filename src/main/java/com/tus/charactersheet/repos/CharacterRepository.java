package com.tus.charactersheet.repos;

import com.tus.charactersheet.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
	Character findByName(String name);
	Character findByStats(String stats);
}

