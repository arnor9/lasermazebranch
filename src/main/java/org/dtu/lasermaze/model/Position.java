package org.dtu.lasermaze.model;

import org.dtu.lasermaze.model.token.Token;

public class Position {
    private Token token;

    public Position() { }

    public Position(Token token) { this.token = token; }

    public Token getToken() { return this.token; }

    public void setToken(Token token) { this.token = token; }

    public boolean isPositionEmpty() { return getToken() == null; }

    public void move(Position target) throws Exception {
        if(!target.isPositionEmpty()) {
            throw new Exception("cannot move token");
        }

        if(!token.getMovable()) {
            throw new Exception("cannot move token");
        }

        target.setToken(this.token);
        this.token = null;
    }
}
