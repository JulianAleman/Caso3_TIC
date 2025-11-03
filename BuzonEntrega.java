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
        System.out.println("ingreaa ENTREGA");
        System.out.println(buzon.size());
        buzon.add(m);
    }

    public synchronized Mensaje consultar(){
        Mensaje m= null;

        if(buzon.size()!=0){
            System.out.println("Sale Entrega");
            m=buzon.remove(0);
            System.out.println(m);
            System.out.println("FINAL?:"+m.getFinal());
            
        }
        return m;
    }
}
