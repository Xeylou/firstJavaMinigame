package fr.xeylou.firstJavaMinigame;

import java.util.Random;

public class Character {

    byte hp = 58;
    byte potionsNumber = 3;

    protected boolean isAlive() {
        return this.hp > 0;
    }

    protected byte hpNumber() {
        return this.hp;
    }

    protected void usePotion() {
        this.potionsNumber -= 1;
        this.hp = 58;
    }

    protected byte enemyAttack() {
        byte min = 5;
        byte max = 15;
        byte random = (byte)Math.floor(Math.random()*(max-min+1)+min);
        return random;
    }

    protected byte playerAttack() {
        byte min = 5;
        byte max = 10;
        byte random = (byte)Math.floor(Math.random()*(max-min+1)+min);
        return random;
    }

    protected void resetCharacter() {
        this.hp = 58;
        this.potionsNumber = 3;
    }
}
