package dataStructure;

public class List_singleArray<T> implements List<T> {
	private static final int defaultSize = 2;
	//array��ʵ������ΪArrPos<T>[],��Ϊ���Ͳ���ʵ�������飬����ֻ����Object[]�����
	//�ϸ����array����Ӻ�����ȷ��array��ÿһ�����󶼱�����ArrPos<T>�����
	private Object[] array;
	private int size;
	 int maxSize;
	
	//constructor
	public List_singleArray() {
		this.array = new Object[defaultSize];
		this.size = 0;
		this.maxSize = defaultSize;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Position<T> first() {
		if(isEmpty())
			return null;
		return (Position<T>)array[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position<T> last() {
		if(isEmpty())
			return null;
		return (Position<T>)array[size-1];
	}
/**
 * ���������p = null, p ����ArrPos��Ķ���--->ֱ�ӷ���null
 * ���p������ĵ�һ����p֮ǰû��Ԫ���ˣ�����null
 * */
	@SuppressWarnings("unchecked")
	@Override
	public Position<T> before(Position<T> p) {
		ArrPos<T> arrIndex = (ArrPos<T>)p;
		if(p == null || !(p instanceof ArrPos) || arrIndex.index == 0) {
			return null;
		}
		
		return (Position<T>)array[arrIndex.index-1];
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position<T> after(Position<T> p) {
		ArrPos<T> arrIndex = (ArrPos<T>)p;
		if(p == null || !(p instanceof ArrPos) || arrIndex.index == size-1) {
			return null;
		}
		return (Position<T>)array[arrIndex.index+1];
	}
/**
 * ��ʹ��insertBeforeʱ���������ǿյģ���������������ArrPos�����
 * */
	@SuppressWarnings({"unchecked" })
	@Override
	public Position<T> insertBefore(Position<T> p, T data) {
		if(p == null || !(p instanceof ArrPos)) {
			return null;
		}
		ArrPos<T> arrIndex = (ArrPos<T>)p;
		ArrPos<T> newItem = new ArrPos<T>(arrIndex.index, data);
		for(int i = size-1;i>=arrIndex.index;i--) {
			ArrPos<T> temp = (ArrPos<T>)array[i];
			temp.incrementPosition();
			array[i+1] = array[i];
		}
		size++;
		array[arrIndex.index-1] = newItem;
		grow();
		return newItem;
	}
/**
 * ��ʹ��insertAfterʱ�������в����ǿյ�
 * */
	@SuppressWarnings("unchecked")
	@Override
	public Position<T> insertAfter(Position<T> p, T data) {
		if(p == null || !(p instanceof ArrPos)) {
			return null;
		}
		ArrPos<T> arrIndex = (ArrPos<T>)p;
		ArrPos<T> newItem = new ArrPos<T>(arrIndex.index+1, data);
		for(int i = size-1;i>=arrIndex.index+1;i--) {
			ArrPos<T> temp = (ArrPos<T>)array[i];
			temp.incrementPosition();
			array[i+1] = array[i];
		}
		array[arrIndex.index+1] = newItem;
		size++;
		grow();
		return newItem;
	}

	@Override
	public Position<T> insertLast(T data) {
		if(isEmpty()) {
			ArrPos<T> newItem = new ArrPos<T>(0, data);
			array[0] = newItem;
			size++;
			return newItem;
		}
		ArrPos<T> last = (ArrPos<T>)last();
		ArrPos<T> newItem = new ArrPos<T>(last.index+1, data);
		array[last.index+1] = newItem;
		size++;
		grow();
		return newItem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position<T> insertFirst(T data) {
		ArrPos<T> newItem = new ArrPos<T>(0, data);
		if(isEmpty()) {
			array[0] = newItem;
			size++;
			return newItem;
		}
		for(int i = size-1;i>=0;i--) {
			ArrPos<T> temp = (ArrPos<T>)array[i];
			temp.incrementPosition();
			array[i+1] = array[i];
		}
		array[0] = newItem;
		size++;
		grow();
		return newItem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(Position<T> p) {
		if(isEmpty() || p == null || !(p instanceof ArrPos))
			return null;
		ArrPos<T> arrIndex = (ArrPos<T>)p;
		if(p == last()) {
			size--;
			return p.element();
		}
		for (int i = arrIndex.index+1; i < size; i++) {
			ArrPos<T> temp = (ArrPos<T>)array[i];
			temp.decrementPosition();
			array[i-1] = array[i];
		}
		size--;
		shrink();
		return p.element();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * �ú�����Ŀ������list�Ĵ�С�Ѿ�������Ĵ�Сһ��������½������С�ӱ������ݣ���
	 * ������ʵ��α���޴������������������������size����
	 * */
	private void grow() {
		if(size <= maxSize-1)
			return;
		maxSize *= 2;
		Object[] newArray = new Object[maxSize];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	private void shrink() {
		if(size > (maxSize/4))
			return;
		maxSize /= 2;
		Object[] newArray = new Object[maxSize];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	@SuppressWarnings("unchecked")
	public void show() {
		for (int i = 0; i < size; i++) {
			ArrPos<T> temp = (ArrPos<T>)array[i];
			System.out.println(temp.getIndex()+" "+temp.element());
		}
	}
	
	//inner class ArrPos that implements Position interface
	//this is the basic data type in array-based list
	class ArrPos<E> implements Position<E>{
		private int index;
		private E data;
		
		public ArrPos(int index, E data){
			this.index = index;
			this.data = data;
		}
		
		@Override
		public E element() {
			return data;
		}
		
		public int getIndex() {
			return index;
		}
		
		public void incrementPosition() {
			index++;
		}
		public void decrementPosition() {
			index--;
		}
	}
}
