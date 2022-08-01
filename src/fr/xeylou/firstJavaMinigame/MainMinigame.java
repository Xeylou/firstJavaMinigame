package fr.xeylou.firstJavaMinigame;

import java.util.Scanner;

public class MainMinigame {

    public static void main(String[] args) {
        languageSelection();
    }
    private static void languageSelection() {
        System.out.println("\n   Please select a language\n     FRENCH - fr_FR (1)\n     ENGLISH - en_UK (2)\n");
        Scanner isEnglish = new Scanner(System.in);
        byte number = isEnglish.nextByte();
        if (number == 2) {
            EnManager manager = new EnManager();
            manager.confirmationPlaying();
        }
        else if (number == 1) {
            FrManager manager = new FrManager();
            manager.confirmationPlaying();
        }
        else {
            System.out.println("\n Please enter a valid character");
            languageSelection();
        }
        isEnglish.close();
    }
}
