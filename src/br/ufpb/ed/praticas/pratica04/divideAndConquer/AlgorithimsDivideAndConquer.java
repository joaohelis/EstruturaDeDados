package br.ufpb.ed.praticas.pratica04.divideAndConquer;

public class AlgorithimsDivideAndConquer {
	
	public static int minValue(int[] v){
		return minValue(v, 0, v.length-1);
	}
	
	private static int minValue(int[] v, int init, int end){
		if(end - init <= 1)
			return Math.min(v[init], v[end]);
		int mid = (init + end)/2;
		int minl1 = minValue(v, init, mid),
			minl2 = minValue(v, mid+1, end);
		return Math.min(minl1, minl2);					
	}
	
	public static int find(int[] v, int k){
		return find(v, 0, v.length-1, k);
	}
	
	private static int find(int[] v, int init, int end, int k){
		if(end - init > 1){
			int mid = (init + end)/2;  
			int result1 = find(v, init, mid, k);
			return (result1 != -1)? result1:find(v, mid + 1, end, k);
		}
		int result = -1;
		if (v[init] == k) result = init;
		else if(v[end] == k)result = end;
		return result;
	}
	
//	private static int find(int[] v, int init, int end, int k){
//		if(end - init > 1){
//			int mid = (init + end)/2;  
//			int	result1 = find(v, init, mid, k),
//				result2 = find(v, mid + 1, end, k);
//			return (result1 != -1 && result2 != -1)? Math.min(result1, result2):Math.max(result1, result2);
//		}
//		int result = -1;
//		if (v[init] == k) result = init;
//		else if(v[end] == k)result = end;
//		return result;
//	}

	/*
	public static int find(int[] v, int init, int end, int k){
		if(end - init > 1){ // Divisão - Se o vetor tiver mais de dois elementos é efeutada a divisão
			int mid = (init + end)/2; // identifica o indice mediano do vetor
//			Procura-se o elemento do lado esquerdo do vetor, tendo como referencia o ponto mediano encontrado (o elemento do ponto mediano tb é add na busca) 
			int result1 = find(v, init, mid, k);
//			Como na busca por um elemento no vetor, retorna-se o indice da primeira ocorrencia do elemento no vetor
//			se for encontrado o elemento no lado esquerdo da busca, não faz sentido procurar no lado subsequente
			return (result1 != -1)? result1:find(v, mid + 1, end, k);
		}
		int result = -1;
		if (v[init] == k) result = init;
		else if(v[end] == k)result = end;
		return result;
	}
	
	public static int minValue(int[] v, int init, int end, Stack<String> stack){
		if(end - init <= 1){
			String result = "";
			if(end - init == 0)
				result += "|"+v[init]+"|"+"Result: "+ Math.min(v[init], v[end]);
			else
				result += "|"+v[init]+"|"+v[end]+"|"+ "Result: "+Math.min(v[init], v[end]);
			System.out.println(result);
			return Math.min(v[init], v[end]);
		}
		String vector = "";
		for(int i = init; i <= end; ++i)
			vector += "|"+v[i];
		vector += "|";
		System.out.println(vector);
		stack.push(vector);
		int mid = (init + end)/2;
		int minl1 = minValue(v, init, mid, stack),
			minl2 = minValue(v, mid+1, end, stack);
		System.out.println("Resultado da conquista da parte: "+stack.pop()+ " = "+Math.min(minl1, minl2));
		return Math.min(minl1, minl2);					
	}
	*/
}
