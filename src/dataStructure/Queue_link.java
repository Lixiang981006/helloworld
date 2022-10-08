package dataStructure;

/**
 * linkQueue��linkStack����������˳���Ƿ��ŵ�
 * */
public class Queue_link<T> implements Queue<T> {
	private int size;
	private Node<T> front;
	private Node<T> rear;
	
	public Queue_link() {
		this.size = 0;
		this.front = null;
		this.rear = null;
	}
	
	@Override
	public void enqueue(T data) {
		Node<T> node = new Node<T>(data);
		if(!isEmpty()) {
			rear.next = node;
			rear = node;
			size++;
		}else {
			front = node;
			rear = node;
			size++;
		}
	}

	@Override
	public T dequeue() {
		if(!isEmpty()) {
			T data = front.getData();
			front = front.next;
			size--;
//��Ϊ��enqueue���Ѿ����ǵ���sizeΪ��ʱ���������������Ĳ����Ƕ����
//			if(size() == 1)
//				rear = null;
			return data;
		}
		return null;
	}

	@Override
	public T front() {
		if(!isEmpty())
			return front.getData();
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	public void show() {
		Node<T> index = front;
		while(index != null) {
			System.out.println(index.getData());
			index = index.next;
		}
	}
	
	class Node<E>{
		private E data;
		Node<E> next;
		public Node(E data) {
			this.data = data;
			next = null;
		}
		public E getData() {
			return this.data;
		}
	}
}
