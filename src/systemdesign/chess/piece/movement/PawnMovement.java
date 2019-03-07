package com.chess.piece.movement;

import com.chess.board.ChessBoard;
import com.chess.board.Position;
import com.chess.piece.Piece;
import com.chess.piece.PieceColor;
import com.chess.piece.PieceType;

import java.util.HashSet;
import java.util.Set;

/**
 * captures the movement of {@link PieceType#PAWN}
 *
 * @author vaibhav
 */
public class PawnMovement extends AbstractPieceMovement {

    private static PawnMovement _instance = null;

    /**
     * can not be initialized from anywhere else except inside this class
     */
    private PawnMovement() {
    }

    public static PawnMovement getInstance() {
        if (_instance == null) {
            _instance = new PawnMovement();
        }

        return _instance;
    }

    @Override
    public Set<Position> getAllowedMoves(ChessBoard chessBoard, Position start) {
        Set<Position> allowedMoves = new HashSet<>();

        Piece piece = chessBoard.getPiece(start);

        getPawnStraightMoves(allowedMoves, chessBoard, piece, start);
        getPawnDiagonalMoves(allowedMoves, chessBoard, piece, start);

        return allowedMoves;
    }

    private static void getPawnStraightMoves(Set<Position> allowedMoves, ChessBoard chessBoard, Piece piece, Position start) {
        int boardSize = chessBoard.getBoardSize();
        int x = start.getX(), y = start.getY();
        PieceColor color = piece.getColor();

        Position end;

        if (PieceColor.WHITE.equals(color)) {
            end = new Position(x + 1, y);

            if (chessBoard.isValidPosition(end) && !chessBoard.pieceExists(end)) {
                allowedMoves.add(end);
            }

            if (x == 1) {
                end = new Position(x + 2, y);
                if (!chessBoard.pieceExists(end)) {
                    allowedMoves.add(end);
                }
            }
        } else {
            end = new Position(x - 1, y);

            if (chessBoard.isValidPosition(end) && !chessBoard.pieceExists(end)) {
                allowedMoves.add(end);
            }

            if (x == ((boardSize - 1) - 1)) {
                end = new Position(x - 2, y);
                if (!chessBoard.pieceExists(end)) {
                    allowedMoves.add(end);
                }
            }
        }
    }

    private static void getPawnDiagonalMoves(Set<Position> allowedMoves, ChessBoard chessBoard, Piece piece, Position start) {
        int x = start.getX(), y = start.getY();
        PieceColor color = piece.getColor();

        Position end;

        if (PieceColor.WHITE.equals(color)) {
            end = new Position(x + 1, y - 1);

            if (chessBoard.isValidPosition(end) && chessBoard.pieceExists(end) && !chessBoard.colorMatch(start, end)) {
                allowedMoves.add(end);
            }

            end = new Position(x + 1, y + 1);

            if (chessBoard.isValidPosition(end) && chessBoard.pieceExists(end) && !chessBoard.colorMatch(start, end)) {
                allowedMoves.add(end);
            }
        } else {
            end = new Position(x - 1, y - 1);

            if (chessBoard.isValidPosition(end) && chessBoard.pieceExists(end) && !chessBoard.colorMatch(start, end)) {
                allowedMoves.add(end);
            }

            end = new Position(x - 1, y + 1);

            if (chessBoard.isValidPosition(end) && chessBoard.pieceExists(end) && !chessBoard.colorMatch(start, end)) {
                allowedMoves.add(end);
            }
        }
    }
}
