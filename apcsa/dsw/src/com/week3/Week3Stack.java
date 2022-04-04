package com.week3;

import java.util.Iterator;

import com.week3.Week3LinkedList;
import com.week3.Week3Queue;

// this is an implementation of Stack with generic types using linkedlist
public class Week3Stack<T> extends Week3LinkedList<T> {
    Week3LinkedList<T> list;
    public Week3Stack() {
        list = new Week3LinkedList<T>();
    }

    public void push(T item) {
        list.addFirst(item);
    }

    public T pop() {
        return list.removeFirst();
    }

    public T peek() {
        return list.getFirst();
    }

    // iterator helps with looping through the queue without copy or destroy the queue
    public Iterator<T> iterator() {
        return list.iterator();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public String toStringFormat() {
	// print with fancy format for Stack
	
        Iterator<T> it = list.iterator();
        String s = "(Top) ";
        while(it.hasNext()) {
            s += it.next() + " -> ";
        }

        return s + "nil";
    }

    public String toString() {
	// print Stack with spaces between items
	
        Iterator<T> it = list.iterator();
        String s = new String();
        while(it.hasNext()) {
            s += it.next() + " ";
        }

        return s;
    }

    // convert queue and Stack to String
    public static String queueToString(Week3Queue<Integer> q) {
        Iterator<Integer> it = q.iterator();
        String s = new String();
        while(it.hasNext()) {
            s += it.next() + " ";
        }
        return s;
    }

    public static String stackToString(Week3Stack<Integer> q) {
	// print stack items with spaces
        Iterator<Integer> it = q.iterator();
        String s = new String();
        while(it.hasNext()) {
            s += it.next() + " ";
        }
        return s;
    }

    public static String formatQueue(Week3Queue<Integer> q) {
	// fancy print for queue
        Iterator<Integer> it = q.iterator();
        String s = "(Head) ";
        while(it.hasNext()) {
            s += it.next() + " -> ";
        }
        return s + "nil";
    }

    public static void main(String[] args) {
	// tester
        Week3Queue<Integer> q = new Week3Queue<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(formatQueue(q));
        System.out.println(queueToString(q));

        Week3Stack<Integer> s = new Week3Stack<Integer>();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.toStringFormat());
        System.out.println(stackToString(s));
    }
}
