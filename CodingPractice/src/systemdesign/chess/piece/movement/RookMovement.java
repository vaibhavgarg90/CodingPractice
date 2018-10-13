package com.chess.piece.movement;

import com.chess.board.ChessBoard;
import com.chess.board.Position;

import java.util.HashSet;
import java.util.Set;

public class RookMovement extends AbstractPieceMovement {

    private static RookMovement _instance = null;

    /**
     * can not be initialized from anywhere else except inside this class
     */
    private RookMovement() {
    }

    public static RookMovement getInstance() {
        if (_instance == null) {
            _instance = new RookMovement();
        }

        return _instance;
    }

    @Override
    public Set<Position> getAllowedMoves(ChessBoard chessBoard, Position start) {
        Set<Position> allowedMoves = new HashSet<>();

        allowedMoves.addAll(getVerticalMoves(chessBoard, start));
        allowedMoves.addAll(getHorizontalMoves(chessBoard, start));

        return allowedMoves;
    }
}
