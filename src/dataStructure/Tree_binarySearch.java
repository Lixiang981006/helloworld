package dataStructure;

/**
 * BST---����������
 * 
 * BST�ǻ���proper binary tree��
 * 
 * �������������������������������������Ƕ������������е���ɾ�����������ϳ���������������
 * �����б�ķ����ܲ����������
 * 
 * ��Ϊ��Ҫ�Է���T���бȽϣ����Խ�T���ó� T extends Comparable, �����Ϳ���ʹ��compareTo
 * �������бȽ���
 * 
 * Ŀǰ�������ǣ���Ϊʹ���˼̳й�ϵ�����Զ�������insertleft/right�����ȵȶ����̳й����ˣ�
 * ����˵��Щ�����ǲ�Ӧ�ó����ڴ����е�
 * */
public class Tree_binarySearch<T extends Comparable<T>> extends Tree_binary_properLink<T>{
	
	/**
	 * ͨ���ݹ�Ӹ��ڵ�Ѱ�Ҵ洢����Ϊelement�Ľڵ�,
	 * 
	 * @return ����ҵ��˾ͷ��ش洢�ĸ����ݵĽڵ㣬���û���ҵ��ͷ�������ҵ��ĸ�Ҷ�ڵ㣬Ҳ����
	 * ����Ϊ�յĽڵ�
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
	 * ͨ��find�����������б���������ҵ��˴��д�ֵ�Ľڵ㣬��ô�Ͳ���ִ��insert������ֱ���˳�
	 * �����ֵ�������У��ͽ���ֵ��ӵ�����
	 * */
	public void insert_BST(T element) {
		if(root == null) {
			super.addRoot(element);
			return;
		}
		Node<T> nodeFinded = (Node<T>)find(element, root());
		//����������ݵ��жϣ��ж��Ƿ�ΪҶ�ڵ�Ҳ��
		if(nodeFinded.element() == element)
			return;
		expandExternal(nodeFinded, element);
		size++;
	}
	
	/**
	 * deletion���������
	 * 1. Ҫ��ɾ���Ľڵ���һ���ⲿ�ڵ���ӽڵ�
	 * 		��������£�ֻ��Ҫ���˽ڵ�������ⲿ�ڵ��ӽڵ�ɾ��������һ���ӽڵ���ڱ�ɾ���ڵ��λ��
	 * 2. ��ɾ���ڵ�û���ⲿ�ڵ���ӽڵ�
	 * 		Ѱ��inorder��������һ���ڵ㣬���ҵ��Ľڵ�ֵ������ɾ���ڵ㣬���ҵ��Ľڵ�ɾ��
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
