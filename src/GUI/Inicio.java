/*
 * 6/10/2013
 * Muestra las opciones rPlay1 y menú, es la primer pantalla que muestra el juego
 */
package GUI;

import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.io.IOException;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author @version 1.0
 */
public class Inicio extends BasicGameState {

    Rectangle rPlay, rDefinir, rCreditos;
    Image fondo, play1, play2, definir1, definir2, creditos1, creditos2;
    boolean dibujarSobrePlay;
    boolean dibujarSobreDefinir;
    boolean dibujarSobreCreditos;
    int XBOTONES;
    int YPLAY = 340;
    int YDEFINIR = 410;
    int YCREDITOS = 480;

    /**
     *
     * @return 0 es el id que la identifica
     */
    @Override
    public int getID() {
        return 0;
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
XBOTONES=container.getWidth()/6;
        fondo = new Image("recursos/fondos/fondo menu.jpg");

        play1 = new Image("recursos/fondos/play11.png");
        play2 = new Image("recursos/fondos/play12.png");
        rPlay = new Rectangle(XBOTONES, YPLAY, 190, 50);


        definir1 = new Image("recursos/fondos/definir1.png");
        definir2 = new Image("recursos/fondos/definir2.png");
        rDefinir = new Rectangle(XBOTONES, YDEFINIR, 300, 50);

        creditos1 = new Image("recursos/fondos/creditos1.png");
        creditos2 = new Image("recursos/fondos/creditos2.png");
        rCreditos = new Rectangle(XBOTONES, YCREDITOS, 190, 50);// posible bug

        dibujarSobrePlay = false;
        dibujarSobreDefinir = false;
        dibujarSobreCreditos = false;




    }

    /**
     *
     * @param container
     * @return La fuente cargada o la fuente por defecto en caso de ocurrir una
     * excepción
     */
    private Font cargarFuente(GameContainer container) {
        try {
            TrueTypeFont font2;
            java.io.InputStream inputStream = ResourceLoader.getResourceAsStream("recursos/Circles-3D.ttf");
            java.awt.Font awtFont2 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(32f); // set font size
            return font2 = new TrueTypeFont(awtFont2, false);
        } catch (FontFormatException | IOException e) {
            return container.getDefaultFont();
        }
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
        fondo.draw(0, 0);

        if (dibujarSobrePlay) {
            play2.draw(XBOTONES, YPLAY);
        } else {
            play1.draw(XBOTONES, YPLAY);
        }
        
        if (dibujarSobreDefinir) {
            definir2.draw(XBOTONES, YDEFINIR);
        } else {
            definir1.draw(XBOTONES, YDEFINIR);
        }
        
        if (dibujarSobreCreditos) {
            creditos2.draw(XBOTONES, YCREDITOS);
        } else {
            creditos1.draw(XBOTONES, YCREDITOS);
        }

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

//        if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
        int x = container.getInput().getMouseX();
        int y = container.getInput().getMouseY();
        Rectangle pulsacion = new Rectangle(x, y, 2, 2);

        if (pulsacion.intersects(rPlay)) {
            dibujarSobrePlay = true;
            if (container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)) {
                game.enterState(2);
            }
        } else {
            dibujarSobrePlay = false;
        }

        if (pulsacion.intersects(rDefinir)) {
            dibujarSobreDefinir = true;
            if (container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)) {
                game.enterState(3);
            }
        } else {
            dibujarSobreDefinir = false;
        }
        
        if (pulsacion.intersects(rCreditos)) {
            dibujarSobreCreditos = true;
            if (container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)) {
                game.enterState(1);
            }
        } else {
            dibujarSobreCreditos = false;
        }
//        }


    }
}
