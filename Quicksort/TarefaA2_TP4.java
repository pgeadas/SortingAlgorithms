package ex;

import java.io.*;

import java.util.Arrays;



public class TarefaA2_TP4 {
	//used to store the times to create the chart

	private static int CUTOFF = 30, num;
	public static int EXECUTIONS = 1;
	public static String CHART_NAME = "QUICKSORT";
	public static boolean PRINT_SORTED_LIST = true;

	public static void main(String Args[]) throws NumberFormatException, IOException {
		int i;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(in.readLine());
		String[] array = new String[num];

		String array_aux[] = new String[num];
		long result = 0;

		for (i = 0; i < num; i++) {
			array[i] = in.readLine();
		}

		for (i = 0; i < EXECUTIONS; i++) {
			array_aux = Arrays.copyOf(array, array.length);
			long startTime = System.nanoTime();
			// System.out.println("ST: "+startTime);
			quicksort(array_aux, 0, num - 1);
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

	public static String[] insertionSort(String array[]) {
		int i, j;
		for (i = 1; i < num; i++) {
			String temp = array[i];
			for (j = i - 1; j >= 0 && array[j].compareToIgnoreCase(temp) > 0; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = temp;
		}
		return array;
	}

	public static void swap(String[] s, int index1, int index2) {
		String tmp = s[index1];
		s[index1] = s[index2];
		s[index2] = tmp;
	}

	private static void quicksort(String[] s, int low, int high) {
		if (low + CUTOFF > high)
			insertionSort(s);

		else {
			// Sort low, middle, high
			int middle = (low + high) / 2;
			if (s[middle].compareToIgnoreCase(s[low]) < 0)
				swap(s, low, middle);
			if (s[high].compareToIgnoreCase(s[low]) < 0)
				swap(s, low, high);
			if (s[high].compareToIgnoreCase(s[middle]) < 0)
				swap(s, middle, high);

			swap(s, middle, high - 1);
			String pivot = s[high - 1];

			int i, j;
			for (i = low, j = high - 1;;) {

				while (s[++i].compareToIgnoreCase(pivot) < 0)
					;
				while (pivot.compareToIgnoreCase(s[--j]) < 0)
					;
				if (i < j)
					swap(s, i, j);
				else
					break;
			}
			swap(s, i, high - 1);
			quicksort(s, low, i - 1);
			quicksort(s, i + 1, high);
		}
	}
}
