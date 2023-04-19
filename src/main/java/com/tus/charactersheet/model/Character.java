package com.tus.charactersheet.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String race;
    private String stats;
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRace(String race) {
    	this.race = race;
    }
    public String getRace() {
    	return race;
    }
    public void setStats(String stats) {
    	this.stats = stats;
    }
    public String getStats() {
    	return stats;
    }

  
}
