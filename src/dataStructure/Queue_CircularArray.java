package dataStructure;

/**
 * 对于ArrayQueue的优化，循环使用数组，可以解决之前被dequeue的空间无法被重复利用的问题
 * 新增了一个size变量代替了rear-front记录queue的大小
 * 
 * */
public class Queue_CircularArray<T> implements Queue<T>{
	private static final int defaultSize = 2;
	private int front;
	private int rear;
	private int size;
	private Object[] array;
	
	//constructor
	public Queue_CircularArray() {
		this.front = 0;
		this.rear = 0;
		this.size = 0;
		this.array = new Object[defaultSize];
	}
	
	@Override
	public void enqueue(T data) {
		array[rear] = data;
		rear = (rear+1)%array.length;
		size++;
		grow();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T dequeue() {
		if(isEmpty())
			return null;
		T data = (T)array[front];
		front = (front +1)%array.length;
		size--;
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T front() {
		if(!isEmpty())
			return (T)array[front];
		return null;
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
	 * 打印queue中的内容
	 * 跟普通ArrayQueue稍有不同
	 * 打印的是array[temp%array.length]
	 * */
		public void show() {
			int temp = front;
			for (int i = 0; i < size; i++) {
				System.out.println(array[temp%array.length]);
				temp++;
			}
		}

	/**
	 * 动态的增加数组的大小
	 * 当数组满了之后，加倍数组大小
	 * 和普通的ArrayQueue区别很大
	 * 这里需要让数据在新数组中顺序排列，之后重新对front和rear进行赋值
	 * 在最后，将新得到的数组赋给原数组，实现数组大小的加倍
	 * */
		private void grow() {
			if(size == array.length) {
				int temp = front;
				Object[] newArray = new Object[array.length * 2];
				for (int i = 0; i < array.length; i++) {
					newArray[i] = array[temp%array.length];
					temp++;
				}
				
				front = 0;
				rear = array.length;
				array = newArray;
			}
		}
}
