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
    Rectangle pulsacion;
    Font fuente;
    Rectangle rAtras, rMas, rMenos;
    Image fondo, atras1, atras2, barraIzquierda, marco;
    SpriteSheet signos;
    boolean dibujarSobreAtras;
    int XATRAS = 17;
    int YATRAS = 560;
    int XMAS = 170;
    int XMENOS = 200;
    int YMASMENOS = 50;
    int ANCHOATRAS=80;
    int ALTOATRAS=25;

    

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
        pulsacion = new Rectangle(2, 2);
        fuente = cargarFuente(container, 23f);
        atras1 = new Image("recursos/fondos/atras1.png");
        atras2 = new Image("recursos/fondos/atras2.png");
        barraIzquierda = new Image("recursos/fondos/barra izquierda2.jpg");
        marco = new Image("recursos/fondos/marco mapa.png");
        signos = new SpriteSheet("recursos/fondos/signos.png", 20, 20);
        rAtras = new Rectangle(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        rMas = new Rectangle(XMAS, YMASMENOS, 20, 20);
        rMenos = new Rectangle(XMENOS, YMASMENOS, 20, 20);

        dibujarSobreAtras = false;

    }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException{
        init(container, game);
    }
    
    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {       
        pulsacion = null;
        fuente = null;
        atras1 = null;
        atras2 = null;
        barraIzquierda = null;
        signos = null;
        rAtras = null;
        rMas = null;
        rMenos = null;
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
        barraIzquierda.draw(0, 0, 250, 600);
        marco.draw(250, 0);
        int x1=300,y1=75,aumento=0;
        for (int i = 0; i <= 15; i++) {
            g.drawLine(x1+aumento, y1, x1+aumento, 525);
            g.drawLine(x1, y1+aumento, 750, y1+aumento);
            aumento+=30;
            
        }
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
        g.drawRoundRect(28, 350, 90, 90, 0);
        g.drawRoundRect(28, 450, 90, 90, 0);
        g.drawRoundRect(130, 350, 90, 90, 0);
        g.drawRoundRect(130, 450, 90, 90, 0);
        


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
        masUpdate(container);
        menosUpdate(container);
        
    }
    
    private void cargarAtrasUpdate(GameContainer container, StateBasedGame game) {
        int x = container.getInput().getMouseX();
        int y = container.getInput().getMouseY();
        pulsacion.setBounds(x, y, 2, 2);

        if (pulsacion.intersects(rAtras)) {
            dibujarSobreAtras = true;
            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                game.enterState(0);
            }
        } else {
            dibujarSobreAtras = false;
        }
    }

    private void masUpdate(GameContainer container) {
        int x = container.getInput().getMouseX();
        int y = container.getInput().getMouseY();
        pulsacion.setBounds(x, y, 2, 2);

        if (pulsacion.intersects(rMas)) {
            
            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
               if(juego.minutos<10)
                juego.minutos++;
            }
        } 
    }
    
    private void menosUpdate(GameContainer container) {
        int x = container.getInput().getMouseX();
        int y = container.getInput().getMouseY();
        pulsacion.setBounds(x, y, 2, 2);

        if (pulsacion.intersects(rMenos)) {
            
            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
               if(juego.minutos>2)
                juego.minutos--;
            }
        } 
    }

    private void cargarAtrasRender() {

        if (dibujarSobreAtras) {
            
            atras2.draw(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        } else {
            atras1.draw(XATRAS, YATRAS, ANCHOATRAS, ALTOATRAS);
        }
    }


    private Font cargarFuente(GameContainer container, float tamaño) {
        try {
            java.io.InputStream inputStream = ResourceLoader.getResourceAsStream("recursos/Action Man Shaded.ttf");
            java.awt.Font awtFont2 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(tamaño); // set font size
            return new TrueTypeFont(awtFont2, false);
        } catch (FontFormatException | IOException e) {
            return container.getDefaultFont();
        }
    }
}
