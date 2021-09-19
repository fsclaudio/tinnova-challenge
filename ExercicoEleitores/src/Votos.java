
public class Votos {

private final static double PERCENTAGEM_TOTAL = 100.00;
	
	private double totalEleitores;
	private double votosValidos;
	private double votosBrancos;
	private double votosNulos;

	public Votos() {
}

	public Votos(double totalEleitores, double votosValidos, double votosBrancos, double votosNulos) {
		this.totalEleitores = totalEleitores;
		this.votosValidos = votosValidos;
		this.votosBrancos = votosBrancos;
		this.votosNulos = votosNulos;
	}

	public double getTotalEleitores() {
		return totalEleitores;
	}

	public void setTotalEleitores(double totalEleitores) {
		this.totalEleitores = totalEleitores;
	}

	public double getVotosValidos() {
		return votosValidos;
	}

	public void setVotosValidos(double votosValidos) {
		this.votosValidos = votosValidos;
	}

	public double getVotosBrancos() {
		return votosBrancos;
	}

	public void setVotosBrancos(double votosBrancos) {
		this.votosBrancos = votosBrancos;
	}

	public double getVotosNulos() {
		return votosNulos;
	}

	public void setVotosNulos(double votosNulos) {
		this.votosNulos = votosNulos;
	}
	
	public double percetualVotosValidos(Double quantidade) {
		return (quantidade * PERCENTAGEM_TOTAL)/totalEleitores;
	}
	public double percetualVotosBrancos(Double quantidade) {
		return (quantidade * PERCENTAGEM_TOTAL)/totalEleitores;
	}
	public double percetualVotosNulos(Double quantidade) {
		return (quantidade * PERCENTAGEM_TOTAL)/totalEleitores;
	}
}

