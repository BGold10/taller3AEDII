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
            nodo_nuevo.anterior = ultimo;
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
        Nodo actual = primero;
        if (i == 0){
            if (longitud == 1){
                primero = null;
                ultimo = null;
            } else {
                primero = primero.siguiente;
                primero.anterior = null;
            }
            
        } else if (i == longitud-1) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
        } else {
            for (int n = 0; n < i; n ++){
                actual = actual.siguiente;
            }
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }
        longitud -= 1;
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
        //ListaEnlazada<T> copia = new ListaEnlazada();
        Nodo nodo = lista.primero;
        for (int i = 0; i< lista.longitud; i ++){
            this.agregarAtras(nodo.contenidoT);
            nodo = nodo.siguiente;
        }
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
    	//Nodo previo;
        //Nodo posterior; 
        Nodo proximo_nodo; // atributo me_caí
        public ListaIterador() {
            proximo_nodo = primero;
            
        }

        public boolean haySiguiente() {
	        boolean res = false;
            if (proximo_nodo == null){
                return res;
            }
            if (proximo_nodo != null){ //lista vacia
                res = true;
            }
            return res;
        }
        
        public boolean hayAnterior() {
            boolean res = false;
            if (proximo_nodo == null){
                return res;
            }
            if (proximo_nodo.anterior != primero){
                res = true;
            }
            return res;
        }

        public T siguiente() {
            T contenido = proximo_nodo.contenidoT;
            if (proximo_nodo.siguiente != null){
                proximo_nodo = proximo_nodo.siguiente;
                
            }
            return contenido;
        }
        

        public T anterior() {
	        T contenido = proximo_nodo.contenidoT;
	        proximo_nodo = proximo_nodo.anterior;
            return contenido;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
