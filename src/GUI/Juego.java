/*
 * 6/10/2013
 * Es donde se despliega el juego
 */
package GUI;

import clases.Aeropuerto;
import clases.AlgoritmoDijkstra;
import clases.Avion;
import clases.Sector;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Gero
 * @version 1.0
 */
public class Juego extends BasicGameState {

    public int minutos;
    public CopyOnWriteArrayList<Aeropuerto> aeropuertosMapa;
    CopyOnWriteArrayList<Avion> aviones;
    CopyOnWriteArrayList<Point> nodosAviones;
    Image barraIzquierda, marco, fondo;
    Sector mapa[];
    Rectangle rMapa;
    AlgoritmoDijkstra grafo;
    Avion avion, avion3, avion4;
    Avion avion2;
//    Animation a1;

    public Juego() {
        minutos = 2;
        aeropuertosMapa = new CopyOnWriteArrayList<Aeropuerto>();

    }

    /**
     *
     * @return 2 es el id que lo identifica
     */
    @Override
    public int getID() {
        return 2;
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
        // inicializa los sectores-------------------
        mapa = new Sector[225];
        rMapa = new Rectangle(300, 60, 450, 450);
        int x;
        int y = 60;
        int id = 0;
        for (int i = 0; i < 15; i++) {
            x = 300;
            for (int j = 0; j < 15; j++) {
                mapa[id] = new Sector(id, x, y);

                id++;
                x += 30;
            }

            y += 30;
        }
        //-------------------------------------------
        grafo = new AlgoritmoDijkstra();
        actualizarPosicionAeropuertos(mapa); //carga en el grafo en la posicion correcta el tipo de aeropuerto  especifico 
        aviones = new CopyOnWriteArrayList<Avion>();
        nodosAviones = new CopyOnWriteArrayList<Point>();
        barraIzquierda = new Image("recursos/fondos/barra izquierda2.jpg");
        fondo = new Image("recursos/fondos/Fondo.jpg");
        avion = new Avion(5, 200, 90); //este es un avión de prueba de 1 a 4
        asignarDestinosAvion(avion);
        avion2 = new Avion(3, 200, 150); //este es un avión de prueba de 1 a 4
        asignarDestinosAvion(avion2);
        avion3 = new Avion(1, 200, 250); //este es un avión de prueba de 1 a 4
        asignarDestinosAvion(avion3);
        avion4 = new Avion(4, 200, 350); //este es un avión de prueba de 1 a 4
        asignarDestinosAvion(avion4);
        aviones.add(avion);
//        aviones.add(avion2);
//        a1 = new Animation(avion.rutasSpriteSheet, 180);
//        a1.setPingPong(true);
//        marco = new Image("recursos/fondos/marco mapa.png");
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
        fondo.draw(250, 0);
        int x1 = 300, y1 = 60, aumento = 0;
        for (int i = 0; i <= 15; i++) {
            g.drawLine(x1 + aumento, y1, x1 + aumento, 510);
            g.drawLine(x1, y1 + aumento, 750, y1 + aumento);
            aumento += 30;
        }
        //------ Pinta aeropuertos del mapa 
        for (int i = 0; i < aeropuertosMapa.size(); i++) {
            int x = aeropuertosMapa.get(i).x;
            int y = aeropuertosMapa.get(i).y;
            int ancho = aeropuertosMapa.get(i).width;
            int alto = aeropuertosMapa.get(i).height;
            aeropuertosMapa.get(i).getImagen().draw(x, y, ancho, alto);
        }
        //----------------------------------


        g.drawImage(avion.rutasSpriteSheet[5], avion.x, avion.y);
        g.drawImage(avion2.rutasSpriteSheet[5], avion2.x, avion2.y);
        g.drawImage(avion3.rutasSpriteSheet[5], avion3.x, avion3.y);
        g.drawImage(avion4.rutasSpriteSheet[5], avion4.x, avion4.y);

//        marco.draw(250, 0);
    }

    /**
     *
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     *
     * Registra los cambios de valores en el juego y los actualiza
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        // código de prueba avion1----------------------------------------------
        if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
            avion.x += 4;
            System.out.println(" x " + avion.x);
        }
        if (container.getInput().isKeyPressed(Input.KEY_DOWN)) {
            avion.y += 4;
            System.out.println(" y " + avion.y);
        }
        if (container.getInput().isKeyPressed(Input.KEY_UP)) {
            avion.y -= 4;
            System.out.println(" y " + avion.y);
        }
        if (container.getInput().isKeyPressed(Input.KEY_LEFT)) {
            avion.x -= 4;
            System.out.println(" x " + avion.x);
        }
        //----------------------------------------------------------------------
        // código de prueba avion2----------------------------------------------
        if (container.getInput().isKeyPressed(Input.KEY_D)) {
            avion2.x += 4;
            System.out.println(" x " + avion2.x);
        }
        if (container.getInput().isKeyPressed(Input.KEY_S)) {
            avion2.y += 4;
            System.out.println(" y " + avion2.y);
        }
        if (container.getInput().isKeyPressed(Input.KEY_W)) {
            avion2.y -= 4;
            System.out.println(" y " + avion2.y);
        }
        if (container.getInput().isKeyPressed(Input.KEY_A)) {
            avion2.x -= 4;
            System.out.println(" x " + avion2.x);
        }
        //----------------------------------------------------------------------
        // código de prueba avion4----------------------------------------------
        if (container.getInput().isKeyPressed(Input.KEY_H)) {
            avion4.x += 4;

        }
        if (container.getInput().isKeyPressed(Input.KEY_G)) {
            avion4.y += 4;

        }
        if (container.getInput().isKeyPressed(Input.KEY_T)) {
            avion4.y -= 4;

        }
        if (container.getInput().isKeyPressed(Input.KEY_F)) {
            avion4.x -= 4;

        }
        //----------------------------------------------------------------------
        // código de prueba avion4----------------------------------------------
        if (container.getInput().isKeyPressed(Input.KEY_L)) {
            avion3.x += 4;

        }
        if (container.getInput().isKeyPressed(Input.KEY_K)) {
            avion3.y += 4;

        }
        if (container.getInput().isKeyPressed(Input.KEY_I)) {
            avion3.y -= 4;

        }
        if (container.getInput().isKeyPressed(Input.KEY_J)) {
            avion3.x -= 4;

        }
        //----------------------------------------------------------------------

        nodosAviones.clear(); // limpia la lista que guarda en que posición de el mapa se encuentra cada avión
        //Reasignar posición a un avion en el mapa -----------------------------
        for (int i = 0; i < aviones.size(); i++) {
            actualizarPosicionAviones(mapa, aviones.get(i));
        }
        grafo.reiniciarTiposSector();
        for (int j = 0; j < nodosAviones.size(); j++) {
            int posicion = nodosAviones.get(j).x;//posición en el vector
            if (grafo.tiposDeSector[posicion] < 6) {// deben ser menores a para que no cambie los valores de los id de los aeropuertos que van de 6 a 9
                int id = nodosAviones.get(j).y;
                grafo.tiposDeSector[posicion] = id;// marca ese punto ocupado por un avi
            }
        }
        //llamo el metodo de la la clase AlgoritmoDijkstra que actualiza los enlaces
        grafo.enlazarGrafo();
        //muestra la posicion de cada avion en el grafo siendo esta el ultimo sector con el que se registro insersección
        if (container.getInput().isKeyPressed(Input.KEY_Z)) {
            for (int i = 0; i < aviones.size(); i++) {
                System.out.println(i + " posición por la izquierda " + aviones.get(i).posicionEnGrafoIzquierda);
                System.out.println(i + " posición por la derecha " + aviones.get(i).posicionEnGrafoDerecha);
            }

        }
        //----------------------------------------------------------------------
        //justo en esta parte debo elegir la ruta para cada avión
        for (int i = 0; i < aviones.size(); i++) {
            if (rMapa.intersects(aviones.get(i))) {
                if (!(aviones.get(i).ruta == null)) {// si no es nula la 
                    int origen;
                    if (aviones.get(i).usarPosicionIzquierda) {
                        origen = aviones.get(i).posicionEnGrafoIzquierda;
                    } else {
                        origen = aviones.get(i).posicionEnGrafoDerecha;
                    }
                    int indiceRuta = aviones.get(i).indiceRuta;

                    if (indiceRuta < aviones.get(i).ruta.length) {
                        int destino = Integer.parseInt(aviones.get(i).ruta[indiceRuta]);
                        //verificar que no haya colisión
                        if (grafo.grafo[origen][destino] < Integer.MAX_VALUE) {
                            //dar paso
                            boolean sincronizadoX = true;
                            if (aviones.get(i).x == mapa[destino].x) {
                                sincronizadoX = false;
                            }
                            if (sincronizadoX) {
                                if (aviones.get(i).x < mapa[destino].x) {
                                    aviones.get(i).x++;
                                } else {
                                    aviones.get(i).x--;
                                }
                            }
                            boolean sincronizadoY = true;
                            if (aviones.get(i).y == mapa[destino].y) {
                                sincronizadoY = false;
                            }
                            if (sincronizadoY) {
                                if (aviones.get(i).y < mapa[destino].y) {
                                    aviones.get(i).y++;
                                } else {
                                    aviones.get(i).y--;
                                }
                            }
                            if (!sincronizadoX && !sincronizadoY) {
                                aviones.get(i).indiceRuta++;
                            }
                            //fin dar paso
                        } else {
//                            System.out.println("infinito");
//                            System.out.println("izquierda " + limiteIzq + " derecha " + limiteDer + " destino " + destino);
//                            System.out.println("distancia izquierda a destino " + grafo.grafo[limiteIzq][destino]);
//                            System.out.println("distancia derecha a destino " + grafo.grafo[limiteDer][destino]);
                            aviones.get(i).indiceRuta = 1;

                            aviones.get(i).ruta = null;
                        }
//                        if (aviones.get(i).ruta.length < aviones.get(i).indiceRuta) {
//                            aviones.remove(i);
//                        }
                    } else {
//                       aviones.remove(i);
                    }
                } else {//si la ruta del avión es nula
                    calcularRuta(aviones.get(i));
                }
            }
        }
        //codigo de prueba------------------------------------------------------
        if (container.getInput().isKeyPressed(Input.KEY_E)) {
            System.out.println("helicópetero");
            for (int i = 0; i < aviones.get(0).ruta.length; i++) {
                System.out.print(aviones.get(0).ruta[i] + " ");
            }
            System.out.println("");
//            System.out.println("avión");
//            for (int i = 0; i < aviones.get(1).ruta.length; i++) {
//                System.out.print(aviones.get(1).ruta[i] + " ");
//            }
        }
        //----------------------------------------------------------------------
        //-------------- Muestra el grafo (temporal) ---------------------------
        if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
            for (int i = 0; i < 225; i++) {
                if (i % 15 == 0) {
                    System.out.println("");
                }
                System.out.print("  " + grafo.tiposDeSector[i]);
            }
            System.out.println("");
//            for (int i = 0; i < 225; i++) {
//                if (i % 15 == 0) {
//                    System.out.println("");
//                }
//                System.out.print("\t  " + grafo.nodos[i]);
//            }
//            System.out.println("");
        }
        //------------------------------------------------------------
    }

    /**
     *
     * @param container
     * @param game
     * @throws SlickException Este método se alza al momento de entrar en este
     * BasicGameState
     */
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        init2(container, game);
    }

    /**
     *
     * @param container
     * @param game
     * @throws SlickException Este método libera los recursos en memoria
     * utilizados en este BasicGameState
     */
    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        mapa = null;
        grafo = null;
        aviones = null;
        nodosAviones = null;
        barraIzquierda = null;
        avion = null; //este es un avión de prueba
        avion2 = null; //este es un avión de prueba
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    /**
     *
     * @param sectores
     * @param avion
     * @param encontrados
     *
     * Compara las posiciones de los aviones con las posiciones de casa sector
     * del mapa con el fin de encontrar en que posicion exacta se encuentra el
     * avion y las guarda en un objeto de tipo Point x= posición del avión en el
     * mapa y= id del avión
     */
    public void actualizarPosicionAviones(Sector[] sectores, Avion avion) {
        int inicio = 0;
        int fin = sectores.length - 1;
        int medio = (inicio + fin) / 2;
        boolean elPrimero = true;

        while (medio < sectores.length) {
            if (inicio < (fin / 2) && sectores[inicio].intersects(avion)) {
                if (elPrimero) {
                    avion.posicionEnGrafoIzquierda = sectores[inicio].getId();
                    elPrimero = false;
                }
                avion.posicionEnGrafoDerecha = sectores[inicio].getId();
                nodosAviones.add(new Point(sectores[inicio].getId(), avion.tipo));
            }
            if (sectores[medio].intersects(avion)) {
                if (elPrimero) {
                    avion.posicionEnGrafoIzquierda = sectores[medio].getId();
                    elPrimero = false;
                }
                avion.posicionEnGrafoDerecha = sectores[medio].getId();
                nodosAviones.add(new Point(sectores[medio].getId(), avion.tipo));
            }
            if (!rMapa.intersects(avion)) {
                avion.posicionEnGrafoIzquierda = -1;
            }
            inicio++;
            medio++;
        }
    }

    /**
     *
     * @param sectores arreglo de sectores que representa el mapa en partes Este
     * método actualiza la posición de de aeropuertos en el grafo para generar
     * los caminos y setea la variable posicionEnGrafoIzquierda del aeropuerto
     */
    public void actualizarPosicionAeropuertos(Sector[] sectores) {
        for (int i = 0; i < aeropuertosMapa.size(); i++) {
            boolean next = true;
            int iterador = 0;
            while (iterador < sectores.length && next) {
                if (aeropuertosMapa.get(i).intersects(sectores[iterador])) {
                    grafo.tiposDeSector[iterador] = aeropuertosMapa.get(i).getTipo();
                    aeropuertosMapa.get(i).posicionEnGrafo = iterador;
                    next = false;
                }
                iterador++;
            }
        }
    }

    /**
     *
     * @param avion Este método agrega al objeto avión los id de los aeropuertos
     * a la variable estinos si el id del aeropuerto es igual a alguno de los id
     * de los aeropuertos donde puede aterrizar el avión
     */
    public void asignarDestinosAvion(Avion avion) {
        for (int i = 0; i < aeropuertosMapa.size(); i++) {
            if (avion.aeropuertosValidos[0] == aeropuertosMapa.get(i).getTipo()
                    || avion.aeropuertosValidos[1] == aeropuertosMapa.get(i).getTipo()) {
                avion.destinos.add(aeropuertosMapa.get(i).posicionEnGrafo);
            }
        }
    }

    public void calcularRuta(Avion avion) {
        int maximo = Integer.MAX_VALUE;
        String ruta = "";
        for (int j = 0; j < avion.destinos.size(); j++) {
            String rutaTemporalLimiteIzquierda = grafo.encontrarRutaMinimaDijkstra(avion.posicionEnGrafoIzquierda, avion.destinos.get(j));
            String rutaTemporalLimiteDerecha = grafo.encontrarRutaMinimaDijkstra(avion.posicionEnGrafoDerecha, avion.destinos.get(j));
            int minimoIzquierda = rutaTemporalLimiteIzquierda.length();
            int minimoDerecha = rutaTemporalLimiteDerecha.length();

            if (maximo > minimoIzquierda || maximo > minimoDerecha) {
                if (minimoIzquierda <= minimoDerecha) {
                    ruta = rutaTemporalLimiteIzquierda;
                    maximo = minimoIzquierda;
                    avion.usarPosicionIzquierda = true;
                } else {
                    ruta = rutaTemporalLimiteDerecha;
                    maximo = minimoDerecha;
                    avion.usarPosicionIzquierda = false;
                }
            }
        }
        avion.ruta = ruta.split(" ");
    }

    public boolean verificarColision(int limiteIzq, int limiteDer, int destino) {

        return false;
    }
//    if ((destino == limiteIzq + 1) || (destino == limiteIzq + 15)) {
//        }
}
