package dataStructure;

import java.util.Iterator;

public interface Tree<T> {
	//返回一个节点的父节点
	public INode<T> parent(INode<T> node);
	//返回树的根节点
	public INode<T> root();
	//返回一个节点所有子节点的迭代器
	public Iterator<INode<T>> children(INode<T> node);
	//判断一个节点是否为Internal/external
	public boolean isInternal(INode<T> node);
	public boolean isExternal(INode<T> node);
	//判断一个节点是否为根节点
	public boolean isRoot(INode<T> node);
	//返回树的节点的数量
	public int size();
	//判断树是否为空
	public boolean isEmpty();
	//将目标节点的element替换掉
	public T replace(INode<T> node, T element);
	//返回一个能遍历所有节点元素值的迭代器，集合元素中的对象是T
	public Iterator<T> iterator();
	//返回一个能遍历所有节点的迭代器，集合元素中的对象是Node<T>
	public Iterator<INode<T>> nodes();

}

//泛型定义的节点接口，存储的数据类型为T
interface INode<T>{
	public T element();
}

/**
 * root:唯一一个没有父节点的节点
 * Internal node:不是叶节点的节点，叶节点：没有子节点的节点
 * External node:叶节点
 * ancestor:和本节点有直接上层关系的所有节点，包括自己本身
 * descendent:本节点所有的下层节点，包括自己
 * depth：节点的深度，根节点为0，根节点的子节点为1
 * degree：本节点子节点的数量
 * height：从下往上看的高度，叶节点高度为0，叶节点的父节点高度为1，根节点为最大也叶节点深度
 * */
