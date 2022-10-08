package dataStructure;

public class Tree_AVL<T extends Comparable<T>> extends Tree_binarySearch<T>{

	public void insert_AVL(T element) {
		if(root == null) {
			addRoot(element);
			height(((Node<T>)root).getLeftNode());
			return;
		}
		Node<T> nodeFinded = (Node<T>)find(element, root());
		//这里进行数据的判断，判断是否为叶节点也行
		if(nodeFinded.element() == element)
			return;
		expandExternal(nodeFinded, element);
		height(nodeFinded.getLeftNode());
		size++;
	}
	
	public void remove_AVL(T element) {
		
	}
	/**
	 * 重构函数
	 * 当对AVL树进行增删操作之后，树有可能变得unbalance，因此需要对unbalanced节点进行重构
	 * 重构分为4种可能性：单左/右，双左/右
	 * 也需要对祖节点的父节点进行分类：
	 * 1. 祖节点是其父节点左节点
	 * 2. 祖节点是其父节点右节点
	 * 3. 祖节点无父节点（祖节点是根节点）
	 * 
	 * 因此总共有12种情况
	 * */
	private void reconstructure(INode<T> node) {
		Node<T> parent = ((Node<T>)node).getParent();
		Node<T> grand = parent.getParent();
		//第一种单重构
		if(grand.getLeftNode() == parent && parent.getLeftNode() == node) {
			grand.setRightNode(parent.getLeftNode());
			parent.getLeftNode().setParent(grand);
			
		}
	}
	
	/**
	 * 输入一个外部节点节点，将这个节点的所有祖先的高度进行更新，包括自己
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
