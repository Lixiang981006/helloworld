package dataStructure;

import java.util.Iterator;

import dataStructure.List_doubleLink.Node;
/**
 * ListMap 使用双向链表实现map，将每一个键值对封装进节点中
 * 对任意节点的操作基于首先找到该key对应的节点，因此需要使用find（）函数，复杂度为O（n）
 * 
 * 应注意的是，ListMap中包含的List对象的泛型是确定的Entry<T>，也就是说每一个节点的泛型
 * 也是Entry<T>,而包含的值是Entry<T>类型的。Map中的泛型在Entry类中，是Entry类对象所含
 * 的数据类型不确定
 * 
 * 时间复杂度
 * find：O（n）
 * get，put，remove方法中都使用到了find方法，所以复杂度都是O（n）
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
	 * 暂时不实现该方法
	 * */
	@Override
	public Iterator<T> entries() {
		// TODO Auto-generated method stub
		return null;
	}

/**
 * 可以直接调用list的方法
 * */
	@Override
	public int size() {
		return list.size();
	}

/**
 * 可以直接调用list的方法
 * */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

/**
 * 查找key对应的节点的方法
 * 如果找到节点，那么讲节点返回
 * 如果没有找到有此key值的节点，返回null
 * */
	private Position<Entry<T>> find(int key){
		Node<Entry<T>> node = (Node<Entry<T>>)list.first();
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
 * 实验性写法，之前的版本是本class中也含有Node内部类，这样的话是优先调用自身的内部类，
 * 而此处需要用到的是双向链表中的Node类，因此引用了那个类的全名，然而最后事实是在本类
 * 中不需要再定义Node类了，解决方法是将List_doubleLink类中的Node内部类设成了static，
 * 这样就可以被外部类直接获取
 * */
	public void show() {
		dataStructure.List_doubleLink.Node<Entry<T>> index = (dataStructure.List_doubleLink.Node<Entry<T>>)list.first();
		while(index != null) {
			System.out.println(index.element().value());
			index = index.next;
		}
	}
	
	/**
	 * 因为使用了双向链表实现Map结构，所以需要定义一个node内部类
	 * 实现了Position接口的Node类
	 * 在双向链表中，每一个node都会持有前一个节点和后一个节点的指针
	 * 存储的数据类型依然是泛型T
	 * 
	 * 以下代码已废弃
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
