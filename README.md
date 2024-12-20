# Project Name: Ludoking Board Game

## Team Members

- **Andrea Zicarelli** - [@Z1CC4](https://github.com/Z1CC4)
- **Arlind Lacej** - [@Arlind393](https://github.com/Arlind393)
- **Francesco Masala** - [@francescomasala](https://github.com/francescomasala)
- **Matteo Hu** - [@matteo4k](https://github.com/matteo4k)

## Project Description

This project is a recreation of the famous board game "Ludo King" (in Italian: "Non t'arrabbiare"), with additional interesting features.

## Building and Running the Project

To package the application, firsly move to the directory of the project. Then, use the command 'mvn package'.
After packaging the project, in order to run the project go to the terminal, move to the directory of the project, and the use the command 'mvn exec:java'.

## User Guide

Each player has 4 tokens, and the goal is to get all of them into the home. To move a token out of the base, the player must roll a six on the die. If a player has no token on the map, he/she must roll the die until he/she gets a six. If he/she doesn't have a token out, and the roll result is <= 6,then nothing happens. If there is already at least one token outside the base and a player rolls a six, they can choose either to move a token or to bring out another token. If a 6 is getted, it is possible to roll the die another time.

### Menu

The menu is characterized by 5 options:

**1. _ROLL THE DICE_**
   
The die gets rolled and the turn of the player ends. If there are no tokens out, the player must roll until he/she gets a 6. If there is at least one token out, if he/she gets     a     six, it is possible to choose either to move the token that is already out or to take another token out. When a token is taken out, it will be placed on a random tile                    (between 0, 16, 32, 46). If the player gets a result <= six, then the only token that he/she has will be moved. If there are at least two tokens out, if the result is <= six,     it     is possible to choose which token move. If the result is six, then it applies the same rule as before, with the possibility to decide which token to move. 

**2. _GET POSITION OF A SPECIFIC TOKEN_**
   
It gets the position of the token chosen by the player. With this option, the turn will not end. If there is only one token out, the game will immediatly display the position     of     that token only. If there at least 2 tokens, it will be possible to select a specific token. Note that the position of the token is not the same as the position of the token   on the    map. 

**3. _SHOW HISTORY POINTS_**

It prints out the history of all points gained between the beginning and the moment the options is selected. With this option, the turn will not end.

**4. _SHOW RANKING CHARTS_**
  
It prints out the ranking chart of the game at the moment when the option is selected. With this option, the turn will not end.

**5. _CHECK TOKEN POSITION ON MAP OF ALL THE PLAYERS_**

It prints out the position of the tokens of all the players on the map. It is useful to help the players to check the possibily to eat/to be eaten. With this option, the turn will not end.

During the game, players might encounter tiles with a minigame. To gain points and perks, the player must win the minigame. If the player wins, they earn points and a perk. However, if the player already has a specific perk, they cannot earn the same one again. Additionally, if the player wins, their token moves one position ahead. Conversely, if they lose, their token moves one position backward. In both cases, it is possible to encounter another minigame.

As in the original game, the "eat" mechanism is implemented. This mechanism is based on the position on the board (when a token is taken out of the base, it is placed in a random position on the board). The player whose token is eaten loses points, which are gained by the player who performed the "eat". The players who eat will always gain points. If players whose token is eaten have less than 35 points, then their points will be decreased directly to 0. In our version of the game, the winner is not the player who gets all their tokens home first, but the one who accumulates the most points.

## Implementation

### RandomGame class 
Overview :
The RandomGame class is the core game engine for a simplified Ludo game called LudoKing. 
It manages game logic, player interactions, AI decisions, dice rolls, token movements, and mini-games. 
The game can be played by 2 to 4 players, who can be either human or AI.

### Constants:
BOARD_SIZE: int (52)
TOKENS_PER_PLAYER: int (Number of tokens per player, from RandomPlayer class)
Attributes:

playerManager: RandomPlayerManager (Manages players and their states)
gameToPosition: Map<Integer, MiniGame> (Maps board positions to mini-games)
playerToPlacement: Map<RandomPlayer, Integer> (Tracks player placements)
placements: List<Integer> (Holds remaining placement ranks)
random: Random (Random number generator)
sc: Scanner (Handles user input)

### RandomPlayer class :
The RandomPlayer class represents a player in the Ludo game. Each player has attributes to track their tokens, identify if they are an AI or human player, and determine whether they have completed the game. 
It also contains logic for managing token positions and interacting with the game board.

## Methods : 
-RandomPlayer(String name, boolean isAI)
Constructor to initialize the player with a name and AI status.

    String getName()
Returns the player's name.

    boolean isAI()
Returns whether the player is AI-controlled.

    boolean isNoTokenOut()
Checks if all tokens are still in the house.

    void takeTokenOut()
Moves the first available token from the house to the starting position.

    int getTokenPosition(int index)
Gets the position of a specific token by index.

    void updateTokenPosition(int index, int newPosition)
Updates the position of a specific token.

    boolean hasAllTokensFinished()
Checks if all tokens have reached the final position.
    
    void setHasFinished(boolean hasFinished)
Sets the player's hasFinished status.

    boolean hasFinished()
Returns whether the player has completed the game.

### Attributes :

-name (String):
The name of the player (e.g., "Player 1" or "AI Bot").

-isAI (boolean):
Indicates whether the player is controlled by AI.

-tokens (int[]):
An array representing the positions of the player's tokens on the board.
A value of -1 means the token is still in the "house" (not in play).
Other values indicate positions on the board.

-hasFinished (boolean):
Tracks whether the player has completed the game by moving all their tokens to the final position.

-TOKENS_PER_PLAYER (int - constant):
Defines the number of tokens each player has. Default is 4.

-BOARD_SIZE (int - constant):
Represents the size of the game board. Default is 100.

-miniGamePositions (List<Integer> - constant):
Specifies special positions on the board where mini-games are triggered.

## RandomPlayerManager class :
The RandomPlayerManager class manages a collection of RandomPlayer instances. It provides methods to add players, retrieve player information, display player details, and check the status of the game (e.g., whether all players have finished).
This class acts as a central hub for managing the list of players and their interactions during the game.

## Attributes
players (List<RandomPlayer>):
A list that stores all the RandomPlayer instances participating in the game.

## Methods

    RandomPlayerManager()
Constructor to initialize the players list.

    void addPlayer(String name, boolean isAI)
Adds a new player to the players list with the given name and AI status.

    RandomPlayer getPlayer(int index)
Retrieves a player by their index in the list. 

    List<RandomPlayer> getAllPlayers()
Returns a copy of the list of all players.

    void displayPlayers()
Prints the list of players to the console, showing their names and AI status.

    boolean allPlayersFinished()
Checks if all players in the game have finished (all tokens in the final position).
Returns true if all players are finished.

    RandomPlayer getHighestRankingPlayer()
Retrieves the first player who has finished the game, indicating the highest rank.

    void movePlayerToken(int playerIndex, int tokenIndex, int diceRoll)
Moves the token of a specified player by updating its position based on a dice roll.
Prints the action to the console, showing the movement of the token.

### 'Game' class

The 'Game' class contains all the instructions to perform a match in LudoKing.

#### Attributes
- _cells_: A constant defining the number of cells on the game board (64).

- _**playerToPlacement**__: A map that associates each player with their final placement in the game.

- _**placements:**_ A list of possible placements (first, second, third, fourth) used to assign rankings as players finish.

- _**playerToColor:**_ A map linking each player to their assigned color.

- _**gameToPosition:** _A map associating mini-games with specific positions on the game board.

- _**players:**_ A list that holds all the players participating in the game.

#### Methods
- _**getCells():**_ Returns the total number of cells on the game board.

- _**ludoKing():**_ The main method that initializes the game, sets up players, assigns colors, places mini-games on the board, and runs the game rounds until a winner is determined.

- _**gameFinished(List<Player> players):**_ Checks if all players have finished the game, signaling the end of the game.

- _**checkWinner():**_ Determines the winner by comparing the points of all players.

- _**miniGame(Player p):**_ Manages the mini-game interaction for a player's token, checking if any token lands on a mini-game position and handling the outcome.

- _**checkMiniGame(Token t, Map<Token, Integer> tToP, Player p):**_ Checks if a token lands on a mini-game position and executes the mini-game if applicable, moving the token based on the game's outcome.

- _**displayMenu():**_ Displays the action menu to the player during their turn.

- _**menu(Player p):**_ Manages the player's turn, allowing them to choose an action from the menu, such as moving a token, checking token positions, viewing points history, or seeing the ranking chart.

- _**showPlayersTokenPositionMap():**_ Displays the current positions of all players' tokens on the game board.

- _**showHistoryPoints(Player p):**_ Displays the points history for a specific player, if available.

- _**rankingList():**_ Displays the current ranking of players based on their points.

- _**checkFinish(Player p):**_ Checks if a player has finished the game by bringing all their tokens home and updates their placement and points.

- _**calculatePointsForPlacement():**_ Calculates the points awarded to a player based on their final placement.

- _**checkForEats(Player p, List<Player> players):**_ Checks if a player's token has "eaten" another player's token, which involves sending the other player's token back to start and deducting points.

- _**eat(Player eater, Player eaten, Token eatenToken):**_ Handles the consequences of one player's token eating another's, including point deduction and resetting the eaten token's position.

### 'Player' class

The Player class represents a player in the Ludo King game, managing their tokens, perks, and actions during the game. 

#### Attributes
- _**name:**_ The player's name.

- _**tokens:**_ A list of the player's tokens, each represented by the Token class.

- _**tokensOut:**_ A list of tokens that have been taken out of the starting area.

- _**tokenToPosition:**_ A map that associates each token with its position on the board (0-63).

- _**tokenToPositionOnMap:**_ A map that tracks the position of each token on the actual game map, considering that tokens do not all start at the same point.

**color:** The player's assigned color.

**hasFinished:** A boolean indicating whether the player has finished the game.

**inHome:** A counter for how many tokens have reached the home position.

**points:** A Points object tracking the player's points.

**isTurn:** A boolean indicating if it's the player's turn.

**noTokenOut:** A boolean that is true if the player has no tokens out of the starting area.

**roll:** A boolean that indicates if the player can roll the dice.

**perkUtil:** A QuizPerkUtil object to manage perks like double rolls.

**startingPos:** A list of positions where the player's tokens can start on the map.

#### Methods
**Player(String name, Color color, int inHome):** Constructor that initializes a new player with the specified name, color, and the number of tokens in the home.

**useBoostRoll() / useDoubleRoll() / useDecideDoubleRoll():** Methods that check if the player can use specific perks during their turn.

**moveToken():** Handles the logic for moving a token during the player's turn. It includes dice rolling and applying any active perks like double rolls or boosting the roll value.

**takeTokenOut():** Allows the player to take a token out of the starting area when a roll of 6 is achieved.

**chooseToken():** Prompts the player to choose which token to move, validating their choice.

**isValidMove(int diceRoll):** Checks if the player can make a valid move based on the dice roll.

**updateTokenPosition(int toUpdate, int rollResult):** Updates the position of a specified token based on the result of a dice roll.

**displayChoices():** Displays the player's options during their turn (moving a token or taking one out).

**startTurn() / endTurn():** Methods that handle the start and end of the player's turn, setting the appropriate state flags.

**reset(Token token):** Resets a token to its starting position.

### 'Token' class

The Token class represents an individual token in the Ludo King game.

#### Attributes
**id:** An integer representing the token's unique identifier, which can be 1, 2, 3, or 4. This helps distinguish between the player's different tokens.

**position:** An integer indicating the token's position on the board. It can range from 0 to the maximum number of cells (63) on the board. A null value indicates the token is not yet on the board (i.e., it hasn't been "taken out").

**positionOnMap:** An integer representing the token's actual position on the map. This differs from position because tokens may start from different points on the board depending on their color.

**isHome:** A boolean flag that indicates whether the token has reached the home area. If true, the token has completed its journey around the board.

**startingPos:** An integer indicating the token's starting position on the map. This is assigned when the token is taken out and helps track its movement.

**color:** The Color object associated with the token, representing its color (e.g., red, blue).

#### Methods
**Token(int id, Integer position, Integer positionOnMap):** Constructor that initializes a token with a given id, position, and positionOnMap. By default, the token is not in the home (isHome is false), and the starting position is set to null.

**Integer getStartingPos() / setStartingPos(Integer startingPos):** Getter and setter for the token's starting position on the map.

**Integer getPositionOnMap() / setPositionOnMap(Integer positionOnMap):** Getter and setter for the token's current position on the map.

**int getId() / setId(int id):** Getter and setter for the token's ID.

**Integer getPosition() / setPosition(Integer position):** Getter and setter for the token's position on the board.

**Color getColor() / setColor(Color color):** Getter and setter for the token's color.

**boolean isHome() / setHome(boolean home):** Getter and setter for the isHome flag. This indicates whether the token has successfully reached the home area.

### 'Points' class

The 'Points' class is designed to manage and track the points a player earns or loses during the game. It keeps a running total of the player's points and maintains a history of all point changes 

#### Attributes

**points:** An integer representing the current total points the player has accumulated. It is updated whenever the player gains or loses points.

**pointsHistory:** A List<String> that keeps a record of all point transactions (gains and losses). Each entry in this list is a string describing a specific event where points were either won or lost.

#### Methods

**addPoints(int points):** This method adds the specified number of points to the player’s total and logs the event in the pointsHistory list with a "Won" prefix.

**losePoints(int points):** This method subtracts the specified number of points from the player’s total and logs the event in the pointsHistory list with a "Lost" prefix.

**getPoints() / setPoints(int points):** Getter and setter for the points attribute, allowing retrieval and direct modification of the current point total.

**getPointsHistory():** Returns the list of all point transactions (both gains and losses).

**displayHistory():** Prints the entire history of point transactions to the console. If no points have been gained or lost, it informs the user that no points have been gained yet.

### 'Quiz1' to 'Quiz10' classes

#### Attributes
sc (Scanner): A Scanner object used to take input from the player. This attribute facilitates reading the player's answers during the quiz.

#### Methods

**play(Player p):** This is the main method that runs the quiz. It presents a series of questions to the player, records their answers, and evaluates their performance. The method returns a boolean indicating whether the player won the mini-game.

The method covers questions across multiple categories such as Math, Geography, History, Science, Informatics, and Sports.
It counts the number of correct answers and passes this count to the evaluateQuiz method.

**askQuestion(String questionCategory, String question, String... correctAnswers)**: This private method is used to ask each question. It prints the question category and the question itself, then reads the player's input. The method checks if the player's answer matches any of the correct answers provided. If the player's answer is correct, it returns 1; otherwise, it returns 0.

**questionCategory:** A string describing the category of the question (e.g., "Math question:").

**question:** The actual question posed to the player.

**correctAnswers**: A variable-length argument list of possible correct answers for the question. The method checks if the player's answer matches any of these.

**evaluateQuiz(Player p, int correctQuestions):** This private method evaluates the player's performance based on the number of correct answers. If the player answers all questions correctly, they win the mini-game, receive points, and potentially gain a perk. If they don't answer all questions correctly, they lose the mini-game.

If the player **wins:**
They receive 50 points via the QuizReturnPoints.returnPoints(50, p) method.
They may also receive a "Double Roll" perk unless they already have it.
If the player **loses:**
The perk "Double Roll" is removed if the player had it.
The player's correct answer count is displayed along with a message that they lost the mini-game.

### 'GuessTheWord' Class
The GuessTheWord class is a mini-game where players attempt to guess a randomly generated secret code consisting of a sequence of four characters (from a set of six possible letters). 

#### Methods:
- _returnPoints(Player player):_ adds 100 points to the player's total points and prints a message indicating the points awarded.

- _play(Player pp):_ this is the main method that runs the game. It handles the entire game logic, including:
   1. Generating a random secret code composed of four characters.
   2. Allowing the player a limited number of attempts (20 by default) to guess the code.
   3. Providing feedback after each guess, indicating which characters are correctly positioned (X) and which are correct but in the wrong position (-).
   4. Offering options such as:
        - Help: Displaying the game rules and commands.
        - Buy: Allowing the player to buy a letter of the code at its correct position by sacrificing five attempts.
        - History: Displaying the history of guesses and their evaluations.

### 'TicTacToe' class
**Player Move**: The player is prompted to enter their move (row and column). The move is validated to ensure it is within bounds and not on an already occupied space.
**Computer Move:** The computer randomly selects an available position on the board.
**Game Check:** After each move, the game checks if there is a winner or if the board is full.

#### Methods
- _returnPoints(Player player):_ this method awards the player 40 points if they win the game and informs them of the points they earned.

- _play(Player p):_ this is the main method that runs the Tic-Tac-Toe game. It handles the game loop where the player and the computer take turns making moves until either one wins or the board is full (resulting in a tie).
  
- _printBoard(char[][] gameBoard):_ prints the current state of the game board to the console.
  
- _playerMove(char[][] gameBoard, Scanner sc, char player, Player p):_ handles the player's move, including input validation to ensure the move is valid.

- _computerMove(char[][] gameBoard, char computer):_ randomly selects a move for the computer.

- _isGameFinished(char[][] gameBoard, char player, Player playerObj):_ checks if the game is finished by either a win or a tie. If the player wins, they receive points and potentially a "Double Roll" perk. The game loop ends if this method returns true.

- _isBoardFull(char[][] gameBoard):_ checks if the game board is full, which would result in a tie.

- _hasContestantWon(char[][] gameBoard, char player):_ checks all possible winning conditions (rows, columns, diagonals) for the specified player.

### 'Dice' class
The 'Dice' class provides functionality to simulate rolling a dice.

#### Methods
- _roll():_ Simulates a standard six-sided dice roll, returning a random integer between 1 and 6.
  
- _customRoll(int min, int max):_ Allows for a dice roll with a customizable range, returning a random integer between min and max.

### 'TestIfQuizWorks' class
The 'TestIfQuizWorks' is a class to test if all the quizzes work.
  
### 'QuizReturnPoints' Class 
The QuizReturnPoints class is a utility class used in the context of the Quiz1 mini-game to manage the distribution of points to a player after they participate in the quiz.

#### Methods
- _returnPoints(int points, Player player):_ This is a static method that adds a specified number of points to the player's total score. It directly interacts with the player's Points object, which manages the player's points and history of point changes.

- _points_: The number of points to be added to the player's score.

- _player:_ The player object to which the points are being awarded. This object contains the player's Points attribute, which the method uses to update the score.

### 'BoostRoll' class
This class represents a perk that allows a player to boost their dice roll by doubling it.

#### Method:
- _rollAndBoost()_: This method rolls a dice using the Dice.roll() method and then doubles the result. It prints the original roll and the boosted roll to the console and returns the boosted value.
  
### 'DoubleRoll' class
This class represents a perk where a player gets to roll the dice twice, and the higher of the two rolls is used.

#### Method:
- _useDoubleRoll():_ This method rolls the dice twice using the Dice.roll() method and then compares the two results. It prints both rolls and the higher of the two, which is returned as the result.

### 'DecideDoubleRoll' class
This class represents a perk where a player rolls the dice twice and gets to choose which of the two rolls they want to use.

#### Method:
- chooseRoll(): This method rolls the dice twice using the Dice.roll() method. It then asks the player to choose which roll to use by entering 1 for the first roll or 2 for the second roll. The method validates the player's input, ensuring it’s either 1 or 2, and then returns the chosen roll.


### 'QuizPerkUtil' class
The QuizPerkUtil class is a utility class designed to manage various perks (special abilities or bonuses) that a player can obtain during the game. 

#### Attributes
- _perkDoubleRoll (boolean):_ Tracks whether the player has the "Double Roll" perk. When true, the player is allowed to roll the dice twice in one turn.

- _perkBoostRoll (boolean):_ Indicates if the player has the "Boost Roll" perk, which likely enhances the outcome of the player's dice roll.

- _perkDecideDoubleRoll (boolean):_ Manages the "Decide Double Roll" perk, which may allow the player to decide if they want to use their double roll or not in a given situation.

- _perkExtraTurn (boolean):_ Indicates whether the player has the "Extra Turn" perk, allowing the player to take an additional turn.

#### Methods
- _hasPerkDoubleRoll():_ Returns true if the player currently has the "Double Roll" perk, otherwise false.

- _hasPerkBoostRoll():_ Returns true if the player currently has the "Boost Roll" perk, otherwise false.

- _hasPerkDecideDoubleRoll():_ Returns true if the player currently has the "Decide Double Roll" perk, otherwise false.

- _hasPerkExtraTurn():_ Returns true if the player currently has the "Extra Turn" perk, otherwise false.

- _setPerkDoubleRoll(boolean status):_ Sets the status of the "Double Roll" perk. Pass true to activate the perk or false to deactivate it.

- _setPerkBoostRoll(boolean status): _Sets the status of the "Boost Roll" perk. Pass true to activate the perk or false to deactivate it.

- _setPerkDecideDoubleRoll(boolean status):_ Sets the status of the "Decide Double Roll" perk. Pass true to activate the perk or false to deactivate it.

- _setPerkExtraTurn(boolean status):_ Sets the status of the "Extra Turn" perk. Pass true to activate the perk or false to deactivate it.

### 'RandomGame' class

The RandomGame class  manages the methods  and attributes needed to play a game of 1 real player and three AI players which take decisions randomly 

#### Attributes:
- *cells*: A static constant representing the total number of cells on the board (64).
  
- *rand*: A Random object used for generating random numbers.
  
- *sc*: A Scanner object for taking user input.
  
- *playerToPlacement*: A map that associates each Player object with their Placement in the game.
  
- *placements*: A list of possible placements (FIRST, SECOND, etc.) that players can achieve.
  
- *playerToColor*: A map that assigns a Color to each Player.
  
- *gameToPosition*: A map that assigns positions on the board to specific mini-games.
  
- *players*: A list of all Player objects participating in the game.

#### Methods
- *startGame()*: Initializes and starts the game, setting up players, assigning colors, and placing mini-games on the board. It controls the flow of the game until a winner is declared.
  
- *playerTurn(Player player)*: Handles the turn of a human player, including dice rolls, token movements, and mini-game participation.
  
- *aiTurn(Player player)*: Handles the turn of an AI player, with similar logic to playerTurn() but automated.
  
- *hasTokensInHouse(Player player)*: Checks if the player has any tokens that have not yet left the starting area ("house").
  
- *takeTokenOut(Player player)*: Moves a token out of the starting area if a 6 is rolled on the dice.
  
- *moveToken(Player player, int diceRoll)*: Moves a token forward based on the dice roll.
  
- *calculateNewPosition(Integer currentPosition, int diceRoll)*: Calculates the new position of a token on the board based on its current position and the dice roll.
  
- *gameFinished(List<Player> players)*: Checks if all players have finished the game.
  
- *rankingList()*: Displays the final ranking of players based on their placements.
  
- *checkFinish(Player player)*: Checks if a player has moved all their tokens to the final position and updates their status accordingly.
  
- *checkForEats(Player currentPlayer, List<Player> allPlayers)*: Checks if the current player's token has landed on another player's token, sending the other token back to the start.
  
- *miniGame(Player player)*: Determines if a player has landed on a mini-game square and initiates the mini-game.

- *checkMiniGame(Token token, Map<Token, Integer> tToP, Player player)*: Analyzes if a token has landed on a mini-game square and adjusts the token's position based on the result of the mini-game.


#### Overridden Methods:

1. *chooseToken()*:
   - *Purpose*: Selects a token randomly from the list of tokens that are not in the home position.
   - *Implementation*: 
     - Creates a list of tokens that are not in the home.
     - Randomly selects one of these available tokens.
     - Returns the id of the selected token.

2. *takeTokenOut()*:
   - *Purpose*: Randomly selects a token that is still in the home position and places it on the board.
   - *Implementation*:
     - Creates a list of tokens that are still in the home (i.e., have a null position).
     - Randomly selects one token from this list.
     - Sets the selected token's position to 0, indicating it has been moved out of the home.
     - Removes the first position from the startingPos list and assigns it to the token as its starting position.
     - Prints a message indicating which token has been taken out.

### Enum 
#### Color
This enum represents the colors available for players in the game. Each player can choose one of these colors.
#### Placement
This enum represents the possible placements a player can achieve in the game, such as finishing in the first, second, third, or fourth position.

### Interface
#### MiniGameInterface
The MiniGameInterface defines a contract for all mini-games. Any class that implements this interface must provide an implementation for the play method.
#### Method
- _boolean play(Player p):_ This method is expected to be implemented by any class that represents a mini-game. The method should handle the logic of playing the mini-game, and return true if the player wins, and false otherwise.

### Abstract class
#### MiniGame
- _Purpose:_ The MiniGame class is an abstract class that implements the MiniGameInterface.

- _Abstract Nature:_ Since it's an abstract class, it cannot be instantiated on its own. It provides a base class for all mini-games, ensuring they implement the play method.

- _Inheritance:_ Any specific mini-game, like TicTacToe or GuessTheWord, would extend MiniGame. By doing so, they inherit from MiniGame and are required to implement the play method due to the MiniGameInterface.

- _Additional Functionality:_ This class can be expanded in the future to include common methods, fields, or utilities that would be useful across all mini-games.

### Human Experience

- Matteo worked on player logic, game logic, token logic, and the four-player version of the game.
- Francesco worked on the GUI, the project blueprint, and syntax corrections.
- Arlind worked on the bots and the multiplayer version of the game.
- Andrea worked on quizzes, minigames, and handling perks.

### GIT Workflow

Each team member had their own branch where they saved their progress. To share progress between branches, we used 'pull requests'.

### Challenges

- Organization.
- Having a clear idea of how the project would be structured.
