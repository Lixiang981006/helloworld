package practise;

public class Decryption {
public static void main(String[] args) {
	char[][] arr = {
			{'i','a','u','t','m','o','c','s','m','n'},
			{'i','m','r','e','b','o','t','n','e','l'},
			{'s','t','r','h','e','r','e','o','a','e'},
			{'v','m','w','i','h','t','s','e','e','a'},
			{'t','m','a','e','o','h','w','h','s','y'},
			{'c','e','e','l','t','t','e','o','h','m'},
			{'u','o','u','f','e','h','t','r','f','t'}
	};
	colomnSwitch(arr, 0, 1);
	colomnSwitch(arr, 1, 3);
	colomnSwitch(arr, 2, 9);
	colomnSwitch(arr, 3, 9);
	colomnSwitch(arr, 5, 8);
	colomnSwitch(arr, 8, 6);
	colomnSwitch(arr, 7, 8);
	colomnSwitch(arr, 8, 9);
	rowSwitch(arr, 0, 2);
	rowSwitch(arr, 1, 4);
	rowSwitch(arr, 4, 6);
//	print(arr);
	colomnSwitch(arr, 2, 4);
//	print(arr);
	colomnSwitch(arr, 2, 7);
//	print(arr);
	colomnSwitch(arr, 2, 4);
//	print(arr);
	colomnSwitch(arr, 2, 7);
//	print(arr);
	colomnSwitch(arr, 2, 4);
//	print(arr);
	colomnSwitch(arr, 3, 6);
	print(arr);
	
//	colomnSwitch(arr, 2, 4);
//	print(arr);
//	colomnSwitch(arr, 2, 7);
//	print(arr);
//	colomnSwitch(arr, 2, 4);
//	print(arr);
//	colomnSwitch(arr, 2, 7);
//	print(arr);
//	colomnSwitch(arr, 2, 4);
//	print(arr);
}

public static void colomnSwitch(char[][] arr, int c1, int c2) {//换列
	  int i=arr.length;
	  int j=arr[0].length;//列数量
	  for(int x=0;x<i;x++) {
	   char temp;
	   temp = arr[x][c2];
	   arr[x][c2]=arr[x][c1];
	   arr[x][c1]=temp;
	  }
	  
	 }
	 public static void rowSwitch(char[][] arr, int r1,int r2) {//行
	  int i=arr.length;
	  int j=arr[0].length;//列数量
	  char[] temp;
	  temp = arr[r2];
	  arr[r2]=arr[r1];
	  arr[r1]=temp;
	  
	 }

public static  void print(char[][] arr) {
	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr[0].length; j++) {
			System.out.print(arr[i][j]);
		}
		System.out.println();
	}
	System.out.println();
}

}
