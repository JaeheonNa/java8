package com.study.java8.marvel;

import java.util.List;
import java.util.Optional;

public class Hero {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String name;
    private String team;
    private String species;
    private String gender;

    public Optional<Ability> getAbilities() {
        return Optional.ofNullable(abilities);
    }

    public void setAbilities(Ability abilities) {
        this.abilities = abilities;
    }

    private Ability abilities;

    public Hero(String name, String team, String species, String gender) {
        this.name = name;
        this.team = team;
        this.species = species;
        this.gender = gender;
    }
}
