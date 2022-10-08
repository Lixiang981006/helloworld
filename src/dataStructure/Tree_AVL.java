package dataStructure;

public class Tree_AVL<T extends Comparable<T>> extends Tree_binarySearch<T>{

	public void insert_AVL(T element) {
		if(root == null) {
			addRoot(element);
			height(((Node<T>)root).getLeftNode());
			return;
		}
		Node<T> nodeFinded = (Node<T>)find(element, root());
		//����������ݵ��жϣ��ж��Ƿ�ΪҶ�ڵ�Ҳ��
		if(nodeFinded.element() == element)
			return;
		expandExternal(nodeFinded, element);
		height(nodeFinded.getLeftNode());
		size++;
	}
	
	public void remove_AVL(T element) {
		
	}
	/**
	 * �ع�����
	 * ����AVL��������ɾ����֮�����п��ܱ��unbalance�������Ҫ��unbalanced�ڵ�����ع�
	 * �ع���Ϊ4�ֿ����ԣ�����/�ң�˫��/��
	 * Ҳ��Ҫ����ڵ�ĸ��ڵ���з��ࣺ
	 * 1. ��ڵ����丸�ڵ���ڵ�
	 * 2. ��ڵ����丸�ڵ��ҽڵ�
	 * 3. ��ڵ��޸��ڵ㣨��ڵ��Ǹ��ڵ㣩
	 * 
	 * ����ܹ���12�����
	 * */
	private void reconstructure(INode<T> node) {
		Node<T> parent = ((Node<T>)node).getParent();
		Node<T> grand = parent.getParent();
		//��һ�ֵ��ع�
		if(grand.getLeftNode() == parent && parent.getLeftNode() == node) {
			grand.setRightNode(parent.getLeftNode());
			parent.getLeftNode().setParent(grand);
			
		}
	}
	
	/**
	 * ����һ���ⲿ�ڵ�ڵ㣬������ڵ���������ȵĸ߶Ƚ��и��£������Լ�
	 * */
	private void height(INode<T> nodeEx) {
		Node<T> node = (Node<T>)nodeEx;
		while(node.getParent() != null) {
			Node<T> parent = node.getParent();
			Node<T> left = parent.getLeftNode();
			Node<T> right = parent.getRightNode();
			if(left.getHeight() >= right.getHeight()) {
				parent.setHeight(left.getHeight()+1);
			}else {
				parent.setHeight(right.getHeight()+1);
			}
			node = parent;
		}
	}
}
