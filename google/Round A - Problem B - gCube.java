import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


public class Round1B {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new FileInputStream("C:/Users/Zhang/Downloads/B-large-practice.in"));
//		Scanner scanner = new Scanner(System.in);
		int T, N, M;
		File output = new File("src/output.out");
		FileWriter fw = new FileWriter(output);
		T = scanner.nextInt();
		for (int i = 0 ; i < T ; i++) {
			N = scanner.nextInt();
			M = scanner.nextInt();
			double dim[] = new double[N];
			for (int j = 0 ; j < N ; j++) {
				dim[j] = scanner.nextDouble();
			}
			fw.write(String.format("Case #%d:\n", i+1));
			for (int j = 0 ; j < M ; j++) {
				int l = scanner.nextInt();
				int r = scanner.nextInt();
				int d = r - l + 1;
				double multi = 1.0;
				for (int k = l ; k <= r ; k++) {
					multi = multi * Math.pow(dim[k], 1.0/d);
				}
				fw.write(String.format("%.9f\n", multi));
//				System.out.println(String.format("Case #%d: %.9f\n", i+1, multi));
			}
		}
		scanner.close();
		fw.close();
		
	}

}
