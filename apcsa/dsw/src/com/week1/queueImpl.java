package com.week1;

import java.util.*;

public class queueImpl<T> {
	Queue<T> queue;
	
	public queueImpl() {
		// init
		queue = new PriorityQueue<T> (); 
	}

	public void add(T item) {
		// print the items that are added with fancy format
		
		System.out.println("Enqueued data: " + item);
		this.queue.add(item);
		printWordCount();
	}

	private void printWordCount() {
		// print item count with fancy format
		
		String temp = this.queue.toString();
		temp = temp.substring(0, temp.length() - 1).substring(1);

		if(temp.isEmpty()) {
			temp = "null";
		}

		System.out.println("Words count: " 
				 + this.queue.size() 
				 + ", data: " 
				 + temp.replaceAll(",", "")
		);
	}

	public void remove() {
		// print the removed from queue
		System.out.println("Dequeued data: " + this.queue.remove());
		printWordCount();
	}

	public String toString() {
		return this.queue.toString();
	}

	public static void main(String[] args) {
		// tester
		queueImpl<String> queueTest = new queueImpl<String>();
		queueTest.add("seven");
		queueTest.add("slimy");
		queueTest.add("snakes");
		queueTest.add("snallying");
		queueTest.add("slowly");
		queueTest.add("slithered");
		queueTest.add("southward");
		queueTest.remove();
		queueTest.remove();
		queueTest.remove();
		queueTest.remove();
		queueTest.remove();
		queueTest.remove();
		queueTest.remove();
	}
}
