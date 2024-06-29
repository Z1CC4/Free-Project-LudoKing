# Board class

This class represents the game board. It contains the following attributes:

- `int[][] board`: a 2D array representing the game board. Each cell of the array contains an integer value that
  represents the state of the cell. The possible values are:
    - 0: empty cell
    - 1: cell occupied by player 1
    - 2: cell occupied by player 2
    - 3: cell occupied by player 3
    - 4: cell occupied by player 4
- `int[][] safeZones`: a 2D array representing the safe zones for each player. Each cell of the array contains an
  integer value that represents the state of the cell. The possible values are:
    - 0: empty cell
    - 1: cell occupied by player 1
    - 2: cell occupied by player 2
    - 3: cell occupied by player 3
    - 4: cell occupied by player 4
    - 5: Minigame cell
    - 6: Safe cell
    - 7: Expansion cell
