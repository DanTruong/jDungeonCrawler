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

    public GameWorld() {
        sector = null;
        sectorArray = new Sector[99];
        sectorIndex = 0;
    }

    public void searchSector(String inputQuery) {
        if (inputQuery.equalsIgnoreCase("list")) {
            for (int i = 0; i < sectorIndex; i++) {
                System.out.println("Sector: " + sectorArray[i].getName());
            }
        } else {
            int low = 0, high = sectorIndex - 1;
            while (high - low > 1) {
                int middle = (high + low) / 2;
                if (sectorArray[middle].getName().compareToIgnoreCase(inputQuery) < 0) {
                    low = middle + 1;
                } else {
                    high = middle;
                }
            }
            if (sectorArray[low].getName().equalsIgnoreCase(inputQuery)) {
                System.out.println("Found Sector: " + sectorArray[low].getName());
                System.out.println(sectorArray[low].toString());
            } else if (sectorArray[high].getName().equalsIgnoreCase(inputQuery)) {
                System.out.println("Found Sector: " + sectorArray[high].getName());
                System.out.println(sectorArray[high].toString());
            } else {
                System.out.println("\"" + inputQuery + "\" does not exist");
            }
        }

    }

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

    private void createSector(String name, String description, String state) {
        sector = new Sector(name, description, state);
        //TODO: Add neighbor references to Sector
        sectorArray[sectorIndex] = sector;
        sectorIndex++;
        executeSort(0, sectorIndex - 1);
    }

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
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attr
     */
    public void startElement(String uri, String localName, String qName,
            Attributes attr) {
        switch (qName) {
            case "sector":
                createSector(attr.getValue("name"),
                        attr.getValue("description"),
                        attr.getValue("state"));
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

    public PlayerCharacter getPlayer() {
        return player;
    }

    private Sector[] sectorArray;
    private int sectorIndex;
    private PlayerCharacter player;

    /**
     * The current Sector being created.
     */
    private Sector sector;

}
