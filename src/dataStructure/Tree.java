package dataStructure;

import java.util.Iterator;

public interface Tree<T> {
	//����һ���ڵ�ĸ��ڵ�
	public INode<T> parent(INode<T> node);
	//�������ĸ��ڵ�
	public INode<T> root();
	//����һ���ڵ������ӽڵ�ĵ�����
	public Iterator<INode<T>> children(INode<T> node);
	//�ж�һ���ڵ��Ƿ�ΪInternal/external
	public boolean isInternal(INode<T> node);
	public boolean isExternal(INode<T> node);
	//�ж�һ���ڵ��Ƿ�Ϊ���ڵ�
	public boolean isRoot(INode<T> node);
	//�������Ľڵ������
	public int size();
	//�ж����Ƿ�Ϊ��
	public boolean isEmpty();
	//��Ŀ��ڵ��element�滻��
	public T replace(INode<T> node, T element);
	//����һ���ܱ������нڵ�Ԫ��ֵ�ĵ�����������Ԫ���еĶ�����T
	public Iterator<T> iterator();
	//����һ���ܱ������нڵ�ĵ�����������Ԫ���еĶ�����Node<T>
	public Iterator<INode<T>> nodes();

}

//���Ͷ���Ľڵ�ӿڣ��洢����������ΪT
interface INode<T>{
	public T element();
}

/**
 * root:Ψһһ��û�и��ڵ�Ľڵ�
 * Internal node:����Ҷ�ڵ�Ľڵ㣬Ҷ�ڵ㣺û���ӽڵ�Ľڵ�
 * External node:Ҷ�ڵ�
 * ancestor:�ͱ��ڵ���ֱ���ϲ��ϵ�����нڵ㣬�����Լ�����
 * descendent:���ڵ����е��²�ڵ㣬�����Լ�
 * depth���ڵ����ȣ����ڵ�Ϊ0�����ڵ���ӽڵ�Ϊ1
 * degree�����ڵ��ӽڵ������
 * height���������Ͽ��ĸ߶ȣ�Ҷ�ڵ�߶�Ϊ0��Ҷ�ڵ�ĸ��ڵ�߶�Ϊ1�����ڵ�Ϊ���ҲҶ�ڵ����
 * */
