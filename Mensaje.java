public class Mensaje {
    private boolean inicio;
    private boolean fin;
    private int ti;

    public Mensaje(){

    }
    public void disminuir(){
        ti--;
    }
    public int getTiempo(){return ti;}
    public boolean Final(){return fin;}
    public void setTiempo(int tiempo){this.ti=tiempo;}
    public boolean getInicio(){return inicio;}
}
