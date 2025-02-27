package org.example;

import java.io.IOException;
import static org.example.Pokemon.pokemonMap;

public class PokemonTrainer {
    String trainerName;
    protected Pokemon[] inventar = new Pokemon[3];
    protected Pokemon activePokemon;

    public PokemonTrainer(String name){
        this.trainerName = name;
    }

}
