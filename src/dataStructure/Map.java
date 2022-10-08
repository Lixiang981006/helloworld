package dataStructure;

import java.util.Iterator;
/**
 * Map ADT
 * map中每一个key都是唯一的，每一个key对应一个数据
 * 
 * */
public interface Map<T> {
	//返回参数key对应的数据
	T get(int key);
	//如果key不存在，新建一个键值对加到map中，返回null
	//如果key存在，更新对应的值，返回旧值
	T put(int key,T value);
	//删除key对应的键值对，如果没有找到，返回null
	T remove(int key);
	//返回map中entries的迭代器
	Iterator<T> entries();
	int size();
	boolean isEmpty();
}
/**
 * Entry ADT
 * Entry是Map数据结构中的基本单元
 * 含有一对键值对（key-value对）
 * 可以使用key（），value（）方法访问其中的数据
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