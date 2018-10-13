package com.chess.piece;

/**
 * The various colors of pieces in a chess games
 */
public enum PieceColor {

    WHITE('W'),
    BLACK('B');

    private char shortName;

    PieceColor(char shortName) {
        this.shortName = shortName;
    }

    public static PieceColor getPieceColor(char shortName) {
        for (PieceColor pieceColor : values()) {
            if (pieceColor.shortName == shortName) {
                return pieceColor;
            }
        }

        return null;
    }

    public char getShortName() {
        return shortName;
    }
}
