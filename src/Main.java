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
 * Main class to start the game on.
 *
 * @author Dan Truong
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World");

        Sector livingRoom = new Sector("Living Room",
                "A place to relax and watch TV",
                "cold"),
                bedroom = new Sector("Bedroom",
                        "Where the player sleeps",
                        "warm");

        LivingEntity human = new PlayerCharacter("Danny",
                "The main player character. He pays the rent and "
                + "enjoys watching TV."),
                dog = new NonPlayableCharacter("Buster", "One of Danny's "
                        + "pets. They enjoy belly rubs and head pats."),
                cat = new NonPlayableCharacter("Muffles", "One of Danny's "
                        + "pets. Enjoys being fed on time and meows when they "
                        + "don't."),
                evilRobot1 = new AdversarialCharacter("Evil Robot #1", 
                        "A broken vacuum that has gained sentience and "
                                + "makes loud noises."),
                evilRobot2 = new AdversarialCharacter("Evil Robot #2", 
                        "A box fan that has a broken bearing and is "
                                + "very dusty.");

        livingRoom.addEntity(human);
        livingRoom.addEntity(cat);
        livingRoom.addEntity(evilRobot2);
        bedroom.addEntity(dog);
        bedroom.addEntity(evilRobot1);

        System.out.println(livingRoom);
        System.out.println("");
        System.out.println(bedroom);

    }

}
