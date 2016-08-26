
package ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TarefaB1_TP4 {

	public static int EXECUTIONS = 1;
	public static String CHART_NAME = "Three-way-Radix-Sort";
	public static boolean PRINT_SORTED_LIST = true;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int i, num;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(in.readLine());

		String[] array = new String[num];
		String array_aux[] = new String[num];
		long result = 0;

		/* read keys to array, in upper case */
		for (i = 0; i < num; i++) {
			array[i] = in.readLine().toUpperCase();
		}

		for (i = 0; i < EXECUTIONS; i++) {
			array_aux = Arrays.copyOf(array, array.length);
			long startTime = System.nanoTime();
			// System.out.println("ST: "+startTime);
			array_aux = RadixSort(array_aux, 0, array_aux.length - 1, 0);
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

	static String[] RadixSort(String a[], int left, int right, int d) {
		if (right <= left)
			return a;

		pivot(a, left, right, (left + right) / 2); // acha pivot

		String pivot = a[right];

		int i = left - 1, j = right, p = left - 1, q = right, k;

		// while they do not cross
		while (i < j) {
			// continues until it finds a word with char > pivot
			while (less(a[++i], pivot, d))
				;
			// continues until it finds a word with char < pivot
			while (less(pivot, a[--j], d)) {
				if (j == left)
					break;
			}
			// if they cross
			if (i > j)
				break;
			// swap the word found in the first while with the one found in the
			// second while
			swapReferences(a, i, j);

			if (equal(a[i], pivot, d))
				swapReferences(a, ++p, i);
			if (equal(pivot, a[j], d))
				swapReferences(a, --q, j);
		}

		// when the first d+1 chars of the keys match
		if (p == q) {
			// if the pivot has still more chars
			if (pivot.length() > d)
				RadixSort(a, left, right, d + 1);
		}
		if (p == q)
			return a;

		if (less(a[i], pivot, d))
			i++;
		// compares the chars of the smaller words and sorts
		for (k = left; k <= p; k++, j--)
			swapReferences(a, k, j);
		// compares the chars of the bigger words and sorts
		for (k = right; k >= q; k--, i++)
			swapReferences(a, k, i);

		RadixSort(a, left, j, d); // smaller

		if ((i == right) && (equal(a[i], pivot, d)))
			i++;
		if (pivot.length() >= d)
			RadixSort(a, j + 1, i - 1, d + 1); // middle

		RadixSort(a, i, right, d); // bigger
		return a;
	}

	public static void swapReferences(String[] array, int i, int j) {
		String temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static boolean less(String s, String t, int d) {
		if (t.length() <= d)
			return false;
		if (s.length() <= d)
			return true;
		return s.charAt(d) < t.charAt(d);
	}

	public static boolean equal(String s, String t, int d) {
		return !less(s, t, d) && !less(t, s, d);
	}

	public static void pivot(String[] array, int l, int r, int m) {
		if (array[l].equals(array[m])) {
			swapReferences(array, l, m);
		}

		if (array[l].equals(array[r])) {
			swapReferences(array, l, r);
		}

		if (array[m].equals(array[r])) {
			swapReferences(array, m, r);
		}
		swapReferences(array, m, r);
	}
}
