package com.chess.piece.movement;

import com.chess.board.ChessBoard;
import com.chess.piece.PieceType;
import com.chess.board.Position;

/**
 * An interface to be implemented for validating the movement of a piece on the chess board.
 *
 * @author vaibhav
 */
public interface PieceMovement {

    /**
     * returns whether the move from specified start position to given end position is valid or not
     *
     * @param chessBoard the chess board
     * @param start      the start position
     * @param end        the end position
     * @return <code>true</code> iff a move can be performed from start to end on given chess board
     */
    boolean isValidMove(ChessBoard chessBoard, Position start, Position end);
}
