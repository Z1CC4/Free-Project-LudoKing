package it.unibz.pp2024.LudoKing.GameLogic.Games.MiniGames;

import it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.MiniGame;
import it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil;
import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Random;
import java.util.Scanner;

public class GuessTheWord extends MiniGame {

    public static void returnPoints(Player player) {
        int pointsToAdd = 100;
        System.out.println("You obtained: " + pointsToAdd + " points.");
        player.getPoints().addPoints(pointsToAdd);
    }

    @Override
    public boolean play(Player pp) {
        int points = 0;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
        int attempts = 20, attemptsMax = attempts, nGuesses = attempts;
        String[] evaluation = new String[nGuesses];
        int lengthCode = 4;
        String eval = "";
        String choice = "";
        char ps;
        int randomValue = rand.nextInt(5, 9);
        String EmptyEval = "No matching characters";
        String[] guesses = new String[nGuesses];
        char[] buyChar = new char[lengthCode];
        int[] rL = new int[lengthCode];
        String buyL = "";
        char c;
        int buy;
        char[] alrCheck = new char[lengthCode];
        char[] X = new char[lengthCode];
        char[] letters = new char[lengthCode];
        int[] nLetters = new int[lengthCode];
        System.out.println("Guess the word mini game. Type 'help' for the rules");

        String secretCode = "";

        for (int i = 0; i < lengthCode; i++) {
            secretCode += characters[rand.nextInt(characters.length)];
        }

        String save = secretCode;
        for (int i = 0; i < buyChar.length; i++) {
            buyChar[i] = '.';
        }

        for (int i = 0; i < rL.length; i++) {
            rL[i] = randomValue;
        }

        String secretCode2 = secretCode;

        Player playerObj = new Player("PlayerName", it.unibz.pp2024.LudoKing.Utils.Color.RED, 4);

        for (int i = 0; i < attemptsMax; i++) {
            if (attempts == 0) {
                System.out.println("The number of attempts are finished. You obtained 0 points.");
                return false;
            }

            for (int po = 0; po < lengthCode; po++) {
                letters[po] = ' ';
                nLetters[po] = 0;
            }

            boolean b = false;

            for (int ii = 0; ii < letters.length; ii++) {
                for (int j = 0; j < letters.length; j++) {
                    b = false;
                    if (secretCode2.charAt(ii) == letters[j]) {
                        for (int jk = 0; jk < letters.length; jk++) {
                            if (secretCode2.charAt(ii) == letters[jk]) {
                                nLetters[jk]++;
                                break;
                            }
                        }
                        break;
                    } else {
                        b = true;
                    }
                }
                if (b) {
                    letters[ii] = secretCode2.charAt(ii);
                    nLetters[ii]++;
                }
            }

            System.out.print(attempts + ">");
            choice = sc.nextLine();
            for (int j = 0; j < alrCheck.length; j++) {
                alrCheck[j] = ' ';
            }
            for (int j = 0; j < X.length; j++) {
                X[j] = ' ';
            }

            boolean s = true;

            buyL = "";

            if (choice.equalsIgnoreCase("buy")) {
                if (attempts < 5) {
                    System.out.println("You do not have enough attempts to buy a letter.");
                    i--;
                    continue;
                }
                String choice2;
                if (attempts == 5) {
                    System.out.println("Are you sure to buy a letter?");
                    System.out.println("The game will end, since you will finish all your attempts");
                    System.out.println("Yes or No?");
                    choice2 = sc.next();

                    yes:
                    if (choice2.equalsIgnoreCase("yes")) {
                        return false;
                    }
                    if (choice2.equalsIgnoreCase("no")) {
                        i--;
                        continue;
                    }

                }
                while (s) {
                    control:
                    while (s) {
                        buy = rand.nextInt(lengthCode);
                        for (int p = 0; p < rL.length; p++) {
                            if (buy == rL[p]) {
                                break control;
                            }
                        }
                        rL[buy] = buy;

                        for (int t = 0; t < rL.length; t++) {
                            if (rL[t] == randomValue) {
                                continue;
                            } else
                                buyChar[t] = secretCode.charAt(rL[t]);


                        }
                        for (int t = 0; t < buyChar.length; t++) {
                            buyL += buyChar[t];
                        }
                        System.out.println("Your cart-->" + buyL);
                        s = false;
                    }
                }

                attempts -= 5;
                i += 4;
                continue;
            }

            int counter = 0;

            if (choice.equalsIgnoreCase("h")) {
                System.out.println("History of guesses");
                for (int h = 0, j = 20; h < guesses.length; h++, j--) {
                    if (guesses[h] == null) {
                        break;
                    } else
                        System.out.print(j + ">" + guesses[h] + " ");
                    System.out.println(evaluation[h]);
                }
                i--;
                continue;
            }

            if (choice.equalsIgnoreCase("help")) {
                System.out.println();
                System.out.println("-Your goal is to guess the secret code which has 4 characters.");
                System.out.println(
                        "-The secret code is composed of the first 6 letters of the alphabet. Repetitions are allowed.");
                System.out.println("-You have to guess the secret code before the number of attempts becomes 0.");
                System.out.println(
                        "-If your choice has a different length than 4, then it will appear an error message on the screen.");
                System.out.println(
                        "-In case you enter something wrong, the number of attempts will not be decremented. So, don't worry.");
                System.out.println("-Key for the evaluation: ");
                System.out.println("X --> a character of your guess is in the right position ");
                System.out.println("- --> a character of your guess is in the wrong position");
                System.out.println("Example:");
                System.out.println("Evaluation for attempt n:18--> XX--");
                System.out.println(
                        "In this case, the evaluation shows that in your guess there are two characters at the correct position and other two characters at the wrong position.");
                System.out.println("-These are the commands that you can enter:");
                System.out.println("'help': Display a help screen explaining the game rules and game commands");
                System.out.println(
                        "'buy': Buy one letter of the secret code at its right position (decreases attempts by 5!)");
                System.out.println("'h': Show history of all guesses and evaluations");
                System.out.println();
                i--;
                continue;
            }

            if (choice.length() > lengthCode) {
                System.out.println(
                        "Your choice is longer than the length of the secret code. Enter a choice with the same length of the secret code.");
                attemptsMax++;
                i--;
                continue;
            }
            if (choice.length() < lengthCode) {
                System.out.println(
                        "Your choice is shorter than the length of the secret code. Enter a choice with the same length of the secret code.");
                attemptsMax++;
                i--;
                continue;
            }

            guesses[i] = choice;

            int d;

            eval = " ";
            for (int e = 0; e < secretCode.length(); e++) {
                d = 0;
                for (d = 0; d < secretCode.length(); d++) {
                    c = secretCode.charAt(d);
                    if (choice.charAt(e) == c) {
                        if (d == e) {
                            eval += "X";
                            X[e] = c;
                            break;
                        }
                    }
                }

            }

            boolean q;

            for (int e = 0; e < secretCode.length(); e++) {
                char c1 = choice.charAt(e);
                char c2 = secretCode.charAt(e);
                if (c1 == c2) {
                } else {
                    finish:
                    for (int jk = 0; jk < choice.length(); jk++) {
                        char c3 = secretCode.charAt(jk);
                        if (X[jk] == c1) {
                            continue;
                        }

                        out:
                        if (c1 == c3) {
                            for (int kk = 0; kk < alrCheck.length; kk++) {
                                if (c1 == alrCheck[kk]) {
                                    break out;
                                }
                            }

                            q = false;

                            for (int hj = 0; hj < nLetters.length; hj++) {
                                if (c1 == letters[hj]) {
                                    nLetters[hj]--;
                                    q = true;
                                }
                                if (nLetters[hj] == 0
                                        && letters[hj] == ' ') {
                                    continue;
                                }
                                if (nLetters[hj] == 0) {
                                    alrCheck[e] = c1;
                                }
                                if (q) {
                                    eval += "-";
                                    break finish;
                                }
                            }
                        }
                    }
                }
            }
            if (eval.isBlank()) {
                System.out.println("For the attempt n:" + attempts + " there are no matching characters.");
                evaluation[i] = EmptyEval;
                attempts--;
                continue;
            }

            evaluation[i] = eval;
            System.out.println("Evaluation for attempt n:" + attempts + "-->" + evaluation[i]);

            if (choice.equalsIgnoreCase(secretCode)) {
                System.out.println();
                System.out.println("Congratulations. You have guessed the secret code.");
                returnPoints(pp);
                if(playerObj.getPerkUtil().hasPerkDoubleRoll()){
                    System.out.println("You already have a 'Double Roll' perk. No perk will be assigned.");
                }else{
                    System.out.println("You obtained a 'Double Roll' perk");
                    playerObj.getPerkUtil().setPerkDoubleRoll(true);
                }
                return true;
            }

            attempts--;
        }

        return false;
    }
}