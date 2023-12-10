## Welcome to Reversi : Sebastian F

**Board Setup & Coordinates :**

- Flexible board size : The board is initialized with a default size of 7, but can be adjusted to custom odd sizes, greater than or equal to 5.
- Piece flipping : Players can flip opponent pieces based on game rules.
- view.Game Over : The game has conditions in place to determine if the board is full, both players have passed their turns, or if a player is trapped. 
- Piece count : Players can get the count of their pieces on the board. 
- Hexagonal coordinate system : The game board uses a hexagonal coordinate system represented by rows and columns (q,r)


q --> represents the column of the hexagon
r --> represents the row of the hexagon

The third coordinate (s) can then be derived from q and r as we use the equation s = q - r. 
Since it's dependent on the other two, we don't explicitly define it in our own.


Internally, our board is stored as a 2D array. This requires converting the hexagonal (q,r)
coordinates to traditional 2D array indices (x,y).

For example :

int q = x + this.getBoardSize() / 2;
int r = y + this.getBoardSize() / 2;

Initializing Our Board : 
- The middle point of our board is calculated by BOARD_SIZE / 2.
- Depending on whether a row is above or below this midpoint, 
the starting (starQ) and (endQ) points of the columns containing these hexagons are determined.
- For each row, hexagons are instantiated for valid columns between 'startQ' and 'endQ'

Class Invariants
- BoardSize is non-negative and cannot be even
- Board size cannot be passed in a negative number, our constructor will throw an exception
- And it cannot be even, because then a hexagon shape cannot be formed, our constructor will also throw an exception.

MAIN 
- 
- Board Initialization: 
  - The user is greeted and prompted to choose a board size
  - If the user selects the default size (7), a default-size board of 11 is created.
  - Valid coordinates :
    - negative x is left, and negative y is up
    - a sample game that we ran was -1,-1 -> pass -> -2,1
    - q and r are non-negative, and less than the board size, ensuring it's not to the left of the board, above/below the board
    - q / r ranges from -BoardSize / 2 to BoardSize / 2

    
    -- Bug Fix for Piece Flipping Mechanism: November 15th

- Issue: Previously, the game encountered a bug in the piece flipping logic, particularly in handling the directions for flipping opponent pieces.
- Solution: We addressed this issue by refining the directional calculations. Specifically, we adjusted the addition and subtraction operations (+/-) applied to the x and y coordinates. This modification ensures a more accurate representation and handling of the hexagonal coordinate system, which our game utilizes.
- Elaboration: In the context of our hexagonal coordinate system (using q, r, s), the accurate translation to the traditional 2D array coordinates (x, y) is crucial for implementing game rules, especially for flipping pieces.
The bug fix involved a detailed review of how directional vectors interact with our hexagonal grid. By adjusting the +/- operations on x and y coordinates, we can now accurately determine the direction of a flip.
This enhancement allows for a consistent and rule-abiding gameplay experience, especially in scenarios where multiple pieces are flipped across different axes of the hexagonal grid.


# Changes for part 2

### GUI Representation and New Interfaces

- GUI Representation: Enhancing the game with a Graphical User Interface (GUI) significantly improves user interaction and visual appeal.
- New Interfaces and Classes: The addition of FallibleHexGameStrategy, InfallibleHexGameStrategy, IStrategy, ReadOnlyBoardModel, Reversi View, CaptureStrategy, CompleteStrategyFromFallible, GoForCornersStrategy, TryTwo, DrawUtils, and PlayerButton suggests a modular and flexible design, allowing for easy maintenance and scalability.

### OOD Principles Incorporation
Incorporating OOD principles shows a professional approach to software development, focusing on modularity, usability, and maintainability. This aspect is crucial for long-term project sustainability and future enhancements.


### Adding an AI Player
- **Role of the AI Player**: The AI Player acts as an autonomous entity capable of making strategic decisions during the game, simulating a human opponent. This addition greatly enhances the game by providing a challenging and intelligent adversary.

- **Strategy Integration**: The AI Player is designed to take in a strategy object, which dictates its gameplay approach. This is where the FallibleHexGameStrategy and InfallibleHexGameStrategy come into play, offering different levels of difficulty and styles of play.


### MouseEvents: Enhancing User Interaction

**Selection and Deselection of Hexes**
- Hex Selection: When a player clicks on a hex, it becomes selected. This can be visually indicated, for example, by changing the hex's color or border. This selection signifies the player's intention to perform an action with that specific hex.

**Deselection Mechanism**: 
- If a player changes their mind, clicking again on the selected hex or selecting another hex can deselect it. This flexibility allows players to make decisions carefully and thoughtfully.

**Feedback on Selection**: 
- Visual feedback on hex selection and deselection aids in better decision-making and enhances the interactive aspect of the game.

## Keyboard Integration
**'Enter' Key Functionality:**  
- If a player hits 'enter' while a hex is selected, the game displays the coordinates of that hex. This feature is useful for players to confirm their choices and for educational purposes, helping players learn the hexagonal coordinate system.

- 'P' for Pass: Pressing 'p' allows players to pass their turn. This is a critical functionality in Reversi, where passing can be a strategic decision.

**Deselection and 'Pass' Feedback:** 
- When a player deselects a hex or presses 'p' for pass, visual or textual feedback can be provided. This could be in the form of a message on the screen or a change in the appearance of the game board.

## Possible Strategies for Reversi: Maximizing Capture and Corner Control
**Strategy 1:
Maximizing Piece Capture per Turn**

_Objective_: The primary goal here is to flip as many of the opponent's pieces as possible in a single move. This approach can rapidly change the board's dynamics and can lead to a significant advantage in piece count.

_Implementation_: Players should look for moves that allow them to flip multiple lines of pieces in different directions. This often involves placing a disc at a position where it aligns with several rows, columns, or diagonals filled with the opponent's discs.

_Other Condition_: If there are more than one move that has the same amount of flippable pieces, it will go for the top-right most hex.

**Strategy 2: Corner Control**

_Objective_: Securing corner positions is a powerful strategy in Reversi. Discs placed in corners cannot be flipped, as they have no opposite neighbors. Control of corners often leads to control of the board.

_Implementation_: Players should aim to position their discs so that they can capture a corner when the opportunity arises. This often involves setting up discs along the edges leading to the corner, forcing the opponent to play into the corner space.

_Other Condition_: If there are no available corners to place a piece, it will place the piece closest to the right of the board. 

# EXTRA CREDIT: 
Exists, in the model package -> strategies package -> Try Two Class
### Chaining Strategy: Combining Multiple Approaches

Concept of Chaining
Dual Strategy Approach: Chaining involves using two distinct strategies sequentially. The AI first attempts to apply the first strategy; if that is not possible or optimal, it shifts to the second strategy.

**Flexibility**: This approach provides a high level of flexibility, as it allows the AI to adapt to different board states and opponent tactics.

Implementation in **TryTwo Class**

_**Strategy Initialization:**_ The class takes two FallibleHexGameStrategy objects as input, representing the two strategies to be chained.

**Move Selection Process**:

The AI first tries to select a move using the first strategy.
If the first strategy yields a viable move (firstMove.isPresent()), that move is selected.
If the first strategy does not provide a viable move, the AI then tries the second strategy.
Advantages: This implementation allows the AI to have a primary strategy but also a fallback plan, ensuring that it can make the best move according to the current situation.

### Possible Strategies to Chain
**Maximizing Capture** and **Corner Control**: One could chain the strategy of maximizing piece capture with corner control. The AI might first look to flip as many pieces as possible, but if that's not viable, it would shift its focus to securing a corner.

**Strategic Depth
Adaptability:** 

The Chaining strategy allows the AI to adapt its play style dynamically, based on the state of the board and the opponent's moves.

Learning and Improvement: Such a strategy can also serve as a learning tool for players, showcasing how different strategies can be effectively combined.


### Creation of A Mock Board

**Simulating Board Behavior:** 

The Mock class extends Board and implements ReadOnlyBoardModel and BoardModel interfaces. This allows it to act as a stand-in for the actual game board during testing.

**Testing Without Side Effects:** 

By using a mock object, we can test the functionality of components that interact with the board without affecting the real game state. This is crucial for isolated testing of specific functionalities.

### Logging Interactions: 

One of the key features of the Mock class is its ability to log interactions. Each method in the class appends information about the call (like getting scores, checking if the game is over, validating moves, etc.) to a StringBuilder log. This provides a detailed record of how other parts of the code interact with the board, which is invaluable for debugging and ensuring that components behave as expected.

**Key Functionalities of the Mock Class**

_**Delegate Method Calls**_: Most methods in the Mock class delegate their calls to the corresponding methods of a ReadOnlyBoardModel object. This simulates the behavior of an actual board.


_**State Verification:**_ Through logging, the Mock class enables verification of the state and behavior of the board during different stages of the game. For example, it records whether the game is over, if the board is full, or if a move is valid.

**_Enhanced Testing Capability:_** With methods like getValidMovesWithCaptures, the class allows for comprehensive testing of game logic, including complex scenarios like capturing pieces and corner moves.

### Benefits in Testing with a Mock Board
**_Debugging Aid:_**

The log generated by the Mock class can be used to trace issues in the game logic or in the interaction between different components.


_**Efficiency:**_ 

Using the Mock class makes testing more efficient as it avoids the overhead of setting up and tearing down actual game boards for each test.

## Drawing The view.Game Out
### DrawUtils Class: The Core of view.Game Rendering
_Hexagonal Board Rendering:_ 

The **DrawUtils** class is responsible for drawing the hexagonal board. It uses a Board object to determine the layout and size of the board and calculates the positions of hexagons based on the board's dimensions.

**Mouse Interactions:** The class includes mouse listeners to handle click and hover events. These events are used to select and highlight hexagons (HexShape objects) on the board.

****Hex Selection and Hovering**:** When a hex is clicked or hovered over, the corresponding HexShape object is identified using the findHex method. This method calculates which hex is under the mouse pointer based on the mouse's coordinates.

**Drawing Hexagons:** The drawEachHexagon method is called to render each hexagon. It changes the color of a hexagon when it's selected or hovered over, enhancing user interaction.

**GUI Components:** The class also includes GUI components like a JButton for quitting the game and a panel for organizing these components.

**PlayerButton Class:** Representing Player Pieces
   
**Player-Specific Buttons:** The PlayerButton class extends JButton, representing the player pieces on the board. Each hexagon on the board is associated with a PlayerButton.
   
**Custom Rendering:** The paintComponent method is overridden to customize the appearance of the buttons based on the player type (e.g., black or white player).
   
**Hex Association:** Each PlayerButton is linked to a HexShape, allowing it to represent the state of a particular hex on the board.



# Changes for Part 3

- ### **Added the Following interfaces**
- Observer
- PlayerActionListener
- MoveHandler
- ModelStatusListener
- Features
- TurnAIPopUp
- ### **Added the Following Classes**
- Command
- MockController
- ReversiController
- FrameSetup
- view.Game

## Improvements made from Previous Submission

- **Fixed The Players Constructor**
  - Now takes in a ReadOnly Board, since a player can only play on a readOnly version.
- **Fixed makeMove(row, column)**
  - This makes the move for the Player that tells the controller what to do.
  - The player cannot make the move, it now tells the controller what it wants to do, following MVC.

## New Additions Made
- ###### Observer Pattern / Notifiers
  - Notifiers for updating the score, popups to indicate whose turn it is, whether the player has passed,
    as well as the game class for updating AIs.
  - When a player makes a move, the notifier informs the score updater in the drawUtils class to update, as well as whose turn it is
  and whether the player has passed. 
  - view.Game Over Handling: When the game finishes, and no more valid moves are left, the view displays a message
    indicating the winner or a draw. And also notifies the view that the game is over as well.


- #### Mocks
  - Added both the view.MockGame and MockController classes for testing to stimulate certain behaviors. 
  - ###### view.MockGame :
    - extension of the view.Game class, captures information about the game's progress, for example when it starts, by updating the log.
    The Constructor takes in three parameters (ReversiController1, ReversiController2, and Board) which initialize the log and update it when the game starts.
    - MockController:
      - Extends the ReversiController class, replicates the purpose of a regular game controller but with specific inputs for testing.
      - Overrides onPlayerMove and appends a log entry indicating the row and column to which the player moved.
      - Tests both onPass and onPlayerMove to test that functionality works, and returns a log for testing 

- Games class Updating AI:
  - The view.Game class represents a game where two controllers work together to update the game state. 
    - It uses a timer to update the controllers at a fixed time interval. 
    - The start method initiates the updating mechanism, and the stop method stops the timer when the game is over.
  - The view.MockGame class represents extends the game class, and uses its same functionality.
  - Overrides the start method to append a log entry when the game begins.
  - GetLog method for testing that it updates when onGameStart is called.
  - Used for testing and debugging purposes. 

- Improving Passing Method
  - Adding notifiers and debugging so that when a player passes their turn, it accurately notifies the other player to go, or automatically sets the Ai
    to make its turn. 
  - If the AI player passes their turn, the passing method then notifies the human player that it is their turn to go.

- DrawUtils Buttons:
  - Buttons let the user both pass their turn, and quit the game.

- Command line:
  - Lets the user input a board size, player type / strategy, player type / strategy
  - when running the reversi game, a user can input arguments in the run configurations 

- Board Size :
  - Improved our view so that the board size is more flexible, and can be changed in the command line.
  - Users can input a different board size in the run configuration. 

- Assigning Controllers to Each Player 
  - Each player gets assigned a controller that's associated with a view as well
  - the controllers receive input from players, processes it, and then updates the game state accordingly.

- Model-View Controller
  - The board, view, and controller all work together following the MVC pattern to update the view to reflect the changes.

- Listeners/Features
  - Event-driven classes to respond to specific commands and events to trigger updates in the game.
    - switching turns
    - passing
    - game-over messages
      - score 
      - whether players tied / who won
    - updating AI moving to be automatic

  - ### Configuring the Game
    - Our Reversi Game allows different combinations of players 
    - for example (7 human capture) or (7 capture capture)
      - 7 -> boardSize, human -> player1type = human capture -> strategy and AIPlayer
      - To chain, you would just do two strategies back to back
      - For example (7 capture corner capture corner) --> this creates a boardSize of and two AIPlayers with chained strategies
  - RUN THIS TO SEE -> java -jar Reversi.jar 7 human human
  - or
  - java -jar Reversi.jar 7 capture capture
  - ###### Conditions
    - If a user decides to do a chained strategy with two of the same strategies, it will just call the one strategy as its strategy


# Reversi PT4 Changes


## Overview

This document provides a detailed account of the recent updates made in the PT4 version of the Reversi game project. The core objective of these updates has been to enhance the modularity and flexibility of the code, making it more adaptable and user-friendly for our customers. The changes focus on the introduction of interfaces in place of concrete class dependencies.

## Key Changes

### Introduction of New Interfaces

In this update, we have introduced three significant interfaces: `Features`, `IGameControlled`, and `ReversiView`. These interfaces replace the earlier practice of directly utilizing concrete classes, which resulted in tight coupling. The new interfaces offer a more flexible and scalable approach.

#### 1. `Features` Interface

- **Purpose**: Serves as a blueprint for the main functionalities offered by the game.
- **Impact**: Enables customers to implement essential features in a manner that aligns with their specific requirements and preferences.

#### 2. `IGameControlled` Interface

- **Purpose**: Specifies the methods necessary for managing the game's control mechanisms.
- **Impact**: Provides a structured framework for controlling game logic, allowing customers to adapt it more readily to various use cases.

#### 3. `ReversiView` Interface

- **Purpose**: Details the methods for visual representation and updates of the game.
- **Impact**: Facilitates the development of customized game views, thereby enhancing user experience and interface diversity.

### Rationale Behind the Changes

The shift to an interface-based architecture was driven by the need to reduce tight coupling in our software. The previous reliance on concrete classes limited our customers' ability to customize and extend the game's functionalities. The introduction of interfaces now offers:

- Enhanced flexibility - Easier integration into diverse software environments.
- Improved maintainability and scalability of the game.


## Adapter Classes Implementing the Adapter Pattern
In the PT4 update, we've also introduced a series of adapter classes, utilizing the adapter pattern to enhance the integration and flexibility of our software components. These adapter classes serve as bridges between the interfaces and their concrete implementations, ensuring compatibility and extending functionality.

### List of Adapter Classes
#### BoardPanelAdapter

Bridges the gap between the board model and its graphical representation.
Allows for a seamless connection between the game's data model and its visual output.
FallibleReversiStratAdapters

Adapts strategies for Reversi gameplay, accounting for potential errors or fallible conditions.
Enhances the robustness of game strategy implementations.
#### HexAdapter

Converts hex-shaped components of the game into a format that can be easily manipulated and displayed.
Facilitates more efficient handling of hexagonal game elements.
#### IBoardAdapter

Provides an adaptable interface for interacting with various board implementations.
Ensures flexibility in how game boards are managed and utilized.
##### IGraphicalReversiViewAdapter

Connects the ReversiView interface with graphical components, enabling a dynamic visual representation of the game.
Offers an adaptable solution for rendering the game's interface.
#### IModelFeaturesAdapter

Adapts model features for different implementation requirements.
Provides a flexible approach to modifying and extending game model features.
#### IMutableModelAdapter

Allows for the adaptation of game models that can be altered or modified during gameplay.
Supports dynamic changes in the game's state and structure.
#### IPlayerAdapter

Enables the integration of different player types and strategies into the game.
Facilitates diverse gameplay experiences through adaptable player interfaces.
#### IReversiPanelAdapter

Provides a bridge between Reversi game panels and their various implementations.
Enhances the modularity and customizability of game panels.
#### IROModelAdapter

Adapts read-only models for use in situations where data modification is not required or allowed.
Ensures data integrity and consistency in read-only scenarios.

#### IViewAdapter

Facilitates the adaptation of various view interfaces, ensuring flexibility in the presentation layer of the application.
Allows for the seamless integration of different view types and styles.
#### IViewFeaturesAdapter

Provides an adaptable interface for implementing view-related features.
Enhances the ability to customize and extend view functionalities.
#### JFrameViewAdapter

Adapts the JFrame view to integrate with our Reversi game logic.
Offers a bridge between the standard JFrame component and the specific requirements of the Reversi game view.
#### PlayerDiscAdapter

Converts player disc data into a format that can be easily used and displayed within the game.
Enhances the handling and representation of player discs in the game environment.
#### ReversiViewAdapter

Connects the ReversiView interface with specific implementations, enabling a tailored visual representation of the game.
Offers flexibility in rendering the game's interface according to specific user interface requirements.

### Impact of Adapter Classes
These adapter classes significantly contribute to the overall adaptability and modularity of the Reversi game software. By implementing the adapter pattern, we ensure that our components are not only interchangeable but also extendable, catering to a wide range of customer needs and scenarios. This design approach aligns with our commitment to providing flexible and robust software solutions.

### Conclusion

The PT4 update of the Reversi game project marks a crucial evolution in our approach to software design. By adopting these interface-based designs, we are confident that our customers will experience a significant improvement in the adaptability and user-friendliness of our software. We are committed to continually enhancing our software to meet and exceed the evolving needs of our customers.


### TO RUN WITH PROVIDER'S STRATEGY

java -jar Reversi.jar 7 human providerstrategy1

### To run with my strategy
java -jar Reversi.jar 7 human capture

### To run with human vs human
java -jar Reversi.jar 7 human human

The player2 is not restricted to any strategy, but player1 is my view
and cannot use the provider's strategy.


### If there is an error with PNG DISPLAYS
- From provider:
  - We do have a fallback method for our view rendering, so you do not have to do this if you don’t want to. But, if you want to see a cute Kirby and Metaknight Reversi game, here’s what you’d do:

Drag the “resources” package out so that it is now directly under the main project (the same level as the src and out folders)

Right click on the directory/package and then go down to “Mark Directory as” (similarly to marking your test directory as “test sources root”)

Choose “Resources Root”

Small Caveat: when building the jar file, make sure that the resources root directory is included in the build (it should automatically be added though!)


### VERY SMALL BUG

The pop up "It is now your turn" may show up twice on certain 
moves. 

In addition, when the game is over, it will display the game over. To display on
the provider's view, a hex must be clicked and hit enter to see the gameOver reflect at the 
JFrame title.

Or you can tap on 's' for it to show up.




## Extra Credit
**Features Implemented**

**_Level 0 - Showing Scoring Hints:_**

Implemented a “hint” mode for players, indicating potential move outcomes.
Hints can be enabled or disabled at runtime for each player independently.
Implemented as a separate decoration, ensuring no modification to the main rendering code.

**_Level 1 - Square Reversi:_**

Reimplemented the game model for a square grid Reversi.
Capturing takes place in eight directions, adhering to the analogous rules of Hexagonal Reversi.
Supports boards of any positive, even side-length.
Included a textual rendering for the square model for quick result visualization.
Both Hexagonal and Square models implement the same model interfaces and coexist without code commenting or recompilation.

**_Level 2 - Visualizing Square Reversi:_**

Developed a visual view for the square-grid Reversi.
Both Hexagonal and Square views implement the same view interfaces and coexist in the code.

**_Level 3 - Controlling Square Reversi:_**

Enhanced the controller to support both Hexagonal and Square Reversi.
Introduced parameterized view and controller interfaces for different coordinate representations.

**_Level 4 - Strategic Square Reversi:_**

Extended strategy implementations to work with both Hexagonal and Square Reversi.
Strategies are parameterized by the coordinate system type.
Required model design refactoring to support this feature.


## New Command Line args

Must be in the followng format:

board size player1 player2

Ex:
**hex 7 human human**

**square 8 human human**


## Hint Feature
To enable hints, this is only allowed for the hex reversi. 'H' to turn on and off.

