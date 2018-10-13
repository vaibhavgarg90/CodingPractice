package com.chess.piece;

public enum PieceType {

    ROOK('R'),
    BISHOP('B'),
    HORSE('H'),
    KING('K'),
    QUEEN('Q'),
    PAWN('P');

    private char shortName;

    PieceType(char shortName) {
        this.shortName = shortName;
    }

    public static PieceType getPieceType(char shortName) {
        for (PieceType pieceType : values()) {
            if (pieceType.shortName == shortName) {
                return pieceType;
            }
        }
        return null;
    }

    public char getShortName() {
        return shortName;
    }
}
