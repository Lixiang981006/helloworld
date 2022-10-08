package dataStructure;

import java.util.Iterator;

public class Test {
	public static void main(String[] args) {
		Tree_binary_properLink<Integer> tree = new Tree_binary_properLink<Integer>();
		INode<Integer> n1 = tree.addRoot(12);
		INode<Integer> n2 = tree.insertLeft(n1, 1);
		INode<Integer> n3 = tree.insertRight(n1, 2);
		tree.insertLeft(n2, 3);
		INode<Integer> n4 = tree.insertRight(n2, 4);
		tree.insertLeft(n3, 5);
		INode<Integer> n5 = tree.insertRight(n3, 6);
		tree.replace(n5, 777);
//		ÅÅĞò²âÊÔ
//		tree.preOrder(n1);
//		tree.inOrder(n1);
//		tree.postOrder(n1);
//		µü´úÆ÷²âÊÔ
//		Iterator<INode<Integer>> itr = tree.children(n4);
//		while(itr.hasNext()) {
//			System.out.println(itr.next().element());
//		}
//		É¾³ı²âÊÔ
		tree.remove(n4);
		tree.remove(n2);
		tree.preOrder(n1);
//		tree.inOrder(n1);
		
	}
}
