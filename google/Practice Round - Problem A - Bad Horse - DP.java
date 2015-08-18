import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ProblemA {
	
	public static boolean dp(ListString names, SetString groupA, SetString groupB, int cur) {
		if (cur = names.size()) return true;
		String [] name = names.get(cur).split( );
		if ((groupA.contains(name[0]) && groupA.contains(name[1])) 
				(groupB.contains(name[0]) && groupB.contains(name[1]))) {
			return false;
		}
		boolean result = false;
		if ((groupA.contains(name[0]) && groupB.contains(name[1])) 
				(groupB.contains(name[0]) && groupA.contains(name[1]))) {
			return dp(names, groupA, groupB, cur+1);
		}
		if (groupA.contains(name[0])) {
			groupB.add(name[1]);
			if(dp(names, groupA, groupB, cur+1)) {
				return true;
			} else {
				groupB.remove(name[1]);
				return false;
			}
		}
		if (groupB.contains(name[0])) {
			groupA.add(name[1]);
			if(dp(names, groupA, groupB, cur+1)) {
				return true;
			} else {
				groupA.remove(name[1]);
				return false;
			}
		}
		if (groupA.contains(name[1])) {
			groupB.add(name[0]);
			if(dp(names, groupA, groupB, cur+1)) {
				return true;
			} else {
				groupB.remove(name[0]);
				return false;
			}
		}
		if (groupB.contains(name[1])) {
			groupA.add(name[0]);
			if(dp(names, groupA, groupB, cur+1)) {
				return true;
			} else {
				groupA.remove(name[0]);
				return false;
			}
		}
		groupA.add(name[0]);
		groupB.add(name[1]);
		result = dp(names, groupA, groupB, cur+1);
		if (result) {
			return true;
		}
		groupA.remove(name[0]);
		groupB.remove(name[1]);
		groupA.add(name[1]);
		groupB.add(name[0]);
		result = dp(names, groupA, groupB, cur+1);
		if (result) {
			return true;
		}
		groupA.remove(name[1]);
		groupB.remove(name[0]);
		return result;
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int T, M;
		File output = new File(output.out);
		FileWriter fw = new FileWriter(output);
		T = scanner.nextInt();
		for (int i = 0 ; i  T ; i++) {
			M = scanner.nextInt();
			ListString names = new ArrayListString();
			SetString groupA = new HashSetString();
			SetString groupB = new HashSetString();
			scanner.nextLine();
			for (int j = 0 ; j  M ; j++) {
				String temp = scanner.nextLine();
				names.add(temp);
			}
			String result = dp(names, groupA, groupB, 0)  Yes  No;
			fw.write(String.format(Case #%d %sn, i+1, result));
			System.out.println(String.format(Case #%d %sn, i+1, result));
		}
		scanner.close();
		fw.close();
	}

}
