package dataStructure;

/**
 * ����ArrayQueue���Ż���ѭ��ʹ�����飬���Խ��֮ǰ��dequeue�Ŀռ��޷����ظ����õ�����
 * ������һ��size����������rear-front��¼queue�Ĵ�С
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
	 * ��ӡqueue�е�����
	 * ����ͨArrayQueue���в�ͬ
	 * ��ӡ����array[temp%array.length]
	 * */
		public void show() {
			int temp = front;
			for (int i = 0; i < size; i++) {
				System.out.println(array[temp%array.length]);
				temp++;
			}
		}

	/**
	 * ��̬����������Ĵ�С
	 * ����������֮�󣬼ӱ������С
	 * ����ͨ��ArrayQueue����ܴ�
	 * ������Ҫ����������������˳�����У�֮�����¶�front��rear���и�ֵ
	 * ����󣬽��µõ������鸳��ԭ���飬ʵ�������С�ļӱ�
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
