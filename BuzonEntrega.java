import java.util.ArrayList;

public class BuzonEntrega {
    private int capacidadT;
    private ArrayList<Mensaje> buzon;

    public BuzonEntrega(int capacidad){
        capacidadT=capacidad;
        buzon= new ArrayList<Mensaje>();
    }

    public synchronized void agregar(Mensaje m){

        while(capacidadT==buzon.size()){
            Thread.yield();
        }
        System.out.println("El mensaje: "+m.getId()+" ha sido agregado del buzon de entrega");
        buzon.add(m);
    }

    public synchronized Mensaje consultar(){
        Mensaje m= null;
        while(buzon.isEmpty()){
            
        }
        m=buzon.remove(0);
        System.out.println("El mensaje: "+m.getId()+" ha sido retirado del buzon de entrega");
        return m;
    }
}
