package org.example;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Random r = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Game.buildGame();
    }
}