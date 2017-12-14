package app;

import java.io.IOException;
import java.util.Scanner;

import logic.Searcher;

public class Main {
	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int N = Integer.parseInt(in.nextLine());
		int squares [] = new int[N];

		
		int result[] = new int[3];
		
		for (int i=0; i<N; i++) {
			squares[i] = in.nextInt();
		}
		
		Searcher searcher = new Searcher(N, squares);
		result = searcher.searchForBestArea();
		// add 1 to indexes
		System.out.println((result[0]+1) + " " + (result[1]+1) + " " + result[2]);
	}
	
	
}
