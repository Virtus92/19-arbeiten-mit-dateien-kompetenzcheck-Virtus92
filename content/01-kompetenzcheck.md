# Kompetenzcheck - Mit Dateien arbeiten

## Aufgabe: Pokémon-Spiel

### Nutze die CSV-Dateien um ein Pokémon Arena Spiel zu erstellen

Mit den zur Verfügung gestellten Datensätzen soll der Arenakampf von Pokémon nachgebaut werden.
Der User soll ein Pokémon auswählen und mithilfe verschiedener Attacken ein gegnerisches Pokémon, welches vom Computer gesteuert wird, bekämpfen.

### Requirements

#### CSV Einlesen

Importiere die Pokemon.CSV und Attacks.csv in dein Projekt.
Lese die Daten ein und erstelle daraus die notwendigen Klassen.
Jedes Pokémon soll zwei zufällige Attacken bekommen.

#### Auswahl

Der Nutzer kann zu Beginn des Spiels ein Pokémon durch Angabe der Index-Nummer oder dem Namen wählen, dieses wird für den Kampf ausgewählt.
Gib auf der Konsole aus, um welches Pokémon es sich handelt und welche Werte und mögliche Attacken es hat.
Das gegnerische Pokémon wählt der Computer zufällig.

#### Kampf

Nach der Selektion soll der Kampf gestartet werden.

Dem Nutzer werden die zwei Attacken aufgelistet. Wählt der Nutzer einen Angriff aus der Liste, greift das Pokémon an.
Wenn die Attacke ausgeführt wird, dann beginnt die Schadensberechnung.

#### Schadensberechnung

Der Schaden berechnet sich wie folgt:

Schaden = (Atk_Pw) * (APk_A / DPk_D) * (1.0/25.0)

**Atk_Pw** = Power Wert der Attacke, die Ausgewählt wurde
**APk_A** = Attack Wert des angreifenden Pokémon
**DPk_D** = Defense Wert des verteidigenden Pokémon

Das Pokémon mit dem größeren Speed Wert greift zuerst an. Verliert das angegriffene Pokémon, bevor es angegriffen hat, wird der Angriff übersprungen. Wenn die HP eines Pokémon auf 0 fallen, verliert der jeweilige Spieler.

#### OPTIONAL Effektivität

Erweitere die Schadensberechnung und berücksichtige die Typen des Angriffs und des verteidigenden Pokémon.

Schaden = (Atk_Pw) * (APk_A / DPk_D) * (Lvl / 50) * Rnd * STAB * Eff1 * Eff2

**Eff1** = Effektivität des Angriffs gegen Typ1 des verteidigenden Pokémon
**Eff2** = Effektivität des Angriffs gegen Typ2 des verteidigenden Pokémon

Nutze die Effectiveness.csv Datei, um die Koeffizienten zu ermitteln. 

### Abnahmekriterien

- Der:die Teilnehmer:in kann aufzählen, welche Klassen vorgestellt wurden, die geeignet sind, um Dateien auszulesen bzw. zu schreiben sind und kann bewerten, welche Klasse für seine Anwendung am besten geeignet ist.
- Der:die Teilnehmer:in hat sinnvolle Klassen für das Auslesen bzw. Schreiben der Datei angewendet und kann gut begründen, warum diese sinnvoll sind.
- Der:die Teilnehmer:in verwendet sinnvolle Objekttypen, SEHR schnell das Pokemon nach ID/Name zu finden.
- Das Programm erfüllt die oben angeführten Anforderungen.
- Das Programm erfüllt die Clean-Code-Richtlinien.
- Die Variablen-/Methoden-/Klassennamen sind aussagekräftig.
- Der Quellcode ist in GITHub eingecheckt.

Zurück zur [Startseite](../README.md)
