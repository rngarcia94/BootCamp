import java.util.*;

public class RadixSortEjerc
{
	public static void radixSort(int []arr)
	{
		int rad = 10;
		String str[] = new String[arr.length];
		str = StringUtil.toStringArray(arr);
		int l = StringUtil.maxLength(str);
		StringUtil.lNormalize(str,'0');

		for (int p = 1;p <= l;p++) {
			List<String>[] listas = new ArrayList[rad];
			for(int i = 0;i < listas.length; i++){
				listas[i] = new ArrayList<String>();
			}
			for (int i = 0; i < str.length; i++) {
				for (int j = 0; j < 10; j++) {
					String aux = str[i];
					aux = aux.substring(l - p);
					char c = aux.charAt(0);
					int num = Integer.parseInt(String.valueOf(c));
					if (num == j) {
						listas[j].add(str[i]);
					}
				}
			}
			str = new String[str.length];
			int acu = 0;
			for (int i = 0; i < listas.length; i++) {
				for (int j = 0; j < listas[i].size(); j++) {
					str[acu] = listas[i].get(j);
					acu++;
				}
			}
		}
		int aux[] = new int[str.length];
		aux = StringUtil.toIntArray(str);
		for(int i = 0; i < arr.length; i++){
			arr[i] = aux[i];
		}
	}

	public static void main(String[] args)
	{
		int arr[]={16223,898,13,906,235,23,9,1532,6388,2511,8};
		radixSort(arr);
		for(int i=0; i<arr.length;i++)
		{
			System.out.print(arr[i]+(i<arr.length-1?",":""));
		}
	}
}
