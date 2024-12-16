# Config Class


#Arlind Lacej 

## `RandomGame` Class

The `RandomGame` class handles the game logic for the Ludo King game, managing both human and AI player turns. It is responsible for setting up players, rolling dice, moving tokens, and determining the winner.

### Attributes:
- **`playersInGame`** (`List<Player>`):  
  Holds a list of all players in the game, including both human and AI players.

- **`humanPlayer`** (`Player`):  
  Stores the human player who participates in the game.

- **`numPlayers`** (`int`):  
  The number of players in the game (between 1 and 4), as specified by the user.

- **`tokenToPosition`** (`Map<Token, Integer>`):  
  A map that links each token to its position on the board.

- **`sc`** (`Scanner`):  
  A `Scanner` object used to read user input from the console.

### Constructor:
- **`RandomGame()`**  
  Initializes the `playersInGame` list, sets up the `Scanner`, and calls the `initializePlayers()` method to set up the game players.

### Methods:

#### `initializePlayers()`
Prompts the user to input the number of players (between 1 and 4). Asks the user to select which player will be the human player, creates the human player, assigns them a color, and adds AI players with different colors. All players are then added to the `playersInGame` list.

#### `startGame()`
Begins the game by displaying a welcome message and starting a loop for each round. Each round processes the turns of all players, allowing the human player and AI players to take their turns. The game continues until a player wins, at which point the winner is declared.

#### `humanTurn(Player player)`
Handles the turn for the human player:
- Displays a menu of options, including:
    - Roll the dice.
    - Get the position of a specific token.
    - Show points history.
    - Check token positions on the map of all players.
    - Enter the chat.
- Validates the player's input and checks if the player has finished or eaten other players' tokens.

#### `aiTurn(Player player)`
Handles the turn for an AI player:
- Rolls a dice and checks if any token can move forward.
- Updates the token's position and manages the player's game state.
- Ensures the AI player follows the same game rules as the human player, like checking for "eats" (captures) and completing their turn.

#### `rollDice()`
Simulates the rolling of a dice, returning a random number between 1 and 6.

#### `declareWinner()`
Determines the winner of the game by checking if any player has finished the game. The winner's name is displayed, and rankings are shown.

#### `showOpponentTokensPosition()`
Displays the current position of all opponent tokens (excluding the human player), showing whether they are still in the starting area, in play, or at home.

-------------------------------------------------------------------------------------------------------------
## `Chat` Class

The `Chat` class facilitates communication between players during the game. It allows players to send messages to all other players in the game. The chat session can be started and players can send messages until they choose to exit the chat.

### Attributes:
- **`playersInGame`** (`List<Player>`):  
  A list that holds all players currently in the game. It is initialized when the `Chat` object is created.

### Constructor:
- **`Chat(List<Player> players)`**  
  Initializes the `playersInGame` list with the players passed to the constructor, which is used to manage and send messages to all players in the game.

### Methods:

#### `startChat(Player currentPlayer)`
Starts a chat session for the `currentPlayer`:
- Prompts the player to type messages in the chat.
- Messages can be sent to all other players.
- The chat continues until the player types "exit", at which point the chat session ends.

#### `broadcastMessage(Player sender, String message)`
Sends the given `message` from the `sender` to all other players in the game. The message is displayed in the format:


## What i would change

The only thing i wasnt able to integrate was the showpositionofopponent players . this is a method that already exists in Game class 
but that method would give an output when i used the displayMenu in my build run so i just created my own method. I would try to 
integrate that too but i didnt have anymore time .
Other than that i think everything is good