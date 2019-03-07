package com.chess.piece.movement;

import com.chess.board.ChessBoard;
import com.chess.board.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class that implements {@link PieceMovement}.
 * All the pieces of the chess board can extend this class to validate their movements.
 *
 * @author vaibhav
 */
public abstract class AbstractPieceMovement implements PieceMovement {

    /**
     * returns all the positions current piece can move from current position.
     * must be implemented by sub-class.
     *
     * @param chessBoard the instance of the chess board
     * @param start      the start position
     * @return all the positions current piece can move from current position
     */
    public abstract Set<Position> getAllowedMoves(ChessBoard chessBoard, Position start);

    /**
     * returns all the possible vertical moves from the given position on the chess board
     *
     * @param chessBoard the instance of the chess board
     * @param start      the position from which to calculate vertical moves
     * @return all the possible vertical moves from the given position on the chess board
     */
    public Set<Position> getVerticalMoves(ChessBoard chessBoard, Position start) {
        Set<Position> allowedMoves = new HashSet<>();

        int x, y = start.getY();
        Position end;

        // top
        x = start.getX() - 1;
        end = new Position(x, y);
        while (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
            x--;
            end = new Position(x, y);
        }

        // bottom
        x = start.getX() + 1;
        end = new Position(x, y);
        while (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
            x++;
            end = new Position(x, y);
        }

        return allowedMoves;
    }

    /**
     * returns all the possible horizontal moves from the given position on the chess board
     *
     * @param chessBoard the instance of the chess board
     * @param start      the position from which to calculate horizontal moves
     * @return all the possible horizontal moves from the given position on the chess board
     */
    public Set<Position> getHorizontalMoves(ChessBoard chessBoard, Position start) {
        Set<Position> allowedMoves = new HashSet<>();

        int x = start.getX(), y;
        Position end;

        // left
        y = start.getY() - 1;
        end = new Position(x, y);
        while (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
            y--;
            end = new Position(x, y);
        }

        // right
        y = start.getY() + 1;
        end = new Position(x, y);
        while (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
            y++;
            end = new Position(x, y);
        }

        return allowedMoves;
    }

    /**
     * returns all the possible diagonal moves from the given position on the chess board
     *
     * @param chessBoard the instance of the chess board
     * @param start      the position from which to calculate diagonal moves
     * @return all the possible diagonal moves from the given position on the chess board
     */
    public Set<Position> getDiagonalMoves(ChessBoard chessBoard, Position start) {
        Set<Position> allowedMoves = new HashSet<>();

        int x, y;
        Position end;

        // top left
        x = start.getX() - 1;
        y = start.getY() - 1;
        end = new Position(x, y);
        while (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
            x--;
            y--;
            end = new Position(x, y);
        }

        // top right
        x = start.getX() - 1;
        y = start.getY() + 1;
        end = new Position(x, y);
        while (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
            x--;
            y++;
            end = new Position(x, y);
        }

        // bottom right
        x = start.getX() + 1;
        y = start.getY() + 1;
        end = new Position(x, y);
        while (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
            x++;
            y++;
            end = new Position(x, y);
        }

        // bottom left
        x = start.getX() + 1;
        y = start.getY() - 1;
        end = new Position(x, y);
        while (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
            x++;
            y--;
            end = new Position(x, y);
        }

        return allowedMoves;
    }

    /**
     * returns <code>true</code> iff start position and end position are valid;
     * and no same color piece exist on these positions
     *
     * @param chessBoard the instance of the chess board
     * @param start      the start position
     * @param end        the end position
     * @return <code>true</code> iff start position and end position are valid;
     * and no same color piece exist on these positions
     */
    public boolean validTransition(ChessBoard chessBoard, Position start, Position end) {
        if (!chessBoard.isValidPosition(start) || !chessBoard.isValidPosition(end)) {
            return false;
        }

        if (chessBoard.pieceExists(end) && chessBoard.colorMatch(start, end)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isValidMove(ChessBoard chessBoard, Position start, Position end) {
        // either start or end is not a valid position
        if (!chessBoard.isValidPosition(start) || !chessBoard.isValidPosition(end)) {
            return false;
        }

        // the square at start position is empty i.e., nothing to move
        if (!chessBoard.pieceExists(start)) {
            return false;
        }

        Set<Position> allowedMoves = getAllowedMoves(chessBoard, start);

        return ((allowedMoves != null) && allowedMoves.contains(end));
    }
}
