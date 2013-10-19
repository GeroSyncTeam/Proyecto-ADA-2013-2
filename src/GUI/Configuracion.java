/*
 * 6/10/2013
 * Se configuran el mapa, el tiempo entre oleada de aviones y el tipo de 
 * aeropuertos
 */
package GUI;

import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;

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
    Rectangle rAtras, rMas, rMenos, pulsacion, rAeropuerto1, rCuadricula;
    Image fondo, atras1, atras2, barraIzquierda, marco, aeropuerto1, imgTemporal;
    LinkedList<Imagen> aeropuertosMapa;
    SpriteSheet signos;
    String rutaImagenTemporal;
    boolean dibujarSobreAtras, dibujartemporal, pintarAeropuerto;
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

    /**
     *
     * @param container
     * @param sbg
     * @throws SlickException
     *
     * Se cargan todos los recursos que se usaran
     */
    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        aeropuertosMapa = new LinkedList<Imagen>();
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

        atras1 = new Image("recursos/fondos/atras1.png");
        atras2 = new Image("recursos/fondos/atras2.png");
        barraIzquierda = new Image("recursos/fondos/barra izquierda2.jpg");
        marco = new Image("recursos/fondos/marco mapa.png");
        aeropuerto1 = new Image("recursos/fondos/a1.jpg");
        signos = new SpriteSheet("recursos/fondos/signos.png", 20, 20);
        rAtras = new Rectangle(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        rMas = new Rectangle(XMAS, YMASMENOS, 20, 20);
        rMenos = new Rectangle(XMENOS, YMASMENOS, 20, 20);
        rCuadricula = new Rectangle(300, 60, 390, 390);
        rAeropuerto1 = new Rectangle(XA1, YA1, 90, 90);
        dibujarSobreAtras = false;



    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        init(container, game);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        aeropuertosMapa=null;
        pulsacion = null;
        fuente = null;
        atras1 = null;
        atras2 = null;
        barraIzquierda = null;
        marco=null;
        rAeropuerto1=null;
        signos = null;
        rAtras = null;
        rMas = null;
        rMenos = null;
        rCuadricula=null;
        rAeropuerto1=null;
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
        for (int i = 0; i < aeropuertosMapa.size(); i++) {
            int x= aeropuertosMapa.get(i).getX();
            int y= aeropuertosMapa.get(i).getY();
            aeropuertosMapa.get(i).getImagen().draw(x, y);
        }
        //----------------------------------
        barraIzquierda.draw(0, 0, 250, 600);
        marco.draw(250, 0);
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
        aeropuerto1.draw(28, 350, 90, 90);
        g.drawRoundRect(28, 350, 90, 90, 2);
        g.drawRoundRect(28, 450, 90, 90, 0);
        g.drawRoundRect(130, 350, 90, 90, 0);
        g.drawRoundRect(130, 450, 90, 90, 0);
        g.setColor(Color.white);
        //------ Pintar imagen temporal------       
        if (pintarAeropuerto) {
            imgTemporal.setAlpha(10f);
            imgTemporal.draw(container.getInput().getMouseX(), container.getInput().getMouseY(), 90, 90);
        }
        //------ Pinta botón atras------
        if (dibujarSobreAtras) {
            atras2.draw(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        } else {
            atras1.draw(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        }
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
        //------ Carga el botón Atrás ------
        if (pulsacion.intersects(rAtras)) {
            dibujarSobreAtras = true;
            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                game.enterState(0);
            }
        } else {
            dibujarSobreAtras = false;
        }
        //------ Carga el botón mas --------
        if (pulsacion.intersects(rMas)) {

            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (juego.minutos < 10) {
                    juego.minutos++;
                }
            }
        }
        //------ Carga el botón menos ------
        if (pulsacion.intersects(rMenos)) {
            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (juego.minutos > 2) {
                    juego.minutos--;
                }
            }
        }
        //  Cargar Imagen temporal de mouse 
        if (pulsacion.intersects(rAeropuerto1)) {
            rutaImagenTemporal="recursos/fondos/a1.jpg";
            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                imgTemporal = new Image(rutaImagenTemporal);
                pintarAeropuerto = true;
            }
        }
        // Pintar aeropuerto en la cuadricula
        if (pulsacion.intersects(rCuadricula)) {
            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && pintarAeropuerto) {
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
                aeropuertosMapa.add(new Imagen(rutaImagenTemporal, x, y));            
            }
        }
        //----------------------------------

    }
}
