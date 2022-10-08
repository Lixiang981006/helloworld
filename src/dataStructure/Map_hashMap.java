package dataStructure;

import java.util.Iterator;
import dataStructure.List_doubleLink.Node;

/**
 * hashMap���ݽṹ
 * 	1. Separate chain
	ʹ�����������ķ�ʽ������һ���������飬��collision����ʱ��hash code�Ѿ����ڵ���������ڷ���collision��λ�õ�������β��
	2. Open addressing
	����Ҫʹ�ö�������ݽṹ��Ҳ���ǲ���Ҫ����list��ֻ�������飬����һ��Entry�����飬��collision����ʱ��������������ݴ洢�ڸ�index֮����һ��δռ�õ�λ��
	
 * ��ʵ��ʹ�õ���separate chain��ʽʵ�ֵ�HashMap���ݽṹ
 * ʹ�õ������������ķ�ʽʵ��Map��ʹ����һ���������飺
 * List<Entry<T>>[] list;//�������Ĭ�ϴ�С
 * 
 * ʱ�临�Ӷȷ���
 * ��Ϊ������ʵ�ֵ�Map�У����Ƿ���find������ʱ�临�Ӷ�ΪO��n�����������з�����
 * ʱ�临�Ӷȶ�ΪO��n������˴�����HashMap�ṹ����hashMap�У����ǽ������Node
 * ͨ��Hash�����������з��֮࣬����ڶ�Ӧ�����������е�λ�ã���������£�������
 * ��������ֻ��һ��Ԫ�أ����ʱ�临�Ӷ�ΪO��1������������ÿһ�������г�ͻ
 * Ԫ�أ�ʱ�临�Ӷ�ΪO��n��
 * 
 * 
 * ���ļ�ʵ�ֺ�java��collection��ʵ�ַ����нϴ���죬һ����collection�е�ʵ��
 * Ϊ׼��������һ���򻯰汾�����������hashMap��˼��
 * 
 * 
 * java jdk1.8�汾֮��ʹ�õ�������+����+�������ʵ�ַ�ʽ�����뷽ʽΪβ�壬����
 * �������С������ֵʱ������������Ҳ����һ����ֵ�����������С������ǰ�����С
 * ����loadFactor��0.75��������ֵʱ�������С�����ӡ�����key������Ҳ����int��
 * ��Ҳ��һ�����͡�����ϸ�ڼ� java.util.HashMap
 * */
public class Map_hashMap<T> implements Map<T>{
	//���Ͳ���ʵ�����顣������������ֻ�ܶ���Object������
	//�����list��ʵ������ΪList<Entry<T>>[]
	private Object[] list;
	private int size;
	private static final int defaultSize = 4;
	
	//constructor
	public Map_hashMap() {
		list = new Object[defaultSize];
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(int key) {
		int hash = hashCode(key);
		List_doubleLink<Entry<T>> subList = (List_doubleLink<Entry<T>>)list[hash];
		Node<Entry<T>> node = (Node<Entry<T>>)find(key,subList);
		if(node == null)
			return null;
		return node.element().value();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T put(int key, T value) {
		int hash = hashCode(key);
		if(!(list[hash] instanceof List_doubleLink)) {
			List_doubleLink<Entry<T>> newSubList = new List_doubleLink<Entry<T>>();
			list[hash] = newSubList;
		}
		List_doubleLink<Entry<T>> subList = (List_doubleLink<Entry<T>>)list[hash];
		Node<Entry<T>> node = (Node<Entry<T>>)find(key,subList);
		if(node == null) {
			Entry<T> newEntry = new Entry<T>(key, value);
			subList.insertLast(newEntry);
			size++;
			return null;
		}else {
			Entry<T> newEntry = new Entry<T>(key, value);
			subList.insertAfter(node, newEntry);
			subList.remove(node);
			return node.element().value();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(int key) {
		int hash = hashCode(key);
		if(!(list[hash] instanceof List))
			return null;
		List<Entry<T>> subList = (List<Entry<T>>)list[hash];
		Node<Entry<T>> node = (Node<Entry<T>>)find(key, subList);
		if(node == null)
			return null;
		subList.remove(node);
		size--;
		return node.element().value();
	}
	
	/**
	 * ��ʱ��ʵ�ָ÷���
	 * */
	@Override
	public Iterator<T> entries() {
		// TODO Auto-generated method stub
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
	
	@SuppressWarnings("unchecked")
	public void show() {
		for (int i = 0; i < list.length; i++) {
			if(!(list[i] instanceof List)) {
				System.out.println("null");
			}else {
				List<Entry<T>> subList = (List<Entry<T>>)list[i];
				Node<Entry<T>> index = (Node<Entry<T>>)subList.first();
				while(index != null) {
					System.out.print(index.element().value()+" ");
					index = index.next;
				}
				if(subList.size() == 0)
					System.out.print("null");
				System.out.println();
			}
		}
	}
	
	/**
	 * ����key��Ӧ�Ľڵ�ķ���
	 * ����ҵ��ڵ㣬��ô���ڵ㷵��
	 * ���û���ҵ��д�keyֵ�Ľڵ㣬����null
	 * 
	 * hashMap����һ��List<Entry<T>>���͵Ĳ���subList����������list�����е��Ǹ�������
	 * ��������
	 * */
	private Position<Entry<T>> find(int key, List<Entry<T>> subList){
		Node<Entry<T>> node = (Node<Entry<T>>)subList.first();
		//ѭ�����������node == null˵��û���ҵ��ڵ㣬�����ǰ����˵���ҵ��ڵ�
		while(node != null) {
			if(node.element().key() == key) {
				return node;
			}
			node = node.next;
		}
		return null;
	}
	
	/**
	 * ���ڼ������keyֵ����Ӧ��hashֵ���ж����㷨����ʵ��
	 * ����ʹ�õ���ȡ�����
	 * 
	 * 
	 * */
	private int hashCode(int key) {
		return key%defaultSize;
	}
}
