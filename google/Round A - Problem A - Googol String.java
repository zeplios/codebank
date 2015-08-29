import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Round1A {

	public static int get(long k) {
		if (k == 0) return 0;
		long len = 1;
		while (len <= k) {
			len = 2 * len + 1;
		}
		if ((len - 1) == (k * 2)) return 0;
		return 1 - get(len - 1 - k);
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		long T, K;
		File output = new File("output.out");
		FileWriter fw = new FileWriter(output);
		T = scanner.nextLong();
		for (int i = 0 ; i < T ; i++) {
			K =  scanner.nextLong();
			fw.write(String.format("Case #%d: %s\n", i+1, get(K-1)));
//			System.out.println(String.format("Case #%d: %s\n", i+1, get(K-1)));
		}
		scanner.close();
		fw.close();
		
	}
}
