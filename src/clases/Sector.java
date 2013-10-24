/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Rectangle;

/**
 *
 * @author Gero
 */
public class Sector extends Rectangle {

    private int id;

    public Sector(int id, int x, int y) {
        super(x, y, 30, 30);
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
