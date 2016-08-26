package ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TarefaB1_TP4 {

	public static int EXECUTIONS = 1;
	public static String CHART_NAME = "Three-way-Radix-Quicksort";
	public static boolean PRINT_SORTED_LIST = true;
	
	public static void main(String Args[]) throws NumberFormatException, IOException {
		int i, num, j, max = 0;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(in.readLine());

		String[] array = new String[num];
		String array_aux[] = new String[num];
		long result = 0;

		for (i = 0; i < num; i++) /* read keys to array, in upper case */
		{
			array[i] = in.readLine().toUpperCase();
			if (array[i].length() > max) {
				max = array[i].length();
			}
		}

		/* add spaces at the end of the string, so all have same length */
		for (i = 0; i < num; i++) {
			if (array[i].length() < max) {
				for (j = array[i].length(); j < max; j++) {
					array[i] += ' ';
				}
			}
		}

		for (i = 0; i < 10; i++) {
			array_aux = Arrays.copyOf(array, array.length);
			long startTime = System.nanoTime();
			// System.out.println("ST: "+startTime);
			array_aux = LSDSort(array_aux, max - 1, num);
			long estimatedTime = (System.nanoTime() - startTime);
			// System.out.println("ET: "+estimatedTime);
			result += estimatedTime;
		}

		result /= i;
		
		System.out.println("Average time of: " + i + " executions: " + result + " nanoseconds\n");

		// Should the sorted list be printed?
		if (PRINT_SORTED_LIST) {
			array = array_aux;
			System.out.println("Sorted list:\n");
			for (i = 0; i < num; i++) {
				System.out.println(array[i]);
			}
		}

	}

	public static String[] LSDSort(String afinal[], int max, int num) {
		int[] conta = new int[27];
		String aux = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		while (max >= 0) {
			for (int i = 0; i < 27; i++) {
				conta[i] = 0;
			}

			for (int i = 0; i < num; i++) {
				conta[aux.indexOf(afinal[i].charAt(max))] += 1;
			}

			for (int i = 1; i < 27; i++) {
				conta[i] += conta[i - 1];
			}

			String temp[] = new String[num];

			for (int i = num - 1; i >= 0; i--) {
				temp[conta[aux.indexOf(afinal[i].charAt(max))] - 1] = afinal[i];
				conta[aux.indexOf(afinal[i].charAt(max))]--;
			}
			afinal = temp;

			max--;
		}

		for (int i = 0; i < num; i++) {
			while (afinal[i].charAt(afinal[i].length() - 1) == ' ') {
				afinal[i] = afinal[i].substring(0, afinal[i].length() - 1);
			}
		}
		return afinal;
	}
}
