package br.ufpb.ed.aulas;

public class Fibonacci {
	
	public static void main(String[] args) {
		for(int i = 0; i <= 100; i++)
			System.out.println("Fib("+i+"): "+fib(i));
		
	}
		
//	public int fib(int num){
//		if (num <= 2)
//			return 1;
//		return fib(num -1) + fib(num-2);
//	}
	
	public static long fib(int num){
		long[] aux = new long[num + 1];
		for(int i = 0; i < aux.length; ++i)
			aux[i] = -1;
		return fib2(num, aux);
	}
	
	private static long fib2(int num, long[] aux){
		if (aux[num] != -1)
			return aux[num];
		else{
			if(num == 0)
				aux[num] = 0;
			else if(num <= 2)
				aux[num] = 1;
			else
				aux[num] = fib2(num -1, aux) + fib2(num -2, aux);
			return aux[num];
		}
	}
	
	
}
