package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Effect {
    protected static Map<String, Map<String, Double>> effectMap = new HashMap<>();


    public Effect() {
    }

    public static void initEffects() throws IOException {
        BufferedReader effect = new BufferedReader(new FileReader("src/main/java/org/example/Effectiveness.csv"));
        String line;
        String[] types = effect.readLine().split(";");
        while ((line = effect.readLine()) != null) {
            line = line.replace("\uFEFF", "");
            String[] values = line.split(";");
            String attackerType = values[0];
            Map<String, Double> effectivenessMap = new HashMap<>();

            for (int i = 1; i < values.length; i++) {
effectivenessMap.put(types[i], Double.parseDouble(values[i]));
            }
            effectMap.put(attackerType, effectivenessMap);
        }
    }

    public static double getEffectiveness(String attackerType, String defenderType) {
        return effectMap.getOrDefault(attackerType, new HashMap<>()).getOrDefault(defenderType, 1.0);
    }
}
