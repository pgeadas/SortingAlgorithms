
package ex;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TarefaA1_TP4 {

	private static ArrayList<Long> tempo = new ArrayList<Long>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int num, i;
		num = Integer.parseInt(in.readLine());
		long result = 0;

		String[] array = new String[num];

		String array_aux[] = new String[num];

		for (i = 0; i < num; i++) {
			array[i] = in.readLine();
		}

		System.out.println();

		for (i = 0; i < 1; i++) {
			array_aux = Arrays.copyOf(array, array.length);
			long startTime = System.nanoTime();
			// System.out.println("ST: "+startTime);

			insertionSort(num, array_aux);

			long estimatedTime = (System.nanoTime() - startTime);
			// System.out.println("ET: "+estimatedTime);

			result += estimatedTime;
		}

		result /= i;

		System.out.println("Average of: " + i + "executions " + result + " nanoseconds");

		// createChart();
		array = array_aux;

		for (i = 0; i < num; i++) {
			System.out.println(array[i]);
		}
	}

	public static String[] insertionSort(int num, String[] array) {
		int i, j;
		for (i = 1; i < num; i++) { // num = number of elements
			String temp = array[i];
			for (j = i - 1; j >= 0 && array[j].compareToIgnoreCase(temp) > 0; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = temp;

		}
		return array;
	}

	public static void createChart() {
		// Create a simple XY chart
		int x = 0;
		XYSeries series = new XYSeries("XYGraph");
		for (long p : tempo) {
			series.add(x, p);
			x += 10;
		}
		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		// Generate the graph

		JFreeChart chart = ChartFactory.createXYLineChart("Execution Time", // Title
				"P value", // x-axis Label
				"Time(ns)", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URL

		);

		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		frame.setVisible(true);

		try {
			ChartUtilities.saveChartAsJPEG(new File("D:chart.jpg"), chart, 500, 300);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
		}
	}
}
