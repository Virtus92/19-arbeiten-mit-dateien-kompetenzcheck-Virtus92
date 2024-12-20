package org.example;

import java.io.IOException;

import static org.example.Game.*;
import static org.example.Main.sc;

public class UserInterface {

    public static void startGame() throws IOException {
        System.out.println("""
                ==========================================
                \uD83C\uDF1F Willkommen bei Pokémon! \uD83C\uDF1F
                ==========================================""");

        System.out.println("Teile uns deinen Namen mit!");
        String name = sc.nextLine();
        PokemonTrainer trainer = new PokemonTrainer(name);
        System.out.println("Sehr gut " + trainer.trainerName + "!");
        System.out.println("Nun wähle " + PokemonTrainer.inventar.length + " Pokemon aus, mit denen du kämpfen möchtest");
        trainer.fillInventar();
    }

    public static void enemyChose() {
        System.out.println("------------------------------------------\nDein Gegner wählt : " + enemy.getName());
        if (InputHelper.getYesNoInput("Möchtest du mehr infos? ")) {
            System.out.println(enemy);
        }
        enemyAttacks();

    }

    static void fightMenu(Pokemon attacker, Pokemon defender, boolean isPlayer) throws IOException {
        System.out.println("====================================");
        System.out.println("⚔ Kampf beginnt! ⚔");
        System.out.println("====================================");

        System.out.println("Dein Pokémon:");
        System.out.printf("   %s - ❤️ %d HP\n", player.getName(), player.getHp());

        System.out.println("\nGegnerisches Pokémon:");
        System.out.printf("   %s - ❤️ %d HP\n", enemy.getName(), enemy.getHp());

        System.out.println("====================================");
        int choice = 1;
        if (isPlayer) {
            String prompt = "Möchtest du angreifen oder dein Pokemon wechseln? \n   1. Angriff\n   2. Wechsel\n";
            choice = InputHelper.getIntInput(prompt, 1, 2);
        }

        if (choice == 2) {
            trainer.setActivePokemon();
            System.out.println("Dein Pokemon wurde gewechselt.");
            attack(enemy, player, false);
        } else {
            System.out.printf("%s greift an!\n", attacker.getName());
        }
    }

    public static void enemyAttacks() {
        System.out.println("------------------------------------");
        System.out.println("➡ Dein Gegner bereitet sich vor. Drücke Enter, um den nächsten Schritt zu sehen!");
        sc.nextLine();
    }

    public static String showAttacks() {
        System.out.println("\n⚔ Deine Attacken ⚔");
        System.out.println("------------------------------------");
        System.out.printf("   1. %s (Typ: %s, Stärke: %d, Genauigkeit: %d%%)\n",
                player.getAttackMove().getName(), player.getAttackMove().getType(), player.getAttackMove().getPower(), player.getAttackMove().getAccuracy());
        System.out.printf("   2. %s (Typ: %s, Stärke: %d, Genauigkeit: %d%%)\n",
                player.getAttack2().getName(), player.getAttack2().getType(), player.getAttack2().getPower(), player.getAttack2().getAccuracy());
        System.out.println("------------------------------------");

        return "\n\uD83C\uDFAFWähle mithilfe der Nummern was du ausführen möchtest:";
    }

    public static void pokemonAttacks(Pokemon attacker, Attack selectedAttack, double damage) {
        System.out.println("====================================");
        System.out.printf("🔥 %s greift mit %s an und verursacht %f Schaden!!\n", attacker.getName(), selectedAttack.getName(), damage);
        System.out.println("====================================");
        System.out.println("➡ Drücke Enter, um den nächsten Schritt zu sehen!");
        sc.nextLine();

    }

    public static void pokemonEvade(Pokemon defender) {

        System.out.println(defender.getName() + " ist ausgewichen!");
    }

    public static void pokemonDied(Pokemon defender) {
        System.out.printf("%s wurde besiegt!\n", defender.getName());
    }

    public static void dead() {

        System.out.println("Alle deine Pokemon sind ausgenockt.. Dir wird schwindelig... Alles wird schwarz.");
    }

    public static String playAgain() {
        return "Möchtest du weiter kämpfen?";
    }

    public static void listPokemon(int x, Pokemon pokemon) {
        System.out.println(x + ". " + pokemon.getName() + (pokemon.isAlive() ? " - Alive" : " - Dead"));
    }

    public static int chooseActivePokemon(Pokemon[] inventar) {
        return InputHelper.getIntInput("Mit welchem Pokemon möchtest du kämpfen? (Zahl)", 1, inventar.length);
    }

    public static void errorDead() {
        System.out.println("❌ Fehler ❌");
        System.out.println("Das ausgewählte Pokémon kann nicht kämpfen, da es kampfunfähig ist.");
        System.out.println("Bitte wähle ein anderes Pokémon.");
    }

    public static void displayBattleResult(Pokemon player, Pokemon enemy) {
        System.out.println("\n====================================");
        System.out.println("🏆 Kampf beendet! 🏆");
        System.out.println("====================================");

        if (!enemy.isAlive()) {
            System.out.printf("🎉 Glückwunsch! Dein Pokémon %s hat den Gegner %s besiegt!\n", player.getName(), enemy.getName());
        } else {
            System.out.printf("❌ Dein Pokémon %s wurde vom Gegner %s besiegt.\n", player.getName(), enemy.getName());
        }
    }


}
