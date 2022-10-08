package dataStructure;
/**
 * Queue(队列)
 * FIFO(first in first out)
 * front 是下一个被释放的数据的指针
 * rear是目前最新被添加的数据的指针
 * Queue的所有操作复杂度都是O（1），enqueue的动态增加数组大小操作除外（为O（n））
 * */
public interface Queue<T> {
	//将新数据插入到队尾
	void enqueue(T data);
	//将队列最前端的数据释放掉
	T dequeue();
	//返回队列最前端的元素的数据，但是不remove
	T front();
	//返回队列的大小
	int size();
	//判断队列是否为空
	boolean isEmpty();
}
