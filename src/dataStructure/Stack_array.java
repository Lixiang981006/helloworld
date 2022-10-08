package dataStructure;
/**
 * ʵ���˷��͵�Array Stack
 * ��Ϊʹ�÷���ֻ�ܶ��巺�����飬���ǲ���ʵ�����������飬�������е�����ʱobject���ͣ������Ƿ�������
 * stack��Ҫʵ�ֵĻ���������
 * void push(T value)
 * T pop()
 * T top()
 * boolean isEmpty()
 * int size()
 * 
 * */
public class Stack_array<T> implements Stack<T>{

	private int maxSize;
	private int topIndex;
	private Object[] array;
	
	/**
	 * ���ι��캯��
	 * ��������ĳ�ʼ��С��
	 * ��������������С��ʼ��object����
	 * ��ʼ��topIndex����һ���������ݴ洢λ�õ������±꣩
	 * */
	public Stack_array(int maxSize) {
		super();
		this.maxSize = maxSize;
		this.topIndex = 0;
		array = new Object[maxSize]; 
	}
/**
 * insertion function
 * �������ݷŵ�topIndex��λ�ã�֮��topIndexָ����һ�������±�
 * ��T��object�������ת��Ϊ������������ת�������ñ��
 * */
	@Override
	public void push(T value) {
		if(topIndex<maxSize) {
			array[topIndex] = value;
			topIndex++;
		}
	}
/**
 * deletion function
 * ��ΪtopIndexָ����������ݲ����λ�ã�ʵ��ָ����ǿ�λ������pop��topIndex-1����
 * ֮��topIndex--
 * popֻ��topIndex��������ɾ�����Ǹ�����Ȼ�������У�ֻ�����޷�����ȡ�ˣ�֮��ᱻ�¼ӽ������ݸ���
 * object��T���͵�ת��Ϊǿ��ת��
 * */
	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		if(!isEmpty()) {
			T popped = (T)array[topIndex-1];
			topIndex--;
			return popped;
		}
		return null;
	}
/**
 * ��ȡ�����������
 * */
	@SuppressWarnings("unchecked")
	@Override
	public T top() {
		//topIndex����Ϊ0������0�������Ҫ��������
		if(topIndex != 0)
			return (T)array[topIndex-1];
		return null;
	}
/**
 * �ж��Ƿ�Ϊ��
 * Ϊ�յ�֤�ݲ������ݵ�length���ԣ�����topIndex�Ƿ�Ϊ0
 * */
	@Override
	 public boolean isEmpty() {
		return topIndex==0;
	}
/**
 * arr.length���ܴ���stack��С����Ϊ�������з��������ݣ��������ݴ�СΪtopIndex
 * */
	@Override
	public int size() {
		return topIndex;
	}
	public void show() {
		for (int i = 0; i < topIndex; i++) {
			System.out.println(array[i]);
		}
	}
	
}
