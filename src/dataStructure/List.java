package dataStructure;

/**
 * List ADT
 * �����stack�� queue�����Է������list�м���ӻ�ɾ������
 * �����stack��queue��list�ӿڶ����˸���ķ���
 * */
public interface List<T> {
	//����list�еĵ�һ��/���һ��PositionԪ��
	Position<T> first();
	Position<T> last();
	//���ز���λ��ǰ/���PositionԪ��
	Position<T> before(Position<T> p);
	Position<T> after(Position<T> p);
	//�ڲ���λ��ǰ/����뷺������data,���ز����Position��������
	Position<T> insertBefore(Position<T> p,T data);
	Position<T> insertAfter(Position<T> p,T data);
	//��List����ǰ/�����뷺������data,���ز����Position��������
	Position<T> insertLast(T data);
	Position<T> insertFirst(T data);
	//ɾ������λ�õ�position�Ͷ��󣬷��ظ�Position���洢��ֵ
	T remove(Position<T> p);
	//Ϊ����ʵ����������������ļ򵥷���
	int size();
	boolean isEmpty();
}

/**
 * Position�ӿ�
 * list�е�����Ԫ�ض���������position�ӿ�
 * �ӿ���Ψһ����element���ش�position�д洢��ֵ
 * �����ʵ���ж���Node��ʵ����Position�ӿ�
 * */
interface Position<T>{
	T element();
}