package br.ufpb.ed.dinamicPrograming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		//File f = new File("Dij.txt");
		File f = new File("Teste.txt");
		Scanner sc = null;
		
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int tamanhoMatriz = sc.nextInt();
				
		float[][] matriz = new float[tamanhoMatriz][tamanhoMatriz];
		
		for(int i = 0; i < tamanhoMatriz; ++i)
			for(int j = 0; j < tamanhoMatriz; ++j)
				matriz[i][j] = Float.parseFloat(sc.next());
		
		System.out.println("Matriz Original\n");
		
		printMatriz(matriz);
		
		float[][] o = new float[tamanhoMatriz][tamanhoMatriz];
		float[][] menorCaminho = floyd(matriz, o);
		
		System.out.println("\nMatriz com menor caminho\n");
		
		printMatriz(menorCaminho);
		
		System.out.println("\nMatriz o");
		
		printMatriz(o);
		
	}
	
	public static float[][] floyd(float[][] l, float[][] o){
		l = l.clone();
		for(int k = 0; k < l.length; ++k)
			for(int i = 0; i < l.length; ++i)
				for(int j = 0; j < l.length; ++j)
					if (l[i][j] < l[i][k] + l[k][j])
						o[i][j] = i;
					else{
						l[i][j] = l[i][k] + l[k][j];
						o[i][j] = k;
					}				
		return l;
	}
	
	public static void printMatriz(float[][] m){
		for(int i = 0; i < m.length; ++i){
			for(int j = 0; j < m.length; ++j)
				System.out.print(m[i][j] + "  ");
			System.out.println();
			System.out.println();
		}
	}

}

