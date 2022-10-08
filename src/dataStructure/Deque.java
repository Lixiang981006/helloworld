package dataStructure;

public interface Deque<T> {
	void addFirst(T data);
	void addLast(T data);
	T removeFirst() throws EmptyDequeException;
	T removeLast() throws EmptyDequeException;
	T getFirst() throws EmptyDequeException;
	T getLast() throws EmptyDequeException;
	int size();
	boolean isEmpty();
	
}

class EmptyDequeException extends RuntimeException{
	/**
	 * 序列号是什么，作用是什么暂时不知道
	 */
	private static final long serialVersionUID = -4660535419072318068L;
	private String message;
	//constructor
	public EmptyDequeException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
}