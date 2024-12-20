package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Attack {
    protected static Map<Integer, Attack> attackMap = new HashMap<>();
    private int id;
    private String name;
    private String effect;
    private String type;
    private String kind;
    private int power;
    private String accuracy;
    private int pp;

    public Attack(int id, String name, String effect, String type, String kind, int power, String accuracy, int pp) {
        this.id = id;
        this.name = name;
        this.effect = effect;
        this.type = type;
        this.kind = kind;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
    }

    public static void initAttacks() throws IOException {
        BufferedReader pokemon = new BufferedReader(new FileReader("src/main/java/org/example/Attacks.csv"));
        String line;
        while ((line = pokemon.readLine()) != null) {
            line = line.replace("\uFEFF", "");
            String[] temp = line.split(";");

            if (temp[0].startsWith("#")) {
                continue;
            }
            int id = Integer.parseInt(temp[0]);
            String name = temp[1];
            String effect = temp[2];
            String type = temp[3];
            String kind = temp[4];
            int power = Integer.parseInt(temp[5]);
            String accuracy = temp[6];
            int pp = Integer.parseInt(temp[7]);

            Attack attack = new Attack(id, name, effect, type, kind, power, accuracy, pp);
            attackMap.put(attack.getId(), attack);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        int percent = Integer.parseInt(accuracy.replaceAll("[^0-9]", ""));
        return percent;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return  name +
                "\n      effect = '" + effect + '\'' +
                "\n      type = '" + type + '\'' +
                "\n      kind = '" + kind + '\'' +
                "\n      power = " + power +
                "\n      accuracy = " + accuracy +
                "\n      pp = " + pp;
    }
}
