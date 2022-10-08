package dataStructure;


/**
 * proper binary tree ��ÿһ��Ҷ�ڵ�������ӽڵ㶼Ϊ�սڵ㣬���ڵ���ڣ����ǽڵ�Ԫ��
 * Ϊnull�������ڼ̳��˶�������ǰ���£��������/ɾ���ڵ�Ĳ����ᱻӰ�쵽
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
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.getLeftNode().element() != null) {
			System.out.println("���ӽڵ��Ѵ���");
			return null;
		}
		INode<T> newNode = expandExternal(left(node), element);
		size++;
		return newNode;
	}
	public INode<T> insertRight(INode<T> node, T element){
		if(node == null) {
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		Node<T> temp = (Node<T>)node;
		if(temp.getRightNode().element() != null) {
			System.out.println("���ӽڵ��Ѵ���");
			return null;
		}
		INode<T> newNode = expandExternal(right(node), element);
		size++;
		return newNode;
	}
	public INode<T> remove(INode<T> node){
		if(node == null) {
			System.out.println("�����ڵ�Ϊ��");
			return null;
		}
		//ͨ�����жϣ�ʣ�µ���ֻ��һ���ӽڵ�����
		if(left(node).element() != null && right(node).element() != null) {
			System.out.println("�˽ڵ������ӽڵ㶼���ڣ����ܱ�ɾ��");
			return null;
		}
		Node<T> target = (Node<T>)node;
		Node<T> parent = target.getParent();
		//�����ɾ���ڵ㸸�ڵ���ڵ㣬����1������Ǹ��ڵ��ҽڵ㣬����2������Ǹ��ڵ㷵��0
		int nodeType = -1;
		if(parent == null) {
			nodeType = 0;
		}else if(parent.getLeftNode() == target) {
			nodeType = 1;
		}else {
			nodeType = 2;
		}
		
	/*
	 * ���ݸ��ڵ��nodeType��target�ڵ���ӽڵ�״��ȷ��ɾ�������
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
	 * proper binary tree ���еĵ���չ�ⲿ�ڵ�ķ���
	 * */
	protected INode<T> expandExternal(INode<T> node, T element){
		if(isInternal(node)) {
			System.out.println("��չ�ڵ�Ϊ�ڲ��ڵ㣬����ʧ��");
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
