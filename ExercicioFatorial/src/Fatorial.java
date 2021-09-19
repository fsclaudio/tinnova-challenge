
public class Fatorial {
	public int calculaFatorial(int numero) {
		if(numero==0) {
			return 1;
		}
		else 
		{
		int fatorial =numero;
		for (int i = 1; i < numero; i++) {
			fatorial *=  i;
		}
		
		return  fatorial;
		}
	}
}
