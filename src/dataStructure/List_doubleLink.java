package dataStructure;

/**
 * ˫�������и������ĸ��Ӷ�
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
 * ˫������洢�˽ڵ�ǰ���������ڽڵ��ָ��
 * ���ַ�ʽռ���˸���Ĵ洢��Դ�������Դ�Ϊ���ۻ�ȡ���㷨�ٶ��ϵ�������
 * ˫��������еķ�������O��1����
 * 
 * ���ڵ����⣺
 * �����ɾ��һ��Ԫ��֮ǰ�Ѿ��������˱�ɾ���ڵ�����ã�
 * ��ɾ��֮��Ա�ɾ���ڵ����insertAfter��insertBefore�����ᱨ��
 * ��������������ǿɽ�ģ�ֻ��Ҫ�Ա�ɾ���Ķ�����л��ռ��ɣ�������Java��
 * ����Ļ������Զ����еġ�
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
 * ���ص�һ��node��ָ��
 * û���������������Ϊ��ʱ��firstΪnull������null��
 * */
	@Override
	public Position<T> first() {
		return first;
	}

/**
 * �������һ��node��ָ��
 * û���������������Ϊ��ʱ��lastΪnull������null��
 * */
	@Override
	public Position<T> last() {
		return last;
	}

/**
 * ���ز���λ�õ�ǰһ���ڵ㣬��ʹ��previous��ȡ
 * ���������
 * 1.�������pΪnullʱ��ֱ�ӷ���null
 * 2.�������p��ΪNode���ͱ���ʱ��ֱ�ӷ���null
 * 3.��p�ǵ�һ���ڵ�ʱ��pǰû�нڵ㣬����null�����ÿ��ǣ���Ϊ��ʱprevious����null
 * */
	@Override
	public Position<T> before(Position<T> p) {
		//��Ч����
		if(p == null || !(p instanceof Node))
			return null;
		Node<T> node = (Node<T>)p;
		return node.previous;
	}

/**
 * ע�ͺ�before����һ��
 * */
	@Override
	public Position<T> after(Position<T> p) {
		if(p == null || !(p instanceof Node))
			return null;
		Node<T> node = (Node<T>)p;
		return node.next;
	}

/**
 * ���������
 * 1.��node�ǵ�һ���ڵ�ʱ��node.previous��null�������ڻ�ȡnode.previous.nextʱ�ᱨ����������
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
 * ɾ��������λ�õĽڵ�
 * �������
 * 1.��p=nullʱ������Ĳ�����Ч��ֱ��return null
 * 2.��pΪ��һ���ڵ�ʱ����Ӱ�쵽first��ֵ
 * 3.��pΪ���һ���ڵ�ʱ����Ӱ�쵽last��ֵ
 * 2,3�����������д��һ��if-else if����У���Ϊ���������ͬʱ�������ڵ�size=1ʱ
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
 * ʵ����Position�ӿڵ�Node��
 * ��˫�������У�ÿһ��node�������ǰһ���ڵ�ͺ�һ���ڵ��ָ��
 * �洢������������Ȼ�Ƿ���T
 * 
 * ��ע����Ϊ����Щʵ���У�Map���ݽṹʦ����˫������ʵ�ֵģ���Ҫ���ⲿ��������node����
 * �������ｫ�������static
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
