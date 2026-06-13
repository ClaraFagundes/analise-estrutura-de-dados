package analise_BST;

public class Program {
    public static void main(String []args){
    	Arvore_BST T1 = new Arvore_BST();
		
		T1.add(new Tinfo(1,"Gustavo"));
		T1.add(new Tinfo(2,"Luiz"));
		T1.add(new Tinfo(3,"Daniel"));
		
		T1.emOrdem(T1.T);
    }
}
