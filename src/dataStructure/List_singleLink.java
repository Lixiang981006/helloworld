package dataStructure;

/**
 * singleLinkList�и������ĸ��Ӷ�
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
 * first����û���������
 * ������Ϊ��ʱ��first=null������null
 * */
	@Override
	public Position<T> first() {
		return first;
	}

	@Override
	public Position<T> last() {
		//firstΪnullʱ����listΪ��ʱ�����һ��
		if(!isEmpty()) {
			//ȡ��first��ָ�룬��������
			Node<T> index = first;
			while(index.next!=null) {
				index = index.next;
			}
			return index;
		}
		//listΪ��ʱ��ֱ�ӷ���null
		return null;
	}

	@Override
	public Position<T> before(Position<T> p) {
		Node<T> index = first;
		//���ᷢ��p��index.next���Ͳ�ƥ��ĵ����޷��жϵ����
		//��Ϊ��ʹ���������Ͳ�ƥ�䣬ֻҪʵ��������ͬ�ͻ᷵��true
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
 * ��Ҫ�ж�����������Ƿ�ΪNode���ͣ�֮�������������ǿ��ת��,��Ϊ��Ҫ�õ�node.next
 * û�п��ǲ���λ�ò��������е����
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
 * ������Ϊ������Ϊ�ջ������λ��Ϊnullʱ��insertBefore��������ʹ��
 * */
	@Override
	public Position<T> insertBefore(Position<T> p, T data) {
		Node<T> newNode = new Node<T>(data);
		//����Ϊnull��ֱ�ӷ���null
		if(p == null || !(p instanceof Node))
			return null;
		//�����pʱ��һ��Ԫ�أ���������
		if(p == first) {
			newNode.next = first;
			first = newNode;
			size++;
			return newNode;
		}
		//p���ǵ�һ��Ԫ�ص����,��Ҫ���������ҵ�ǰһ������
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
 * ��Ҫǿ������ת������Ϊ��Ҫʹ�õ�node.next
 * */
	@Override
	public Position<T> insertAfter(Position<T> p, T data) {
		//�ų������������
		if(p == null || !(p instanceof Node))
			return null;
		
		Node<T> newNode = new Node<T>(data);
		Node<T> node = (Node<T>)p;
		newNode.next = node.next;
		node.next = newNode;
		return newNode;
	}

/**
 * �ռ����insertLast��������Ӱ�죬��˵�������
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
 * û���κ��������
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
 * ɾ��һ���ڵ���Ҫ֪������ǰ��ڵ㣬�������ڵ�ƴ������
 * ���Ҫ֪��ǰһ���ڵ㣬�ڵ�����������Ҫ����
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
