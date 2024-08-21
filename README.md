# Project Name: Ludoking Board Game

## Team Members

- **Andrea Zicarelli** - [@Z1CC4](https://github.com/Z1CC4)
- **Arlind Lacej** - [@Arlind393](https://github.com/Arlind393)
- **Francesco Masala** - [@francescomasala](https://github.com/francescomasala)
- **Matteo Hu** - [@matteo4k](https://github.com/matteo4k)

## Project Description

This project is a recreation of the famous board game "Ludo King" (in Italian: "Non t'arrabbiare"), with additional interesting features.

## Building and Running the Project

(Instructions on building and running the project should go here.)

## User Guide

Each player has 4 tokens, and the goal is to get all of them to the home. To move a token out of the base, the player must roll a six on the die. If there is already at least one token outside the base and a player rolls a six, they can choose either to move a token or to bring out another token.

During the game, players might encounter tiles with a minigame. To gain points and perks, the player must win the minigame. If the player wins, they earn points and a perk. However, if the player already has a specific perk, they cannot earn the same one again. Additionally, if the player wins, their token moves one position ahead. Conversely, if they lose, their token moves one position backward. In both cases, it is possible to encounter another minigame.

As in the original game, the "eat" mechanism is implemented. This mechanism is based on the position on the board (when a token is taken out of the base, it is placed in a random position on the board). The player whose token is eaten loses points, which are gained by the player who performed the "eat." In our version of the game, the winner is not the player who gets all their tokens home first, but the one who accumulates the most points.

### Implementation

### 'Game' class

The 'Game' class contains all the instructions to perform a match in LudoKing.

#### Attributes
**cells**: A constant defining the number of cells on the game board (64).

**playerToPlacement**: A map that associates each player with their final placement in the game.

**placements:** A list of possible placements (first, second, third, fourth) used to assign rankings as players finish.

**playerToColor:** A map linking each player to their assigned color.

**gameToPosition:** A map associating mini-games with specific positions on the game board.

**players:** A list that holds all the players participating in the game.

#### Methods
**getCells():** Returns the total number of cells on the game board.

**ludoKing():** The main method that initializes the game, sets up players, assigns colors, places mini-games on the board, and runs the game rounds until a winner is determined.

**gameFinished(List<Player> players):** Checks if all players have finished the game, signaling the end of the game.

**checkWinner():** Determines the winner by comparing the points of all players.

**miniGame(Player p):** Manages the mini-game interaction for a player's token, checking if any token lands on a mini-game position and handling the outcome.

**checkMiniGame(Token t, Map<Token, Integer> tToP, Player p):** Checks if a token lands on a mini-game position and executes the mini-game if applicable, moving the token based on the game's outcome.

**displayMenu():** Displays the action menu to the player during their turn.

**menu(Player p):** Manages the player's turn, allowing them to choose an action from the menu, such as moving a token, checking token positions, viewing points history, or seeing the ranking chart.

**showPlayersTokenPositionMap():** Displays the current positions of all players' tokens on the game board.

**showHistoryPoints(Player p):** Displays the points history for a specific player, if available.

**rankingList():** Displays the current ranking of players based on their points.

**checkFinish(Player p):** Checks if a player has finished the game by bringing all their tokens home and updates their placement and points.

**calculatePointsForPlacement():** Calculates the points awarded to a player based on their final placement.

**checkForEats(Player p, List<Player> players):** Checks if a player's token has "eaten" another player's token, which involves sending the other player's token back to start and deducting points.

**eat(Player eater, Player eaten, Token eatenToken):** Handles the consequences of one player's token eating another's, including point deduction and resetting the eaten token's position.

### 'Player' class

The Player class represents a player in the Ludo King game, managing their tokens, perks, and actions during the game. 

#### Attributes
**name:** The player's name.

**tokens:** A list of the player's tokens, each represented by the Token class.

**tokensOut:** A list of tokens that have been taken out of the starting area.

**tokenToPosition:** A map that associates each token with its position on the board (0-63).

**tokenToPositionOnMap:** A map that tracks the position of each token on the actual game map, considering that tokens do not all start at the same point.

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
