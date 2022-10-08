package algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class DataGenerator {
	/**
	 * 取出前n个数
	 * @param length 得到文件中的前length个数
	 * */
	public static int[] get(int length) {
		File file = new File("./src/algorithm/data.txt");
		byte[] arr = new byte[length];
		int[] data = new int[length];
		try {
			FileInputStream fis = new FileInputStream(file);
			for (int i = 0; i < arr.length; i++) {
				fis.read(arr, i, 1);
				data[i] = arr[i];
			}
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
/**
 * 生成-128到127之间的随机数列
 * @param size 数列长度
 * @param pathname address
 * */
	public static void generate(int size,String pathname) {
		File file = new File(pathname);
		byte[] arr = new byte[size];
		if(file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			FileOutputStream fos =	new FileOutputStream(file);
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (byte)(Math.random()*200);
			}
			fos.write(arr);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
