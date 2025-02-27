package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.example.Main.r;

public class Pokemon {
    protected static Map<Integer, Pokemon> pokemonMap = new HashMap<>();
    private int id;
    private String name;
    private String type1;
    private String type2;
    private int total;
    private int hp;
    private int attack;
    private int attack2;
    private int defense;
    private int speed;
    private Attack attackMove;
    private Attack attackMove2;
    private int level;
    private boolean isAlive = true;

    public Pokemon(int id, String name, String type1, String type2, int total, int hp, int attack, int attack2, int defense, int speed) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2 ;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.attack2 = attack2;
        this.defense = defense;
        this.speed = speed;
        this.level = 1;
    }

    public static void initPokemon() throws IOException {
        BufferedReader pokemon = new BufferedReader(new FileReader("src/main/java/org/example/Pokemon.csv"));
        String line;
        int i = 0;
        while ((line = pokemon.readLine()) != null) {
            line = line.replace("\uFEFF", "");
            String[] temp = line.split(";");
            if (Objects.equals(temp[0], "#")) {
                continue;
            }
            Pokemon poke = new Pokemon(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3], Integer.parseInt(temp[4]), Integer.parseInt(temp[5]), Integer.parseInt(temp[6]), Integer.parseInt(temp[7]), Integer.parseInt(temp[8]), Integer.parseInt(temp[10]));
            poke.level = r.nextInt(46) + 5;

            pokemonMap.put(i, poke);
            poke.attackMove = Attack.attackMap.get(r.nextInt(217));
            poke.attackMove2 = Attack.attackMap.get(r.nextInt(217));
            i++;
        }
    }

    public List<String> getPokemonByType(String type) {
        List<String> tempList = new ArrayList<>();
        for (int i =0; i<pokemonMap.size(); i++){
            Pokemon tempPokemon = pokemonMap.get(i);
            if (Objects.equals(tempPokemon.type1, type) || Objects.equals(tempPokemon.type2, type)) {
                tempList.add("\n" + tempPokemon.id + " - " + tempPokemon.name);
            }
        }
        return tempList;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public Attack getAttackMove() {
        return attackMove;
    }

    public int getSpeed() {
        return speed;
    }

    public Attack getAttack2() {
        return attackMove2;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getLevel() {
        return level;
    }

    public void increaseLevel() {
        System.out.println("Glückwunsch! " + name + " hat Level " + level + " erreicht!");
        level = level +1;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void unalive() {
        isAlive = false;
    }

    public Pokemon clone() {
        return new Pokemon(this.id, this.name, this.type1, this.type2, this.total, this.hp, this.attack, this.attack2, this.defense, this.speed);
    }

    @Override
    public String toString() {
        return "Pokemon " + id + " - " + name  + "\n" +
                "   level = '" + level + '\'' + "\n" +
                "   type1 = '" + type1 + '\'' + "\n" +
                "   type2 = '" + type2 + '\'' + "\n" +
                "   total = " + total + "\n" +
                "   hp = " + hp + "\n" +
                "   attack power = " + attack + "\n" +
                "   attack 1 - " + attackMove + "\n" +
                "   attack 2 - " + attackMove2 + "\n" +
                "   defense = " + defense + "\n" +
                "   speed = " + speed + "\n" +
                '}';
    }
}
