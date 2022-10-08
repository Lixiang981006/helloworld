package dataStructure;


/**
 * proper binary tree 是每一个叶节点的两个子节点都为空节点，即节点存在，但是节点元素
 * 为null，所以在继承了二叉树的前提下，所有添加/删除节点的操作会被影响到
 * */
public class Tree_binary_properLink<T> extends Tree_binary_link<T>{
	@Override
	public INode<T> addRoot(T element){
		if(root != null)
			return null;
		Node<T> n1 = new Node<T>();
		Node<T> n2 = new Node<T>();
		Node<T> newNode = new Node<T>(element, n1, n2);
		n1.setParent(newNode);
		n2.setParent(newNode);
		root = newNode;
		size++;
		return newNode;
	}
	
	@Override
	public INode<T> insertLeft(INode<T> node, T element){
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.getLeftNode().element() != null) {
			System.out.println("左子节点已存在");
			return null;
		}
		INode<T> newNode = expandExternal(left(node), element);
		size++;
		return newNode;
	}
	public INode<T> insertRight(INode<T> node, T element){
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.getRightNode().element() != null) {
			System.out.println("右子节点已存在");
			return null;
		}
		INode<T> newNode = expandExternal(right(node), element);
		size++;
		return newNode;
	}
	public INode<T> remove(INode<T> node){
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		//通过此判断，剩下的是只有一个子节点的情况
		if(left(node).element() != null && right(node).element() != null) {
			System.out.println("此节点左右子节点都存在，不能被删除");
			return null;
		}
		Node<T> target = (Node<T>)node;
		Node<T> parent = target.getParent();
		//如果被删除节点父节点左节点，返回1，如果是父节点右节点，返回2，如果是根节点返回0
		int nodeType = -1;
		if(parent == null) {
			nodeType = 0;
		}else if(parent.getLeftNode() == target) {
			nodeType = 1;
		}else {
			nodeType = 2;
		}
		
	/*
	 * 根据父节点的nodeType和target节点的子节点状况确定删除的情况
	 * */
		
		if(left(node).element() != null) {
			Node<T> leftNode = ((Node<T>)node).getLeftNode();
			if(nodeType == 0) {
				root = leftNode;
				leftNode.setParent(null);
				target.setLeftNode(null);
			}else if(nodeType == 1) {
				parent.setLeftNode(leftNode);
				leftNode.setParent(parent);
				target.setParent(null);
				target.setLeftNode(null);
			}else {
				parent.setRightNode(leftNode);
				leftNode.setParent(parent);
				target.setLeftNode(null);
				target.setParent(null);
			}
		}else if(right(node).element() != null) {
			Node<T> rightNode = ((Node<T>)node).getRightNode();
			if(nodeType == 0) {
				root = rightNode;
				rightNode.setParent(null);
				target.setRightNode(null);
			}else if(nodeType == 1) {
				parent.setLeftNode(rightNode);
				rightNode.setParent(parent);
				target.setParent(null);
				target.setRightNode(null);
			}else {
				parent.setRightNode(rightNode);
				rightNode.setParent(parent);
				target.setParent(null);
				target.setRightNode(null);
			}
		}else {
			if(nodeType == 0) {
				root = null;
				target.setParent(null);
			}else if(nodeType == 1) {
				Node<T> n1 = new Node<T>();
				parent.setLeftNode(n1);
				n1.setParent(parent);
				target.setParent(null);
			}else {
				Node<T> n1 = new Node<T>();
				parent.setRightNode(n1);
				n1.setParent(parent);
				target.setParent(null);
			}
		}
		size--;
		return node;
	}
	
	/**
	 * proper binary tree 独有的的扩展外部节点的方法
	 * */
	protected INode<T> expandExternal(INode<T> node, T element){
		if(isInternal(node)) {
			System.out.println("扩展节点为内部节点，操作失败");
			return null;
		}
		Node<T> newNode = (Node<T>)node;
		Node<T> n1 = new Node<T>();
		Node<T> n2 = new Node<T>();
		newNode.setElement(element);
		newNode.setLeftNode(n1);
		n1.setParent(newNode);
		newNode.setRightNode(n2);
		n2.setParent(newNode);
		return newNode;
	}
}
