import java.util.*;
class Node {
	public int value;
	public Node next;
}

class Table {
	public int length = 0;
	public Node node;
}
public class Main {
	public static int size;
	public static Table[] tables;
	public static void buildHash(Integer v, Table[] tables) {
		int index = v % size;
		Node node = new Node();
		node.value = v;
		node.next = tables[index].node;
		tables[index].node = node;
		tables[index].length++;
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		String[] split = str.split("/");
		size = Integer.parseInt(split[0]);
		tables = new Table[size];
		for (int i = 0; i < size; i++) {
			tables[i] = new Table();
		}
		String[] str2 = split[1].split(",");
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < str2.length; i++) {
			if (str2[i].contains("-")) {
				String[] str3 = str2[i].split("-");
				int begin = Integer.parseInt(str3[0]);
				int end = Integer.parseInt(str3[1]);
				for (int j = begin; j <= end; j++) {
					set.add(j);
				}
			} else {
				set.add(Integer.parseInt(str2[i]));
			}
		}
		
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			buildHash(it.next(), tables);
		}
		int max = 0;
		int location = 0;
		for (int i = 0; i < size; i++) {
			if (max < tables[i].length) {
				max = tables[i].length;
				location = i;
			}
		}
		
		System.out.print(max + "-" + location + "-");
		Node node = tables[location].node;
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

}