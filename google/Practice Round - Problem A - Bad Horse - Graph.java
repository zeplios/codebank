import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


public class ProblemA {
	
	public static Set<String> get(Map<String, Set<String>> map, String key) {
		Set<String> list = map.get(key);
		if (list == null) {
			list = new HashSet<String>();
			map.put(key, list);
		}
		return list;
	}
	
	public static boolean resolve(Map<String, Set<String>> graph) {
		Map<String, Integer> groups = new HashMap<String, Integer>();
		Queue<String> q = new LinkedList<String>();
		while (graph.size() > 0) {
			String name = graph.keySet().iterator().next();
			q.add(name);
			groups.put(name, 0);
			while (q.size() > 0) {
				String cur = q.poll();
				for (String adjacent : graph.get(cur)) {
					if (groups.containsKey(adjacent) && 
							groups.get(adjacent) + groups.get(cur) != 1) {
						return false;
					}
					if (!groups.containsKey(adjacent)) {
						q.add(adjacent);
						groups.put(adjacent, 1 - groups.get(cur));
					}
				}
				graph.remove(cur);
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int T, M;
		File output = new File("output.out");
		FileWriter fw = new FileWriter(output);
		T = scanner.nextInt();
		for (int i = 0 ; i < T ; i++) {
			M =  scanner.nextInt();
			Map<String, Set<String>> graph = new HashMap<String, Set<String>>();
			scanner.nextLine();
			for (int j = 0 ; j < M ; j++) {
				String [] tmp = scanner.nextLine().split(" ");
				get(graph, tmp[0]).add(tmp[1]);
				get(graph, tmp[1]).add(tmp[0]);
			}
			String result = resolve(graph) ? "Yes" : "No";
			fw.write(String.format("Case #%d: %s\n", i+1, result));
//			System.out.println(String.format("Case #%d: %s\n", i+1, result));
		}
		scanner.close();
		fw.close();
	}

}
