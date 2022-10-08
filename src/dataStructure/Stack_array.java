package dataStructure;
/**
 * 实现了泛型的Array Stack
 * 因为使用泛型只能定义泛型数组，但是不能实例化泛型数组，所以类中的数组时object类型，而不是泛型数组
 * stack需要实现的基本方法有
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
	 * 带参构造函数
	 * 设置数组的初始大小，
	 * 根据输入的数组大小初始化object数组
	 * 初始化topIndex（下一个进入数据存储位置的数组下标）
	 * */
	public Stack_array(int maxSize) {
		super();
		this.maxSize = maxSize;
		this.topIndex = 0;
		array = new Object[maxSize]; 
	}
/**
 * insertion function
 * 将新数据放到topIndex的位置，之后topIndex指向下一个数组下标
 * 由T向object类的向上转型为隐形数据类型转化，不用标出
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
 * 因为topIndex指向的是新数据插入的位置，实际指向的是空位，所以pop出topIndex-1的数
 * 之后topIndex--
 * pop只对topIndex做操作，删除的那个数依然在数组中，只不过无法被获取了，之后会被新加进的数据覆盖
 * object向T类型的转换为强制转换
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
 * 获取最上面的数据
 * */
	@SuppressWarnings("unchecked")
	@Override
	public T top() {
		//topIndex可能为0，所以0的情况需要单独考虑
		if(topIndex != 0)
			return (T)array[topIndex-1];
		return null;
	}
/**
 * 判断是否为空
 * 为空的证据不是数据的length属性，而是topIndex是否为0
 * */
	@Override
	 public boolean isEmpty() {
		return topIndex==0;
	}
/**
 * arr.length不能代表stack大小，因为数组中有废弃的数据，所以数据大小为topIndex
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
