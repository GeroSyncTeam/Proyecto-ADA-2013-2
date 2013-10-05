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
public class Helicoptero extends Rectangle {

    public int altura;
    public int velocidad;
    public int ganancia;
    public String[] rutasSpriteSheet;

    public Helicoptero(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        rutasSpriteSheet = new String[10];
        cargarSpriteSheet();
    }

    
    /** 
     * En este método se cargan las rutas de las imagenes del avion y se
     * asignan los valores de las variables altura, velocidad y ganancia 
     */
    
    private void cargarSpriteSheet() {
        String ruta = "recursos/helicóptero/";

        for (int i = 0; i < 10; i++) {
            rutasSpriteSheet[i] = ruta.concat("" + i + ".png");
        }
    }
}
