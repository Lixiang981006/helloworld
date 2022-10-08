package dataStructure;
/**
 * Queue(����)
 * FIFO(first in first out)
 * front ����һ�����ͷŵ����ݵ�ָ��
 * rear��Ŀǰ���±���ӵ����ݵ�ָ��
 * Queue�����в������Ӷȶ���O��1����enqueue�Ķ�̬���������С�������⣨ΪO��n����
 * */
public interface Queue<T> {
	//�������ݲ��뵽��β
	void enqueue(T data);
	//��������ǰ�˵������ͷŵ�
	T dequeue();
	//���ض�����ǰ�˵�Ԫ�ص����ݣ����ǲ�remove
	T front();
	//���ض��еĴ�С
	int size();
	//�ж϶����Ƿ�Ϊ��
	boolean isEmpty();
}
