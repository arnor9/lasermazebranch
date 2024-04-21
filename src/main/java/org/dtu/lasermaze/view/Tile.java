package org.dtu.lasermaze.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile extends JButton {
    public static final int PIXEL_SIZE = 102;
    private TyleType type;
    private int row;
    private int col;
    private int direction;

    private BufferedImage image;
    private boolean hasLaserBeam = false;
    private boolean isPressed = false;
    private int displayDirection = 0;

    public Tile(TyleType type, int row, int col, int direction) {
        this.type = type;
        this.row = row;
        this.col = col;
        this.direction = direction;

        try {
            this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(type.getPictureFile()));
        } catch (IOException e) {
            this.image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        }
        setMinimumSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
    }

    public void rotateToken(Tile pressedButton, Tile pressedTile) {
        // if same tile is selected again and tile is not in inventory, change direction of tile
        if (pressedButton.getDirection() < 270) {
            pressedTile.direction = pressedButton.getDirection() + 90;
        } else {
            pressedTile.direction = 0;
        }

        BufferedImage originalImage = pressedButton.image;
        pressedButton.image = ImageRotation.rotateImage(originalImage, 90); // Rotate by 90 degrees

    }

    public void moveToken(Tile lastPressedTile, Tile pressedTile) {
        try {
            // change image of currently pressed button to image of tile type
            pressedTile.image = lastPressedTile.image;
            // change image of the last pressed button to EMPTY
            lastPressedTile.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(TyleType.EMPTY.getPictureFile()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // set type of currently pressed Tile to type of last pressed Tile
        pressedTile.type = lastPressedTile.type;

        // assign row and column to the tile (change of location needs change of position)
        pressedTile.direction = lastPressedTile.getDirection();

        lastPressedTile.type = TyleType.EMPTY;

        // repaint to reflect changes
        lastPressedTile.repaint();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDirection() {
        return direction;
    }

    public TyleType getType() {
        return type;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return this.isPressed;
    }

    public void setLaserBeam(boolean hasLaserBeam) {
        this.hasLaserBeam = hasLaserBeam;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // draw frame around the button if it is pressed
        if (isPressed) {
            g2d.setColor(Color.ORANGE);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        // clear previous content
        g2d.clearRect(0, 0, PIXEL_SIZE, PIXEL_SIZE);

        // draw image
        g2d.drawImage(image, 0, 0, null);

        // draw laser beam if present
        if (hasLaserBeam) {
            int verticalCenter = PIXEL_SIZE / 2 - 5;
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(3));

            if (this.getType() == TyleType.EMPTY && this.getType() != TyleType.LASER) {

                // Check the direction of the laser and draw accordingly
                if (displayDirection == 0 || displayDirection == 180) {
                    // Horizontal laser
                    g2d.drawLine(0, verticalCenter, PIXEL_SIZE, verticalCenter);
                    g2d.setColor(new Color(255, 0, 0, 50));
                    g2d.drawLine(0, verticalCenter - 3, PIXEL_SIZE, verticalCenter - 3);
                    g2d.drawLine(0, verticalCenter + 3, PIXEL_SIZE, verticalCenter + 3);
                } else if (displayDirection == 90 || displayDirection == 270) {
                    // Vertical laser
                    g2d.drawLine(PIXEL_SIZE / 2, 0, PIXEL_SIZE / 2, PIXEL_SIZE);
                    g2d.setColor(new Color(255, 0, 0, 50));
                    g2d.drawLine(PIXEL_SIZE / 2 - 3, 0, PIXEL_SIZE / 2 - 3, PIXEL_SIZE);
                    g2d.drawLine(PIXEL_SIZE / 2 + 3, 0, PIXEL_SIZE / 2 + 3, PIXEL_SIZE);
                }
            }
        }

    }
}