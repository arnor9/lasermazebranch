package org.dtu.lasermaze.model.token;

public class Token {
    private boolean movable;
    private int direction;
    private boolean rotatable;

    public boolean getMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setRotatable(boolean rotatable) {
        this.rotatable = rotatable;
    }

    public boolean getRotatable() {
        return rotatable;
    }

    public void rotateToken() throws Exception{
        int newDeg;

        if (!getRotatable()) {
            throw new Exception("cannot rotate token");
        }

        newDeg = (this.direction + 90) % 360;
        setDirection(newDeg);
    }
}

