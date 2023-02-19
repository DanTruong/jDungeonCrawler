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
    }

    private void createEntity(String qName, String name, String description) {
        System.out.println("Adding \"" + qName + "\" with name \""
                + name + "\"");
        //TODO: Add conditional to deliniate between Entity objects
        //TODO: Create Entity object and add to Sector
    }

    private void createSector(String name, String description, String state) {
        System.out.println("Creating Sector \"" + name
                + "\" with initial state of \"" + state + "\"");
        //TODO: Create Sector object
        //TODO: Add neighbor references to Sector
        //TODO: Add Sector to list of Sectors
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

}