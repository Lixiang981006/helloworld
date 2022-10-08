package dataStructure;

/**
 * 正常情况下的arrayQueue不能很好的利用空间
 * 因为dequeue之后，被抛弃的空间将不再能被使用但是却并没有被释放
 * */
public class Queue_array<T> implements Queue<T> {
	private static final int defaultSize = 5;
	private int front;
	private int rear;
	private Object[] array;
	
	public Queue_array() {
		array = new Object[defaultSize];
		front = 0;
		rear = 0;
	}
/**
 * 每次添加完元素之后检验数组是否已满，如果已满，加倍数据大小
 * */
	@Override
	public void enqueue(T data) {
		array[rear] = data;
		rear++;
		grow();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T dequeue() {
		if(isEmpty())
			return null;
		T data = (T)array[front];
		front++;
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T front() {
		return (T)array[front];
	}

	@Override
	public int size() {
		return rear-front;
	}

	@Override
	public boolean isEmpty() {
		return (rear-front) == 0;
	}
	
/**
 * 打印queue中的内容
 * */
	public void show() {
		for (int i = front; i < rear; i++) {
			System.out.println(array[i]);
		}
	}

/**
 * 动态的增加数组的大小
 * 当数组满了之后，加倍数组大小
 * */
	private void grow() {
		if(rear == array.length) {
			Object[] newArray = new Object[array.length * 2];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
	}
}
