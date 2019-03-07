package com.chess.piece.movement;

import com.chess.board.ChessBoard;
import com.chess.board.Position;
import com.chess.piece.PieceType;

import java.util.HashSet;
import java.util.Set;

/**
 * captures the movement of {@link PieceType#KING}
 *
 * @author vaibhav
 */
public class KingMovement extends AbstractPieceMovement {

    private static KingMovement _instance = null;

    /**
     * can not be initialized from anywhere outside this class
     */
    private KingMovement() {
    }

    public static KingMovement getInstance() {
        if (_instance == null) {
            _instance = new KingMovement();
        }

        return _instance;
    }

    @Override
    public Set<Position> getAllowedMoves(ChessBoard chessBoard, Position start) {
        Set<Position> allowedMoves = new HashSet<>();

        Position end;

        // top left
        end = new Position(start.getX() - 1, start.getY() - 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // top
        end = new Position(start.getX() - 1, start.getY());
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // top right
        end = new Position(start.getX() - 1, start.getY() + 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // right
        end = new Position(start.getX(), start.getY() + 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // bottom right
        end = new Position(start.getX() + 1, start.getY() + 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // bottom
        end = new Position(start.getX() + 1, start.getY());
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // bottom left
        end = new Position(start.getX() + 1, start.getY() - 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // left
        end = new Position(start.getX(), start.getY() - 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        return allowedMoves;
    }
}
