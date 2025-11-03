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
                System.out.println("El Cliente esta en pausa porque el buzon esta en su capacidad maxima");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El mensaje: "+mensaje.getId()+" ha sido agregado del buzon de entrada");
        mensajes.add(mensaje);
        totalMensajes--;
        if (totalMensajes==0){
            System.out.println("Ya se agregaron todos los mensajes");
        }
        notify();
    }

    public synchronized Mensaje SacarMensaje(){
        Mensaje mensaje=null;
        while (mensajes.size()==0) {
            try {
                System.out.println("Filtro SPAM ha entrado en pausa porque no hay mensajes");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        mensaje=mensajes.remove(0);
        System.out.println("El mensaje: "+mensaje.getId()+" ha sido retirado del buzon de entrada");
        notify();

        return mensaje;
    }
}
