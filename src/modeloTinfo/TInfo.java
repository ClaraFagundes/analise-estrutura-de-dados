package modeloTinfo;

public class TInfo {
    public int chave;
    public String nome;

    //características do perfume: /////////////////
    //protected int id; Retirei o ID pois acho que chave já faz o trabalho dele
    //protected String perfume; já tem nome;
    public String brand;
    public String country;
    public String gender;
    public double ratingValue;
    public double ratingCount;
    public double year;
    public String top;
    public String middle;
    public String base;
    public String perfumer1;
    public String perfumer2;
    public String mainaccord1;
    public String mainaccord2;
    public String mainaccord3;
    public String mainaccord4;
    public String mainaccord5;
    ///------------------------------///////////////

    public TInfo(
            int chave,
            String nome,
            String brand,
            String country,
            String gender,
            double ratingValue,
            double ratingCount,
            double year,
            String top,
            String middle,
            String base,
            String perfumer1,
            String perfumer2,
            String mainaccord1,
            String mainaccord2,
            String mainaccord3,
            String mainaccord4,
            String mainaccord5) {

        this.chave = chave;
        this.nome = nome;

        this.brand = brand;
        this.country = country;
        this.gender = gender;
        this.ratingValue = ratingValue;
        this.ratingCount = ratingCount;
        this.year = year;
        this.top = top;
        this.middle = middle;
        this.base = base;
        this.perfumer1 = perfumer1;
        this.perfumer2 = perfumer2;
        this.mainaccord1 = mainaccord1;
        this.mainaccord2 = mainaccord2;
        this.mainaccord3 = mainaccord3;
        this.mainaccord4 = mainaccord4;
        this.mainaccord5 = mainaccord5;
    }
}
