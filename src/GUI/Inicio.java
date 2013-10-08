/*
 * 6/10/2013
 * Muestra las opciones rPlay1 y menú, es la primer pantalla que muestra el juego
 */
package GUI;

import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.io.IOException;
import org.lwjgl.input.Cursor;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author @version 1.0
 */
public class Inicio extends BasicGameState {

    Rectangle rPlay, rMenu;
   
    Image fondo, play1, play2, menu1, menu2;
    boolean dibujarSobrePlay;
    boolean dibujarSobreMenu;
    int XBOTONES = 300;
    int YPLAY = 450;
    int YMUNU = 520;

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

        fondo = new Image("recursos/fondos/fondo menu.jpg");

        play1 = new Image("recursos/fondos/play11.png");
        play2 = new Image("recursos/fondos/play12.png");
        rPlay = new Rectangle(XBOTONES, YPLAY, 190, 50);


        menu1 = new Image("recursos/fondos/menu11.png");
        menu2 = new Image("recursos/fondos/menu12.png");
        rMenu = new Rectangle(XBOTONES, YMUNU, 190, 50);

        dibujarSobrePlay = false;
        dibujarSobreMenu = false;




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
        if (dibujarSobreMenu) {
            menu2.draw(XBOTONES, YMUNU);
        } else {
            menu1.draw(XBOTONES, YMUNU);
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

        if (pulsacion.intersects(rMenu)) {
            dibujarSobreMenu = true;
            if (container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)) {
                game.enterState(1);
            }
        } else {
            dibujarSobreMenu = false;
        }
//        }


    }
}
