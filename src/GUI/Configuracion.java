/*
 * 6/10/2013
 * Se configuran el mapa, el tiempo entre oleada de aviones y el tipo de 
 * aeropuertos
 */
package GUI;

import clases.Aeropuerto;
import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.io.IOException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Gero
 */
public class Configuracion extends BasicGameState {

    Juego juego;
    Font fuente;
    Rectangle rAtras, rMas, rMenos, pulsacion, rCuadricula, rBorrador;
    Rectangle rAeropuerto1, rAeropuerto2, rAeropuerto3, rAeropuerto4;
    Image atras1, atras2, barraIzquierda, marco, imgTemporal;
    Image borrador, aeropuerto1, aeropuerto2, aeropuerto3, aeropuerto4;
    SpriteSheet signos;
    String rutaImagenTemporal;
    boolean dibujarSobreAtras, pintarAeropuerto, pintarBorradorTemporal, botonMas, botonMenos;
    boolean aeropuerto1Booleano;
    boolean aeropuerto2Booleano;
    boolean aeropuerto3Booleano;
    boolean aeropuerto4Booleano;
    boolean borradorBooleano;
    boolean cuadriculaBooleano;
    int XATRAS = 17;
    int YATRAS = 560;
    int XMAS = 170;
    int XMENOS = 200;
    int YMASMENOS = 50;
    int ANCHOATRAS = 80;
    int ALTOATRAS = 25;
    int pulsacionX;
    int pulsacionY;
    int XA1 = 28;
    int YA1 = 350;
    int XA2 = 28;
    int YA2 = 450;
    int XA3 = 130;
    int YA3 = 350;
    int XA4 = 130;
    int YA4 = 450;
    int XBORRADOR = 475;
    int YBORRADOR = 520;
    int tipoAeropuerto = -1;
    //----------------------------------

    public Configuracion(Juego juego) {
        this.juego = juego;
    }

    /**
     *
     * @return 4 es el id que lo identifica
     */
    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    /**
     *
     * @param container
     * @param sbg
     * @throws SlickException
     *
     * Se cargan todos los recursos que se usaran
     */
    public void init2(GameContainer container, StateBasedGame sbg) throws SlickException {

        pulsacion = new Rectangle(2, 2);
        //------ Carga la fuente ---------
        try {
            java.io.InputStream inputStream = ResourceLoader.getResourceAsStream("recursos/Action Man Shaded.ttf");
            java.awt.Font awtFont2 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(23f); // set font size
            fuente = new TrueTypeFont(awtFont2, false);
        } catch (FontFormatException | IOException e) {
            fuente = container.getDefaultFont();
        }
        //----------------------------------
        borrador = new Image("recursos/fondos/borrador.png");
        atras1 = new Image("recursos/fondos/atras1.png");
        atras2 = new Image("recursos/fondos/atras2.png");
        barraIzquierda = new Image("recursos/fondos/barra izquierda2.jpg");
        marco = new Image("recursos/fondos/marco mapa.png");
        aeropuerto1 = new Image("recursos/fondos/a1.jpg");
        aeropuerto2 = new Image("recursos/fondos/a2.jpg");
        aeropuerto3 = new Image("recursos/fondos/a3.jpg");
        aeropuerto4 = new Image("recursos/fondos/a4.jpg");
        signos = new SpriteSheet("recursos/fondos/signos.png", 20, 20);
        rAtras = new Rectangle(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        rMas = new Rectangle(XMAS, YMASMENOS, 20, 20);
        rMenos = new Rectangle(XMENOS, YMASMENOS, 20, 20);
        rCuadricula = new Rectangle(300, 60, 390, 390);
        rAeropuerto1 = new Rectangle(XA1, YA1, 90, 90);
        rAeropuerto2 = new Rectangle(XA2, YA2, 90, 90);
        rAeropuerto3 = new Rectangle(XA3, YA3, 90, 90);
        rAeropuerto4 = new Rectangle(XA4, YA4, 90, 90);
        rBorrador = new Rectangle(XBORRADOR, YBORRADOR, 100, 60);
        dibujarSobreAtras = false;



    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        init2(container, game);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        pulsacion = null;
        fuente = null;
        atras1 = null;
        atras2 = null;
        barraIzquierda = null;
        marco = null;
        rAeropuerto1 = null;
        signos = null;
        rAtras = null;
        rMas = null;
        rMenos = null;
        rCuadricula = null;
        rAeropuerto1 = null;
    }

    /**
     *
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     *
     * Se encarga de realizar el dibujado de los graficos
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        //------ Pinta la cuadricula ------
        int x1 = 300, y1 = 60, aumento = 0;
        for (int i = 0; i <= 15; i++) {
            g.drawLine(x1 + aumento, y1, x1 + aumento, 510);
            g.drawLine(x1, y1 + aumento, 750, y1 + aumento);
            aumento += 30;
        }
        //------ Pinta aeropuertos del mapa 
        for (int i = 0; i < juego.aeropuertosMapa.size(); i++) {
            int x = juego.aeropuertosMapa.get(i).x;
            int y = juego.aeropuertosMapa.get(i).y;
            juego.aeropuertosMapa.get(i).getImagen().draw(x, y);
        }
        //----------------------------------
        barraIzquierda.draw(0, 0, 250, 600);
        marco.draw(250, 0);
        borrador.draw(XBORRADOR, YBORRADOR, 100, 60);
        g.setFont(fuente);
        g.setColor(Color.lightGray);
        g.drawString("Tiempo De Juego", 18, 20);
        g.setColor(Color.white);
        g.drawString("Tiempo De Juego", 19, 20);
        g.setColor(Color.lightGray);
        g.drawString(juego.minutos + " minutos", 25, 50);
        g.setColor(Color.white);
        g.drawString(juego.minutos + " minutos", 25, 50);
        signos.getSprite(0, 0).draw(XMAS, YMASMENOS);
        signos.getSprite(0, 1).draw(XMENOS, YMASMENOS);
        g.setColor(Color.lightGray);
        g.drawString("Aeropuertos", 40, 120);
        g.setColor(Color.white);
        g.drawString("Aeropuertos", 41, 120);
        g.setColor(Color.blue);
        aeropuerto1.draw(XA1, YA1, 90, 90);
        aeropuerto2.draw(XA2, YA2, 90, 90);
        aeropuerto3.draw(XA3, YA3, 90, 90);
        aeropuerto4.draw(XA4, YA4, 90, 90);
        g.drawRoundRect(28, 350, 90, 90, 2);
        g.drawRoundRect(28, 450, 90, 90, 2);
        g.drawRoundRect(130, 350, 90, 90, 2);
        g.drawRoundRect(130, 450, 90, 90, 2);
        g.setColor(Color.white);
        //------ Pintar imagen temporal------       
        if (pintarAeropuerto) {

            imgTemporal.draw(container.getInput().getMouseX(), container.getInput().getMouseY(), 90, 90);
        }
        if (pintarBorradorTemporal) {

            imgTemporal.draw(container.getInput().getMouseX() - 5, container.getInput().getMouseY() - 5, 50, 50);
        }
        //------ Pinta botón atrás ----------
        if (dibujarSobreAtras) {
            atras2.draw(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        } else {
            atras1.draw(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        }
        //------ pintar Borrador ------------

        //-----------------------------------

    }

    /**
     *
     * @param container
     * @param game
     * @param i
     * @throws SlickException
     *
     * Registra los cambios de valores en el juego y los actualiza
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {

        pulsacionX = container.getInput().getMouseX();
        pulsacionY = container.getInput().getMouseY();
        pulsacion.setBounds(pulsacionX, pulsacionY, 2, 2);
        //------ Carga el botón Atrás ------------------------------------------
        if (pulsacion.intersects(rAtras)) {
            dibujarSobreAtras = true;
        } else {
            dibujarSobreAtras = false;
        }
        //------ Carga el botón mas --------------------------------------------
        if (pulsacion.intersects(rMas)) {
            botonMas = true;
        } else {
            botonMas = false;
        }
        //-- Carga el botón menos ----------------------------------------------
        if (pulsacion.intersects(rMenos)) {
            botonMenos = true;
        } else {
            botonMenos = false;
        }
        //-- Cargar Aeropuerto 1 como temporal en el mouse ---------------------
        if (pulsacion.intersects(rAeropuerto1)) {
            aeropuerto1Booleano = true;
        } else {
            aeropuerto1Booleano = false;
        }
        //-- Cargar Aeropuerto 2 como temporal en el mouse ---------------------
        if (pulsacion.intersects(rAeropuerto2)) {
            aeropuerto2Booleano = true;
        } else {
            aeropuerto2Booleano = false;
        }
        //-- Cargar Aeropuerto 3 como temporal en el mouse ---------------------
        if (pulsacion.intersects(rAeropuerto3)) {
            aeropuerto3Booleano = true;
        } else {
            aeropuerto3Booleano = false;
        }
        //-- Cargar Aeropuerto 4 como temporal en el mouse ---------------------
        if (pulsacion.intersects(rAeropuerto4)) {
            aeropuerto4Booleano = true;
        } else {
            aeropuerto4Booleano = false;
        }
        //-- Cargar Aeropuerto temporal de mouse borrador ----------------------
        if (pulsacion.intersects(rBorrador) && juego.aeropuertosMapa.size() > 0) {
            borradorBooleano = true;
        } else {
            borradorBooleano = false;
        }
        // eventos que pasan en la cuadricula
        if (pulsacion.intersects(rCuadricula)) {
            cuadriculaBooleano = true;
        } else {
            cuadriculaBooleano = false;
        }

// Evento de click--------------------------------------------------------------
        if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (dibujarSobreAtras) {
                game.enterState(0);
            }
            if (botonMas) {
                if (juego.minutos < 10) {
                    juego.minutos++;
                }
            }
            if (botonMenos) {
                if (juego.minutos > 2) {
                    juego.minutos--;
                }
            }
            if (aeropuerto1Booleano) {
                rutaImagenTemporal = "recursos/fondos/a1.jpg";
                pintarBorradorTemporal = false;
                imgTemporal = null;
                imgTemporal = new Image(rutaImagenTemporal);
                pintarAeropuerto = true;
                tipoAeropuerto = 5; //esta linea es importante cambia el tipo de aeropuerto
            }
            if (aeropuerto2Booleano) {
                rutaImagenTemporal = "recursos/fondos/a2.jpg";
                pintarBorradorTemporal = false;
                imgTemporal = null;
                imgTemporal = new Image(rutaImagenTemporal);
                pintarAeropuerto = true;
                tipoAeropuerto = 6; //esta linea es importante cambia el tipo de aeropuerto
            }
            if (aeropuerto3Booleano) {
                rutaImagenTemporal = "recursos/fondos/a3.jpg";
                pintarBorradorTemporal = false;
                imgTemporal = null;
                imgTemporal = new Image(rutaImagenTemporal);
                pintarAeropuerto = true;
                tipoAeropuerto = 7; //esta linea es importante cambia el tipo de aeropuerto
            }
            if (aeropuerto4Booleano) {
                rutaImagenTemporal = "recursos/fondos/a4.jpg";
                pintarBorradorTemporal = false;
                imgTemporal = null;
                imgTemporal = new Image(rutaImagenTemporal);
                pintarAeropuerto = true;
                tipoAeropuerto = 8; //esta linea es importante cambia el tipo de aeropuerto
            }
            if (borradorBooleano) {//esta pendiente la prueba
                pintarAeropuerto = false;
                imgTemporal = null;
                imgTemporal = new Image("recursos/fondos/borrador.png");
                pintarBorradorTemporal = true;
            }
            if (cuadriculaBooleano && pintarAeropuerto) {
                int x = pulsacionX;
                int y = pulsacionY;
                while (x % 30 != 0 || y % 30 != 0) {
                    if (x % 30 != 0) {
                        x--;
                    }
                    if (y % 30 != 0) {
                        y--;
                    }
                }
                pintarAeropuerto = false;
                imgTemporal = null;
                juego.aeropuertosMapa.add(new Aeropuerto(rutaImagenTemporal, tipoAeropuerto, x, y));
            }
            if (cuadriculaBooleano && pintarBorradorTemporal) {
                boolean buscar = true;
                int j = 0;
                while (j < juego.aeropuertosMapa.size() && buscar) {
                    if (juego.aeropuertosMapa.get(j).intersects(pulsacion)) {
                        juego.aeropuertosMapa.remove(j);
                        pintarBorradorTemporal = false;
                        imgTemporal = null;
                        buscar = false;
                    }
                    j++;
                }
            }

        }
// Fin eventos de click --------------------------------------------------------
    }
}
