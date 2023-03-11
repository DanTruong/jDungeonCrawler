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
 * Class to create the game world by parsing XML game data.
 *
 * @author Dan Truong
 */
import java.util.ArrayList;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class GameWorld extends DefaultHandler {

    /**
     * Default constructor for the GameWorld.
     */
    public GameWorld() {
        sector = null;
        sectorList = new ArrayList();
    }

    /**
     * Return Sector based on string input.
     *
     * @param sectorName Name of the Sector to search for.
     * @return Sector (based on String name).
     */
    public Sector getSector(String sectorName) {
        int low = 0, high = sectorList.size() - 1;
        while (high - low > 1) {
            int middle = (high + low) / 2;
            if (sectorList.get(middle).getName().compareToIgnoreCase(sectorName) < 0) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        if (sectorList.get(low).getName().equalsIgnoreCase(sectorName)) {
            return sectorList.get(low);
        } else if (sectorList.get(high).getName().equalsIgnoreCase(sectorName)) {
            return sectorList.get(high);
        } else {
            return null;
        }
    }

    /**
     * Creates Entity object and adds it to the current Sector object.
     *
     * @param qName Class of Entity to create.
     * @param name Name of the Entity.
     * @param description Description of the Entity.
     */
    private void createEntity(String qName, String name, String description) {
        LivingEntity entity = null;
        switch (qName) {
            case "PlayerCharacter" -> {
                entity = new PlayerCharacter(name, description);
                player = (PlayerCharacter) entity;
            }
            case "AdversarialCharacter" ->
                entity = new AdversarialCharacter(name, description);
            default ->
                entity = new NonPlayableCharacter(name, description);
        }
        sector.addEntity(entity);
        entity.setCurrentSector(sector);
    }

    /**
     * Creates Sector object, add it to Sector array and sort the array
     * afterwards.
     *
     * @param name Name of the Sector.
     * @param description Description of the Sector.
     * @param state Initial temperature state of the Sector.
     * @param neighbors Directional references to neighboring Sectors.
     */
    private void createSector(String name, String description, String state,
            String[] neighbors) {
        sector = new Sector(name, description, state, neighbors, this);
        sectorList.add(sector);
        executeSort(0, sectorList.size() - 1);
    }

    /**
     * Executes quicksort sorting algorithm on the Sector array for easier
     * searching.
     *
     * @param low Left-most index of the array.
     * @param high Right-most index of the array.
     */
    private void executeSort(int low, int high) {
        int qsLow = low, qsHigh = high, pivot = low + (high - low) / 2;
        while (qsLow <= qsHigh) {
            while (sectorList.get(qsLow).getName().compareTo(sectorList.get(pivot).getName()) < 0) {
                qsLow++;
            }
            while (sectorList.get(qsHigh).getName().compareTo(sectorList.get(pivot).getName()) > 0) {
                qsHigh--;
            }
            if (qsLow <= qsHigh) {
                Sector temp = sectorList.get(qsLow);
                sectorList.set(qsLow, sectorList.get(qsHigh));
                sectorList.set(qsHigh, temp);
                qsLow++;
                qsHigh--;
            }
        }
        if (low < qsHigh) {
            executeSort(low, qsHigh);
        }
        if (qsLow < high) {
            executeSort(qsLow, high);
        }
    }

    /**
     * Overrides default SAXParser startElement method to read in game world
     * elements.
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attr
     */
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attr) {
        switch (qName) {
            case "Sector" ->
                createSector(attr.getValue("name"),
                        attr.getValue("description"),
                        attr.getValue("state"),
                        new String[]{
                            attr.getValue("north"),
                            attr.getValue("east"),
                            attr.getValue("south"),
                            attr.getValue("west")
                        });
            case "AdversarialCharacter", "NonPlayableCharacter", "PlayerCharacter" ->
                createEntity(qName,
                        attr.getValue("name"),
                        attr.getValue("description"));
        }
    }

    /**
     * Returns the player character that is generated for this game.
     *
     * @return The player character object.
     */
    public PlayerCharacter getPlayer() {
        return player;
    }

    /**
     * Used for holding the Sectors in the Game World.
     */
    private ArrayList<Sector> sectorList;

    /**
     * Player character being created for the game session.
     */
    private PlayerCharacter player;

    /**
     * The current Sector being created.
     */
    private Sector sector;

}
