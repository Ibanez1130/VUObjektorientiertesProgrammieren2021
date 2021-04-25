import tree.ITree;
import tree.GenericTree;
import tree.node.GenericTreeNode;
import java.util.Random;

public class Application {
	public static void main(String[] args) {
		ITree<Integer> t = Application.generateGenericTree();
		System.out.println(t.generateConsoleView(" "));
	}

	public static ITree<Integer> generateGenericTree() {
		Random r = new Random();
		int min = 0;
		int max = 100;

		GenericTree<Integer> t = new GenericTree<>();

		for (int i = 0; i < 20; i++) {
			Integer n = r.nextInt(((max - min) + 1) + min);
			t.insertNode(new GenericTreeNode<Integer>(n.toString(), n));
		}
		
		return t;
	}
	
}
