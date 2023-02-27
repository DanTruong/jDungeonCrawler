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
     * @param description Description of the Sector.
     * @param temperature Initial state of the Sector's temperature.
     * @param neighbors References to neighboring Sector's names.
     * @param gw GameWorld object linked to the Sector.
     */
    public Sector(String name, String description, String temperature,
            String[] neighbors, GameWorld gw) {
        this.name = name;
        this.description = description;
        this.temperature = temperature;
        this.population = new LivingEntity[7];
        this.populationCount = 0;
        this.northRef = neighbors[0];
        this.eastRef = neighbors[1];
        this.southRef = neighbors[2];
        this.westRef = neighbors[3];
        this.activeGameWorld = gw;
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
     * Alerts all Entities in the Sector of changes in the temperature state.
     *
     * @param action The action that was taken to change the Sector's
     * temperature state.
     */
    private void notifyAllEntities(String action) {
        for (int i = 0; i < this.populationCount; i++) {
            this.population[i].react(action);
        }
    }

    /**
     * Increases the temperature of the sector (if it's already hot, then the
     * temperature will stay the same).
     */
    public void increaseTemperature() {
        if (this.temperature.equalsIgnoreCase("cold")) {
            this.temperature = "cool";
            notifyAllEntities("warming");
        } else if (this.temperature.equalsIgnoreCase("cool")) {
            this.temperature = "warm";
            notifyAllEntities("warming");
        } else if (this.temperature.equalsIgnoreCase("warm")) {
            this.temperature = "hot";
            notifyAllEntities("warming");
        } else {
            System.out.println("Sector already hot");
        }
    }

    /**
     * Decreases the temperature of the sector (if it's already cold, then the
     * temperature will stay the same).
     */
    public void decreaseTemperature() {
        if (this.temperature.equalsIgnoreCase("hot")) {
            this.temperature = "warm";
            notifyAllEntities("cooling");
        } else if (this.temperature.equalsIgnoreCase("warm")) {
            this.temperature = "cool";
            notifyAllEntities("cooling");
        } else if (this.temperature.equalsIgnoreCase("cool")) {
            this.temperature = "cold";
            notifyAllEntities("cooling");
        } else {
            System.out.println("Sector already cold");
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
                this.activeGameWorld.getSector(northRef);
            case "E" ->
                this.activeGameWorld.getSector(eastRef);
            case "S" ->
                this.activeGameWorld.getSector(southRef);
            default ->
                this.activeGameWorld.getSector(westRef);
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
     * String method to return the description of the Sector.
     *
     * @return Description of the Sector object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * String method to return the state of the Sector's temperature.
     *
     * @return Sector's current temperature.
     */
    public String getTemperature() {
        return this.temperature;
    }

    /**
     * Returns the name of the Sector and the entities that reside in it.
     *
     * @return Name of the Sector and list of entity names.
     */
    @Override
    public String toString() {
        String listOfEntities = "", neighbors = "";

        for (int i = 0; i < populationCount; i++) {
            listOfEntities += population[i] + " ("
                    + population[i].getClass().getName() + "), ";
        }

        try {
            neighbors += "\nNorth: " + getNeighbor("N").getName();
        } catch (NullPointerException npe) {
        }
        try {
            neighbors += "\nEast: " + getNeighbor("E").getName();
        } catch (NullPointerException npe) {
        }
        try {
            neighbors += "\nSouth: " + getNeighbor("S").getName();
        } catch (NullPointerException npe) {
        }
        try {
            neighbors += "\nWest: " + getNeighbor("W").getName();
        } catch (NullPointerException npe) {
        }

        return "Sector: " + getName()
                + ". " + getDescription()
                + "\nCurrent Temperature: " + getTemperature()
                + neighbors
                + "\nEntities: " + listOfEntities;
    }

    /**
     * String variables to hold the name and description of the Sector. Cannot
     * be changed afterwards.
     */
    private final String name, description;

    /**
     * String variable to hold the state of the Sector's temperature.
     */
    private String temperature;

    /**
     * Array to hold Entity objects in the Sector.
     */
    private LivingEntity[] population;

    /**
     * Number count to track how many Entities are in the Sector.
     */
    private int populationCount;

    /**
     * String names of the neighboring Sectors.
     */
    private final String northRef, eastRef, southRef, westRef;

    /**
     * GameWorld object to call back to.
     */
    private final GameWorld activeGameWorld;

}
