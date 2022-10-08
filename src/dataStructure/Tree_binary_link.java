package dataStructure;

import java.util.Iterator;
import java.util.LinkedList;

public class Tree_binary_link<T> implements Tree_binary<T>{
	protected int size;
	protected INode<T> root;
	
	/**
	 * 默认构造函数，默认初始情况下树的大小为0，根节点为空
	 * */
	public Tree_binary_link() {
		this.size = 0;
		this.root = null;
	}

/*-------------------------------------------------------------------
 * 实现自Tree_binary接口的方法
 * */
	@Override
	public INode<T> parent(INode<T> node) {
		return ((Node<T>)node).parent;
	}

	@Override
	public INode<T> root() {
		return this.root;
	}

	/**
	 * 使用了java自带的Iterator类，先将节点加入一个新的链表中，之后返回这个
	 * 链表的迭代器，对于节点数据的显示和迭代在使用时实现
	 * */
	@Override
	public Iterator<INode<T>> children(INode<T> node) {
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		LinkedList<INode<T>> list = new LinkedList<INode<T>>();
		Node<T> temp = (Node<T>)node;
		if(hasLeft(temp)) {
			list.add(temp.leftNode);
		}
		if(hasRight(temp)) {
			list.add(temp.rightNode);
		}
		
		return list.iterator();
	}

	
	@Override
	public boolean isInternal(INode<T> node) {
		if(node == null) {
			System.out.println("参数节点为空");
			return false;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.leftNode == null && temp.rightNode == null)
			return false;
		return true;
	}

	@Override
	public boolean isExternal(INode<T> node) {
		if(node == null) {
			System.out.println("参数节点为空");
			return false;
		}
		if(isInternal(node))
			return false;
		return true;
	}

	@Override
	public boolean isRoot(INode<T> node) {
		return node.equals(root);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 暂时不实现，搜索方法目前解决不了
	 * */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<INode<T>> nodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T replace(INode<T> node, T element) {
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		T temp = node.element();
		((Node<T>)node).element = element;
		return temp;
	}

	@Override
	public INode<T> left(INode<T> node) {
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		return ((Node<T>)node).leftNode;
	}

	@Override
	public INode<T> right(INode<T> node) {
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		return ((Node<T>)node).rightNode;
	}

	@Override
	public boolean hasLeft(INode<T> node) {
		if(node == null) {
			System.out.println("参数节点为空");
			return false;
		}
		return !(((Node<T>)node).leftNode == null);
	}

	@Override
	public boolean hasRight(INode<T> node) {
		if(node == null) {
			System.out.println("参数节点为空");
			return false;
		}
		return !(((Node<T>)node).rightNode == null);
	}
	
/*-----------------------------------------------------------
 * 以下是非实现二叉树接口的方法部分
 * 
 * 是对树的添加和删除方法，因为与树实现方式不同，这些方法的实现也会有差异，所以没有
 * 将这些方法写入接口
 * 
 * 
 * 
 * */
	
	/**
	 * create and return a new root node storing e;
	 * an error should occur if the tree is not empty
	 * 
	 * 我设置的是当root已经存在时，返回null
	 * 当树为空时，正常添加根节点
	 * */
	public INode<T> addRoot(T element){
		if(root != null)
			return null;
		Node<T> newNode = new Node<T>(element, null, null);
		root = newNode;
		size++;
		return newNode;
	}
	
	/**
	 * create and return a new node storing e as the
	 * left child of n; an error should occur if n already
	 * has a left child.
	 * */
	public INode<T> insertLeft(INode<T> node,T element){
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.leftNode != null) {
			System.out.println("左子节点已存在");
			return null;
		}
		Node<T> newNode = new Node<T>(element, null, null);
		
		temp.leftNode = newNode;
		newNode.parent = temp;
		size++;
		return newNode;
	}
	
	/**
	 * create and return a new node storing e as the
	 * right child of n; an error should occur if n
	 * already has a right child.
	 * */
	public INode<T> insertRight(INode<T> node,T element){
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.rightNode != null) {
			System.out.println("右子节点已存在");
			return null;
		}
		Node<T> newNode = new Node<T>(element, null, null);
		temp.rightNode = newNode;
		newNode.parent = temp;
		size++;
		return newNode;
	}
	
	/**
	 * remove node n and replace it with its child, if
	 * any, and return the element stored at n; an
	 * error occurs if n has two children.
	 * */
	public INode<T> remove(INode<T> node){
		if(node == null) {
			System.out.println("参数节点为空");
			return null;
		}
		//通过此判断，剩下的是只有一个子节点的情况
		if(hasLeft(node) && hasRight(node)) {
			System.out.println("此节点左右子节点都存在，不能被删除");
			return null;
		}
		Node<T> target = (Node<T>)node;
		Node<T> parent = target.parent;
		//如果被删除节点父节点左节点，返回1，如果是父节点右节点，返回2，如果是根节点返回0
		int nodeType = -1;
		if(parent == null) {
			nodeType = 0;
		}else if(parent.leftNode == target) {
			nodeType = 1;
		}else {
			nodeType = 2;
		}
		
	/*
	 * 根据父节点的nodeType和target节点的子节点状况确定删除的情况
	 * */
		
		if(hasLeft(node)) {
			Node<T> leftNode = ((Node<T>)node).leftNode;
			if(nodeType == 0) {
				root = leftNode;
				leftNode.parent = null;
				target.leftNode = null;
			}else if(nodeType == 1) {
				parent.leftNode = leftNode;
				leftNode.parent = parent;
				target.parent = null;
				target.leftNode = null;
			}else {
				parent.rightNode = leftNode;
				leftNode.parent = parent;
				target.leftNode = null;
				target.parent = null;
			}
		}else if(hasRight(node)) {
			Node<T> rightNode = ((Node<T>)node).rightNode;
			if(nodeType == 0) {
				root = rightNode;
				rightNode.parent = null;
				target.leftNode = null;
			}else if(nodeType == 1) {
				parent.leftNode = rightNode;
				rightNode.parent = parent;
				target.parent = null;
				target.leftNode = null;
			}else {
				parent.rightNode = rightNode;
				rightNode.parent = parent;
				target.leftNode = null;
				target.parent = null;
			}
		}else {
			if(nodeType == 0) {
				root = null;
				target.parent = null;
			}else if(nodeType == 1) {
				parent.leftNode = null;
				target.parent = null;
			}else {
				parent.rightNode = null;
				target.parent = null;
			}
		}
		size--;
		return node;
	}
	
	/**
	 * Attach T1 and T2 respectively, as the left and
	 * right subtrees of the external node n; an error
	 * occurs if n is not external.
	 * */
	public void attach(INode<T> node, INode<T> tree_left, INode<T> tree_right) {
		if(isInternal(node)) {
			System.out.println("内部节点不能进行attach操作");
			return;
		}
		Node<T> temp = (Node<T>)node;
		temp.leftNode = (Node<T>)tree_left;
		temp.rightNode = (Node<T>)tree_right;
		
	}
	
/*--------------------------------------------------------------------
 * 遍历方式
 * 
 * 目前遍历方法是在二叉树中的，按照单一职责原则，遍历类应该单放在一个类中，但是
 * 遍历的实现是和树的类型相关的，比如二叉树和非二叉树的遍历方法实现是不一样的（
 * 目前我没有找到普适的实现方法），所以在具体的树中实现遍历
 * 
 * 此处可重构成单独一个类
 * */
	/**
	 * 1. A preorder traversal visits a node before it
	 * recursively visits its subtrees (left first, then right).
	 * 2. When we recursively visit a subtree, it means that
	 * we visit all the descendants in that subtree before
	 * we go further.
	 * 3. Preorder traversal can be used with any tree type.
	 * */
	public void preOrder(INode<T> node) {
		Node<T> temp = (Node<T>)node;
		visit(temp);
		if(hasLeft(temp)) {
			preOrder(left(temp));
		}
		if(hasRight(temp)) {
			preOrder(right(temp));
		}
	}
	
	/**
	 * 1. An inorder traversal visits a node after recursively visiting
	 * its left subtree, but before recursively visiting the right
	 * subtree.
	 * 2. A node cannot be visited until all of its descendants in the
	 * left subtree have been visited (but before any in the right
	 * subtree).
	 * 3. Inorder traversal only makes sense for binary trees.
	 * */
	public void inOrder(INode<T> node) {
		Node<T> temp = (Node<T>)node;
		if(hasLeft(temp)) {
			inOrder(left(temp));
		}
		visit(temp);
		if(hasRight(temp)) {
			inOrder(right(temp));
		}
	}
	
	/**
	 * 1. A postorder traversal visits a node after recursively
	 * visiting all its child subtrees (left first, then right).
	 * 2. A node will not be visited until all its descendants
	 * have been visited.
	 * 3. Postorder traversal can be used with any tree type.
	 * */
	public void postOrder(INode<T> node) {
		Node<T> temp = (Node<T>)node;
		if(hasLeft(temp)) {
			postOrder(left(temp));
		}
		if(hasRight(temp)) {
			postOrder(right(temp));
		}
		visit(temp);
	}
	
	
	public void visit(INode<T> node) {
		System.out.println(((Node<T>)node).height+" "+node.element());
	}
//--------------------------------------------------------------------
	/**
	 * 实现了INode<T>接口的内部node类，对外界不可见
	 * 
	 * 不同种类的树Node的实现方式可能不一样，所以将Node类设计成了二叉树类的内部类
	 * 
	 * 
	 * */
	class Node<E> implements INode<E>{
		private E element;
		private Node<E> leftNode;
		private Node<E> rightNode;
		private Node<E> parent;
		//height属性在AVL树中被使用到，在之前的几个简单树结构中没有用
		private int height;
		
		public Node() {
			element = null;
			leftNode = null;
			rightNode = null;
			parent = null;
			height = 0;
		}
		
		public Node(E element,Node<E> leftNode,Node<E> rightNode) {
			this.element = element;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
		
		@Override
		public E element() {
			return element;
		}

		//getters and setters
		//外部类可以直接访问内部类的私有变量，这样getter/setter方法就多余了
		//但是，在外部类被继承之后，内部类不属于那个被继承的子类，所以getter/setter依然有存在必要		


		public void setElement(E element) {
			this.element = element;
		}

		public Node<E> getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(Node<E> leftNode) {
			this.leftNode = leftNode;
		}

		public Node<E> getRightNode() {
			return rightNode;
		}

		public void setRightNode(Node<E> rightNode) {
			this.rightNode = rightNode;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}
		
		public int getHeight() {
			return height;
		}
		
		public void setHeight(int height) {
			this.height = height;
		}
	}
}




