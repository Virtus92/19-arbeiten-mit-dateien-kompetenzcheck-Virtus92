package org.example;

import static org.example.Main.sc;

public class InputHelper {
    public static int getIntInput(String prompt, int min, int max) {
        int input = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine();
                if (input >= min && input <= max) {
                    valid = true;
                } else {
                    System.out.println("Bitte eine Zahl zwischen " + min + " und " + max + "eingeben!");
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben. ");
                sc.nextLine();
            }
        }
        return input;
    }

    public static boolean getYesNoInput(String prompt) {
        String input;
        while (true) {
            System.out.println(prompt + ("ja/nein"));
            input = sc.nextLine().trim().toLowerCase();
            if (input.equals("ja")) {
                return true;
            } else if (input.equals("nein")) {
                return false;
            } else {
                System.out.println("Ungültige Eingabe. Bitte 'ja' oder 'nein' eingeben.");
            }
        }
    }
}
