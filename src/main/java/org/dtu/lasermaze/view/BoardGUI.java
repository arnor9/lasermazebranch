package org.dtu.lasermaze.view;

import org.dtu.lasermaze.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BoardGUI extends JPanel{
    private int rows;
    private int cols;
    private Tile[][] boardTiles;
    private int laserRow;
    private int laserCol;
    private int laserDirection;
    Tile mirror1;
    public BoardGUI(int row, int col) {
        this.boardTiles = new Tile[row][col];
        this.rows = row;
        this.cols = col;

       // setLayout(new GridLayout(rows, cols));
        initializeBoard();

//        JPanel boardPanel = new JPanel(new GridLayout(rows, cols));
//
//        this.add(boardPanel);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(800, 800); // Set size according to your needs
//        this.setVisible(true);
//
//        setLayout(new GridLayout(rows, cols));
//        setBackground(Color.WHITE);
//
//        setBorder(
//                BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(Color.RED, 10),
//                        BorderFactory.createLineBorder(Color.WHITE, 10)));
//
//        setMinimumSize(new Dimension(cols * Tile.PIXEL_SIZE, rows * Tile.PIXEL_SIZE));
//        setMaximumSize(getMinimumSize());
//        setPreferredSize(getMinimumSize());
        JFrame f = new JFrame("Laser Maze Demo - v.0.1");

        // set the layout
        f.setLayout(new BorderLayout());

        // add all parts to the GUI
        //f.add(inventoryGUI, BorderLayout.NORTH);
        f.add(this, BorderLayout.CENTER);
        //f.add(buttonPanel, BorderLayout.SOUTH);


        // set size, visibility and close operation
        f.setSize(800, 800);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void initializeBoard(){
        JFrame f = new JFrame("Laser Maze Demo - v.0.1");

        // set the layout
        f.setLayout(new BorderLayout());

        // add all parts to the GUI
        //f.add(inventoryGUI, BorderLayout.NORTH);
        f.add(this, BorderLayout.CENTER);
        //f.add(buttonPanel, BorderLayout.SOUTH);


        // set size, visibility and close operation
        f.setSize(800, 800);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardTiles[i][j] = new Tile(TyleType.EMPTY, i, j, 0);
                f.add(boardTiles[i][j]);
            }
        }
    }

    public Tile[][] getBoardTiles() {
        return boardTiles;
    }

    public Tile getTileAt(int row, int col) {
        return boardTiles[row][col];
    }

    public void loadBoard(ActionListener actionListener) {
        // set tokens
        // laser
        laserCol = 0;
        laserRow = 1;
        laserDirection = 0;

        Tile laser = new Tile(TyleType.LASER, laserRow, laserCol, laserDirection);
        boardTiles[laserRow][laserCol] = laser;
        //laser.addActionListener(actionListener);

        // mirrors
        int mirrorCol = 1;
        int mirrorRow = 2;
        int mirrorDirection = 0;

        Tile mirror1 = new Tile(TyleType.MIRROR, mirrorRow, mirrorCol, mirrorDirection);
        boardTiles[mirrorRow][mirrorCol] = mirror1;

        //mirror1.addActionListener(actionListener);
        Tile mirror2 = new Tile(TyleType.MIRROR,mirrorRow+1, mirrorCol+1, mirrorDirection);
        boardTiles[mirrorRow+1][mirrorCol+1] = mirror2;
        //mirror2.addActionListener(actionListener);

        // add the rest of the tiles
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                if (boardTiles[j][i] != null) {
                    add(boardTiles[j][i]);
                } else {
                    Tile t = new Tile(TyleType.EMPTY,j,i, 0);
                    boardTiles[j][i] = t;
                    add(t);
                   // t.addActionListener(actionListener);

                }
            }
        }
    }
    public void addButtonClickedListener(ActionListener listenForButtonClick){

    }

    public void updateView(int row, int col, Position position) {
    }

    public void displayErrorMessage(String invalidMove) {
    }
}

