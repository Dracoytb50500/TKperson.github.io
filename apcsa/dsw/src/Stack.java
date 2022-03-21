import java.util.*;

// create a Stack class

public class Stack<T> {
    LinkedList<T> list;
    public Stack() {
        list = new LinkedList<T>();
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
        Iterator<T> it = list.iterator();
        String s = "(Top) ";
        while(it.hasNext()) {
            s += it.next() + " -> ";
        }

        return s + "nil";
    }

    public String toString() {
        Iterator<T> it = list.iterator();
        String s = new String();
        while(it.hasNext()) {
            s += it.next() + " ";
        }

        return s;
    }

    // convert queue and Stack to String
    public static String queueToString(Queue<Integer> q) {
        Iterator<Integer> it = q.iterator();
        String s = new String();
        while(it.hasNext()) {
            s += it.next() + " ";
        }
        return s;
    }

    public static String stackToString(Stack q) {
        Iterator<Integer> it = q.iterator();
        String s = new String();
        while(it.hasNext()) {
            s += it.next() + " ";
        }
        return s;
    }

    public static String formatQueue(Queue<Integer> q) {
        Iterator<Integer> it = q.iterator();
        String s = "(Head) ";
        while(it.hasNext()) {
            s += it.next() + " -> ";
        }
        return s + "nil";
    }

    public static void main(String[] args) {
        Queue<Integer> q = new PriorityQueue<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(formatQueue(q));
        System.out.println(queueToString(q));

        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.toStringFormat());
        System.out.println(stackToString(s));
    }
}
