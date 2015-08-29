import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Round1C {
	
	static class Edge implements Comparable<Edge> {
		public Edge(int s, int e, int c) {this.s = s; this.e = e; this.c = c;}
		int s; // start
		int e; // end
		int c; // cost
		@Override
		public int compareTo(Edge o) {
			return this.c - o.c;
		}
	}
	
	public static void judge(Scanner scanner, FileWriter fw) throws IOException {
		List<Edge> edges = new ArrayList<>();
		int N =  scanner.nextInt();
		int M =  scanner.nextInt();
		int [][] weight = new int [N][N];
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < N ; j++) {
				weight[i][j] = Integer.MAX_VALUE / 2; // account for overflow
			}
			weight[i][i] = 0;
		}
		for (int i = 0 ; i < M ; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			int cost = scanner.nextInt();
			edges.add(new Edge(start, end, cost));
			weight[start][end] = Math.min(weight[start][end], cost);
			weight[end][start] = Math.min(weight[end][start], cost);
		}
		for (int k = 0 ; k < N ; k++) {
			for (int i = 0 ; i < N ; i++) {
				for (int j = 0 ; j < N ; j++) {
					weight[i][j] = Math.min(weight[i][j], weight[i][k] + weight[k][j]);
				}
			}
		}
//		System.out.print(String.format("Case #%d:\n", i+1));
		for (int i = 0 ; i < edges.size() ; i++) {
			Edge e = edges.get(i);
			if (weight[e.s][e.e] < e.c) {
				fw.write(Integer.toString(i) + "\n");
//				System.out.print(Integer.toString(i) + "\n");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("C:/Users/Zhang/Downloads/C-large-practice.in"));
		int T;
		File output = new File("src/output.out");
		FileWriter fw = new FileWriter(output);
		T = scanner.nextInt();
		for (int i = 0 ; i < T ; i++) {
			fw.write(String.format("Case #%d:\n", i+1));
			judge(scanner, fw);
		}
		scanner.close();
		fw.close();
		
	}

}
