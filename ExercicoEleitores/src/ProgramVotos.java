
public class ProgramVotos {

	public static void main(String[] args) {
		Votos votos = new Votos();
		votos.setTotalEleitores(1000.00);
		votos.setVotosValidos(800.00);
		votos.setVotosBrancos(150.00);
		votos.setVotosNulos(50.0);
		
		System.out.println("Votos Validos= " + votos.percetualVotosValidos(votos.getVotosValidos())+ "%");
		System.out.println("Votos Brancos= " +votos.percetualVotosBrancos(votos.getVotosBrancos())+ "%");
		System.out.println("Votos Nulos= " +votos.percetualVotosNulos(votos.getVotosNulos())+ "%");

	}

}
