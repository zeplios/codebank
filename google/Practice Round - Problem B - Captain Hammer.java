import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ProblemB {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int T, V, D;
		File output = new File("output.out");
		FileWriter fw = new FileWriter(output);
		T = scanner.nextInt();
		for (int i = 0 ; i < T ; i++) {
			V = scanner.nextInt();
			D = scanner.nextInt();
			double r = 9.8 * D / V / V;
			if (r > 1.0) r = 1.0;
//			fw.write(String.format("Case #%d: %.7f\n", i+1, 180*Math.asin(r)/2/Math.PI));
			System.out.println(String.format("Case #%d: %.7f\n", i+1, 180*Math.asin(r)/2/Math.PI));
		}
		scanner.close();
		fw.close();
	}

}
