package dataStructure;

import java.util.Iterator;

import dataStructure.List_doubleLink.Node;
/**
 * ListMap ʹ��˫������ʵ��map����ÿһ����ֵ�Է�װ���ڵ���
 * ������ڵ�Ĳ������������ҵ���key��Ӧ�Ľڵ㣬�����Ҫʹ��find�������������Ӷ�ΪO��n��
 * 
 * Ӧע����ǣ�ListMap�а�����List����ķ�����ȷ����Entry<T>��Ҳ����˵ÿһ���ڵ�ķ���
 * Ҳ��Entry<T>,��������ֵ��Entry<T>���͵ġ�Map�еķ�����Entry���У���Entry���������
 * ���������Ͳ�ȷ��
 * 
 * ʱ�临�Ӷ�
 * find��O��n��
 * get��put��remove�����ж�ʹ�õ���find���������Ը��Ӷȶ���O��n��
 * */
public class Map_list<T> implements Map<T>{
	private List<Entry<T>> list = new List_doubleLink<Entry<T>>();
	
	
	@Override
	public T get(int key) {
		Node<Entry<T>> node = (Node<Entry<T>>)find(key);
		if(node == null)
			return null;
		return node.element().value();
	}

	@Override
	public T put(int key, T value) {
		Node<Entry<T>> node = (Node<Entry<T>>)find(key);
		if(node == null) {
			Entry<T> newEntry = new Entry<T>(key, value);
			list.insertLast(newEntry);
			return null;
		}else {
			Entry<T> newEntry = new Entry<T>(key, value);
			list.insertAfter(node, newEntry);
			list.remove(node);
			return node.element().value();
		}
	}

	@Override
	public T remove(int key) {
		Node<Entry<T>> node = (Node<Entry<T>>)find(key);
		if(node == null) {
			return null;
		}else {
			list.remove(node);
			return node.element().value();
		}
	}

	/**
	 * ��ʱ��ʵ�ָ÷���
	 * */
	@Override
	public Iterator<T> entries() {
		// TODO Auto-generated method stub
		return null;
	}

/**
 * ����ֱ�ӵ���list�ķ���
 * */
	@Override
	public int size() {
		return list.size();
	}

/**
 * ����ֱ�ӵ���list�ķ���
 * */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

/**
 * ����key��Ӧ�Ľڵ�ķ���
 * ����ҵ��ڵ㣬��ô���ڵ㷵��
 * ���û���ҵ��д�keyֵ�Ľڵ㣬����null
 * */
	private Position<Entry<T>> find(int key){
		Node<Entry<T>> node = (Node<Entry<T>>)list.first();
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
 * ʵ����д����֮ǰ�İ汾�Ǳ�class��Ҳ����Node�ڲ��࣬�����Ļ������ȵ���������ڲ��࣬
 * ���˴���Ҫ�õ�����˫�������е�Node�࣬����������Ǹ����ȫ����Ȼ�������ʵ���ڱ���
 * �в���Ҫ�ٶ���Node���ˣ���������ǽ�List_doubleLink���е�Node�ڲ��������static��
 * �����Ϳ��Ա��ⲿ��ֱ�ӻ�ȡ
 * */
	public void show() {
		dataStructure.List_doubleLink.Node<Entry<T>> index = (dataStructure.List_doubleLink.Node<Entry<T>>)list.first();
		while(index != null) {
			System.out.println(index.element().value());
			index = index.next;
		}
	}
	
	/**
	 * ��Ϊʹ����˫������ʵ��Map�ṹ��������Ҫ����һ��node�ڲ���
	 * ʵ����Position�ӿڵ�Node��
	 * ��˫�������У�ÿһ��node�������ǰһ���ڵ�ͺ�һ���ڵ��ָ��
	 * �洢������������Ȼ�Ƿ���T
	 * 
	 * ���´����ѷ���
	 * */
//		class Node<E> implements Position<E>{
//			private E data;
//			Node<E> previous;
//			Node<E> next;
//			
//			//constructor
//			public Node(E data){
//				this.data = data;
//				this.next = null;
//				this.previous = null;
//			}
//			
//			@Override
//			public E element() {
//				return this.data;
//			}
//			
//		}
}
