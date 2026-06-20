package analise_AVL;

public class TArvoreAVL {

    public TNodo T;
    private int h;
    private TNodo p;
    private long comparacoes = 0;
    private long rotacoes = 0;

    public TArvoreAVL() {
        T = null;
    }

    public TNodo raiz(){
        return T;
    }
    public long getRotacoes(){
        return rotacoes;
    }

    public void insere (TInfo item) {
        System.out.print("Inserindo "+item.chave+"\n");
        T = insere(T,item,null);
        AVL(p);
    }
    public long getComparacoes(){
        return comparacoes;
    }

    public TNodo insere (TNodo T, TInfo item, TNodo pai) {
        if (T == null) {
            T = new TNodo(item,pai);
            this.p = T;
        } else {
            pai = T;
            if (item.chave < T.item.chave) {
                comparacoes++;
                T.esq = insere(T.esq, item, pai);
            }
            else if (item.chave > T.item.chave){
                comparacoes++;
                T.dir = insere(T.dir,item,pai);
            }
        }
        return T;
    }

    public TNodo pesquisa (TInfo item) {
        return pesquisa(T,item);
    }

    public TNodo pesquisa(TNodo T, TInfo item) {
        if (T == null) {
            return T;
        } else {

            if (item.chave == T.item.chave)
                return T;
            else
            if (item.chave < T.item.chave)
                T = pesquisa(T.esq,item);
            else
                T = pesquisa(T.dir,item);
        }
        return T;
    }

    public void AVL(TNodo T) {
        if (T != null) {
            T.bal = balanco(T);
            if (T.bal < 2) {
                AVL(T.pai);
            } else {

                if (T.hesq >= T.hdir)
                    if (T.esq.hesq >= T.esq.hdir) {
                        rotacao_direita(T);
                    }
                    else {
                        rotacao_esquerda(T.esq);
                        rotacao_direita(T);
                    }

                if (T.hdir >= T.hesq)
                    if (T.dir.hdir >= T.dir.hesq) {
                        rotacao_esquerda(T);
                    }
                    else {
                        rotacao_direita(T.dir);
                        rotacao_esquerda(T);
                    }
            }
        }
    }


    public int balanco(TNodo T) {
        h = 0; balpreOrdem(T.esq,0); T.hesq = h;
        h = 0; balpreOrdem(T.dir,0); T.hdir = h;
        return Math.abs(T.hesq-T.hdir);
    }

    public void balpreOrdem (TNodo T, int v) {
        if (T != null) {
            v++;
            balpreOrdem(T.esq,v);
            balpreOrdem(T.dir,v);
        } else
        if (v > h) h = v;
    }

    public void rotacao_direita(TNodo T) {
        rotacoes++;
        TNodo apu = T.esq;
        T.esq = apu.dir; if (apu.dir != null) apu.dir.pai = T;
        apu.pai = T.pai;
        apu.dir = T; T.pai = apu;
        T.bal = 0;
        if (apu.pai == null)
            this.T = apu;
        else {
            if (apu.item.chave < apu.pai.item.chave)
                apu.pai.esq = apu;
            else
                apu.pai.dir = apu;
        }
    }

    public void rotacao_esquerda(TNodo T) {
        rotacoes++;
        TNodo apu = T.dir;
        T.dir = apu.esq; if (apu.esq != null) apu.esq.pai = T;
        apu.pai = T.pai;
        apu.esq = T; T.pai = apu;
        T.bal = 0;
        if (apu.pai == null)
            this.T = apu;
        else {
            if (apu.item.chave < apu.pai.item.chave)
                apu.pai.esq = apu;
            else
                apu.pai.dir = apu;
        }
    }

    public void Remove (TInfo item) {
        System.out.print("Removendo "+item.chave+"\n");
        T = Remove(T,item);
        AVL(p);
    }

    public TNodo Remove(TNodo T, TInfo X) {

        if (T == null) {
            System.out.print("Elemento não encontrado\n");
            this.p = T;
            return T;
        }
        if (X.chave == T.item.chave)
        {
            TNodo P = T;
            if ((T.esq == null) && (T.dir == null)) {
                if (T.pai == null)
                {
                    this.T = null;
                    this.p = T;
                    return T;
                } else {
                    if (T.item.chave > T.pai.item.chave)
                        T.pai.dir = null;
                    else
                        T.pai.esq = null;
                    this.p = T.pai;
                }
            }
            else
            if (T.esq == null) {
                if (T.pai != null) {
                    if (T.dir != null) T.dir.pai = T.pai;
                    if (T.item.chave > T.pai.item.chave)
                        T.pai.dir = T.dir;
                    else
                        T.pai.esq = T.dir;
                    this.p = T.pai;
                } else {
                    this.T = T.dir;
                    this.T.pai = null;
                    this.p = T.pai;
                }
            }
            else
            if (T.dir == null) {
                if (T.pai != null) {
                    if (T.esq != null) T.esq.pai = T.pai;
                    if (T.item.chave > T.pai.item.chave)
                        T.pai.dir = T.esq;
                    else
                        T.pai.esq = T.esq;
                    this.p = T.pai;
                } else {
                    this.T = T.esq;
                    this.T.pai = null;
                    this.p = T.pai;
                }
            }
            else
            {
                P = getMax(T.esq);
                T.item = P.item;
            }
        }
        else
        if (X.chave < T.item.chave)
            Remove(T.esq,X);
        else
            Remove(T.dir,X);

        return T;
    }

    public TNodo getMax (TNodo T) {
        if (T.dir == null)
        {
            if (T.esq != null) T.esq.pai = T.pai;
            if (T.item.chave > T.pai.item.chave)
                T.pai.dir = T.esq;
            else
                T.pai.esq = T.esq;

            return T;
        }
        else
            return getMax(T.dir);
    }

    public void emOrdem (TNodo T) {
        if (T != null) {
            emOrdem(T.esq);
            System.out.print (T.item + " ");
            emOrdem(T.dir);
        }
    }

    public void preOrdem (TNodo T) {
        if (T != null) {
            System.out.print (T.item + " ");
            preOrdem(T.esq);
            preOrdem(T.dir);
        }
    }

    public void posOrdem (TNodo T) {
        if (T != null) {
            posOrdem(T.esq);
            posOrdem(T.dir);
            System.out.print (T.item + " ");
        }
    }

    public void mostraArvore()
    {
        TPilhaPonteiro globalStack = new TPilhaPonteiro();
        globalStack.push(this.T);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("...................................................................");
        while(isRowEmpty==false)
        {
            TPilhaPonteiro localStack = new TPilhaPonteiro();
            isRowEmpty = true;
            for(int j=0; j<nBlanks; j++)
                System.out.print(" ");
            while(globalStack.vazia()==false)
            {
                TNodo temp = (TNodo)globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.item);
                    localStack.push(temp.esq);
                    localStack.push(temp.dir);
                    if(temp.esq != null || temp.dir != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<nBlanks*2-2; j++)
                    System.out.print(" ");
            }
            System.out.println();
            nBlanks /= 2;
            while(localStack.vazia()==false)
                globalStack.push( localStack.pop() );
        }
        System.out.println("...................................................................");
    }
    public int altura(TNodo no){
        if(no == null)
            return 0;

        return 1 + Math.max(
                altura(no.esq),
                altura(no.dir)
        );
    }

}
