/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Rectangle;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Gero
 */
public class Aeropuerto extends Rectangle {

    private int tipo;
    private String ruta;
    private Image imagen;
    public int posicionEnGrafo;

    public Aeropuerto(String ruta, int tipo, int x, int y) {
        super(x, y, 90, 90);
        try {
            this.ruta = ruta;

            imagen = new Image(ruta);
        } catch (SlickException ex) {
        }
        this.tipo = tipo;
        posicionEnGrafo=-1;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
