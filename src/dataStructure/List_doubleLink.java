package dataStructure;

/**
 * 双向链表中各方法的复杂度
 * first:O(1)
 * last:O(1)
 * after: O(1)
 * before: O(1)
 * insertFirst: O(1)
 * insertLast: O(1)
 * insertAfter: O(1)
 * insertBefore: O(1)
 * remove: O(1)
 * size: O(1)
 * isEmpty: O(1)
 * 
 * 双向链表存储了节点前后两个相邻节点的指针
 * 这种方式占用了更多的存储资源，但是以此为代价换取了算法速度上的提升，
 * 双向表中所有的方法都是O（1）的
 * 
 * 存在的问题：
 * 如果在删除一个元素之前已经保存下了被删除节点的引用，
 * 在删除之后对被删除节点进行insertAfter或insertBefore操作会报错。
 * 理论上这个问题是可解的，只需要对被删除的对象进行回收即可，但是在Java中
 * 对象的回收是自动进行的。
 * */
public class List_doubleLink<T> implements List<T> {
	private int size;
	private Node<T> first;
	private Node<T> last;
	
	//constructor
	public List_doubleLink() {
		this.size = 0;
		this.last = null;
		this.first = null;
	}

/**
 * 返回第一个node的指针
 * 没有特殊情况（链表为空时，first为null，返回null）
 * */
	@Override
	public Position<T> first() {
		return first;
	}

/**
 * 返回最后一个node的指针
 * 没有特殊情况（链表为空时，last为null，返回null）
 * */
	@Override
	public Position<T> last() {
		return last;
	}

/**
 * 返回参数位置的前一个节点，可使用previous获取
 * 特殊情况：
 * 1.当输入的p为null时，直接返回null
 * 2.当输入的p不为Node类型变量时，直接返回null
 * 3.当p是第一个节点时，p前没有节点，返回null，不用考虑，因为此时previous就是null
 * */
	@Override
	public Position<T> before(Position<T> p) {
		//无效输入
		if(p == null || !(p instanceof Node))
			return null;
		Node<T> node = (Node<T>)p;
		return node.previous;
	}

/**
 * 注释和before方法一致
 * */
	@Override
	public Position<T> after(Position<T> p) {
		if(p == null || !(p instanceof Node))
			return null;
		Node<T> node = (Node<T>)p;
		return node.next;
	}

/**
 * 特殊情况：
 * 1.当node是第一个节点时，node.previous是null，所以在获取node.previous.next时会报错，单独考虑
 * */
	@Override
	public Position<T> insertBefore(Position<T> p, T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> node = (Node<T>)p;
		if(node != first) {
			Node<T> previousNode = node.previous;
			newNode.previous = previousNode;
			previousNode.next = newNode;
		}else {
			first = newNode;
		}
		newNode.next = node;
		node.previous = newNode;
		size++;
		return newNode;
	}

	@Override
	public Position<T> insertAfter(Position<T> p, T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> node = (Node<T>)p;
		if(node != last) {
			Node<T> nextNode = node.next;
			newNode.next = nextNode;
			nextNode.previous = newNode;
		}else {
			last = newNode;
		}
		newNode.previous = node;
		node.next = newNode;
		size++;
		return newNode;
		}

	@Override
	public Position<T> insertLast(T data) {
		Node<T> newNode = new Node<T>(data);
		if(isEmpty()) {
			first = newNode;
			last = newNode;
			size++;
			return newNode;
		}
		newNode.previous = last;
		last.next = newNode;
		last = newNode;
		size++;
		return newNode;
	}

	@Override
	public Position<T> insertFirst(T data) {
		Node<T> newNode = new Node<T>(data);
		if(isEmpty()) {
			first = newNode;
			last = newNode;
			size++;
			return newNode;
		}
		newNode.next = first;
		first.previous = newNode;
		first = newNode;
		size++;
		return newNode;
	}
/**
 * 删除掉参数位置的节点
 * 特殊情况
 * 1.当p=null时，输入的参数无效，直接return null
 * 2.当p为第一个节点时，会影响到first的值
 * 3.当p为最后一个节点时，会影响到last的值
 * 2,3两种情况不能写在一个if-else if语句中，因为两种情况会同时发生，在当size=1时
 * */
	@Override
	public T remove(Position<T> p) {
		if(p == null)
			return null;
		Node<T> node = (Node<T>)p;
		if(size == 1) {
			first = null;
			last = null;
			size--;
			return p.element();
		}else if(node == first) {
			first = node.next;
			node.next = null;
			first.previous = null;
			size--;
			return p.element();
		}else if(node == last) {
			last = node.previous;
			last.next = null;
			node.previous = null;
			size--;
			return p.element();
		}else {
			Node<T> previousNode = node.previous;
			Node<T> nextNode = node.next;
			previousNode.next = nextNode;
			nextNode.previous = previousNode;
			node.previous = null;
			node.next = null;
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
	
/**
 * 实现了Position接口的Node类
 * 在双向链表中，每一个node都会持有前一个节点和后一个节点的指针
 * 存储的数据类型依然是泛型T
 * 
 * 备注：因为在这些实例中，Map数据结构师基于双向链表实现的，需要在外部类中声明node变量
 * 所以这里将类设成了static
 * */
	static class Node<E> implements Position<E>{
		private E data;
		Node<E> previous;
		Node<E> next;
		
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
