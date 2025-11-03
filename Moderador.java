import java.util.Random;

public class Moderador extends Thread{
    private BuzonCuarentena buzonC;
    private BuzonEntrega BE;
    private boolean Fin;
    private Random random;

    public Moderador(BuzonCuarentena buzon1, BuzonEntrega Buzon2){
        buzonC=buzon1;
        BE= Buzon2;
        random= new Random();
    }

    @Override
    public void run(){
        while(Fin==false){
            Mensaje m =buzonC.revisar();
            if (m!=null){
                if(m.getFinal()==false){
                    int numero= random.nextInt(21)+1;
                    if ((numero%7)!=0){
                        BE.agregar(m);
                    }
                    
                }else{Fin=m.getFinal();}
            }
            Thread.yield();
        }

    }
}
