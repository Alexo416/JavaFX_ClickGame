package ClickGame;

public class Game {

    /**
     * This Class is where the calculations of number of clicks, number of gold, damage done and health points of the monster are created
     */

    private int numberOfClicks;
    private int numberOfGold;
    private int numberOfDamage;
    private int healthPoints;

    /**
     * Set Game Defaults
     */
    public Game() {
        numberOfClicks = 0;
        numberOfGold = 0;
        numberOfDamage = 0;
        healthPoints = 100_000;
    }

    /**
     * @return Return the Number of clicks
     */
    public int getNumberOfClicks() {
        return numberOfClicks;
    }

    /**
     * @param numberOfClicks set number of clicks which damages the monster
     */
    public void setNumberOfClicks(int numberOfClicks) {
        this.numberOfClicks += numberOfClicks;

        setNumberOfDamage(numberOfClicks);


        if (getNumberOfClicks() % 5 == 0) {
            setNumberOfGold(1);

        }

        setHealthPoints(numberOfClicks);

    }

    /**
     * @return Returns the Number of Gold
     */
    public int getNumberOfGold() {
        return numberOfGold;
    }

    /**
     * @param numberOfCurrency Gold increase for the user
     */
    public void setNumberOfGold(int numberOfCurrency) {
        this.numberOfGold += numberOfCurrency;
    }

    /**
     * @param numberOfClicks The number of damage done to the monster is based off the number of clicks
     */
    public void setNumberOfDamage(int numberOfClicks) {
        this.numberOfDamage += numberOfClicks;
    }

    /**
     * @return Returns Monsters Health
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * @param damageDone each time damage is done, the monsters health point is reduced
     */
    public void setHealthPoints(int damageDone) {
        this.healthPoints -= damageDone;
    }

    @Override
    public String toString() {
        return " [Gold] = " + numberOfGold +
                " [Total Damage] = " + numberOfDamage +
                " [Total Health Points] = " + healthPoints;
    }
}
