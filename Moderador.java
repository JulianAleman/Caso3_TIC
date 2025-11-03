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
        Fin=false;
    }

    @Override
    public void run(){
        System.out.println("=!== Se inicio el Moderador =!==");
        while(Fin==false){
            Mensaje m =buzonC.revisar();
            if (m!=null){
                if(m.getId()!=-1){
                    int numero= random.nextInt(21)+1;
                    if ((numero%7)!=0){
                        System.out.println("Agregado desde cuarentena");
                        BE.agregar(m);
                    }
                }else{Fin=true;}
            }
        }
        System.out.println("==== Termino el Moderador ====");
    }
}
