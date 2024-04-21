package org.dtu.lasermaze.model;

import org.dtu.lasermaze.model.token.Token;
import org.dtu.lasermaze.view.TyleType;

public class Board {
    private final int SIZE = 5;
    private Position[][] board = new Position[SIZE][SIZE];

    private void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Position();
            }
        }
    }

    public Board() { init(); }

    private boolean checkBounds(int newX, int newY) {
        return newX >= 0 && newX <= 4 && newY >= 0 && newY <= 4;
    }

    public Position getPosition(int x, int y) throws Exception {
        if(!checkBounds(x, y)) throw new Exception("cannot move token outside the board");

        return board[y][x];
    }

    public void moveToken(int oldX, int oldY, int newX, int newY) throws Exception {
        Position source = getPosition(oldX, oldY);
        Position target = getPosition(newX, newY);
        source.move(target);
    }

    public void moveTokenFromInventory(Token token, int newX, int newY) throws Exception {
        if(!getPosition(newX, newY).isPositionEmpty()) {
            throw new Exception("cannot move token");
        }
        getPosition(newX, newY).setToken(token);
    }

    public void resetBoard(Inventory inventory) throws Exception {
        for (int i = 0; i<SIZE; i++){
            for (int j = 0; j<SIZE; j++){
                if (!getPosition(i, j).isPositionEmpty() && getPosition(i, j).getToken().getMovable()){
                    inventory.addToken(j, getPosition(i, j).getToken());
                    getPosition(i, j).setToken(null);
                }
            }
        }
    }

    public boolean processMove(int row, int col, int direction) throws Exception {
        if (getPosition(row,col).getToken().getMovable()){
            return true;
        }
        return false;
    }

    public Position[][] getBoard() {
        return board;
    }
}
