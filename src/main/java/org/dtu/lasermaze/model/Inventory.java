package org.dtu.lasermaze.model;

import org.dtu.lasermaze.model.token.Token;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class Inventory {
    private final int SIZE = 5;
    private List<Token> inventory;

    public Inventory() {

        // Create a fixed-size list
        this.inventory = Arrays.asList(new Token[SIZE]);
    }

    public void addToken(int index, Token token) throws Exception{
        if (index >= 0 && index <= this.inventory.size()) {
            if (this.inventory.get(index) == null){
                this.inventory.set(index, token);
            }
            else{
                throw new Exception("inventory position occupied");
            }
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public Token getElement(int index) {
        Token token = this.inventory.get(index);
        this.inventory.set(index, null);
        return token;
    }
}
