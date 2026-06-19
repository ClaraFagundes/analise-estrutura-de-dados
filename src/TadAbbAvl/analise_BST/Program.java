package TadAbbAvl.analise_BST;
import modeloTinfo.TInfo;


public class Program {
    public static void main(String []args){
    	Arvore_BST T1 = new Arvore_BST();
		
		T1.add(new TInfo(1,"Gustavo"));
		T1.add(new TInfo(2,"Luiz"));
		T1.add(new TInfo(3,"Daniel"));
		
		T1.emOrdem(T1.T);
    }
}
