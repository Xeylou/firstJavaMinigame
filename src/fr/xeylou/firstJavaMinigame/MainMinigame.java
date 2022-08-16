package fr.xeylou.firstJavaMinigame;

import java.util.Scanner;

public class MainMinigame {

    public static void main(String[] args) {
        languageSelection();
    }

    private static void languageSelection() {
        System.out.println("\n   Please select a language\n     FRENCH - fr_FR (1)\n     ENGLISH - en_UK (2)\n");
        Scanner englishRequested = new Scanner(System.in);
        byte number = englishRequested.nextByte();
        switch (number) {
            case 2:
                EnManager.explanations();
                //i have never made a //EnManager en = new EnManager();??
                break;
            case 1:
                FrManager.explanations();
                break;
            default:
                System.out.println("\n Please enter a valid character");
                languageSelection();
                break;
        }
        englishRequested.close();
    }
}