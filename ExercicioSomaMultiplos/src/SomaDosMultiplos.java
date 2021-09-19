
public class SomaDosMultiplos {
	public int SomaMultiplos(int numero) {
		int mtres=0;
		int mcincos=0;
		
			for (int i = 0; i < numero; i++) {
				if (i % 3 ==0) {
					mtres +=i;
			    }
				if (i % 5 ==0) {
					mcincos +=i;
			    }
		    }
			return mtres + mcincos;
	}
}
