package org.dtu.lasermaze.model.token;

public class DoubleMirror extends Token {
    private int laserDirection;
    private int tokenDirection;

    public DoubleMirror() {
    }

    public int[] laserDisplayDirection(int laserDirection, int tokenDirection, int row, int col) {
        this.laserDirection = laserDirection;
        this.tokenDirection = tokenDirection;
        switch (laserDirection) {
            case 0:
                switch (tokenDirection) {
                    case 0, 180:
                        laserDirection = 90;
                        row++;
                        break;
                    case 90, 270:
                        laserDirection = 270;
                        row--;
                        break;
                }
                break;
            case 90:
                switch (tokenDirection) {
                    case 0, 180:
                        laserDirection = 0;
                        col++;
                        break;
                    case 90, 270:
                        laserDirection = 180;
                        col--;
                        break;
                }
                break;
            case 180:
                switch (tokenDirection) {
                    case 0, 180:
                        laserDirection = 270;
                        row--;
                        break;
                    case 90, 270:
                        laserDirection = 90;
                        row++;
                        break;
                }
                break;
            case 270:
                switch (tokenDirection) {
                    case 0, 180:
                        laserDirection = 180;
                        col--;
                        break;
                    case 90, 270:
                        laserDirection = 0;
                        col++;
                        break;
                }
                break;
        }

        return new int[]{laserDirection, row, col};

    }
}