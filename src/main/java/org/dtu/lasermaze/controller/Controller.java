package org.dtu.lasermaze.controller;

import org.dtu.lasermaze.model.Board;
import org.dtu.lasermaze.view.BoardGUI;
import org.dtu.lasermaze.view.Tile;

public class Controller {
    private final int SIZE = 5;
    private Board boardModel;
    private BoardGUI boardView;
    private Tile clickedButton;
    private Tile clickedTile;
    private boolean inInventoryPressed;


    public Controller(Board boardModel, BoardGUI boardView){
        this.boardModel = boardModel;
        this.boardView = boardView;

        Tile[][] tiles = boardView.getBoardTiles();
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                tiles[i][j].addActionListener(e -> {
                    clickedButton = (Tile) e.getSource();
                    try {
                        processTileClicked(clickedButton);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
    }

    public void processTileClicked(Tile clickedButton) throws Exception {
        int row = clickedButton.getRow();
        int col = clickedButton.getCol();
        int direction = clickedButton.getDirection();
        if(boardModel.processMove(row, col, direction)){
            boardView.updateView(row,col, boardModel.getBoard()[row][col]);
        } else {
            boardView.displayErrorMessage("Invalid move");
        }
    }

}
