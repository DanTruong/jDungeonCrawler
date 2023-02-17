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
 * Class to define entity characters that will be populate the game world.
 *
 * @author Dan Truong
 */
public class LivingEntity {

    /**
     * Creates new Entity object and gives it a name.
     *
     * @param name String to identify the Entity.
     */
    public LivingEntity(String name) {
        this.name = name;
    }

    /**
     * String method to return name of the Entity.
     *
     * @return Name of the Entity.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the name of the Living Entity object.
     *
     * @return String name of the Entity.
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * String object to hold name of the Entity. Cannot be changed afterwards.
     */
    private final String name;

}
