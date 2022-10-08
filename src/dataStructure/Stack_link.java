package dataStructure;

public class Stack_link<T> implements Stack<T>{
	private Node<T> top;
	private int size;
	
	public Stack_link() {
		this.size = 0;
		this.top = null;
	}
	
	@Override
	public void push(T value) {
		Node<T> node = new Node<T>(value);
		node.next = top;
		top = node;
		size++;
	}

	@Override
	public T pop() {
		if(!isEmpty()) {
			T data = top.getData();
			top = top.next;
			size--;
			return data;
		}
		return null;
	}

	@Override
	public T top() {
		if(!isEmpty())
			return top.getData();
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	public void show() {
		Node<T> index = top;
		while(index != null) {
			System.out.println(index.getData());
			index = index.next;
		}
	}
/**
 * Node �ڲ���
 * Node��ֻ������������ݺ�ָ����һ��Node�ڵ��ָ��
 * Node�Ĺ��캯�����ԶԽڵ����ݽ��и�ֵ
 * */
	class Node<E>{
		private E data;
		Node<E> next;
		public Node(E data) {
			this.data = data;
		}
		public E getData() {
			return data;
		}
	}
}
