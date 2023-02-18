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
 * Class to define the physical sectors that Living Entity objects will reside
 * in.
 *
 * @author Dan Truong
 */
public class Sector {

    /**
     * Creates new Sector object and gives it a name.
     *
     * @param name String to identify the Sector.
     */
    public Sector(String name) {
        this.name = name;
        this.population = new LivingEntity[7];
        this.populationCount = 0;
    }

    /**
     * Method for adding Entities to the Sector.
     *
     * @param le Entity object being added to the Sector.
     */
    public void addEntity(LivingEntity le) {
        if (populationCount < 7) {
            le.setCurrentSector(this);
            population[populationCount] = le;
            populationCount++;
        }

    }

    /**
     * Sets the neighboring Sectors for the current Sector object.
     *
     * @param direction String of the direction to set the neighbor.
     * @param sector Sector object to assign to the neighboring direction.
     */
    public void setNeighbor(String direction, Sector sector) {
        switch (direction) {
            case "N":
                this.north = sector;
                break;
            case "E":
                this.east = sector;
                break;
            case "S":
                this.south = sector;
                break;
            default:
                this.west = sector;
                break;
        }
    }

    /**
     * Get the neighboring Sector, based on direction.
     *
     * @param direction String direction (N = North, E = East, S = South, W =
     * West) to get neighbor from.
     * @return Neighboring Sector that corresponds to the requested direction.
     */
    public Sector getNeighbor(String direction) {
        return switch (direction) {
            case "N" ->
                this.north;
            case "E" ->
                this.east;
            case "S" ->
                this.south;
            default ->
                this.west;
        };
    }

    /**
     * String method to return name of the Sector.
     *
     * @return Name of the Sector object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the name of the Sector and the entities that reside in it.
     *
     * @return Name of the Sector and list of entity names.
     */
    @Override
    public String toString() {
        String listOfEntities = "";

        for (int i = 0; i < populationCount; i++) {
            listOfEntities += population[i] + " ("
                    + population[i].getClass().getName() + "), ";
        }

        return "Sector: " + getName() + "\nEntities: " + listOfEntities;
    }

    /**
     * String object to hold name of the Sector. Cannot be changed afterwards.
     */
    private final String name;

    /**
     * Array to hold Entity objects in the Sector.
     */
    private LivingEntity[] population;

    /**
     * Number count to track how many Entities are in the Sector.
     */
    private int populationCount;

    /**
     * Neighboring Sector references.
     */
    private Sector north, east, south, west;

}
