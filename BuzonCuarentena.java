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
        int tiempo= random.nextInt(10001)+10000;
        m.setTiempo(tiempo);
        mensajes.add(m);
    }
    
    public synchronized Mensaje revisar(){
        Iterator<Mensaje> it = mensajes.iterator();
        while (it.hasNext()){
            Mensaje m = it.next();
            m.disminuir();
            if(m.getTiempo()==0){
                it.remove();
                return m;
            }
        }
        return null;
    }
}
