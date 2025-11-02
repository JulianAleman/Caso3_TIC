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
        buzon.add(m);
    }
}
