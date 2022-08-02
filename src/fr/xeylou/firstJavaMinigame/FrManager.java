package fr.xeylou.firstJavaMinigame;

import java.util.Scanner;

public class FrManager {
    static Character player = new Character();
    static Character enemy = new Character();

    private static final String actionsSeparation = "----------------------------------------------------------------------------";
    private static final String announcementSeparation = "========================================================";
    private static final String closeSeparation = "#######################################################";

    protected static void explanations() {
        System.out.println("\n   Règles du jeu en français\n Vous devez battre votre ennemi, pour cela vous possédez 3 potions de soin et une arme\n Votre adversaire n'a pas de potion mais fait plus de dêgats par coups que vous\n Une potion régénérera entièrement vos PV, cependant vous ne pourrez attaquer le même tour\n\n\n   Souhaitez-vous jouer ? (o/n)\n");
        confirmationPlaying();
    }

    protected static void confirmationPlaying() {
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        switch (c) {
            case 'o':
                player.resetCharacter();
                enemy.resetCharacter();
                confirmationShowActions();
                break;
            case 'n':
                System.out.println("\n Vous venez de fermer le jeu\n\n" + closeSeparation);
                break;
            default:
                System.out.println("\n   Veuillez indiquer un caractère valide");
                confirmationPlaying();
                break;
        }
        sc.close();
    }

    private static void confirmationShowActions() {
        System.out.println("\n   Voulez-vous que les actions soient affichés chaque tours ? (o/n)\n");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        switch (c){
            case 'o':
                System.out.println("\n Les actions seront affichés chaque tour\n");
                boolean showActions = true;
                init(showActions);
                break;
            case 'n':
                System.out.println("\n Les actions ne s'afficheront pas");
                boolean hideActions = true;
                init(hideActions);
                break;
            default:
                System.out.println("\n   Veuillez indiquer un caractère valide");
                confirmationShowActions();
                break;
        }
        sc.close();
    }

    private static void init(boolean actionsPrompted) {
        if (enemy.isAlive()) {
            if (player.isAlive()) {
                System.out.println("\n" + announcementSeparation+"\n Vous avez "+player.hp+" PV et "+player.potionsNumber+" potion(s)\n Votre ennemi possède "+enemy.hp+" PV\n\n Attaquer votre ennemi (a) ou utiliser une potion (p) ?\n"+announcementSeparation+"\n");
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
                        if (actionsPrompted) {
                            System.out.println('\n'+actionsSeparation+"\n Vous avez fait "+playerAttackDamage+" dêgats à votre adversaire, votre ennemi en avait "+enemyPreviousHp+" PV\n Celui-ci vous a attaqué en retour faisant "+enemyAttackDamage+" dêgats à vos précédents "+playerPreviousHp+" PV\n"+actionsSeparation+'\n');
                        }
                        init(actionsPrompted);
                        break;
                    case 'p':
                        player.usePotion();
                        byte enemyAttackDammage = enemy.enemyAttack();
                        player.hpAfterAttack(enemyAttackDammage);
                        if (actionsPrompted) {
                            System.out.println('\n'+actionsSeparation+"----\n Vous avez décidé de boire une potion et vos PV sont à nouveau à leur maximum !\n Juste après cela, votre ennemi vous a attaqué faisant "+enemyAttackDammage+" dêgats\n"+actionsSeparation+"----\n");
                            // awful method to concatenate strings after actionsSeparation but i do not want to create a new String
                        }
                        init(actionsPrompted);
                        break;
                    default:
                        System.out.println("\n   Veuillez indiquer un caractère valide\n");
                        init(actionsPrompted);
                        break;
                }
                sc.close();
            }
            else {
                System.out.println("\n Votre adversaire a réussi à vous tuer!\n\n\n   Voulez-vous prendre votre revanche ?(o/n)\n");
                confirmationPlaying();
            }
        }
        else {
            System.out.println("\n Félicitations ! Vous en avez fini avec votre adversaire\n Il vous a fallu pour cela "+player.strokesNumber+" coups et "+player.usedPotionsNumber+" potion(s)\n\n"+closeSeparation+"\n\n   Souhaitez-vous rejouer et battre votre record ? (o/n)\n");
            confirmationPlaying();
        }
    }
}