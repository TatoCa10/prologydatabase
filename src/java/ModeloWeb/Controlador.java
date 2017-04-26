package ModeloWeb;

import java.util.ArrayList;


public class Controlador {

    
    ArrayList<Integer> Valores= new ArrayList<>();
  
    
    public NodoArbol raiz;
    
    public void AdministradorArboles(){
    
    NodoArbol raiz= new NodoArbol();
    }
    
     public boolean esVacio(){
        return (raiz == null);
    }

    public void inOrder(){
        if (!esVacio()) {
            raiz.izquierdo.inOrder();
            System.out.print( raiz.IdInfo + "->-> "+raiz.PosicionArchivo+"\n");
            raiz.derecho.inOrder();
        }
    }

    public boolean IDExistente(Integer ValorId){
    
        if (esVacio()) {
            
           return false;
            
            
        }else  if(ValorId==raiz.IdInfo){               
                return true;
            }if (ValorId < raiz.IdInfo) {
                (raiz.izquierdo).IDExistente(ValorId);
            }else if (ValorId > raiz.IdInfo) {
                (raiz.derecho).IDExistente(ValorId);
            } 
        
        
        
     return false;
    }
    
    
    

    public void insertarNodo(Integer ValorId, Long Posicion) {

        if (esVacio()) {
            
            NodoArbol arbolito = new NodoArbol();
            arbolito.IdInfo = ValorId;
            arbolito.PosicionArchivo = Posicion;
            arbolito.derecho= new Controlador();
            arbolito.izquierdo= new Controlador();
            raiz=arbolito;
            
            
        } else {
            if (ValorId < raiz.IdInfo) {
                (raiz.izquierdo).insertarNodo( ValorId, Posicion);
            } else if (ValorId > raiz.IdInfo) {
                (raiz.derecho).insertarNodo( ValorId, Posicion);
            } else if(ValorId==raiz.IdInfo){
                System.out.println("ID ya registrado");
                
            }

        }
        
        
    }
    
    public void insertarNodoUser(Integer ValorId, String Pass) {

        if (esVacio()) {
            
            NodoArbol arbolito = new NodoArbol();
            arbolito.IdInfo = ValorId;
            arbolito.Pass = Pass;
            arbolito.derecho= new Controlador();
            arbolito.izquierdo= new Controlador();
            raiz=arbolito;
            
            
        } else {
            if (ValorId < raiz.IdInfo) {
                (raiz.izquierdo).insertarNodoUser( ValorId, Pass);
            } else if (ValorId > raiz.IdInfo) {
                (raiz.derecho).insertarNodoUser( ValorId, Pass);
            } else if(ValorId==raiz.IdInfo){
                System.out.println("ID ya registrado");
                
            }

        }
        
        
    }

    public long buscar(int a){
        
        NodoArbol arbolito = null;
        
        if (!esVacio()) {
            if (a == raiz.IdInfo) {
            return raiz.PosicionArchivo;
            }
            else {
                if (a < raiz.IdInfo) {
                    raiz.PosicionArchivo = raiz.izquierdo.buscar(a);
                }
                else {
                    raiz.PosicionArchivo = raiz.derecho.buscar(a);
                }
            }
        }
        return raiz.PosicionArchivo;
    }
    
    public boolean buscarUser(int a, String password){
        
        NodoArbol arbolito = null;
        
        if (!esVacio()) {
            if (a == raiz.IdInfo && password.equals(raiz.Pass)) {
            return true;
            }
            else {
                if (a != raiz.IdInfo) {
                    System.out.println("ID incorrecto");
                    return false;
                }
                else if(!password.equals(raiz.Pass)){
                    System.out.println("Contraseña Incorrecta");
                }
            }
        }
        return false;
        
    }

//    public NodoArbol borrarNodo(NodoArbol arbolito, int valor) {
//        NodoArbol arbolitoo = new NodoArbol();
////        imprimirArbolInOrden(arbolito);
//        for (int i = 0; i < valores.size(); i++) {
//            if (valor == valores.get(i)) {
//                valores.remove(i);
//            }
//
//        }
//        for(int p = 0; p < valores.size(); p++){ //Cargar segundo array sin el cero
//                    if(valores.get(p).equals(0)){
//                        System.out.println("Cero");
//                    }else{
//                       terminales.add(valores.get(p)); 
//                    }
//                }
//        for (int q = 0; q < terminales.size(); q++) { //Cargar Segundo Arbol
//                    Integer x = terminales.get(q);
//                    insertarNodo(arbolitoo, x);
//                }
//        return arbolitoo;
//    }
    public void Recorrer() {
        for (int i = 0; i < Valores.size(); i++) {
            System.out.println(Valores.get(i));
        }
    }
    
}
