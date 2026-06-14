package analise_AVL;

public class TInfo {
    public int chave;
    public String nome;

    public TInfo(int chave, String nome){
        this.chave = chave;
        this.nome = nome;
    }

    public String toString(){
        return chave + " ";
    }

    public int getChave(){
        return chave;
    }
}