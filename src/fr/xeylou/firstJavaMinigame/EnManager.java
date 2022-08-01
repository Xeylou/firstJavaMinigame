package fr.xeylou.firstJavaMinigame;

import java.util.Scanner;

public class EnManager {

    private static String actionsSeparation = "";
    private static String announcementSeparation = "";
    private static String closeSeparation = "";

    protected static void explanations() {
        System.out.println("\n blabla+would you like to play?(y/n)\n");
        confirmationPlaying();
    }

    protected static void confirmationPlaying() {
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        if (c == 'y') {
            confirmationShowActions();
        }
        else if (c == 'n') {
            System.out.println("\n You closed the game\n\n################################");
        }
        else {
            System.out.println(" Please enter a valid character");
            confirmationPlaying();
        }
        sc.close();
    }

    private static void confirmationShowActions() {
        System.out.println("\n   Would you like to see actions between laps?(y/n)\n");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        if (c == 'y') {
            System.out.println("\n Actions will show off between laps\n");
            boolean showActions = true;
            init(showActions);
        }
        else if (c == 'n') {
            System.out.println("\n Actions will not show off");
        }
        else {
            System.out.println(" Please enter a valid character");
            confirmationPlaying();
        }
        sc.close();

    }

    private static void init(boolean showActions) {

    }


}
