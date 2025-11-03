import java.util.ArrayList;
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
        Mensaje encontrado= null;
        for (Mensaje m: mensajes){
            m.disminuir();
            if(m.getTiempo()==0){
                encontrado=m;
            }
        }
        return encontrado;
    } 
}
