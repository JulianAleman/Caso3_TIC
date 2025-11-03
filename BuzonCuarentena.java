import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class BuzonCuarentena {
    private Random random; 
    private ArrayList<Mensaje> mensajes;
    // private 

    public BuzonCuarentena(){
        random= new Random();
        mensajes= new ArrayList<Mensaje>();
    }
    
    public synchronized void Ingresar(Mensaje m){
        if (m.getId()!=-1) {
            int tiempo= random.nextInt(10001)+10000;
            m.setTiempo(tiempo);
            System.out.println("El mensaje: "+m.getId()+" ha sido agregado del buzon de cuarentena con tiempo "+ tiempo);
        }else{
            System.out.println("El mensaje de fin ha sido agregado del buzon de cuarentena");
        }
        mensajes.add(m);
    }
    
    public synchronized Mensaje revisar(){
        Iterator<Mensaje> it = mensajes.iterator();
        Mensaje encontrado = null;
        while (it.hasNext()){
            Mensaje m = it.next();
            m.disminuir();
            if(m.getTiempo()==0){
                it.remove();
                encontrado=m;
                System.out.println("El mensaje: "+m.getId()+" ha sido retirado del buzon de cuarentena");
            }
        }
        
        return encontrado;
    }
}
