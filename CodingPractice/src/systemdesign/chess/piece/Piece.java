package com.chess.piece;

/**
 * represents a chess piece
 *
 * @author vaibhav
 */
public class Piece {

    /**
     * the color of the chess board piece
     */
    private final PieceColor color;

    /**
     * the type of the piece
     */
    private final PieceType type;

    public Piece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }
}
