package analise_estrutura_de_dado;

public class Tinfo {
	protected int Chave;
	protected String Nome;
	
	public Tinfo (int Chave, String Nome) {
		this.Nome = Nome; this.Chave=Chave;
	}

	public int getChave() {
		return Chave;
	}

	public void setChave(int chave) {
		Chave = chave;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	@Override
	public String toString() {
		return "Tinfo -> Chave=" + Chave + ", Nome=" + Nome + " ";
	}
}
