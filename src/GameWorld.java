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
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class GameWorld extends DefaultHandler {

    /**
     * Default constructor for the GameWorld.
     */
    public GameWorld() {
        sector = null;
        sectorArray = new Sector[99];
        sectorIndex = 0;
    }
    
    public void showAllRooms(){
        for(int i = 0; i < sectorIndex; i++){
            System.out.println(sectorArray[i] + "\n\n");
        }
        
    }

    /*
    public void connectNeighbors() {
        for (int i = 0; i < sectorIndex; i++) {
            try {
                sectorArray[i].setNeighbor("N", getSector(sectorArray[i].getNeighborReference("N")));
            } catch (NullPointerException npe) {
                //System.out.println("Neighbor doesn't exist");
            }
            try {
                sectorArray[i].setNeighbor("E", getSector(sectorArray[i].getNeighborReference("E")));
            } catch (NullPointerException npe) {
                //System.out.println("Neighbor doesn't exist");
            }
            try {
                sectorArray[i].setNeighbor("S", getSector(sectorArray[i].getNeighborReference("S")));
            } catch (NullPointerException npe) {
                //System.out.println("Neighbor doesn't exist");
            }
            try {
                sectorArray[i].setNeighbor("W", getSector(sectorArray[i].getNeighborReference("W")));
            } catch (NullPointerException npe) {
                //System.out.println("Neighbor doesn't exist");
            }
        }
    }*/

    /**
     * Return Sector based on string input.
     *
     * @param sectorName Name of the Sector to search for.
     * @return Sector (based on String name).
     */
    public Sector getSector(String sectorName) {
        int low = 0, high = sectorIndex - 1;
        while (high - low > 1) {
            int middle = (high + low) / 2;
            if (sectorArray[middle].getName().compareToIgnoreCase(sectorName) < 0) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        if (sectorArray[low].getName().equalsIgnoreCase(sectorName)) {
            return sectorArray[low];
        } else if (sectorArray[high].getName().equalsIgnoreCase(sectorName)) {
            return sectorArray[high];
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
            case "PlayerCharacter":
                entity = new PlayerCharacter(name, description);
                this.player = (PlayerCharacter) entity;
                break;
            case "AdversarialCharacter":
                entity = new AdversarialCharacter(name, description);
                break;
            default:
                entity = new NonPlayableCharacter(name, description);
                break;
        }
        sector.addEntity(entity);
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
        sectorArray[sectorIndex] = sector;
        sectorIndex++;
        executeSort(0, sectorIndex - 1);
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
            while (sectorArray[qsLow].getName().compareTo(sectorArray[pivot].getName()) < 0) {
                qsLow++;
            }
            while (sectorArray[qsHigh].getName().compareTo(sectorArray[pivot].getName()) > 0) {
                qsHigh--;
            }
            if (qsLow <= qsHigh) {
                Sector temp = sectorArray[qsLow];
                sectorArray[qsLow] = sectorArray[qsHigh];
                sectorArray[qsHigh] = temp;
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
            case "sector":
                createSector(attr.getValue("name"),
                        attr.getValue("description"),
                        attr.getValue("state"),
                        new String[]{
                            attr.getValue("north"),
                            attr.getValue("east"),
                            attr.getValue("south"),
                            attr.getValue("west")
                        });
                break;
            case "AdversarialCharacter":
            case "NonPlayableCharacter":
            case "PlayerCharacter":
                createEntity(qName,
                        attr.getValue("name"),
                        attr.getValue("description"));
                break;
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
    private Sector[] sectorArray;

    /**
     * To keep track of Sectors in the Sector array.
     */
    private int sectorIndex;

    /**
     * Player character being created for the game session.
     */
    private PlayerCharacter player;

    /**
     * The current Sector being created.
     */
    private Sector sector;

}
