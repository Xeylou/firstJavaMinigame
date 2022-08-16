package fr.xeylou.firstJavaMinigame;

import java.util.Scanner;

public class EnManager {

    static Character player = new Character();
    static Character enemy = new Character();

    private static final String actionsSeparation = "---------------------------------------------------------------";
    private static final String announcementSeparation = "============================================";
    private static final String closeSeparation = "###############################################################";

    protected static void explanations() {
        System.out.println("\n   Here are the rules of the game in English\n You need to defeat an enemy, for that you have 3 potions of healing and a weapon\n Your opponent has no potion but makes more damage than you and your weapon\n A potion will give your HP full back but you can not attack in the same round you use it\n\n\n   Would you like to play?(y/n)\n");
        confirmationPlaying();
    }

    protected static void confirmationPlaying() {
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        switch (c) {
            case 'y':
                player.resetCharacter();
                enemy.resetCharacter();
                System.out.println("\n   Would you like to see actions between laps?(y/n)\n");
                confirmationShowActions();
                break;
            case 'n':
                System.out.println("\n You closed the game\n\n" + closeSeparation);
                break;
            default:
                System.out.println("\n   Please enter a valid character\n");
                confirmationPlaying();
                break;
        }
        sc.close();
    }

    private static void confirmationShowActions() {
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        switch (c) {
            case 'y':
                System.out.println("\n Actions will show off between laps\n");
                boolean showActions = true;
                init(showActions);
                break;
            case 'n':
                System.out.println("\n Actions will not show off between laps\n");
                boolean hideActions = false;
                init(hideActions);
                break;
            default:
                System.out.println("\n   Please enter a valid character\n");
                confirmationShowActions();
                break;
        }
        sc.close();
    }

    private static void init(boolean showActions) {
        if (enemy.isAlive()) {
            if (player.isAlive()) {
                System.out.println("\n" + announcementSeparation+"\n You have "+player.hp+" HP and "+player.potionsNumber+" potion(s)\n Your enemy has "+enemy.hp+" HP\n\n Attack your enemy (a) or use a potion (p)?\n"+announcementSeparation+"\n");
                Scanner sc = new Scanner(System.in);
                char c = sc.next().charAt(0);
                switch (c) {
                    case 'a':
                        player.incrementStrokesNumber();
                        byte playerPreviousHp = player.hp;
                        byte enemyPreviousHp = enemy.hp;
                        byte playerAttackDamage = player.playerAttack();
                        byte enemyAttackDamage = enemy.enemyAttack();
                        enemy.hpAfterAttack(playerAttackDamage);
                        player.hpAfterAttack(enemyAttackDamage);
                        if (showActions) {
                            System.out.println("\n---"+actionsSeparation+"\n You did "+playerAttackDamage+" damage to your enemy, he had "+enemyPreviousHp+" HP\n He attacked you back and did "+enemyAttackDamage+" damage on your previous "+playerPreviousHp+" HP\n"+actionsSeparation+"---\n");
                        }
                        init(showActions);
                        break;
                    case 'p':
                        player.usePotion();
                        enemyAttackDamage = enemy.enemyAttack();
                        player.hpAfterAttack(enemyAttackDamage);
                        if (showActions) {
                            System.out.println('\n'+actionsSeparation+"\n You decided to use a potion and you have now your HP full back!\n After that, your enemy attack you and did "+enemyAttackDamage+" damage\n"+actionsSeparation+'\n');
                        }
                        init(showActions);
                        break;
                    default:
                        System.out.println("\n   Please enter a valid action character\n\n");
                        init(showActions);
                        break;
                }
                sc.close();
            }
            else {
                System.out.println("\n Your enemy managed to kill you!\n\n\n   Would you try again?(y/n)\n");
                confirmationPlaying();
            }
        }
        else {
            System.out.println("\n Congratulations! You have finished with your opponent\n It took you "+player.strokesNumber+" strokes and "+player.usedPotionsNumber+" potion(s) for this\n\n"+closeSeparation+"\n\n   Would you like to play again?(y/n)\n");
            confirmationPlaying();
        }
    }
}