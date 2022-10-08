package dataStructure;

import java.util.Iterator;
import java.util.LinkedList;

public class Tree_binary_link<T> implements Tree_binary<T>{
	protected int size;
	protected INode<T> root;
	
	/**
	 * Ĭ�Ϲ��캯����Ĭ�ϳ�ʼ��������Ĵ�СΪ0�����ڵ�Ϊ��
	 * */
	public Tree_binary_link() {
		this.size = 0;
		this.root = null;
	}

/*-------------------------------------------------------------------
 * ʵ����Tree_binary�ӿڵķ���
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
	 * ʹ����java�Դ���Iterator�࣬�Ƚ��ڵ����һ���µ������У�֮�󷵻����
	 * ����ĵ����������ڽڵ����ݵ���ʾ�͵�����ʹ��ʱʵ��
	 * */
	@Override
	public Iterator<INode<T>> children(INode<T> node) {
		if(node == null) {
			System.out.println("�����ڵ�Ϊ��");
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
			System.out.println("�����ڵ�Ϊ��");
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
			System.out.println("�����ڵ�Ϊ��");
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
	 * ��ʱ��ʵ�֣���������Ŀǰ�������
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
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		T temp = node.element();
		((Node<T>)node).element = element;
		return temp;
	}

	@Override
	public INode<T> left(INode<T> node) {
		if(node == null) {
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		return ((Node<T>)node).leftNode;
	}

	@Override
	public INode<T> right(INode<T> node) {
		if(node == null) {
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		return ((Node<T>)node).rightNode;
	}

	@Override
	public boolean hasLeft(INode<T> node) {
		if(node == null) {
			System.out.println("�����ڵ�Ϊ��");
			return false;
		}
		return !(((Node<T>)node).leftNode == null);
	}

	@Override
	public boolean hasRight(INode<T> node) {
		if(node == null) {
			System.out.println("�����ڵ�Ϊ��");
			return false;
		}
		return !(((Node<T>)node).rightNode == null);
	}
	
/*-----------------------------------------------------------
 * �����Ƿ�ʵ�ֶ������ӿڵķ�������
 * 
 * �Ƕ�������Ӻ�ɾ����������Ϊ����ʵ�ַ�ʽ��ͬ����Щ������ʵ��Ҳ���в��죬����û��
 * ����Щ����д��ӿ�
 * 
 * 
 * 
 * */
	
	/**
	 * create and return a new root node storing e;
	 * an error should occur if the tree is not empty
	 * 
	 * �����õ��ǵ�root�Ѿ�����ʱ������null
	 * ����Ϊ��ʱ��������Ӹ��ڵ�
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
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.leftNode != null) {
			System.out.println("���ӽڵ��Ѵ���");
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
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.rightNode != null) {
			System.out.println("���ӽڵ��Ѵ���");
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
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		//ͨ�����жϣ�ʣ�µ���ֻ��һ���ӽڵ�����
		if(hasLeft(node) && hasRight(node)) {
			System.out.println("�˽ڵ������ӽڵ㶼���ڣ����ܱ�ɾ��");
			return null;
		}
		Node<T> target = (Node<T>)node;
		Node<T> parent = target.parent;
		//�����ɾ���ڵ㸸�ڵ���ڵ㣬����1������Ǹ��ڵ��ҽڵ㣬����2������Ǹ��ڵ㷵��0
		int nodeType = -1;
		if(parent == null) {
			nodeType = 0;
		}else if(parent.leftNode == target) {
			nodeType = 1;
		}else {
			nodeType = 2;
		}
		
	/*
	 * ���ݸ��ڵ��nodeType��target�ڵ���ӽڵ�״��ȷ��ɾ�������
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
			System.out.println("�ڲ��ڵ㲻�ܽ���attach����");
			return;
		}
		Node<T> temp = (Node<T>)node;
		temp.leftNode = (Node<T>)tree_left;
		temp.rightNode = (Node<T>)tree_right;
		
	}
	
/*--------------------------------------------------------------------
 * ������ʽ
 * 
 * Ŀǰ�����������ڶ������еģ����յ�һְ��ԭ�򣬱�����Ӧ�õ�����һ�����У�����
 * ������ʵ���Ǻ�����������صģ�����������ͷǶ������ı�������ʵ���ǲ�һ���ģ�
 * Ŀǰ��û���ҵ����ʵ�ʵ�ַ������������ھ��������ʵ�ֱ���
 * 
 * �˴����ع��ɵ���һ����
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
	 * ʵ����INode<T>�ӿڵ��ڲ�node�࣬����粻�ɼ�
	 * 
	 * ��ͬ�������Node��ʵ�ַ�ʽ���ܲ�һ�������Խ�Node����Ƴ��˶���������ڲ���
	 * 
	 * 
	 * */
	class Node<E> implements INode<E>{
		private E element;
		private Node<E> leftNode;
		private Node<E> rightNode;
		private Node<E> parent;
		//height������AVL���б�ʹ�õ�����֮ǰ�ļ��������ṹ��û����
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
		//�ⲿ�����ֱ�ӷ����ڲ����˽�б���������getter/setter�����Ͷ�����
		//���ǣ����ⲿ�౻�̳�֮���ڲ��಻�����Ǹ����̳е����࣬����getter/setter��Ȼ�д��ڱ�Ҫ		


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




