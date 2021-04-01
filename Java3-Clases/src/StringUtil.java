public class StringUtil
{
	// Retorna una cadena compuesta por n caracteres c
	// Ejemplo: replicate('x',5) ==> 'xxxxx'
	public static String replicate(char c, int n) {
		String str = "";
		for(int i = 0; i < n; i++){
			str = c + str ;
		}
		return str;
	}

	// Retorna una cadena de longitud n, compuesta por s
	// y precedida de tantos caracteres c como sea necesario
	// para completar la longitud mencionada
	// Ejemplo lpad("5",3,'0') ==> "005"
	public static String lpad(String s, int n, char c){
		String str = s;
		int a = n - s.length();
		if (s.length() < n){
			str = replicate(c,a) + s;
		}
		return str;
	}

	// Retorna un String[] conteniendo los elementos de arr
	// representados como cadenas de caracteres
	public static String[] toStringArray(int arr[])
	{
		String str[] = new String[arr.length];
		for(int i = 0; i < arr.length; i++ ){
			str[i] = Integer.toString(arr[i]);
		}
		return str;
	}

	// Retorna un String[] conteniendo los elementos de arr
	// representados como array
	public static int[] toIntArray(String arr[])
	{
		int nums[] = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			nums[i] = Integer.parseInt(arr[i]);
		}
		return nums;
	}

	// Retorna la longitud del elemento con mayor cantidad
	// de caracteres del array arr
	public static int maxLength(String arr[]) {
		int l = 0;
		for (int i = 0; i < arr.length; i++) {
			if(l < arr[i].length()){
				l = arr[i].length();
			}
		}
		return l;
	}

	// Completa los elemento del arr agregando caracteres c
	// a la izquierda, dejando a todos con la longitud del mayor
	public static void lNormalize(String arr[], char c){
		int l = maxLength(arr);
		for (int i = 0;i < arr.length; i++){
			arr[i] = lpad(arr[i],l,c);
		}
	}

	public static String rpad(String s, int n, char c){
		String str = s;
		int a = n - s.length();
		if (s.length() < n){
			str = s + replicate(c,a) ;
		}
		return str;
	}

	public static String ltrim(String s){
		return s.replaceAll("^\\s+","");
	}

	public static String rtrim(String s){
		return s.replaceAll("\\s+$","") ;
	}

	public static String trim(String s){
		return  s.replaceAll( "^\\s+ | \\s+$","");
	}

	public static int indexOfN(String s, char c, int n){
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			if (s.charAt(i) == c){
				count++;
			}
			if (count == n){
				return i;
			}
		}
		return 0;
	}


}
