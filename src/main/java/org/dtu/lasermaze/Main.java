package org.dtu.lasermaze;
import org.dtu.lasermaze.controller.Controller;
import org.dtu.lasermaze.model.Board;
import org.dtu.lasermaze.view.BoardGUI;

import java.awt.event.ActionListener;

public class Main
{
    public static void main( String[] args )
    {
        int row = 5;
        int col = 5;
        Board boardModel = new Board();
        BoardGUI boardView = new BoardGUI(row, col);
        Controller controller = new Controller(boardModel, boardView);

        boardView.setVisible(true);
    }
}
