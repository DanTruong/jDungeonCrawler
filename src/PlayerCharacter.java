
/*
 * The MIT License
 *
 * Copyright 2023 Dan Truong.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
 * Child class of LivingEntity. Used for handling Playable Character objects.
 *
 * @author Dan Truong
 */
import java.util.Scanner;

public class PlayerCharacter extends LivingEntity {

    /**
     * Constructor for Player Character object.
     *
     * @param name Name to give to the Player.
     * @param description Description of the Player.
     */
    public PlayerCharacter(String name, String description) {
        super(name, description);
        this.health = 20;
    }

    /**
     * Gets the player's current health points.
     *
     * @return Current health of the player.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * This method is currently programmed to do nothing at the moment. This
     * must exist because it is an abstract method for this object's parent
     * class.
     */
    @Override
    public void changeSectorTemperature() {
        //Do nothing at the moment
    }

    /**
     * This method is currently programmed to do nothing at the moment. This
     * must exist because it is an abstract method for this object's parent
     * class.
     *
     * @param action N/A
     */
    @Override
    public void react(String action) {
        //Do nothing at the moment
    }

    public void gameLoop(Scanner sc) {
        System.out.print("Type in a command > ");
        String userInput = sc.nextLine();
        while (!userInput.equalsIgnoreCase("exit")) {
            System.out.print("Type in a command > ");
            userInput = sc.nextLine();
            System.out.println("You typed in: " + userInput);
        }
    }

    /**
     * Variable used to measure the player's health points.
     */
    private int health;

}
