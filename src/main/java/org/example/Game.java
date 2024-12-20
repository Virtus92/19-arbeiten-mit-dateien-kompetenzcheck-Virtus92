package org.example;

import java.io.IOException;
import java.util.Objects;

import static org.example.Effect.getEffectiveness;
import static org.example.Main.r;
import static org.example.Pokemon.*;

public class Game {
    protected static PokemonTrainer trainer;
    protected static Pokemon player;
    protected static Pokemon enemy;

    public static void buildGame() throws IOException {
        Attack.initAttacks();
        Effect.initEffects();
        initPokemon();
        if (trainer == null) {
            UserInterface.startGame();
        }
        setupGame();
        startGame();
    }

    public static void setupGame() {
        enemy = pokemonMap.get(r.nextInt(0, 151));
        UserInterface.enemyChose();
    }

    public static void startGame() throws IOException {
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



    static void attack(Pokemon attacker, Pokemon defender, boolean isPlayer) throws IOException {
        if (isPlayer) {
            UserInterface.fightMenu(attacker, defender, isPlayer);
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

    public static int playerAttack() {
        String prompt = UserInterface.showAttacks();
        return InputHelper.getIntInput(prompt, 1, 2);
    }


    static void wantsToPlayAgain() throws IOException {
            if (enemy.getHp() <= 0) {
                player.increaseLevel();
                setupGame();
                startGame();
                return;
            } else {
            player.unalive();
            if (areAnyPokemonAlive()) {
                trainer.setActivePokemon();
                startGame();
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

    private static boolean areAnyPokemonAlive() {
        for (Pokemon pokemon : PokemonTrainer.inventar) {
            if (pokemon.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
