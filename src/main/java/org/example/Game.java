package org.example;

import java.io.IOException;
import java.util.Objects;

import static org.example.Effect.getEffectiveness;
import static org.example.Main.r;
import static org.example.Main.sc;
import static org.example.Pokemon.pokemonMap;

public class Game {
    protected PokemonTrainer trainer;
    protected Pokemon player;
    protected Pokemon enemy;

    public void buildGame() throws IOException {
        if (trainer == null) {
            initialise();
            setPlayer();
        }
        if( enemy == null || !enemy.isAlive()) {
            enemyChosePokemon();
        }

        boolean isPlayer = player.getSpeed() > enemy.getSpeed();

        while (player.isAlive() && enemy.isAlive()) {
            if (player.getSpeed() > enemy.getSpeed()) {
                attack(player, enemy, true);
            } else {
                attack(enemy, player, false);
            }
            isPlayer = !isPlayer;
        }

        if (!enemy.isAlive() || !player.isAlive()) {
            UserInterface.displayBattleResult(player, enemy);
            if (!enemy.isAlive()) {
            } else {
                UserInterface.dead();
            }
            wantsToPlayAgain();
        }
    }

    public void setPlayer() throws IOException {
        UserInterface.startGame();
        String name = sc.nextLine();
        trainer = new PokemonTrainer(name);
        fillInventar();
    }

    public void initialise() throws IOException {
        Attack.initAttacks();
        Effect.initEffects();
        Pokemon.initPokemon();
    }

    public void enemyChosePokemon() {
        enemy = pokemonMap.get(r.nextInt(0, 151));
        UserInterface.enemyChose(enemy);
    }

    public void attack(Pokemon attacker, Pokemon defender, boolean isPlayer) throws IOException {
        if (isPlayer) {
            UserInterface.fightMenu(player, enemy);
            String prompt = "Möchtest du angreifen oder dein Pokemon wechseln? \n   1. Angriff\n   2. Wechsel\n";
            int choice = InputHelper.getIntInput(prompt, 1, 2);

            if (choice == 2) {
                setActivePokemon();
                UserInterface.pokeChange();
                attack(enemy, player, false);
            } else {
                UserInterface.enemyAttackMsg(attacker);
            }
        } else {
            UserInterface.enemyAttacks();
        }

        int choice = isPlayer ? playerAttack() : r.nextInt(1, 2);
        Attack selectedAttack = choice == 1 ? attacker.getAttackMove() : attacker.getAttack2();

        double power = selectedAttack.getPower();
        boolean STAB = Objects.equals(attacker.getType1(), selectedAttack.getType()) || Objects.equals(attacker.getType2(), selectedAttack.getType());
        double damage = power * ((double) attacker.getAttack() / defender.getDefense()) * ((double) attacker.getLevel() / 50) * r.nextDouble(0.85, 1.00) * (STAB ? 1.5 : 1) * getEffectiveness(attacker.getType1(), defender.getType1()) * getEffectiveness(attacker.getType1(), defender.getType2());

        if (Math.random() < (double) selectedAttack.getAccuracy() / 100) {
            defender.setHp((int) (defender.getHp() - damage));
            UserInterface.pokemonAttacks(attacker, selectedAttack, damage);
            if (defender.getHp() <= 0) {
                defender.unalive();
            }
        } else {
            UserInterface.pokemonEvade(defender);
        }

        if (defender.getHp() <= 0) {
            UserInterface.pokemonDied(defender);
            UserInterface.displayBattleResult(player, enemy);
            wantsToPlayAgain();
            return;
        }

        isPlayer = !isPlayer;

        if (player == attacker) {
            attack(enemy, player, isPlayer);
        } else {
            attack(player, enemy, isPlayer);
        }
    }

    public int playerAttack() {
        String prompt = UserInterface.showAttacks(player);
        return InputHelper.getIntInput(prompt, 1, 2);
    }


    public void wantsToPlayAgain() throws IOException {
        if (enemy.getHp() <= 0) {
            player.increaseLevel();
            enemyChosePokemon();
            buildGame();
            return;
        } else {
            player.unalive();
            if (areAnyPokemonAlive()) {
                setActivePokemon();
                buildGame();
                return;
            }
        }
        UserInterface.dead();
        String prompt = UserInterface.playAgain();
        boolean input = InputHelper.getYesNoInput(prompt);
        if (input) {
            buildGame();
        } else {
            System.exit(0);
        }
    }



    public void setActivePokemon() throws IOException {
        while (true) {
            int x = 1;
            int deadCount = 0;
            System.out.println();
            for (Pokemon pokemon : trainer.inventar) {
                UserInterface.listPokemon(x, pokemon);
                x++;
                if (!pokemon.isAlive()) {
                    deadCount++;
                }
            }
            if(deadCount==trainer.inventar.length) {
                wantsToPlayAgain();
            }
            int temp = UserInterface.chooseActivePokemon(trainer.inventar);
            if (trainer.inventar[temp-1].isAlive()) {
                trainer.activePokemon = trainer.inventar[temp-1];
                player = trainer.activePokemon;
                break;
            } else {
                UserInterface.errorDead();
            }
        }
    }


    public void fillInventar() throws IOException {
        System.out.println("Nun wähle " + trainer.inventar.length + " Pokemon aus, mit denen du kämpfen möchtest");
//        for (int i = 0; i < inventar.length; i++) {
//            System.out.println("Welchen Typ soll dein " + (i+1) + ". Pokemon haben? (Fire, Grass, Water, etc.)");
//            String type = sc.nextLine();
//            List<String> list = getPokemonByType(type);
//            System.out.println(list);
//
//            System.out.println("Wähle nun mithilfe der ID welches Pokemon du hinzufügen möchtest");
//            int choice = sc.nextInt();
//            inventar[i] = pokemonMap.get(choice-1).clone();
//            sc.nextLine();
//
//            System.out.println("Du wählst: " + inventar[i] + "\n Drücke enter um weiterzumachen.");
//            sc.nextLine();
//        }
        trainer.inventar[0] = pokemonMap.get(150);
        trainer.inventar[1] = pokemonMap.get(140);
        trainer.inventar[2] = pokemonMap.get(130);
        setActivePokemon();
    }



    private boolean areAnyPokemonAlive() {
        for (Pokemon pokemon : trainer.inventar) {
            if (pokemon.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
