package dataStructure;

/**
 * singleLinkList中各方法的复杂度
 * first:O(1)
 * last:O(n)
 * after: O(1)
 * before: O(n)
 * insertFirst: O(1)
 * insertLast: O(n)
 * insertAfter: O(1)
 * insertBefore: O(n)
 * remove: O(n)
 * size: O(1)
 * isEmpty: O(1)
 * */
public class List_singleLink<T> implements List<T> {
	private Node<T> first;
	private int size;

	//constructor
	public List_singleLink() {
		this.size = 0;
		this.first = null;
	}
/**
 * first函数没有特殊情况
 * 当链表为空时，first=null，返回null
 * */
	@Override
	public Position<T> first() {
		return first;
	}

	@Override
	public Position<T> last() {
		//first为null时，即list为空时情况不一样
		if(!isEmpty()) {
			//取出first的指针，用来遍历
			Node<T> index = first;
			while(index.next!=null) {
				index = index.next;
			}
			return index;
		}
		//list为空时，直接返回null
		return null;
	}

	@Override
	public Position<T> before(Position<T> p) {
		Node<T> index = first;
		//不会发生p和index.next类型不匹配的导致无法判断的情况
		//因为即使表面上类型不匹配，只要实际内容相同就会返回true
		if(isEmpty() || p == first || !(p instanceof Node)) {
			return null;
		}else {
			while(index.next != null && index.next != p) {
				index = index.next;
			}
			return index;
		}
	}
/**
 * 需要判断输入的类型是否为Node类型，之后进行数据类型强制转换,因为需要用到node.next
 * 没有考虑参数位置不在链表中的情况
 * */
	@Override
	public Position<T> after(Position<T> p) {
		if(p instanceof Node) {
			Node<T> node = (Node<T>)p;
			return node.next;
		}
		return null;
	}
/**
 * 个人认为在链表为空或输入的位置为null时，insertBefore方法不能使用
 * */
	@Override
	public Position<T> insertBefore(Position<T> p, T data) {
		Node<T> newNode = new Node<T>(data);
		//输入为null，直接返回null
		if(p == null || !(p instanceof Node))
			return null;
		//如果是p时第一个元素，单独考虑
		if(p == first) {
			newNode.next = first;
			first = newNode;
			size++;
			return newNode;
		}
		//p不是第一个元素的情况,需要遍历链表找到前一个数据
		Node<T> index = first;
		while(index.next != null && index.next != p) {
			index = index.next;
		}
		newNode.next = index.next;
		index.next = newNode;
		size++;
		return newNode;
	}
/**
 * 需要强制类型转换，因为需要使用到node.next
 * */
	@Override
	public Position<T> insertAfter(Position<T> p, T data) {
		//排除输入错误的情况
		if(p == null || !(p instanceof Node))
			return null;
		
		Node<T> newNode = new Node<T>(data);
		Node<T> node = (Node<T>)p;
		newNode.next = node.next;
		node.next = newNode;
		return newNode;
	}

/**
 * 空集会对insertLast方法产生影响，因此单独考虑
 * */
	@Override
	public Position<T> insertLast(T data) {
		Node<T> newNode = new Node<T>(data);
		if(isEmpty()) {
			first = newNode;
		}else {
			Node<T> index = first;
			while(index.next != null) {
				index = index.next;
			}
			index.next = newNode;
		}
		size++;
		return newNode;
	}
/**
 * 没有任何特殊情况
 * */
	@Override
	public Position<T> insertFirst(T data) {
		Node<T> newNode = new Node<T>(data);
		newNode.next = first;
		first = newNode;
		size++;
		return newNode;
	}
/**
 * 删除一个节点需要知道他的前后节点，将两个节点拼接起来
 * 如果要知道前一个节点，在单向链表中需要遍历
 * */
	@Override
	public T remove(Position<T> p) {
		if(isEmpty() || !(p instanceof Node)) {
			return null;
		}else if(p == first){
			first = first.next;
			size--;
			return p.element();
		}else {
			Node<T> node = (Node<T>)p;
			Node<T> index = first;
			while(index.next != null && index.next != p) {
				index = index.next;
			}
			index.next = node.next;
			size--;
			return p.element();
		}
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
	
	//inner class Node implements Position interface
	class Node<E> implements Position<E>{
		private E data;
		Node<E> next;
		//Node constructor
		public Node(E data){
			this.data = data;
			this.next = null;
		}
		@Override
		public E element() {
			return data;
		}
	}
}
