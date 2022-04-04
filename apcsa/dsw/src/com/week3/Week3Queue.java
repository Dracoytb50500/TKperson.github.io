package com.week3;

import java.util.Iterator;
import java.util.function.BiFunction;

import com.week3.Week3LinkedList;
import com.week3.Sorts;

// java Queue
public class Week3Queue<T> extends Sorts<T> {
    private Week3LinkedList<T> list;

    public Week3Queue() {
        list = new Week3LinkedList<T>();
    }

    public void add(T data) {
        list.add(data);
    }

    public T dequeue() {
        return list.remove(0);
    }

    public T peek() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public String toStringFormat() {
        // print with fancy format for Queue
        Iterator<T> it = list.iterator();
        String s = "(Front) ";
        while (it.hasNext()) {
            s += it.next() + " -> ";
        }

        return s + "nil";
    }

    public String toString() {
        // print Queue with spaces between items
        Iterator<T> it = list.iterator();
        String s = new String();
        while (it.hasNext()) {
            s += it.next() + " ";
        }

        return s;
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    // use _bubbleSort to sort Queue if generic type is String
    public void bubbleSort() {
        // using switch case to check if generic type is String 
        switch (list.get(0).getClass().getName()) {
            case "java.lang.String":
                // for generic type where the type is a string
                _bubbleSort(list, (T a, T b) -> {
                    return ((String) a).compareTo((String) b) > 0;
                });
                break;

            // for generic type where the type is an integer
            case "java.lang.Integer":
                _bubbleSort(list, (T a, T b) -> {
                    return ((Integer) a).compareTo((Integer) b) > 0;
                });
                break;
            default:
                System.out.println("Error: The given generic type is not support for this implementation of bubble sort");
                break;
        }
    }

    public void selectionSort() {
        // see bubbleSort() for comments about switch case
        // using switch case to check if generic type is String 
        switch (list.get(0).getClass().getName()) {
            case "java.lang.String":
                _selectionSort(list, (T a, T b) -> {
                    return ((String) a).compareTo((String) b) < 0;
                });
                break;
            case "java.lang.Integer":
                _selectionSort(list, (T a, T b) -> {
                    return ((Integer) a) > ((Integer) b);
                });
                break;
            default:
                System.out.println("Error: The given generic type is not support for this implementation of selection sort");
                break;
        }
    }

    public void insertionSort() {
        // see bubbleSort() for comments about switch case
        // using switch case to check if generic type is String 
        switch (list.get(0).getClass().getName()) {
            case "java.lang.String":
                _insertionSort(list, (T a, T b) -> {
                    return ((String) a).compareTo((String) b) < 0;
                });
                break;
            case "java.lang.Integer":
                _insertionSort(list, (T a, T b) -> {
                    return ((Integer) a) > ((Integer) b);
                });
                break;
            default:
                System.out.println("Error: The given generic type is not support for this implementation of insertion sort");
                break;
        }
    }

    public void mergeSort() {
        // see bubbleSort() for comments about switch case
        switch (list.get(0).getClass().getName()) {
            // if list element type is string
            case "java.lang.String":
                _mergeSort(list, (T a, T b) -> {
                    return ((String) a).compareTo((String) b) < 0;
                });
                break;
            case "java.lang.Integer":
                _mergeSort(list, (T a, T b) -> {
                    return ((Integer) a) < ((Integer) b);
                });
                break;
            default:
                System.out.println("Error: The given generic type is not support for this implementation of merge sort");
                break;
        }
    }

    public static void main(String[] args) {
        Week3Queue<String> q = new Week3Queue<String>();
        q.add("e");
        q.add("b");
        q.add("c");
        q.add("a");
        q.add("d");
        q.add("a");
        q.mergeSort();

        System.out.println(q.toStringFormat());
        System.out.println(q.toString());

        Week3Queue<Integer> q2 = new Week3Queue<>();
        q2.add(2);
        q2.add(5);
        q2.add(100);
        q2.add(2341);
        q2.add(1);
        q2.add(4);

        q2.bubbleSort();

        System.out.println(q2.toStringFormat());
        System.out.println(q2.toString());
    }
}
