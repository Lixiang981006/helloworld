package dataStructure;

public class Test_1 {
	public static void main(String[] args) {
		Tree_AVL<Integer> tree = new Tree_AVL<Integer>();
		tree.insert_AVL(12);
		tree.insert_AVL(1);
		tree.insert_AVL(15);
		tree.insert_AVL(2);
		tree.insert_AVL(3);
		tree.preOrder(tree.root);
		System.out.println(tree.size());
	}
}
