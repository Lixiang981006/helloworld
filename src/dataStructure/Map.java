package dataStructure;

import java.util.Iterator;
/**
 * Map ADT
 * map��ÿһ��key����Ψһ�ģ�ÿһ��key��Ӧһ������
 * 
 * */
public interface Map<T> {
	//���ز���key��Ӧ������
	T get(int key);
	//���key�����ڣ��½�һ����ֵ�Լӵ�map�У�����null
	//���key���ڣ����¶�Ӧ��ֵ�����ؾ�ֵ
	T put(int key,T value);
	//ɾ��key��Ӧ�ļ�ֵ�ԣ����û���ҵ�������null
	T remove(int key);
	//����map��entries�ĵ�����
	Iterator<T> entries();
	int size();
	boolean isEmpty();
}
/**
 * Entry ADT
 * Entry��Map���ݽṹ�еĻ�����Ԫ
 * ����һ�Լ�ֵ�ԣ�key-value�ԣ�
 * ����ʹ��key������value���������������е�����
 * */
class Entry<T>{
	private int key;
	private T value;
	
	//constructor
	public Entry(int key, T value) {
		this.key = key;
		this.value = value;
	}
	
	public int key() {
		return key;
	}
	public T value() {
		return value;
	}
}