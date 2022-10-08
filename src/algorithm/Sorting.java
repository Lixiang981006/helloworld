package algorithm;

public class Sorting {
	public static void main(String[] args) {
//		DataGenerator.generate(500,"./src/algorithm/data.txt");
		int[] arr = DataGenerator.get(30);
//		show(arr);
		arr = insertionSort(arr);
		show(arr);
	}
	
	/**
	 * Selection Sort
	 * 数组从大到小排序
	 * 平均复杂度O(n2)，最优复杂度O(n2)，最差复杂度O(n2)
	 * 内循环选择出数列从第i+1到n中的最大值/最小值
	 * 外循环将最大值/最小值放在数列的第i个位置
	 * 数列被分为了两部分，即已排序部分和未排序部分
	 * @return 排序完的数组
	 * */
	public static int[] selectionSort(int[] arr) {
		int temp;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if(arr[j] > arr[i]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
	
	/**
	 * Rank sort
	 * 数组从大到小排序
	 * 复杂度O（n2）
	 * 原理：
	 * 内循环计数比arr[i]小的数的数量，记为rank
	 * 外循环通过rank，将arr[i]放置在一个新数组的合适位置
	 * 注意当有多个数相同时，会出现将多个数放置在同一个位置的情况（因为rank相同）
	 * 
	 * @return 排序完的数组
	 * */
	public static int[] rankSort(int[] arr) {
		int[] arr_temp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int rank = 0;
			for (int j = 0; j < arr.length; j++) {
				if(arr[i]<arr[j]) {
					rank++;
				}
			}
			//判断多个数相同的情况，如果有多个数相同，那么将下一个数放置在rank+1的位置上
			if(arr_temp[rank] == 0) {
				arr_temp[rank] = arr[i];
			}
			else {
				for(int j = rank+1;;j++) {
					if(arr_temp[j] == 0) {
						arr_temp[j] = arr[i];
						break;
					}
				}
			}
		}
		return arr_temp;
	}
	
	/**
	 * insertion sort
	 * 从大到小排序
	 * 复杂度O(n2),最优复杂度O(n)--已经排完序的情况，最差复杂度O(n2)
	 * 数列分为了已排序段和未排序段，外循环每次选择下一个未排序段中的数，内循环将外循环
	 * 选择的数进行排序，将其加进已排序段中
	 * */
	public static int[] insertionSort(int[] arr) {
		int temp;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j > 0; j--) {
				if(arr[j]>arr[i]) {
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
					i--;
				}
				else {
					break;
				}
			}
		}
		return arr;
	}
	
	/**
	 * 打印原数列
	 * */
	public static void show(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
