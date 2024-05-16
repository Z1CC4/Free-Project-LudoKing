package it.unibz.pp2024.LudoKing.MiniGames;
import java.util.Random;
import java.util.Scanner;
public class GuessTheWord {
        public static void main(String[] args) {
            // TODO Auto-generated method stub
            Game();
        }

        public static void Game() {
            Random rand = new Random();
            Scanner sc = new Scanner(System.in);
            char[] characters = { 'a', 'b', 'c', 'd', 'e', 'f' };
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
            System.out.println("Hi. Welcome to the Guess Game." + " Try to guess the secret Code.");

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

            for (int i = 0; i < attemptsMax; i++) {

                for (int po = 0; po < lengthCode; po++) {// loop to clean the arrays 'letters' and 'nLetters'
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
                    if (b == true) {
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

                        yes: if (choice2.toLowerCase().equalsIgnoreCase("yes")) {
                            break yes;
                        }
                        if (choice2.toLowerCase().equalsIgnoreCase("no")) {
                            i--;
                            continue;
                        }

                    }
                    while (s == true) {
                        control: while (s == true) {
                            buy = rand.nextInt(lengthCode);
                            for (int p = 0; p < rL.length; p++) {
                                /*
                                 * for loop to check if the random letter is already inside of the array "rL".
                                 * The control is done by checking their positions. Notice that inside of the
                                 * array "rL" there are contained at the beginning only numbers of the same
                                 * value. The random value is just a random number generated with Random. The
                                 * most important thing is that the numbers contained inside of the array are
                                 * not smaller than 3-->length of the secretCode is 4 and the positions starts
                                 * from 0. Since when an array of int is created there will be contained only
                                 * zeros, this is done in order to avoid the comparisons between zeros(zeros of
                                 * the array "rL" and zero of the position of the secretCode.
                                 */
                                if (buy == rL[p]) {
                                    /*
                                     * whenever the position of the random letter and an element of rL are the
                                     * same(which means that the random letter was already picked previously,
                                     * because inside of the array "rL" there are contained positions of letters
                                     * that were already bought) on the label "control" it will be executed the
                                     * command break. This will be computed, because if the random letter was
                                     * already picked, then it means that it does not make sense to continue the
                                     * control. So because of this, by breaking the label "control", the while loop
                                     * will restart again, and consequently, the position of a new random letter
                                     * will be picked.
                                     */
                                    break control;
                                }
                            }
                            rL[buy] = buy;

                            for (int t = 0; t < rL.length; t++) {
                                /*
                                 * for loop to insert inside of the array "buyChar" characters of the secretCode
                                 * that were not picked yet.
                                 */
                                if (rL[t] == randomValue) {
                                    continue;
                                } else
                                    buyChar[t] = secretCode.charAt(rL[t]);
                                // since inside of rL there are the right position(without repetitions) of each
                                // character of the secret code,
                                // there will be stored inside of buyLetters the character of the secret code in
                                // the right position

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

                if (choice.equalsIgnoreCase("p")) {
                    secretCode = "";
                    boolean preSet;
                    preSet=false;
                    System.out.println("Enter the secret code that you want to preset(you have to enter 4 chars)");
                    prest2: for (int jp = 0; jp < lengthCode; jp++) {
                        /*
                         * for loop used to check if the character entered from the user is a valid one,
                         * or if it does not respect the rules. For rules it is meant that the
                         * secretCode must contain only letter from 'a' to 'f'. For this reason, if an
                         * incorrect character will be entered, then there will be printed on the
                         * console that the character entered by the user is not valid.
                         */
                        System.out.println("Enter the char n:" + (jp + 1));
                        ps = sc.nextLine().charAt(0);
                        counter = 0;
                        prest: for (int qp = 0; qp < characters.length; qp++) {
                            if (ps == characters[qp]) {
                                /*
                                 * if the character entered is a equal to at least one of ones used to compose
                                 * the secretCode, then the loop will be interrupted. By executing the command
                                 * break on the label "prest", the control will be stopped, and subsequently it
                                 * will return to the first loop to ask the next character.
                                 */
                                secretCode += ps;
                                secretCode2 = secretCode;
                                preSet=true;
                                break prest;
                            } else if (ps != characters[qp]) {
                                counter++;
                            }

                            /*
                             * the counter is used to count for how many times does the character entered by
                             * the user not match the characters that compose the secretCode(from 'a' to
                             * 'f'). If the counter is equal to 6(which means that the character entered by
                             * the user is not valid), then an error message will be printed on the console.
                             */
                            if (counter == 6) {
                                System.out.println("Not valid. Enter something valid");
                                secretCode = save;
                                secretCode2 = save;
                                preSet=false;
                                break prest2; /*
                                 * by breaking the label "prest2", the command "p" will be interrupted, and
                                 * therefore, the user will return to the main screen.
                                 */
                            }

                        }
                    }
                    if(preSet==true) {
                        System.out.println("Successful preset of the new secret code.");
                    }
                    i--;
                    continue;
                }

                if (choice.equalsIgnoreCase("new")) {
                    System.out.println("You have restarted the game");
                    System.out.println();
                    Game(); // recursive method to start a new game
                    break;
                }

                if (choice.equalsIgnoreCase("h")) {
                    System.out.println("History of guesses");
                    for (int h = 0, j=20; h < guesses.length; h++, j--) {
                        if (guesses[h] == null) {
                            break;
                        } else
                            System.out.print(j + ">" + guesses[h] + " ");
                        System.out.println(evaluation[h]);
                    }
                    i--;
                    ;
                    continue;
                }

                if (choice.equalsIgnoreCase("help")) {
                    System.out.println();
                    System.out.println("-Your goal is to guess the secret code which has 4 character.");
                    System.out.println(
                            "-The secret code is composed by the first 6 letters of the alphabet. Repetitions are allowed.");
                    System.out.println("-You have to guess the secret code before the number of attempts becomes 0.");
                    System.out.println(
                            "-If your choice has different length than 4, then it will appear an error message on the screen.");
                    System.out.println(
                            "-In case you enter something wrong, the number of attempts will not be decremented. So, don't worry.");
                    System.out.println("-Key for the evaluation: ");
                    System.out.println("X -->a character of your guess is in the right position ");
                    System.out.println("- -->a character of your guess is in the wrong position");
                    System.out.println("Example:");
                    System.out.println("Evaluation for attempt n:18--> XX--");
                    System.out.println(
                            "In this case the evaluation shows that in your guess there are two characters at the correct position and other two characters at the wrong position.");
                    System.out.println("-This are the commands that you can enter:");
                    System.out.println("'new':Start over a new game ");
                    System.out.println("'help': Display a help screen explaining the game rules and game commands");
                    System.out.println(
                            "'buy': Buyone letter of the secret code at its right position (decreases attempts by 5!)");
                    System.out.println("'h': Show history of all guesses and evaluations");
                    System.out.println("'r': Reveal the ‘secret code’ and continue.");
                    System.out.println("'p':Preset the ‘secret code’ with the next input ");
                    System.out.println("'quit': Reveal the solution and quit the game.");
                    System.out.println();
                    i--;
                    continue;
                }

                if (choice.equalsIgnoreCase("quit")) {
                    System.out.println("The secret code is: " + secretCode);
                    System.out.println("Quitting from the game");
                    for (int a = 0; a < 3; a++) {
                        try {
                            Thread.sleep(1000);
                            System.out.print(". ");
                        } catch (Exception e) {
                        }
                    }
                    System.out.println();
                    System.out.println("Exit completed.");
                    System.exit(1);

                }

                if (choice.equalsIgnoreCase("r")) {
                    System.out.println("The secret code is: " + secretCode);
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
                        if (choice.charAt(e) == c) { // if the character of the choice of the user and the character of the
                            // secretCode are the same
                            if (d == e) { // if they are at the same position
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
                    if (c1 == c2) { // if they are matching letters, then nothing happens
                    } else {
                        finish: for (int jk = 0; jk < choice.length(); jk++) { // loop to check letters at the wrong
                            // positions('-')
                            char c3 = secretCode.charAt(jk);
                            if (X[jk] == c1) {/*
                             * if at that position of the secretCode there is a letter which is a
                             * matching one, the evaluation on that letter will be skipped
                             */
                                continue;
                            }

                            out: if (c1 == c3) {
                                for (int kk = 0; kk < alrCheck.length; kk++) {
                                    if (c1 == alrCheck[kk]) { /*
                                     * Inside of alrCheck there are the letters of the secret
                                     * code that are finished to be evaluated. This means that
                                     * evaluations will not be done anymore to that letter. If
                                     * c1 is equal to a letter which will not be evaluated
                                     * anymore, then the evaluation will be skipped. EXAMPLE:
                                     * SecretCode:accc, UserChoice:caca , Evaluation:X-- Since
                                     * in the secret code there is only one 'a', after the
                                     * evaluation of the first 'a' of the user choice, the 'a'
                                     * of the secret code will not be compared anymore.
                                     * Consequently, the last 'a' in the user choice will not be
                                     * compared with the only 'a' present in the secret code. If
                                     * there were a second 'a' in the secret code, then the
                                     * evaluation would be done.
                                     */
                                        break out;
                                    }
                                }

                                q = false;

                                for (int hj = 0; hj < nLetters.length; hj++) {// loop to update the number of letters
                                    if (c1 == letters[hj]) {// if c1 matches with a letter of the secret code, the counter
                                        // of that letter will be decremented.
                                        nLetters[hj]--;
                                        q = true; // there is a matching letter
                                    }
                                    if (nLetters[hj] == 0
                                            && letters[hj] == ' ') { /*
                                     * It could be possible that inside of the array
                                     * "letters" there are some empty spaces. This is
                                     * done to avoid wrong jumps.
                                     */
                                        continue;
                                    }
                                    if (nLetters[hj] == 0) {/*
                                     * If the number of a letter of the secret code that has to be
                                     * evaluated is zero, then it means that that letter will not be
                                     * compared anymore. EXAMPLE: SecretCode:accc, UserChoice:caca ,
                                     * Evaluation:X-- Since after the evaluation of the first 'a'
                                     * the number of 'a' will become zero, then the second 'a' in
                                     * the choice of the user will not be evaluated.
                                     */
                                        alrCheck[e] = c1;
                                    }
                                    if (q == true) {// Since there is a matching letter, a '-' will be added to the
                                        // evaluation string.
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
                    System.exit(1);
                }

                attempts--;
            }
            if (attempts == 0) {
                System.out.println("The number of attempts are finished. Try again by running the code another time.");
            }
        }


    }


