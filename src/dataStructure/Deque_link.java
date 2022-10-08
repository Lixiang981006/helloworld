package dataStructure;

/**
 * 可以说Deque是双向链表的简化版本，Deque是在双向链表的基础上删除了操作中间节点的方法并对
 * 可操作的节点加以限制
 * 
 * Deque算法复杂度：
 * 全部的方法复杂度都是O（1），
 * 和双向链表一样，Deque以使用更多的空间存储指针来换取运算速度
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
 * throw之后的语句全部无法运行，运行到throw程序立刻中断
 * 特殊情况：
 * 1.当deque为空时，不能删除
 * 2.当deque只有一个元素时，删除完deque为空
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
 * Node内部类实现了Position接口
 * Deque的内部类实现方式和双向链表一致
 * 一个节点会持有它前一个和后一个节点的指针，从而实现双向操作
 * Position接口定义在了List接口文件中
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
