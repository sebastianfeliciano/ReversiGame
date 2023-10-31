Welcome to Reversi : Sebastian F & Evelyn Y.

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

MAIN 
- 
- Board Initialization: 
  - The user is greeted and prompted to choose a board size
  - If the user selects the default size (0), a default-size board of 11 is created.
  - Valid coordinates :
    - q and r are non-negative, and less than the board size, ensuring it's not to the left of the board, above/below the board
    - q / r ranges from -1 to n - 1
    - For example, is the board size if 8 then valid q and r values can be from - 1 to 7.

