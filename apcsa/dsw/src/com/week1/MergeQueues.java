package com.week1;

import java.util.*;
public class MergeQueues {

	public static Queue<Integer> merge(Queue<Integer> firstQueue,
					      	 	 	   Queue<Integer> secondQueue) {
		
		Queue<Integer> mergedQueue = new PriorityQueue<Integer>();

		Integer a = null, 
				b = null;

		while(!firstQueue.isEmpty() || !secondQueue.isEmpty()) {
			if(a == null) a = firstQueue.remove();

			if(b == null) b = secondQueue.remove();

			if(a < b) {
				mergedQueue.add(a);
				a = null;
			} else if(b < a) {
				mergedQueue.add(b);
				b = null;
			} else {
				mergedQueue.add(a);
				mergedQueue.add(b);
				a = null;
				b = null;
			}
		}

		mergedQueue.add(
			(a == null) ? b : a
		);

		return mergedQueue;
	}

	public static void printQueue(Queue<Integer> queue) {
		Iterator<Integer> it = queue.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " -> ");
		}
		System.out.println("nil");
	}

	public static void main(String[] args) {
		Queue<Integer> queue1 = new PriorityQueue<Integer>();
		queue1.add(1);
		queue1.add(4);
		queue1.add(5);
		queue1.add(8);
		System.out.print("(1st Queue) ");
		printQueue(queue1);

		Queue<Integer> queue2 = new PriorityQueue<Integer>();
		queue2.add(2);
		queue2.add(3);
		queue2.add(6);
		queue2.add(7);
		System.out.print("(2nd Queue) ");
		printQueue(queue2);
		
		Queue<Integer> queue3 = merge(queue1, queue2);
		System.out.print("(3rd Queue) ");
		printQueue(queue3);

	}
}
