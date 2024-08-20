package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

public class QuizPerkUtil {
    private boolean perkDoubleRoll = false;
    private boolean perkBoostRoll = false;
    private boolean perkDecideDoubleRoll = false;
    private boolean perkExtraTurn = false;

    public boolean hasPerkDoubleRoll() {
        return perkDoubleRoll;
    }

    public boolean hasPerkBoostRoll() {
        return perkBoostRoll;
    }

    public boolean hasPerkDecideDoubleRoll(){
        return perkDecideDoubleRoll;
    }

    public boolean hasPerkExtraTurn(){ return perkExtraTurn;}

    public void setPerkDoubleRoll(boolean status) {
        perkDoubleRoll = status;
    }

    public void setPerkBoostRoll(boolean status) {
        perkBoostRoll = status;
    }

    public void setPerkDecideDoubleRoll(boolean status){ perkDecideDoubleRoll = status; }

    public void setPerkExtraTurn(boolean status){ perkExtraTurn = status; }
}
