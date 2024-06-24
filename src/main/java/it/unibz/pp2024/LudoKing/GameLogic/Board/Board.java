package it.unibz.pp2024.LudoKing.GameLogic.Board;

import lombok.Getter;
import lombok.Setter;

public class Board {
    @Getter
    @Setter
    protected int[][] board;
    @Getter
    @Setter
    protected int[][] playerPositions;
    @Getter
    @Setter
    protected int[][] boardExtension1;
}
