package dataStructure;
/**
 * ��������һ���ڵ���ӽڵ�ֻ����0/2������˷����ҽڵ�
 * */
public interface Tree_binary<T> extends Tree<T>{
	public INode<T> left(INode<T> node);
	public INode<T> right(INode<T> node);
	public boolean hasLeft(INode<T> node);
	public boolean hasRight(INode<T> node);
}
