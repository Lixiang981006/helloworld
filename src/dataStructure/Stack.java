package dataStructure;
/**
 * Queue�����в������Ӷȶ���O��1����push�Ķ�̬���������С�������⣨ΪO��n����
 * ��ʵ��û��ʵ�ֶ�̬�����С
 * */
public interface Stack<T> {
	void push(T value);
	T pop();
	T top();
	boolean isEmpty();
	int size();
}
