package aed;

public class ListaEnlazada<T> implements Secuencia<T> {
    private int longitud;
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T contenidoT;
        Nodo siguiente;
        Nodo anterior;
        Nodo (T v) {contenidoT = v;}
    }

    public ListaEnlazada() {
        primero = null;
        longitud = 0;

    }   

    public int longitud() {
        int res = longitud;
        return res;
    }

    public void agregarAdelante(T elem) {
        Nodo nodo_nuevo = new Nodo(elem);
        if (longitud == 0) {
            primero = nodo_nuevo;
            ultimo = nodo_nuevo;
        } else {
            primero.anterior = nodo_nuevo;
            nodo_nuevo.siguiente = primero;
            primero = nodo_nuevo;
        }
        longitud += 1;
    }

    public void agregarAtras(T elem) {
        Nodo nodo_nuevo = new Nodo(elem);
        if (longitud == 0) {
            primero = nodo_nuevo;
            ultimo = nodo_nuevo;
        } else {
            ultimo.siguiente = nodo_nuevo;
            ultimo = nodo_nuevo;
        }
        longitud += 1;
    }

    public T obtener(int i) {
        Nodo res = primero;
        for (int n = 0; n < i; n ++){
            res = res.siguiente;
        }
        return res.contenidoT;
    }

    public void eliminar(int i) {
        Nodo nodo = primero;
        int indice = 0;
        Nodo previo = nodo.anterior;
        Nodo posterior = nodo.siguiente;
        if (i == 0){
            primero = primero.siguiente;
        } else if (i == longitud) {
            ultimo = ultimo.anterior;
        } else {
            for (int n = 0; n < i; n ++){
                nodo = nodo.siguiente;
                indice = n;
            }
            previo.siguiente = nodo.anterior;
            posterior.anterior = nodo.siguiente;

        }
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo nuevo_nodo = new Nodo(elem);
        Nodo nodo = primero;
        for (int n = 0; n < indice; n++){
            nodo = nodo.siguiente;
        }
        nodo.contenidoT = nuevo_nodo.contenidoT;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        String res = "[";
        for (int n = 0; n< longitud-1; n++){
            res = res + obtener(n) + ", ";
        }
        res = res + obtener(longitud()-1);
        return res + "]";
    }

    private class ListaIterador implements Iterador<T> {
    	Nodo previo;
        Nodo posterior;
        Nodo actual;

        public boolean haySiguiente() {
	        boolean res = false;
            if (actual.siguiente != null){
                res = true;
            }
            return res;
        }
        
        public boolean hayAnterior() {
            boolean res = false;
            if (actual.anterior != null){
                res = true;
            }
            return res;
        }

        public T siguiente() {
            Nodo nodo = actual;
	        actual = actual.siguiente;
            previo = actual;
            return nodo.contenidoT;
        }
        

        public T anterior() {
	        Nodo nodo = actual;
	        actual = actual.anterior;
            posterior = actual;
            return nodo.contenidoT;
        }
    }

    public Iterador<T> iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
