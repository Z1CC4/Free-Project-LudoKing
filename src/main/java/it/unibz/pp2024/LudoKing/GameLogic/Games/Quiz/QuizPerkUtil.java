package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

public class QuizPerkUtil {
    private static boolean perkDoubleRoll = false;
    private static boolean perkBoostRoll = false;
    private static boolean perkDecideDoubleRoll = false;

    public static boolean hasPerkDoubleRoll() {
        return perkDoubleRoll;
    }

    public static boolean hasPerkBoostRoll() {
        return perkBoostRoll;
    }

    public static boolean hasPerkDecideDoubleRoll(){
        return perkDecideDoubleRoll;
    }

    public static void setPerkDoubleRoll(boolean status) {
        perkDoubleRoll = status;
    }

    public static void setPerkBoostRoll(boolean status) {
        perkBoostRoll = status;
    }

    public static void setPerkDecideDoubleRoll(boolean status){
        perkDecideDoubleRoll = status;
    }
}
