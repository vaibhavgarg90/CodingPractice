package com.chess.board;

import com.chess.piece.Piece;

/**
 * represents a square on the chess board
 *
 * @author vaibhav
 */
public class Square {

    /**
     * the position of the square
     */
    private final Position position;

    /**
     * the piece present on the square
     */
    private Piece piece;

    /**
     * the string to represent an empty square
     */
    static final String EMPTY_SQUARE = "--";

    public Square(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public String getPieceDetails() {
        if (isEmpty()) {
            return EMPTY_SQUARE;
        }

        return "" + this.piece.getColor().getShortName() + this.piece.getType().getShortName();
    }
}
