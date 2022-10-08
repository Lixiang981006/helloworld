package dataStructure;

/**
 * List ADT
 * 相比于stack， queue，可以方便的在list中间添加或删除数据
 * 相比于stack，queue，list接口定义了更多的方法
 * */
public interface List<T> {
	//返回list中的第一个/最后一个Position元素
	Position<T> first();
	Position<T> last();
	//返回参数位置前/后的Position元素
	Position<T> before(Position<T> p);
	Position<T> after(Position<T> p);
	//在参数位置前/后插入泛型数据data,返回插入的Position类型数据
	Position<T> insertBefore(Position<T> p,T data);
	Position<T> insertAfter(Position<T> p,T data);
	//在List的最前/最后插入泛型数据data,返回插入的Position类型数据
	Position<T> insertLast(T data);
	Position<T> insertFirst(T data);
	//删除参数位置的position型对象，返回该Position处存储的值
	T remove(Position<T> p);
	//为帮助实现其他方法而加入的简单方法
	int size();
	boolean isEmpty();
}

/**
 * Position接口
 * list中的所有元素都必须满足position接口
 * 接口中唯一方法element返回此position中存储的值
 * 在这个实例中都是Node类实现了Position接口
 * */
interface Position<T>{
	T element();
}