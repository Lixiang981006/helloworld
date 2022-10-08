package dataStructure;

/**
 * ��������µ�arrayQueue���ܺܺõ����ÿռ�
 * ��Ϊdequeue֮�󣬱������Ŀռ佫�����ܱ�ʹ�õ���ȴ��û�б��ͷ�
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
 * ÿ�������Ԫ��֮����������Ƿ�����������������ӱ����ݴ�С
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
 * ��ӡqueue�е�����
 * */
	public void show() {
		for (int i = front; i < rear; i++) {
			System.out.println(array[i]);
		}
	}

/**
 * ��̬����������Ĵ�С
 * ����������֮�󣬼ӱ������С
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
