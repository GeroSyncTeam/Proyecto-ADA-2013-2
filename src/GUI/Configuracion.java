/*
 * 6/10/2013
 * Se configuran el mapa, el tiempo entre oleada de aviones y el tipo de 
 * aeropuertos
 */
package GUI;

import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.io.IOException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Gero
 */
public class Configuracion extends BasicGameState {

    TextField minutos;
    Rectangle rAtras;
    Image fondo, atras1, atras2, barraIzquierda;
    boolean dibujarSobreAtras;
    int XATRAS = 10;
    int YATRAS = 540;

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
        
        atras1 = new Image("recursos/fondos/atras1.png");
        atras2 = new Image("recursos/fondos/atras2.png");
        barraIzquierda = new Image("recursos/fondos/barra izquierda.jpg");
        rAtras = new Rectangle(XATRAS, YATRAS, 150, 30);
        minutos = new TextField(container, cargarFuente(container, 25f), 30, 50, 60, 25);
        
        dibujarSobreAtras = false;
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
        barraIzquierda.draw(0, 0);
        
        g.setFont(cargarFuente(container,25f));
        g.setColor(Color.lightGray);
        g.drawString("Definir Tiempo", 26, 20);
        g.setColor(Color.white);
        g.drawString("Definir Tiempo", 27, 20);
        
        g.setColor(Color.lightGray);
        g.drawString("minutos", 100, 50);
        g.setColor(Color.white);
        g.drawString("minutos", 101, 50);
        minutos.setBackgroundColor(Color.red);
        minutos.render(container, g);
        cargarAtrasRender();
//        g.drawLine(250,0, 250,800);
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
        cargarAtrasUpdate(container, game);
    }

    private void cargarAtrasUpdate(GameContainer container, StateBasedGame game) {
        int x = container.getInput().getMouseX();
        int y = container.getInput().getMouseY();
        Rectangle pulsacion = new Rectangle(x, y, 2, 2);

        if (pulsacion.intersects(rAtras)) {
            dibujarSobreAtras = true;
            if (container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)) {
                game.enterState(0);
            }
        } else {
            dibujarSobreAtras = false;
        }
    }

    private void cargarAtrasRender() {

        if (dibujarSobreAtras) {
            atras2.draw(XATRAS, YATRAS,150,30);
        } else {
            atras1.draw(XATRAS, YATRAS,150,30);
        }
    }

    private Font cargarFuente(GameContainer container, float tamaño) {
        try {
            TrueTypeFont font2;
            java.io.InputStream inputStream = ResourceLoader.getResourceAsStream("recursos/Action Man Shaded.ttf");
            java.awt.Font awtFont2 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(tamaño); // set font size
            return font2 = new TrueTypeFont(awtFont2, false);
        } catch (FontFormatException | IOException e) {
            return container.getDefaultFont();
        }
    }
}
