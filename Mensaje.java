import java.util.Random;

public class Mensaje {
    private int id;
    private boolean inicio;
    private boolean fin;
    private int ti;
    private boolean SPAM;

    public Mensaje(int id){
        //Definir si es SPAM
        Random r= new Random();
        int i=r.nextInt(2);
        this.SPAM=(i==1);
        // Definir identificador
        this.id=id;
        //Definir Inicio y Fin
        this.inicio=false;
        this.fin=false;
    }

    public void disminuir(){
        ti--;
    }

    //Getters y Setters necesarios
    public void setInicio(boolean inicio){ this.inicio=inicio;}
    public void setFin(boolean inicio){ this.inicio=fin;}
    public void setSPAM(boolean SPAM){this.SPAM=SPAM;}
    public int getTiempo(){return ti;}
    public boolean getFinal(){return fin;}
    public void setTiempo(int tiempo){this.ti=tiempo;}
    public boolean getInicio(){return inicio;}
    public boolean getSPAM(){ return SPAM;}
    public int getId(){return id;}
    
}
