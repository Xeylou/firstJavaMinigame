package fr.xeylou.firstJavaMinigame;

import java.util.Scanner;

public class EnManager {

    static Character player = new Character();
    static Character enemy = new Character();

    private static final String actionsSeparation = "--------------------------------------------------------------";
    private static final String announcementSeparation = "============================================";
    private static final String closeSeparation = "##############################";

    protected static void explanations() {
        System.out.println("\n blabla+would you like to play?(y/n)\n");
        confirmationPlaying();
    }

    protected static void confirmationPlaying() {
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        if (c == 'y') {
            player.resetCharacter();
            enemy.resetCharacter();
            confirmationShowActions();
        }
        else if (c == 'n') {
            System.out.println("\n You closed the game\n\n" + closeSeparation);
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
            System.out.println("\n Actions will not show off between laps");
            boolean showActions = false;
            init(showActions);
        }
        else {
            System.out.println(" Please enter a valid character");
            confirmationShowActions();
        }
        sc.close();
    }

    private static void init(boolean showActions) {
        if (enemy.isAlive()) {
            if (player.isAlive()) {
                System.out.println("\n" + announcementSeparation+"\n You have "+player.hp+" HP and "+player.potionsNumber+" potion(s)\n Your enemy has "+enemy.hp+" HP\n\n Attack your enemy (a) or use a potion (p)?\n"+announcementSeparation+"\n");
                Scanner sc = new Scanner(System.in);
                char c = sc.next().charAt(0);
                if (c == 'a') {
                    byte playerPreviousHp = player.hp;
                    byte enemyPreviousHp = enemy.hp;
                    byte playerAttackDamage = player.playerAttack();
                    byte enemyAttackDamage = enemy.enemyAttack();
                    enemy.hpAfterAttack(playerAttackDamage);
                    player.hpAfterAttack(enemyAttackDamage);
                    if (showActions) {
                        System.out.println('\n'+actionsSeparation+"\n You did "+playerAttackDamage+" damage to your enemy, he had "+enemyPreviousHp+" HP\n He attacked you back and did "+enemyAttackDamage+" damage on your previous "+playerPreviousHp+" HP\n"+actionsSeparation+'\n');
                    }
                    init(showActions);
                }
                else if (c == 'p') {
                    player.usePotion();
                    byte enemyAttackDammage = enemy.enemyAttack();
                    player.hpAfterAttack(enemyAttackDammage);
                    if (showActions) {
                        System.out.println('\n'+actionsSeparation+"\n You decided to use a potion and you have now your HP full back!\n After that, your enemy attack you and did "+enemyAttackDammage+" damage\n"+actionsSeparation+'\n');
                    }
                    init(showActions);
                }
                else {
                    System.out.println("\n Please enter a valid action character\n");
                    init(showActions);
                }
                sc.close();
            }
            else {
                System.out.println("\n u dead\n try again?(y/n)\n");
                confirmationPlaying();
            }
        }
        else {
            System.out.println("\n u won\n play again?(y/n)\n");
            confirmationPlaying();
        }
    }
}
