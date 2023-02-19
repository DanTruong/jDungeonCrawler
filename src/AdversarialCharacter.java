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
 * Child class of LivingEntity. Used for handling Adversarial Character
 * objects. Think of this class as an Entity that is supposed to be opposing
 * the player in terms of gameplay.
 *
 * @author Dan Truong
 */
public class AdversarialCharacter extends LivingEntity {

    /**
     * Constructor for the Adversarial Character object.
     *
     * @param name Name given to the Adversarial Character object.
     * @param description Description of the Adversarial Character.
     */
    public AdversarialCharacter(String name, String description) {
        super(name, description);
    }

    /**
     * This character object likes Sectors that are hot. This method will
     * increase the temperature of the Sector that the current Entity is in.
     */
    @Override
    public void changeSectorTemperature() {
        getCurrentSector().increaseTemperature();
    }

    /**
     * Perform an action based on change of the Sector's temperature.
     *
     * @param action The action that was taken in the Sector.
     */
    @Override
    public void react(String action) {
        if (action.equals("cooling")) {
            //TODO: Harm the player character
            //TODO: Try to move to another Sector in a random direction
        }
    }

}
