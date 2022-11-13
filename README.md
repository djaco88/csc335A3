# csc335A3
Programmers: Quinn Jones and Jake Davis
CSC 335 
X-Tank

TO RUN: run XTankServer.java, wait until its running and then run XTank.java. Multiple Xtank.javas can be run, the server can support multiple clients

NOTE: the switch we made to have the clients rely solely on the data stored on the server did not go smoothly. The clients only display (And update) the first tank created. All clients have access to eachothers data from the server, but the drawing on the canvas did not work properly.


Learning Experience:
This being my first time working with a server, this program has had its ups and downs. For one, being able to connect my Windows computer with Jake’s Mac caused some issues, which we solved by making our game multiplayer and accessible on one computer. 
This being our second program working together, we did have a much smoother time through the process. We got better at using GitHub, and reading and understanding each other's functions and ideas. Although, I mainly focused on cosmetics of the program though the UI and Jake focused on the Server, and so much more! We both found our strong suites for the program and attempted to solve the problem. We still ran into a few issues where our functions clashed with one another, but we were more adept at fixing any given issues that arise. 

Summary:
Our X-Tank game will support multiple clients, but it has only been tested with all clients on the same computer. 
At the start of the game, a randomly generated map will appear and each player will be given a randomized tank color. This will be helpful for the players to not confuse their tanks with other tanks during game time. 

Extensibility: 
Each user only updates the server when they perform an action. This will prevent the server from being overwhelmed by an abundance of players. It is unnecessary to change the server if there is nothing to update. 

Flexibility:
We broke our code into 11 different classes. This allows us to easily make modifications in our program. These classes will be displayed in our UML Diagram showing the relationships that they all hold.

Reusability: 
Since our program is broken into many classes. These classes share code between one another by inheritance or composition. This allows us to make sure that our classes are organized to contain specific information that is necessary for the functionality of the program as a whole rather than repeating functions everywhere. We also incorporated global instantiations of these class objects in order to simplify the code. 



1. The client-server nature of the game and how that affects the way we represent game state.
Our server takes in information from each client as soon as a client performs an action and only when an action is performed. This prevents an overload of information being sent to the server when there are multiple clients connected to the server. 

2. The tanks that will be used by the players.
Each tank will have its own randomized color. This will help the users differentiate their tank from another tank. Each tank can move up, down, left, and right and may shoot a missile in either of these directions. 

3. The maze that will constrain the motion of the tanks.
At the start of the game, the game will randomly generate walls and borders. The border is restricted to the size of the game display. No tank may exit the screen display. The walls will be generated in the display. The tanks may not pass the walls, but they can move around them.

4. The animation of tank movement and the act of firing at other tanks.
The tank moves at a pace of 10 pixels per move. You may hold down an arrow to see a more smooth course of movement. We decided to have the missile shoot at a realistic speed. In whichever direction the tank is pointing, the bullet will shoot. The bullet will be displayed wherever it stops, whether it is the end of the display or at a wall.

5. The players and how they are represented.
The players are represented as tanks in this game. Each tank has a specified hitpoints. This is the amount of times they are able to be hit by missiles before they are out of the game. Once they are out of hitpoints, they will disappear from the game. Anyone who is disconnected from the server during the duration of the game will also have their tank disappear from the game play.

6. The game play; how it starts, how it ends and how it can be played again.
The game starts by opening up to your tank and the map. As other clients join, their tanks will start to appear. You can begin game play then. Each user can use the UP, DOWN, LEFT, RIGHT arrows to move around. Hitting the space button will shoot a missile in the direction of your tank. Your goal is to shoot the other players tanks and be the last one standing. Once you run out of hitpoints, you will be booted from the game, as you have lost. 
In order to exit the game, you may select the x button on the display. In order to play again, you can restart the game. The server should still be running unless you close down the server.

