package dataStructure;

/**
 * ����˵Deque��˫������ļ򻯰汾��Deque����˫������Ļ�����ɾ���˲����м�ڵ�ķ�������
 * �ɲ����Ľڵ��������
 * 
 * Deque�㷨���Ӷȣ�
 * ȫ���ķ������Ӷȶ���O��1����
 * ��˫������һ����Deque��ʹ�ø���Ŀռ�洢ָ������ȡ�����ٶ�
 * */
public class Deque_link<T> implements Deque<T>{
	private int size;
	private Node<T> first;
	private Node<T> last;
	
	//constructor
	public Deque_link() {
		this.size = 0;
		this.first = null;
		this.last = null;
	}
	
	@Override
	public void addFirst(T data) {
		Node<T> newNode = new Node<T>(data);
		if(isEmpty()) {
			first = newNode;
			last = newNode;
		}else {
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
		}
		size++;
	}

	@Override
	public void addLast(T data) {
		Node<T> newNode = new Node<T>(data);
		if(isEmpty()) {
			first = newNode;
			last = newNode;
		}else {
			newNode.previous = last;
			last.next = newNode;
			last = newNode;
		}
		size++;		
	}
/**
 * throw֮������ȫ���޷����У����е�throw���������ж�
 * ���������
 * 1.��dequeΪ��ʱ������ɾ��
 * 2.��dequeֻ��һ��Ԫ��ʱ��ɾ����dequeΪ��
 * 
 * */
	@Override
	public T removeFirst() throws EmptyDequeException {
		if(isEmpty())
			throw new EmptyDequeException("deque is empty");
		T data = first.element();
		if(size == 1) {
			first = null;
			last = null;
			size--;
			return data;
		}
		Node<T> nextNode = first.next;
		nextNode.previous = null;
		first.next = null;
		first = nextNode;
		size--;
		return data;
	}

	@Override
	public T removeLast() throws EmptyDequeException {
		if(isEmpty())
			throw new EmptyDequeException("deque is empty");
		T data = last.element();
		if(size == 1) {
			first = null;
			last = null;
			size--;
			return data;
		}
		Node<T> previousNode = last.previous;
		previousNode.next = null;
		last.previous = null;
		last = previousNode;
		size--;
		return data;
	}

	@Override
	public T getFirst() throws EmptyDequeException {
		if(isEmpty())
			throw new EmptyDequeException("deque is empty");
		T data = first.element();
		return data;
	}

	@Override
	public T getLast() throws EmptyDequeException {
		if(isEmpty())
			throw new EmptyDequeException("deque is empty");
		T data = last.element();
		return data;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public void show() {
		Node<T> index = first;
		while(index != null) {
			System.out.println(index.element());
			index = index.next;
		}
	}
	
/**
 * Node�ڲ���ʵ����Position�ӿ�
 * Deque���ڲ���ʵ�ַ�ʽ��˫������һ��
 * һ���ڵ�������ǰһ���ͺ�һ���ڵ��ָ�룬�Ӷ�ʵ��˫�����
 * Position�ӿڶ�������List�ӿ��ļ���
 * */
	class Node<E> implements Position<E>{
		private E data;
		Node<E> next;
		Node<E> previous;
		
		//constructor
		public Node(E data){
			this.data = data;
			this.next = null;
			this.previous = null;
		}
		
		@Override
		public E element() {
			return this.data;
		}
	}
}
