/*
 * 4/10/2013
 * La clase avion representa los tipos de aviones del juego
 */
package clases;

import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Gero
 * @version 1.0
 *
 */
public class Avion extends Rectangle {

    /*
     * constantes que definen el tipo de avión que se va a crear
     */
    public int AEROPLANO = 0;
    public int AVION_CC = 1;
    public int AVION_CTP = 2;
    public int AVION_MILITAR = 3;
    /*
     * variable de altura velocidad y ganancia del avion
     */
    public int altura;
    public int velocidad;
    public int ganancia;
    public int tipo;
    public Image[] rutasSpriteSheet;

    /**
     *
     * @param tipo con el que se elige que tipo de avión crear
     * @param x posición x
     * @param y posición y
     * @param ancho ancho del avión
     * @param alto alto del avión
     *
     * Se carganen memoria las rutas dde los sprites del objeto avión
     */
    public Avion(int tipo, int x, int y) {
        super(x, y, 1, 1);
        this.tipo = tipo;
        rutasSpriteSheet = new Image[21];
        cargarSpriteSheet();
    }

    /**
     * @param tipo define el tipo de avion a crear
     *
     * En este método se cargan las rutas de las imagenes del avion y se asignan
     * los valores de las variables altura, velocidad y ganancia
     */
    private void cargarSpriteSheet() {
        String ruta = "";
        switch (tipo) {
            case 1:
                ruta = "recursos/aeroplano/";
                altura = 1200;
                velocidad = 300;
                ganancia = 10;
                this.width=30;
                this.height=30;
                break;
            case 2:
                ruta = "recursos/avión comercial de carga/";
                altura = 5300;
                velocidad = 580;
                ganancia = 50;
                this.width=45;
                this.height=45;
                break;
            case 3:
                ruta = "recursos/avión comercial de pasajeros/";
                altura = 10000;
                velocidad = 800;
                ganancia = 80;
                this.width=45;
                this.height=45;
                break;
            case 4:
                ruta = "recursos/avión militar/";
                altura = 12000;
                velocidad = 1200;
                ganancia = 100;
                this.width=30;
                this.height=30;
                break;
        }
        for (int i = 0; i < 21; i++) {
            try {
                rutasSpriteSheet[i] = new Image(ruta.concat("" + i + ".png"));
            } catch (SlickException ex) {
                Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
