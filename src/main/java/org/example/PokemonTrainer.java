package org.example;

import java.io.IOException;
import java.util.List;

import static org.example.Game.*;
import static org.example.Main.sc;
import static org.example.Pokemon.getPokemonByType;
import static org.example.Pokemon.pokemonMap;

public class PokemonTrainer {
    String trainerName;
    static Pokemon[] inventar = new Pokemon[3];
    static Pokemon activePokemon;

    public PokemonTrainer(String name){
        this.trainerName = name;
    }

    public static void fillInventar() throws IOException {
        for (int i = 0; i < inventar.length; i++) {
            System.out.println("Welchen Typ soll dein " + (i+1) + ". Pokemon haben? (Fire, Grass, Water, etc.)");
            String type = sc.nextLine();
            List<String> list = getPokemonByType(type);
            System.out.println(list);

            System.out.println("Wähle nun mithilfe der ID welches Pokemon du hinzufügen möchtest");
            int choice = sc.nextInt();
            player = pokemonMap.get(choice-1);
            sc.nextLine();

            System.out.println("Du wählst: " + player + "\n Drücke enter um weiterzumachen.");
            sc.nextLine();
            inventar[i] = player;
        }
        inventar[0] = pokemonMap.get(150);
        inventar[1] = pokemonMap.get(140);
        inventar[2] = pokemonMap.get(130);
        setActivePokemon();
    }

    public static void setActivePokemon() throws IOException {
        while (true) {
            int x = 1;
            int deadCount = 0;
            System.out.println();
            for (Pokemon pokemon : inventar) {
                UserInterface.listPokemon(x, pokemon);
                x++;
                if (!pokemon.isAlive()) {
                    deadCount++;
                }
            }
            if(deadCount==inventar.length) {
                wantsToPlayAgain();
            }
            int temp = UserInterface.chooseActivePokemon(inventar);
            if (inventar[temp-1].isAlive()) {
                activePokemon = inventar[temp-1];
                player = activePokemon;
                break;
            } else {
                UserInterface.errorDead();
            }
        }
    }
}
