package analise_AVL;

public class TInfo {
    protected int chave;
    protected String nome;
    protected String brand;
    protected String country;
    protected String sexo;
    protected double ratingVAL;
    protected int ratingCountry;
    protected double ano;
    protected String top;
    protected String midlle;
    protected String base;
    protected String perfurmer;
    protected String mainaccon;


    public TInfo(int chave, String nome, String brand, String country, String sexo,
                 double ratingVAL, int ratingCountry, double ano,
                 String top, String midlle, String base,
                 String perfurmer, String mainaccon) {

        this.chave = chave;
        this.nome = nome;
        this.brand = brand;
        this.country = country;
        this.sexo = sexo;
        this.ratingVAL = ratingVAL;
        this.ratingCountry = ratingCountry;
        this.ano = ano;
        this.top = top;
        this.midlle = midlle;
        this.base = base;
        this.perfurmer = perfurmer;
        this.mainaccon = mainaccon;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getRatingVAL() {
        return ratingVAL;
    }

    public void setRatingVAL(double ratingVAL) {
        this.ratingVAL = ratingVAL;
    }

    public int getRatingCountry() {
        return ratingCountry;
    }

    public void setRatingCountry(int ratingCountry) {
        this.ratingCountry = ratingCountry;
    }

    public double getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getMidlle() {
        return midlle;
    }

    public void setMidlle(String midlle) {
        this.midlle = midlle;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getPerfurmer() {
        return perfurmer;
    }

    public void setPerfurmer(String perfurmer) {
        this.perfurmer = perfurmer;
    }

    public String getMainaccon() {
        return mainaccon;
    }

    public void setMainaccon(String mainaccon) {
        this.mainaccon = mainaccon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Tinfo -> Chave=" + chave + ", Nome=" + nome + " ";
    }
}