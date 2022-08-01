package fr.xeylou.firstJavaMinigame;

public class Character {

    byte hp = 58;
    byte potionsNumber = 3;

    protected boolean isAlive() {
        return this.hp > 0;
    }


    protected void hpAfterAttack(byte inputDamage) {
        this.hp -= inputDamage;
    }

    protected void usePotion() {
        this.potionsNumber -= 1;
        this.hp = 58;
    }

    protected byte enemyAttack() {
        byte minDamage = 5;
        byte maxDamage = 15;
        return (byte)Math.floor(Math.random()*(maxDamage-minDamage+1)+minDamage);
    }

    protected byte playerAttack() {
        byte minDamage = 5;
        byte maxDamage = 10;
        return (byte)Math.floor(Math.random()*(maxDamage-minDamage+1)+minDamage);
    }

    protected void resetCharacter() {
        this.hp = 58;
        this.potionsNumber = 3;
    }
}
