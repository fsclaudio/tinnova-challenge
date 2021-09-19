
public class BubbleSort {

	public static void main(String[] args) {
		int vetor[]= {5,3,2,4,7,1,0,6};
		int mudaPos;
		
		
		for (int i = 0; i < vetor.length; i++) {
			
			for (int j = 0; j < vetor.length -1; j++) {
				if (vetor[j] > vetor[j +1]) {
					mudaPos = vetor[j];
					vetor[j]= vetor[j+1];
					vetor[j+1]=mudaPos;
				}
			}
		
		}
		System.out.println("Vetor ordenado:");
		for (int i = 0; i < vetor.length; i++) {
			System.out.print(vetor[i]+ " ");
		}

	}

}
