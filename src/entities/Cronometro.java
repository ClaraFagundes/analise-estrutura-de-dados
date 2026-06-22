package entities;

public class Cronometro {
    private long inicio;
    private long fim;

    public void iniciar() {
        inicio = System.nanoTime();
    }

    public void finalizar() {
        fim = System.nanoTime();
    }

    public long getTempoNanos() {
        return fim - inicio;
    }

    public double getTempoMs() {
        return (fim - inicio) / 1000000.0;
    }

    @Override
    public String toString() {
        return String.format("%.3f ms", getTempoMs());
    }
}