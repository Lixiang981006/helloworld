package dataStructure;
/**
 * 二叉树，一个节点的子节点只能是0/2个，因此分左右节点
 * */
public interface Tree_binary<T> extends Tree<T>{
	public INode<T> left(INode<T> node);
	public INode<T> right(INode<T> node);
	public boolean hasLeft(INode<T> node);
	public boolean hasRight(INode<T> node);
}
