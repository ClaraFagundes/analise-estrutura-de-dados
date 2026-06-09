public class TArvore {

    public TNodo T;

    public TArvore(){
        T = null;
    }

    public TNodo Remove(TNodo T, TInfo item){

        if(T == null){
            return T;
        }

        if(item.chave == T.item.chave){

            TNodo P = T;


            if(T.esq == null && T.dir == null){

                if(T.pai == null){
                    this.T = null;
                    return T;
                }
                else{

                    if(T.item.chave > T.pai.item.chave){
                        T.pai.dir = null;
                    }
                    else{
                        T.pai.esq = null;
                    }

                }

            }


            else if(T.esq == null){

                if(T.pai != null){
                    if(T.dir != null) {

                        T.dir.pai = T.pai;

                    }
                    if(T.item.chave > T.pai.item.chave){
                        T.pai.dir = T.dir;
                    }
                    else{
                        T.pai.esq = T.dir;
                    }

                }
                else{
                    this.T = T.dir;
                    this.T.pai = null;
                }

            }


            else if(T.dir == null){

                if(T.pai != null){

                    if(T.esq != null) {

                        T.esq.pai = T.pai;

                    }
                    if(T.item.chave > T.pai.item.chave){
                        T.pai.dir = T.esq;
                    }
                    else{
                        T.pai.esq = T.esq;
                    }

                }
                else{
                    this.T = T.esq;
                    this.T.pai = null;
                }

            }


            else{

                P = getMax(T.esq);
                T.item = P.item;

            }

        }
        else{

            if(item.chave < T.item.chave){
                Remove(T.esq, item);
            }
            else{
                Remove(T.dir, item);
            }

        }

        return T;
    }

    public  TNodo getMax(TNodo T) {

        if(T.dir == null){

            if(T.esq != null){

                T.esq.pai = T.pai;

            }
            if(T.item.chave > T.pai.item.chave){

                T.pai.dir = T.esq;

            }
            else{

                T.pai.esq = T.esq;

            }

            return T;
        }
        else{
            return getMax(T.dir);
        }

    }

    public void Insere(TInfo item) {
        T = Insere(T, item, null);
    }

    public TNodo Insere(TNodo T, TInfo item, TNodo pai) {

        if (T == null) {
            T = new TNodo(item, pai);
        }

        if (item.chave > T.item.chave) {
            T.dir = Insere(T.dir, item, T);
        }
        else if (item.chave < T.item.chave) {
            T.esq = Insere(T.esq, item, T);
        }

        return T;
    }

    public TNodo Pesquisa(TInfo item) {
        return Pesquisa(T, item);
    }

    public TNodo Pesquisa(TNodo T, TInfo item) {

        if (T == null) {
            return T;
        }
        else {
            if (item.chave == T.item.chave) {
                return T;
            }
            else{

                if(item.chave < T.item.chave){
                    T = Pesquisa(T.esq, item);
                }

                else{
                    T = Pesquisa(T.dir, item);
                }

            }
        }

        return T;
    }

}
