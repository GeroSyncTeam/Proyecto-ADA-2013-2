/*
 * 4/10/2013
 * La clase avion representa los tipos de aviones del juego
 */
package clases;

import java.awt.Rectangle;

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

   
    public String[] rutasSpriteSheet;

    private Avion() {
    }

    public Avion(int tipo, int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        rutasSpriteSheet = new String[21];
        cargarSpriteSheet(tipo);
    }

    /**
     * @param tipo define el tipo de avion a crear
     * 
     * En este método se cargan las rutas de las imagenes del avion y se
     * asignan los valores de las variables altura, velocidad y ganancia 
     */

    private void cargarSpriteSheet(int tipo) {
        String ruta = "";
        switch (tipo) {
            case 0:
                ruta = "recursos/aeroplano/";
                altura = 1200;
                velocidad = 300;
                ganancia = 10;
                break;
            case 1:
                ruta = "recursos/avión comercial de carga/";
                altura = 5300;
                velocidad = 580;
                ganancia = 50;
                break;
            case 2:
                ruta = "recursos/avión comercial de pasajeros/";
                altura = 10000;
                velocidad = 800;
                ganancia = 80;
                break;
            case 3:
                ruta = "recursos/avión militar/";
                altura = 12000;
                velocidad = 1200;
                ganancia = 100;
                break;
        }
        for (int i = 0; i < 21; i++) {
            rutasSpriteSheet[i] = ruta.concat("" + i + ".png");
        }
    }
}
