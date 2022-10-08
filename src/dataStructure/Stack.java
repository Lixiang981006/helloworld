package dataStructure;
/**
 * Queue的所有操作复杂度都是O（1），push的动态增加数组大小操作除外（为O（n））
 * 本实例没有实现动态数组大小
 * */
public interface Stack<T> {
	void push(T value);
	T pop();
	T top();
	boolean isEmpty();
	int size();
}
