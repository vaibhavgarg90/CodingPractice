package com.chess.piece.movement;

import com.chess.board.ChessBoard;
import com.chess.board.Position;

import java.util.Set;

public class BishopMovement extends AbstractPieceMovement {

    private static BishopMovement _instance = null;

    /**
     * can not be initialized from anywhere else except inside this class
     */
    private BishopMovement() {
    }

    public static BishopMovement getInstance() {
        if (_instance == null) {
            _instance = new BishopMovement();
        }

        return _instance;
    }

    @Override
    public Set<Position> getAllowedMoves(ChessBoard chessBoard, Position start) {
        return getDiagonalMoves(chessBoard, start);
    }
}
