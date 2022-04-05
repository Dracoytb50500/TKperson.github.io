package com.week3;

import java.util.function.BiFunction;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;

import com.week3.Week3LinkedList;
import com.week3.Week3Queue;

public class Sorts<T> {
	private int comparisions = 0;
	private int swaps = 0;

	// bubble sort that takes a LinkedList and a function that compares 2 arguments and returns a boolean 
	public void _bubbleSort(Week3LinkedList<T> list, BiFunction<T, T, Boolean> compare) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				comparisions++;
				if (compare.apply(list.get(j), list.get(j+1))) {
					swaps++;
					list.swap(j+1, j);
				}
			}
		}
	}

	// selection sort that takes a LinkedList and a function that compares 2 arguments and returns a boolean
	public void _selectionSort(Week3LinkedList<T> list, BiFunction<T, T, Boolean> compare) {
		for (int i = 0; i < list.size(); i++) {
			int min = i;
			for (int j = i + 1; j < list.size(); j++) {
				comparisions++;
				if (compare.apply(list.get(j), list.get(min))) {
					min = j;
				}
			}
			swaps++;
			list.swap(i, min);
		}
	}

	// insertion sort that takes a LinkedList and a function that compares 2 arguments and returns a boolean
	public void _insertionSort(Week3LinkedList<T> list, BiFunction<T, T, Boolean> compare) {
		for (int i = 1; i < list.size(); i++) {
			T temp = list.get(i);
			int j = i;
			while (j > 0 && compare.apply(temp, list.get(j - 1))) {
				comparisions++;
				list.set(j, list.get(j - 1));
				j--;
				swaps++;
			}
			list.set(j, temp);
		}
	}

	// merge sort that takes a LinkedList and a function that compares 2 arguments and returns a boolean
	public void _mergeSort(Week3LinkedList<T> list, BiFunction<T, T, Boolean> compare) {
		if (list.size() > 1) {
			int mid = list.size() / 2;
			Week3LinkedList<T> left = new Week3LinkedList<T>();
			Week3LinkedList<T> right = new Week3LinkedList<T>();
			for (int i = 0; i < mid; i++) {
				left.add(list.get(i));
			}
			for (int i = mid; i < list.size(); i++) {
				right.add(list.get(i));
			}
			_mergeSort(left, compare);
			_mergeSort(right, compare);
			_merge(list, left, right, compare);
		}
	}

	// define _merge
	private void _merge(Week3LinkedList<T> list, 
						Week3LinkedList<T> left, 
						Week3LinkedList<T> right, 
						BiFunction<T, T, Boolean> compare) {
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < left.size() && j < right.size()) {
			comparisions++;
			if (compare.apply(left.get(i), right.get(j))) {
				list.set(k, left.get(i));
				i++;
			} else {
				list.set(k, right.get(j));
				j++;
			}
			k++;
			swaps++;
		}
		while (i < left.size()) {
			list.set(k, left.get(i));
			i++;
			k++;
			swaps++;
		}
		while (j < right.size()) {
			list.set(k, right.get(j));
			j++;
			k++;
			swaps++;
		}
	}

	public int getComparisions() {
		return comparisions;
	}

	public int getSwaps() {
		return swaps;
	}

	// convert nano seconds to seconds
	public static double nanoToSeconds(long nano) {
		return nano / 1000000000.0;
	}

	public static double nanoToSeconds(double nano) {
		return nano / 1000000000.0;
	}

	public static void testBubbleSort(int TESTSIZE, int SAMPLESIZE) {
		Instant startTime, endTime;
		Duration duration;
		long totalTime = 0, totalComparisions = 0, totalSwaps = 0;
		double averageTime = 0.0;

		for(int i = 0; i < SAMPLESIZE; i++) {
			Week3Queue<Integer> q = new Week3Queue<Integer>();
			System.out.println("Test #" + i + ": adding " + TESTSIZE + " random numbers to the queue");

			for (int j = 0; j < TESTSIZE; j++) {
				// q.add random number between 0 and 1000
				q.add((int) (Math.random() * 1000));
			}
			startTime = Instant.now();
			q.bubbleSort();
			endTime = Instant.now();
			duration = Duration.between(startTime, endTime);
			totalComparisions += q.getComparisions();
			totalSwaps += q.getSwaps();
			totalTime += (long) duration.getNano();
			averageTime += (double) duration.getNano() / SAMPLESIZE;
		}

		System.out.println("bubble sort");
		System.out.println("average time: " + nanoToSeconds(averageTime) + " seconds");
		System.out.println("total time: " + nanoToSeconds(totalTime) + " seconds");
		System.out.println("comparisions per sample: " + totalComparisions / SAMPLESIZE + ". Swap per sample: " + totalSwaps / SAMPLESIZE);
		System.out.println("total comparisions: " + totalComparisions + ". Total swaps: " + totalSwaps);
	}

	// test for selection sort
	public static void testSelectionSort(int TESTSIZE, int SAMPLESIZE) {
		Instant startTime, endTime;
		Duration duration;
		long totalTime = 0, totalComparisions = 0, totalSwaps = 0;
		double averageTime = 0.0;

		for(int i = 0; i < SAMPLESIZE; i++) {
			Week3Queue<Integer> q = new Week3Queue<Integer>();
			System.out.println("Test #" + i + ": adding " + TESTSIZE + " random numbers to the queue");

			for (int j = 0; j < TESTSIZE; j++) {
				// q.add random number between 0 and 1000
				q.add((int) (Math.random() * 1000));
			}
			startTime = Instant.now();
			q.selectionSort();
			endTime = Instant.now();
			duration = Duration.between(startTime, endTime);
			totalComparisions += q.getComparisions();
			totalSwaps += q.getSwaps();
			totalTime += (long) duration.getNano();
			averageTime += (double) duration.getNano() / SAMPLESIZE;
		}

		System.out.println("selection sort");
		System.out.println("average time: " + nanoToSeconds(averageTime) + " seconds");
		System.out.println("total time: " + nanoToSeconds(totalTime) + " seconds");
		System.out.println("comparisions per sample: " + totalComparisions / SAMPLESIZE + ". Swap per sample: " + totalSwaps / SAMPLESIZE);
		System.out.println("total comparisions: " + totalComparisions + ". Total swaps: " + totalSwaps);
	}

	// test for insertion sort	
	public static void testInsertionSort(int TESTSIZE, int SAMPLESIZE) {
		Instant startTime, endTime;
		Duration duration;
		long totalTime = 0, totalComparisions = 0, totalSwaps = 0;
		double averageTime = 0.0;

		for(int i = 0; i < SAMPLESIZE; i++) {
			Week3Queue<Integer> q = new Week3Queue<Integer>();
			System.out.println("Test #" + i + ": adding " + TESTSIZE + " random numbers to the queue");

			for (int j = 0; j < TESTSIZE; j++) {
				// q.add random number between 0 and 1000
				q.add((int) (Math.random() * 1000));
			}
			startTime = Instant.now();
			q.insertionSort();
			endTime = Instant.now();
			duration = Duration.between(startTime, endTime);
			totalComparisions += q.getComparisions();
			totalSwaps += q.getSwaps();
			totalTime += (long) duration.getNano();
			averageTime += (double) duration.getNano() / SAMPLESIZE;
		}

		System.out.println("insertion sort");
		System.out.println("average time: " + nanoToSeconds(averageTime) + " seconds");
		System.out.println("total time: " + nanoToSeconds(totalTime) + " seconds");
		System.out.println("comparisions per sample: " + totalComparisions / SAMPLESIZE + ". Swap per sample: " + totalSwaps / SAMPLESIZE);
		System.out.println("total comparisions: " + totalComparisions + ". Total swaps: " + totalSwaps);
	}

	// test for merge sort
	public static void testMergeSort(int TESTSIZE, int SAMPLESIZE) {
		Instant startTime, endTime;
		Duration duration;
		long totalTime = 0, totalComparisions = 0, totalSwaps = 0;
		double averageTime = 0.0;

		for(int i = 0; i < SAMPLESIZE; i++) {
			Week3Queue<Integer> q = new Week3Queue<Integer>();
			System.out.println("Test #" + i + ": adding " + TESTSIZE + " random numbers to the queue");

			for (int j = 0; j < TESTSIZE; j++) {
				// q.add random number between 0 and 1000
				q.add((int) (Math.random() * 1000));
			}
			startTime = Instant.now();
			q.mergeSort();
			endTime = Instant.now();
			duration = Duration.between(startTime, endTime);
			totalComparisions += q.getComparisions();
			totalSwaps += q.getSwaps();
			totalTime += (long) duration.getNano();
			averageTime += (double) duration.getNano() / SAMPLESIZE;
		}

		System.out.println("merge sort");
		System.out.println("average time: " + nanoToSeconds(averageTime) + " seconds");
		System.out.println("total time: " + nanoToSeconds(totalTime) + " seconds");
		System.out.println("comparisions per sample: " + totalComparisions / SAMPLESIZE + ". Swap per sample: " + totalSwaps / SAMPLESIZE);
		System.out.println("total comparisions: " + totalComparisions + ". Total swaps: " + totalSwaps);
	}

	public static void main(String[] args) {
		int TESTSIZE = 500,
			SAMPLESIZE = 12;
		
		testBubbleSort(TESTSIZE, SAMPLESIZE);
		testSelectionSort(TESTSIZE, SAMPLESIZE);
		testInsertionSort(TESTSIZE, SAMPLESIZE);
		testMergeSort(TESTSIZE, SAMPLESIZE);
	}
}
