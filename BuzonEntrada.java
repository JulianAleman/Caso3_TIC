import java.util.ArrayList;

public class BuzonEntrada {
    private int capacidad;
    private ArrayList<Mensaje> mensajes;
    private int totalMensajes;

    public BuzonEntrada(int capacidad, int totalMensajes){
        this.capacidad=capacidad;
        this.totalMensajes=totalMensajes;
        mensajes= new ArrayList<Mensaje>();
    }


    public synchronized void IngresarMensaje(Mensaje mensaje) {
        while (mensajes.size()==capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //System.out.println("ingreaa BE");
        mensajes.add(mensaje);
        totalMensajes--;
        notify();
    }

    public synchronized Mensaje SacarMensaje(){
        Mensaje mensaje=null;
        while (mensajes.size()==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (mensajes.size()!=0){
         //   System.out.println("Sale BE");
            mensaje=mensajes.remove(0);
             System.out.println(mensajes.size());
            notify();
        }

        return mensaje;
    }
}
