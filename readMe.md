## Welcome to Reversi : Sebastian F & Evelyn Y.

**Board Setup & Coordinates :**

- Flexible board size : The board is initialized with a default size of 7, but can be adjusted to custom odd sizes, greater than or equal to 5.
- Piece flipping : Players can flip opponent pieces based on game rules.
- Game Over : The game has conditions in place to determine if the board is full, both players have passed their turns, or if a player is trapped. 
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
Incorporating OOD principles shows a professional approach to software development, focusing on modularity, reusability, and maintainability. This aspect is crucial for long-term project sustainability and future enhancements.


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

## Drawing The Game Out
### DrawUtils Class: The Core of Game Rendering
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