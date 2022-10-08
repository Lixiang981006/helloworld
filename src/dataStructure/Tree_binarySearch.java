package dataStructure;

/**
 * BST---二叉搜索树
 * 
 * BST是基于proper binary tree的
 * 
 * 二叉搜索树新增了三个方法，这三个方法是二叉搜索树独有的增删方法，理论上除了这三个方法外
 * 不能有别的方法能操作这个树。
 * 
 * 因为需要对泛型T进行比较，所以将T设置成 T extends Comparable, 这样就可以使用compareTo
 * 方法进行比较了
 * 
 * 目前的问题是：因为使用了继承关系，所以二叉树的insertleft/right方法等等都被继承过来了，
 * 按理说这些方法是不应该出现在此类中的
 * */
public class Tree_binarySearch<T extends Comparable<T>> extends Tree_binary_properLink<T>{
	
	/**
	 * 通过递归从根节点寻找存储数据为element的节点,
	 * 
	 * @return 如果找到了就返回存储哪个数据的节点，如果没有找到就返回最后找的哪个叶节点，也就是
	 * 内容为空的节点
	 * */
	protected INode<T> find(T element, INode<T> nextNode){
		if(isExternal(nextNode)) {
			return nextNode;
		}
		if(element.compareTo(nextNode.element()) < 0) {
			return find(element, left(nextNode));
		}else if(element.compareTo(nextNode.element()) > 0) {
			return find(element,right(nextNode));
		}else {
			return nextNode;
		}
	}
	
	/**
	 * 通过find（）方法进行遍历，如果找到了存有此值的节点，那么就不再执行insert方法，直接退出
	 * 如果此值不在树中，就将此值添加到树中
	 * */
	public void insert_BST(T element) {
		if(root == null) {
			super.addRoot(element);
			return;
		}
		Node<T> nodeFinded = (Node<T>)find(element, root());
		//这里进行数据的判断，判断是否为叶节点也行
		if(nodeFinded.element() == element)
			return;
		expandExternal(nodeFinded, element);
		size++;
	}
	
	/**
	 * deletion分两种情况
	 * 1. 要被删除的节点有一个外部节点的子节点
	 * 		这种情况下，只需要将此节点和他的外部节点子节点删除，将另一个子节点放在被删除节点的位置
	 * 2. 被删除节点没有外部节点的子节点
	 * 		寻找inorder排序中下一个节点，将找到的节点值赋给被删除节点，将找到的节点删除
	 * */
	public void remove_BST(T element) {
		Node<T> nodeFinded = (Node<T>)find(element, root());
		if(isExternal(nodeFinded))
			return;
		Node<T> parent = (Node<T>) nodeFinded.getParent();
		if(isExternal(nodeFinded.getLeftNode())) {
			if(parent == null) {
				root = nodeFinded.getRightNode();
				nodeFinded.getRightNode().setParent(null);
				nodeFinded.setRightNode(null);
			}else if(parent.getLeftNode().element() == element) {
				parent.setLeftNode(nodeFinded.getRightNode());
				nodeFinded.getRightNode().setParent(parent);
				nodeFinded.setParent(null);
				nodeFinded.setRightNode(null);
			}else {
				parent.setRightNode(nodeFinded.getRightNode());
				nodeFinded.getRightNode().setParent(parent);
				nodeFinded.setParent(null);
				nodeFinded.setRightNode(null);
			}
		}else if(isExternal(nodeFinded.getRightNode())) {
			if(parent == null) {
				root = nodeFinded.getLeftNode();
				nodeFinded.getLeftNode().setParent(null);
				nodeFinded.setLeftNode(null);
			}else if(parent.getLeftNode().element() == element) {
				parent.setLeftNode(nodeFinded.getLeftNode());
				nodeFinded.getLeftNode().setParent(parent);
				nodeFinded.setParent(null);
				nodeFinded.setLeftNode(null);
			}else {
				parent.setRightNode(nodeFinded.getLeftNode());
				nodeFinded.getLeftNode().setParent(parent);
				nodeFinded.setParent(null);
				nodeFinded.setLeftNode(null);
			}
		}else {
			Node<T> node = nodeFinded.getRightNode();
			while(node.getLeftNode().element() != null) {
				node = node.getLeftNode();
			}
			nodeFinded.setElement(node.element());
			if(node.getParent().getLeftNode() == node) {
				node.getParent().setLeftNode(node.getRightNode());
			}else {
				node.getParent().setRightNode(node.getRightNode());
			}
			node.getRightNode().setParent(node.getParent());
			node.setParent(null);
			node.setRightNode(null);
		}
		size--;
	}
}
