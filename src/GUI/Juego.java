/*
 * 6/10/2013
 * Es donde se despliega el juego
 */
package GUI;

import clases.Aeropuerto;
import clases.AlgoritmoDijkstra;
import clases.Avion;
import clases.Ordenes;
import clases.Sector;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Hashtable;

import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Timer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Gero
 * @version 1.0
 */
public class Juego extends BasicGameState {

    public int minutos;
    public int linea;
    private int xSalida, ySalida;
    Ordenes ordenSalida[];
    Ordenes ordenDibujo[];
    public CopyOnWriteArrayList<Aeropuerto> aeropuertosMapa;
    Hashtable<Integer, Avion> contenedorAviones;
    CopyOnWriteArrayList<Avion> aviones;
    CopyOnWriteArrayList<Point> nodosAviones;
    Image barraIzquierda, fondo;
    Sector mapa[];
    Rectangle rMapa;
    AlgoritmoDijkstra grafo;
    Avion avion, avion3, avion4;
    Avion avion2;
    Timer tiempo;
//    Animation a1;

    public Juego() {
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
        contenedorAviones = new Hashtable<Integer, Avion>();

        //-------------------------------------------
        contenedorAviones = new Hashtable<Integer, Avion>();
        //inicializa, crea y ordena vectores de salida y dibujado
        obtenerVariables();
        mergeSort(ordenSalida, 0, ordenSalida.length-1);
        mergeSort(ordenDibujo, 0, ordenDibujo.length-1);
        //-----------------------------------------------------
        grafo = new AlgoritmoDijkstra();
        actualizarPosicionAeropuertos(mapa); //carga en el grafo en la posicion correcta el tipo de aeropuerto especifico 
        aviones = new CopyOnWriteArrayList<Avion>();
        nodosAviones = new CopyOnWriteArrayList<Point>();
        barraIzquierda = new Image("recursos/fondos/barra izquierda2.jpg");
        fondo = new Image("recursos/fondos/Fondo.jpg");
        avion = new Avion(5, 200, 90); //este es un avión de prueba de 1 a 4
        asignarDestinosAvion(avion);
        avion2 = new Avion(3, 200, 200); //este es un avión de prueba de 1 a 4
        asignarDestinosAvion(avion2);
        avion3 = new Avion(2, 200, 310); //este es un avión de prueba de 1 a 4
        asignarDestinosAvion(avion3);
        avion4 = new Avion(4, 200, 450); //este es un avión de prueba de 1 a 4
        asignarDestinosAvion(avion4);
        aviones.add(avion);
        aviones.add(avion2);
        aviones.add(avion3);
        aviones.add(avion4);
//        a1 = new Animation(avion.rutasSpriteSheet, 180);
//        a1.setPingPong(true);
//        marco = new Image("recursos/fondos/marco mapa.png");
//        tiempo = new Timer(1000, null);
//        tiempo.start();

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
            g.drawLine(x1 + aumento, y1, x1 + aumento, 510);//pinta lineas verticales
            g.drawLine(x1, y1 + aumento, 750, y1 + aumento);//pinta lineas horizontales
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
                System.out.println(i + " posición por la izquierda " + aviones.get(i).posicionEnGrafoIzquierdaArriba);
                System.out.println(i + " posición por la derecha " + aviones.get(i).posicionEnGrafoDerechaAbajo);
            }

        }
        //----------------------------------------------------------------------
        //justo en esta parte debo elegir la ruta para cada avión
        for (int i = 0; i < aviones.size(); i++) {
            aviones.get(i).setRetraso(aviones.get(i).getRetraso() + 1);
            if (rMapa.intersects(aviones.get(i))) {
                if (aviones.get(i).ruta != null) {// si no es nula la    
//                    for (int j = 0; j < aviones.get(i).ruta.length; j++) {
//                        System.out.print(" "+aviones.get(i).ruta[j]);
//                        
//                    }
//                    System.out.println("");
                    int indiceRuta = aviones.get(i).indiceRuta;
                    int origen = indiceRuta - 1;
//                    if (aviones.get(i).usarPosicionIzquierda) {
//                        origen = aviones.get(i).posicionEnGrafoIzquierdaArriba;
//                    } else {
//                        origen = aviones.get(i).posicionEnGrafoDerechaAbajo;
//                    }

                    if (indiceRuta < aviones.get(i).ruta.length) {
                        int destino = Integer.parseInt(aviones.get(i).ruta[indiceRuta]);//aca eescoje el destino
                        //verificar que no haya colisión
                        if (grafo.grafo[origen][destino] < Integer.MAX_VALUE) {
                            //dar paso
//                            if (aviones.get(i).retraso > 100) {
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
                                aviones.get(i).setIndiceRuta(aviones.get(i).getIndiceRuta() + 1);
                            }

                            aviones.get(i).retraso = 0;
//                            }
                            //fin dar paso
                        } else {///se supone que es cuando hay colisión
                            System.out.println("recalculo ruta del avion de tipo " + aviones.get(i).tipo);
                            aviones.get(i).ruta = null;
                            aviones.get(i).indiceRuta = 1;
                        }
                    }
                } else {//si la ruta del avión es nula
                    calcularRuta(aviones.get(i));
                    System.out.println(" ruta del avion de tipo " + aviones.get(i).tipo);

                }
            } else {//aca se hace el aumento de posición en x para que entren a la zona de vuelo

//                if (aviones.get(i).retraso > 100) {
                aviones.get(i).x++;
                aviones.get(i).retraso = 0;
//                }
            }
        }
        //codigo de prueba------------------------------------------------------
        if (container.getInput().isKeyPressed(Input.KEY_E)) {

            for (int j = 0; j < aviones.size(); j++) {
                System.out.println("----------------------------------");
                for (int i = 0; i < aviones.get(j).ruta.length; i++) {
                    System.out.print(aviones.get(j).ruta[i] + " ");
                }
                System.out.println("");
            }

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
        System.out.println("minutos " + minutos);
        System.out.println("linea " + linea);
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
     * del mapa con el fin de encontrar en que posicion exacta donde se
     * encuentra el avión y las guarda en un objeto de tipo Point x= posición
     * del avión en el mapa y= id del avión
     */
    public void actualizarPosicionAviones(Sector[] sectores, Avion avion) {
        int inicio = 0;
        int fin = sectores.length - 1;
        int medio = (inicio + fin) / 2;
        boolean elPrimero = true;

        while (medio < sectores.length) {
            if (inicio < (fin / 2) && sectores[inicio].intersects(avion)) {
                if (elPrimero) {
                    avion.posicionEnGrafoIzquierdaArriba = sectores[inicio].getId();
                    elPrimero = false;
                }
                avion.posicionEnGrafoDerechaAbajo = sectores[inicio].getId();
                nodosAviones.add(new Point(sectores[inicio].getId(), avion.tipo));
            }
            if (sectores[medio].intersects(avion)) {
                if (elPrimero) {
                    avion.posicionEnGrafoIzquierdaArriba = sectores[medio].getId();
                    elPrimero = false;
                }
                avion.posicionEnGrafoDerechaAbajo = sectores[medio].getId();
                nodosAviones.add(new Point(sectores[medio].getId(), avion.tipo));
            }
            if (!rMapa.intersects(avion)) {
                avion.posicionEnGrafoIzquierdaArriba = -1;
            }
            inicio++;
            medio++;
        }
    }

    /**
     *
     * @param sectores arreglo de sectores que representa el mapa en partes Este
     * método actualiza la posición de de aeropuertos en el grafo para generar
     * los caminos y setea la variable posicionEnGrafoIzquierdaArriba del
     * aeropuerto
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

    /**
     *
     * @param avion Este método escoje el mejor camino desde el punto en el que
     * se encuentra el avión hasta uno de sus posibles aeropuertos de destino
     */
    public void calcularRuta(Avion avion) {
        int maximo = Integer.MAX_VALUE;
        String ruta = "";
        for (int j = 0; j < avion.destinos.size(); j++) {
            String rutaTemporalLimiteIzquierda = grafo.encontrarRutaMinimaDijkstra(avion.posicionEnGrafoIzquierdaArriba, avion.destinos.get(j));
            String rutaTemporalLimiteDerecha = grafo.encontrarRutaMinimaDijkstra(avion.posicionEnGrafoDerechaAbajo, avion.destinos.get(j));
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

    
    /**
     * Busca que tipo de aviones se puden crear y que cantidad
     * les asigna la linea de salida y los añade al hash de aviones
     */
    public void crearAviones() {
        int[] tipoaviones = new int[2];
        int cantidadAviones = 4 * minutos;
        Avion avion = new Avion();

        for (int i = 0; i < cantidadAviones; i++) {
            tipoaviones = avion.ConsultaAvionesAeroupuerto(idCrearAvionSegunAeropuertos());
        }

        avion = new Avion(tipoaviones[(int) Math.round(Math.random())], 0, 0);
        avion = generarXY(avion);
        contenedorAviones.put(contenedorAviones.size(), avion);;       
    }

    public int idCrearAvionSegunAeropuertos() {
        int idAeropuerto = -1;
        do {
            idAeropuerto = (int) Math.round(Math.random() * 4);
        } while (tipoAeropuerto[idAeropuerto] >= 0);
        return idAeropuerto;
    }

    /**
     * Inicio del mapa aereo X=300 XFinal=750 -- Y=60 YFinal=510 (250,0) inicio
     * campo (765,540)
     *
     * Recibe un avion el cual le va a indicar sus posiciones xy respecto a la
     * linea de salida
     *
     *
     *
     */
    public Avion generarXY(Avion a) {
        switch (linea) {
            case 0: // izquierda
                a.x = 0;
                a.y = (int) (Math.round(Math.random() * 520));
                break;
            case 1: // Arriba                
                a.x = (int) (Math.round(Math.random() * 465)) + 300;
                a.y = 0;
                break;
            case 2: // Derecha
                a.x = 765;
                a.y = (int) (Math.round(Math.random() * 520));
                break;
            case 3: // Abajo
                a.x = (int) (Math.round(Math.random() * 465)) + 300;
                a.y = 540;
                break;
        }

        return a;
    }

    public void obtenerVariables() {
        ordenSalida = new Ordenes[contenedorAviones.size()];
        ordenDibujo = new Ordenes[contenedorAviones.size()];
        for (int i = 0; i < contenedorAviones.size(); i++) {
            ordenSalida[i] = new Ordenes(i, contenedorAviones.get(i).velocidad);
            ordenDibujo[i] = new Ordenes(i, contenedorAviones.get(i).altura);
        }
    }

    /**
     * @param A el arreglo a ordenar
     * @param p indice desde donde comienza el ordenamiento generalmente es 0
     * @param r indice del final del arreglo
     *
     * Este método verifica si el arreglo no esta ordenado, y en caso de no
     * estarlo lo ordena dividiendo el tamaño del arreglo de manera recursiva y
     * haciendo un llamado al metodo merge encargado de mezclar las soluciones
     * que va entregando la recurción
     *
     * T(n) = 2T(n/2) + cn T(n) = O(nlg(n))
     */
    private static void mergeSort(Ordenes[] A, int p, int r) {
        // verifica si p es menor que r, sino se ordena el arreglo
        if (p < r) {
            // obtiene el indice del elemento en la mitad
            int q = (p + r) / 2;
            // ordena el lado izquierdo del arreglo
            mergeSort(A, p, q);
            // ordena el lado derecho del arreglo
            mergeSort(A, q + 1, r);
            // convina los dos lados del arreglo
            merge(A, p, q, r);
        }
    }

    /**
     * @param A el arreglo a ordenar
     * @param p indice desde donde comienza el ordenamiento generalmente es 0
     * @param q indice que marca la mitad del arreglo
     * @param r indice del final del arreglo
     *
     * Este método utilizando una copia del arreglo A divide el arreglo en dos y
     * verifica que el orden que tienen los números sea el correcto, de lo
     * contrario los acomoda de menor a mayor mediante un proceso de mezcla
     *
     * T(n)= cn T(n)=O(n)
     */
    private static void merge(Ordenes A[], int p, int q, int r) {
        Ordenes aux[] = Arrays.copyOfRange(A, 0, r + 1);
        // hace una copia del arreglo original en el aux

    public int[] obtenerVelocidades() {

        for (java.util.Map.Entry e : contenedorAviones.entrySet()) {

        }
        return null;
    }

}
