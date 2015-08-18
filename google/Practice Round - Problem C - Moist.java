import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class ProblemC {
	
	public static boolean dp(Map<String, Set<String>> r) {
		return false;
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int T, M;
		File output = new File("output.out");
		FileWriter fw = new FileWriter(output);
		T = scanner.nextInt();
		for (int i = 0 ; i < T ; i++) {
			M = scanner.nextInt();
			int count = 0;
			String last = null;
			scanner.nextLine();
			for (int j = 0 ; j < M ; j++) {
				String tmp = scanner.nextLine();
				if (ge(tmp, last)) {
					last = tmp;
				} else {
					count++;
				}
			}
			fw.write(String.format("Case #%d: %d\n", i+1, count));
//			System.out.println(String.format("Case #%d: %d\n", i+1, count));
		}
		scanner.close();
		fw.close();
	}
	
	public static boolean ge(String s1, String s2) {
		if (s2 == null) return true;
		char [] c1 = s1.toCharArray();
		char [] c2 = s2.toCharArray();
		int len = c1.length < c2.length ? c1.length : c2.length;
		for (int i = 0 ; i < len ; i++) {
			if (c1[i] < c2[i]) {
				return false;
			} else if (c1[i] > c2[i]) {
				return true;
			}
		}
		if (c2.length > len) {
			return false;
		}
		return true;
	}

}
