package dataStructure;

import java.util.Iterator;
import dataStructure.List_doubleLink.Node;

/**
 * hashMap数据结构
 * 	1. Separate chain
	使用链表加数组的方式，创建一个链表数组，当collision发生时（hash code已经存在的情况），在发生collision的位置的自链表尾插
	2. Open addressing
	不需要使用额外的数据结构，也就是不需要基于list。只是用数组，创建一个Entry类数组，当collision发生时，将插入的新数据存储在该index之后下一个未占用的位置
	
 * 本实例使用的是separate chain方式实现的HashMap数据结构
 * 使用的是链表加数组的方式实现Map，使用了一个链表数组：
 * List<Entry<T>>[] list;//数组具有默认大小
 * 
 * 时间复杂度分析
 * 因为在链表实现的Map中，我们发现find方法的时间复杂度为O（n），导致所有方法的
 * 时间复杂度都为O（n），因此创建了HashMap结构。在hashMap中，我们将输入的Node
 * 通过Hash（）方法进行分类，之后放在对应的链表数组中的位置，理想情况下，数组中
 * 所有链表都只有一个元素，因此时间复杂度为O（1），最差情况下每一个链表都有冲突
 * 元素，时间复杂度为O（n）
 * 
 * 
 * 本文件实现和java的collection中实现方法有较大差异，一切以collection中的实现
 * 为准，这里是一个简化版本，以助于理解hashMap的思想
 * 
 * 
 * java jdk1.8版本之后使用的是数组+链表+红黑树的实现方式，插入方式为尾插，并且
 * 在数组大小超过阈值时会树化，并且也有另一个阈值限制着数组大小，当当前数组大小
 * 乘以loadFactor（0.75）大于阈值时，数组大小会增加。而且key的类型也不是int，
 * 而也是一个泛型。具体细节见 java.util.HashMap
 * */
public class Map_hashMap<T> implements Map<T>{
	//泛型不能实现数组。。。所以这里只能定义Object类数组
	//这里的list的实际类型为List<Entry<T>>[]
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
	 * 暂时不实现该方法
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
	 * 查找key对应的节点的方法
	 * 如果找到节点，那么讲节点返回
	 * 如果没有找到有此key值的节点，返回null
	 * 
	 * hashMap多了一个List<Entry<T>>类型的参数subList，代表了在list数组中的那个子数组
	 * 进行搜索
	 * */
	private Position<Entry<T>> find(int key, List<Entry<T>> subList){
		Node<Entry<T>> node = (Node<Entry<T>>)subList.first();
		//循环结束，如果node == null说明没有找到节点，如果提前返回说明找到节点
		while(node != null) {
			if(node.element().key() == key) {
				return node;
			}
			node = node.next;
		}
		return null;
	}
	
	/**
	 * 用于计算各个key值所对应的hash值，有多种算法可以实现
	 * 这里使用的是取余计算
	 * 
	 * 
	 * */
	private int hashCode(int key) {
		return key%defaultSize;
	}
}
