# jDungeonCrawler
Dan Truong  
March 11th, 2023

jDungeonCrawler is a text adventure game engine written in Java (much in the vein of Zork). It takes in user input and parses text response to the player.

### Running the game

At this time, a GUI client is yet to be developed for the game. To run this
game, execute the Java executable (**\jDungeonCrawler\dist\jDungeonCrawler.jar**) from a terminal/command line environment. Ensure that the game.xml file is in the same directory as the JAR file.
Below is an example running in a Windows PowerShell environment:

```
PS C:\jDungeonCrawler\dist> java -jar ".\jDungeonCrawler.jar"
Enter a command (type "help" for a list of commands):
```

### Commands

Commands are case-sensitive

- **n** - Moves your character to the Sector in the North
- **s** - Moves your character to the Sector in the South
- **e** - Moves your character to the Sector in the East
- **w** - Moves your character to the Sector in the West
- **warm** - Makes the current Sector warmer
- **cool** - Makes the current Sector cooler
- **Entity's Name:action** - Directs the specified Entity to move in a specific direction or warm/cool the sector (i.e. "House Fly #3:n" will move the specified entity to the North neighbor.
- **look** - Look at current information about the Sector
- **help** - Display the help commands
- **exit** - Exit the game
