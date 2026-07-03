package common;

// Agora TInfo virou Perfume e todas estruturas de dados armazenam Perfume
public class Perfume {

    private int chave;
    private String nome;
    private String brand;
    private String country;
    private String gender;
    private double ratingValue;
    private int ratingCount;
    private double year;
    private String top;
    private String middle;
    private String base;
    private String perfumer1;
    private String perfumer2;
    private String mainaccord1;
    private String mainaccord2;
    private String mainaccord3;
    private String mainaccord4;
    private String mainaccord5;

    public Perfume(int chave) {
        this.chave = chave;
    }

    public Perfume(int chave, String nome, String brand, String country, String gender,
                   double ratingValue, int ratingCount, double year,
                   String top, String middle, String base,
                   String perfumer1, String perfumer2,
                   String mainaccord1, String mainaccord2,
                   String mainaccord3, String mainaccord4,
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getPerfumer1() {
        return perfumer1;
    }

    public void setPerfumer1(String perfumer1) {
        this.perfumer1 = perfumer1;
    }

    public String getPerfumer2() {
        return perfumer2;
    }

    public void setPerfumer2(String perfumer2) {
        this.perfumer2 = perfumer2;
    }

    public String getMainaccord1() {
        return mainaccord1;
    }

    public void setMainaccord1(String mainaccord1) {
        this.mainaccord1 = mainaccord1;
    }

    public String getMainaccord2() {
        return mainaccord2;
    }

    public void setMainaccord2(String mainaccord2) {
        this.mainaccord2 = mainaccord2;
    }

    public String getMainaccord3() {
        return mainaccord3;
    }

    public void setMainaccord3(String mainaccord3) {
        this.mainaccord3 = mainaccord3;
    }

    public String getMainaccord4() {
        return mainaccord4;
    }

    public void setMainaccord4(String mainaccord4) {
        this.mainaccord4 = mainaccord4;
    }

    public String getMainaccord5() {
        return mainaccord5;
    }

    public void setMainaccord5(String mainaccord5) {
        this.mainaccord5 = mainaccord5;
    }

    @Override
    public String toString() {
        return "Perfume -> Chave = " + chave + ", Nome = " + nome;
    }
}