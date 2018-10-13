package com.chess.board;

import com.chess.piece.Piece;
import com.chess.piece.PieceColor;
import com.chess.piece.PieceType;
import com.chess.piece.movement.*;

import java.util.List;

/**
 * represents a chess board.
 *
 * @author vaibhav
 */
public class ChessBoard {

    /**
     * the size of the chess board
     */
    private final int boardSize;

    /**
     * the squares on the chess board
     */
    private Square[][] squares;

    /**
     * the string to capture a valid move
     */
    private static final String VALID_MOVE = "Valid";

    /**
     * the string to capture an invalid move
     */
    private static final String INVALID_MOVE = "Invalid";

    /****************************************************************
     *                        HELPER METHODS                        *
     ****************************************************************/

    /**
     * get the piece color from given details of the piece
     *
     * @param pieceDetails the details of the piece
     * @return the piece color from given details of the piece
     */
    private PieceColor getPieceColor(String pieceDetails) {
        char shortName = pieceDetails.charAt(0);
        return PieceColor.getPieceColor(shortName);
    }

    /**
     * get the piece type from given details of the piece
     *
     * @param pieceDetails the details of the piece
     * @return the piece type from given details of the piece
     */
    private PieceType getPieceType(String pieceDetails) {
        char shortName = pieceDetails.charAt(1);
        return PieceType.getPieceType(shortName);
    }

    /**
     * get the x ordinal from the given details of the position
     *
     * @param positionDetails the details of the position
     * @return the x ordinal from the given details of the position
     */
    private int getXPosition(String positionDetails) {
        char x = positionDetails.charAt(0);
        return Integer.parseInt("" + x);
    }

    /**
     * get the y ordinal from the given details of the position
     *
     * @param positionDetails the details of the position
     * @return the y ordinal from the given details of the position
     */
    private int getYPosition(String positionDetails) {
        char y = positionDetails.charAt(1);
        return Integer.parseInt("" + y);
    }

    /**
     * returns an instance of {@link PieceMovement} that captures the movement of the piece at specified position
     *
     * @param position the position of interest
     * @return an instance of {@link PieceMovement} that captures the movement of the piece at specified position
     */
    private PieceMovement getPieceMovement(Position position) {
        if (!isValidPosition(position)) {
            return null;
        }

        PieceType pieceType = getPieceType(position);

        if (pieceType == null) {
            return null;
        }

        switch (pieceType) {
            case ROOK:
                return RookMovement.getInstance();
            case BISHOP:
                return BishopMovement.getInstance();
            case HORSE:
                return HorseMovement.getInstance();
            case KING:
                return KingMovement.getInstance();
            case QUEEN:
                return QueenMovement.getInstance();
            case PAWN:
                return PawnMovement.getInstance();
        }

        return null;
    }

    /**
     * set the given piece at specified position
     *
     * @param position the position at which to set the piece
     * @param piece    the piece to set
     */
    private void setPiece(Position position, Piece piece) {
        Square square = this.squares[position.getX()][position.getY()];
        square.setPiece(piece);
        this.squares[position.getX()][position.getY()] = square;
    }

    /**
     * reset the piece at specified position
     *
     * @param position the position at which to reset the piece
     */
    private void resetPiece(Position position) {
        setPiece(position, null);
    }

    /**
     * move the piece from start position to end position
     *
     * @param start the start position of the move
     * @param end   the end position of the move
     */
    private void movePiece(Position start, Position end) {
        System.out.print(start.toString() + " " + end.toString() + " : ");

        boolean isValidMove = true;

        if (isValidPosition(start) && isValidPosition(end)) {
            PieceMovement pieceMovement = getPieceMovement(start);

            if ((pieceMovement == null) || !pieceMovement.isValidMove(this, start, end)) {
                isValidMove = false;
            } else {
                setPiece(end, this.squares[start.getX()][start.getY()].getPiece());
                resetPiece(start);
            }
        }

        System.out.println(isValidMove ? VALID_MOVE : INVALID_MOVE);

        printChessBoard();
    }

    /**
     * prints the current state of the chess board
     */
    private void printChessBoard() {
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                Square square = this.squares[i][j];
                System.out.print(square.getPieceDetails());
                System.out.print(" ");
            }

            System.out.println();
        }

        System.out.println();
    }

    /****************************************************************
     *                    END OF HELPER METHODS                     *
     ****************************************************************/

    /**
     * the constructor for initializing the chess board
     */
    public ChessBoard() {
        this.boardSize = 8;
        this.squares = new Square[this.boardSize][this.boardSize];
    }

    /**
     * initialize the chess board
     *
     * @param lines the list of lines with each line describing the row of the chess board
     */
    public void initBoard(List<String> lines) {
        String line;
        Position position;
        Piece piece;

        for (int i = 0; i < getBoardSize(); i++) {
            line = lines.get(i).trim();

            String[] boardConfiguration = line.split(" ", -1);
            int j = 0;

            for (String curConfiguration : boardConfiguration) {
                position = new Position(i, j);
                this.squares[i][j] = new Square(position);

                if (!curConfiguration.equals(Square.EMPTY_SQUARE)) {
                    piece = new Piece(getPieceColor(curConfiguration), getPieceType(curConfiguration));
                    setPiece(position, piece);
                }

                j++;
            }
        }

        System.out.println("Initial Board");
        printChessBoard();
    }

    /**
     * move the pieces on the chess board
     *
     * @param moves the list of moves with each move describing the start and end position of the move
     */
    public void movePieces(List<String> moves) {
        int startX, startY, endX, endY;
        Position start, end;

        for (String move : moves) {
            String[] startEnd = move.split(" ", -1);
            startX = getXPosition(startEnd[0]);
            startY = getYPosition(startEnd[0]);
            start = new Position(startX, startY);

            endX = getXPosition(startEnd[1]);
            endY = getYPosition(startEnd[1]);
            end = new Position(endX, endY);

            movePiece(start, end);
        }
    }

    /**
     * @return the size (row / column) of the board
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * returns <code>true</code> iff specified position is valid
     *
     * @param position the position to check
     * @return <code>true</code> iff specified position is valid
     */
    public boolean isValidPosition(Position position) {
        return ((position != null) && (position.getX() >= 0) && (position.getX() < getBoardSize())
                && (position.getY() >= 0) && (position.getY() < getBoardSize()));
    }

    /**
     * returns <code>true</code> iff the square at given position on the chess board is not empty
     *
     * @param position the position to check for emptiness on the chess board
     * @return <code>true</code> iff the square at given position on the chess board is not empty
     */
    public boolean pieceExists(Position position) {
        if (!isValidPosition(position)) {
            return false;
        }

        Square square = this.squares[position.getX()][position.getY()];
        return !square.isEmpty();
    }

    /**
     * returns the piece at specified position
     *
     * @param position the position for which to get the piece
     * @return the piece at specified position
     */
    public Piece getPiece(Position position) {
        if (!isValidPosition(position)) {
            return null;
        }

        return this.squares[position.getX()][position.getY()].getPiece();
    }

    /**
     * returns the piece type at specified position
     *
     * @param position the position for which to get the piece type
     * @return the piece type at specified position
     */
    public PieceType getPieceType(Position position) {
        Piece piece = getPiece(position);
        return ((piece != null) ? piece.getType() : null);
    }

    /**
     * returns the piece color at specified position
     *
     * @param position the position for which to get the piece color
     * @return the piece color at specified position
     */
    public PieceColor getPieceColor(Position position) {
        Piece piece = getPiece(position);
        return ((piece != null) ? piece.getColor() : null);
    }

    /**
     * returns <code>true</code> iff pieces at specified positions (iff present) are of same color
     *
     * @param first  the position of the first piece
     * @param second the position of the second piece
     * @return <code>true</code> iff pieces at specified positions (iff present) are of same color
     */
    public boolean colorMatch(Position first, Position second) {
        PieceColor firstPieceColor = getPieceColor(first);
        PieceColor secondPieceColor = getPieceColor(second);
        return ((firstPieceColor != null) && firstPieceColor.equals(secondPieceColor));
    }
}
