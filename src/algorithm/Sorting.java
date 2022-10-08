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
	 * ����Ӵ�С����
	 * ƽ�����Ӷ�O(n2)�����Ÿ��Ӷ�O(n2)�����Ӷ�O(n2)
	 * ��ѭ��ѡ������дӵ�i+1��n�е����ֵ/��Сֵ
	 * ��ѭ�������ֵ/��Сֵ�������еĵ�i��λ��
	 * ���б���Ϊ�������֣��������򲿷ֺ�δ���򲿷�
	 * @return �����������
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
	 * ����Ӵ�С����
	 * ���Ӷ�O��n2��
	 * ԭ��
	 * ��ѭ��������arr[i]С��������������Ϊrank
	 * ��ѭ��ͨ��rank����arr[i]������һ��������ĺ���λ��
	 * ע�⵱�ж������ͬʱ������ֽ������������ͬһ��λ�õ��������Ϊrank��ͬ��
	 * 
	 * @return �����������
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
			//�ж϶������ͬ�����������ж������ͬ����ô����һ����������rank+1��λ����
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
	 * �Ӵ�С����
	 * ���Ӷ�O(n2),���Ÿ��Ӷ�O(n)--�Ѿ����������������Ӷ�O(n2)
	 * ���з�Ϊ��������κ�δ����Σ���ѭ��ÿ��ѡ����һ��δ������е�������ѭ������ѭ��
	 * ѡ������������򣬽���ӽ����������
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
	 * ��ӡԭ����
	 * */
	public static void show(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
