import java.util.Random;

public class ServidorEntrega extends Thread{
    private BuzonEntrega BE;
    private static boolean Final;
    private Random random;

    public ServidorEntrega(BuzonEntrega b){
        BE=b;
        Final=false;
        random= new Random();
    }

    @Override
    public void run(){
        System.out.println("=!== Se inicio Servidor de Entrega =!==");
        while(Final==false){
            Mensaje m = BE.consultar();
            if (m!=null){
                int r= random.nextInt(1000);
                try {
                    Thread.sleep(r);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                if(m.getId()==-1){
                    Final=true;
                }
            }
        }
        System.out.println("==== Termino el servidor de entrega ====");
    }
}
