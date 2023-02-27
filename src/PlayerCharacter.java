
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

    /**
     * Display commands for the Player to use.
     */
    private void displayHelp() {
        String helpText = """
                          The goal of this game is to increase your health 
                          points over 30, while not letting it go below 0. To do 
                          this, you change the temperature of the Sector that 
                          you are currently in. You can either "warm" or "cool"  
                          the Sector that you are currently in. Doing either 
                          action will affect the people or enemies that are in 
                          there. Their reaction will either increase or decrease 
                          your health points.
                          
                          Commands:
                          look: Display information about the Sector.
                          warm: Increase the temperature of the Sector.
                          cool: Decrease the temperature of the Sector.
                          north, east, south, west: Move to the Sector of the indicated direction.
                          exit: Exit the game.
                          """;
        System.out.println(helpText);
    }

    /**
     * Method to handle and parse user input commands.
     *
     * @param sc Scanner object to handle keyboard input with.
     */
    public void gameLoop(Scanner sc) {
        String userInput = "";
        while (!userInput.equalsIgnoreCase("exit")) {
            System.out.print("Type in a command > ");
            userInput = sc.nextLine();
            switch (userInput) {
                case "help" ->
                    displayHelp();
                case "look" ->
                    System.out.println("INSERT INFO ABOUT ROOM HERE");
                case "warm", "cool" ->
                    System.out.println("INSERT ACTIONS HERE TO CHANGE SECTOR TEMP");
                case "north", "east", "south", "west" ->
                    System.out.println("You went " + userInput);
                case "exit" ->
                    System.out.println("You are now exiting the game...");
                default ->
                    System.out.println("Command not found");
            }
        }
    }

    /**
     * Variable used to measure the player's health points.
     */
    private int health;

}
