| [Home](..) | [Tech Talk Notes](.) | [Test prep plans](../tpp) | [Data struture work](../dsw) | [Review tickets](../rtk)

# Tech talk notes
### Week 0
* Data structures
  * primitive 
    * int
    * float
    * double
  * non-primitive
    * Integer
    * List
    * String

* Paradigms
  * procedural
    * Showing every single step on how to achieve certain actions
  * decorative 
    * Telling what action you want to do

# Week 1
* Linked list
  * head and tail linked together
    * so there's a previous and next node
  * head points to an item in front of the element in the front of t list
  * Queue
    * Adds to the top of the stack
    * Removes from the bottom of the stack

```java
class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> node = new Node<T>(data);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }
        this.size++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        if (index == 0) {
            this.addFirst(data);
        } else if (index == this.size) {
            this.addLast(data);
        } else {
            Node<T> node = new Node<T>(data);
            Node<T> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.setNext(node);
            this.size++;
        }
    }

    public void addFirst(T data) {
        Node<T> node = new Node<T>(data);
        node.setNext(this.head);
        this.head = node;
        if (this.size == 0) {
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(T data) {
        Node<T> node = new Node<T>(data);
        if (this.size == 0) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }
        this.size++;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public T getFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.head.getData();
    }

    public T getLast() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.tail.getData();
    }

    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        T data = null;
        if (index == 0) {
            data = this.removeFirst();
        } else if (index == this.size - 1) {
            data = this.removeLast();
        } else {
            Node<T> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            data = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            this.size--;
        }
        return data;
    }

    public T removeFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        T data = this.head.getData();
        this.head = this.head.getNext();
        this.size--;
        if (this.size == 0) {
            this.tail = null;
        }
        return data;
    }

    public T removeLast() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        T data = this.tail.getData();
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node<T> current = this.head;
            for (int i = 0; i < this.size - 2; i++) {
                current = current.getNext();
            }
            this.tail = current;
            this.tail.setNext(null);
        }
        this.size--;
        return data;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
}
```

* implements
  * use for defining existing methods (very similar to abstract)

* Generic
  * <T> T type generic
  * Used for "any type"
  * For reducing code duplications

Example:
```java
class test<T> {
	LinkedList<T> ll = new LinkedList<T>;
	
}
```
}

### Week 3 
* Calculator
  * push numbers 
* Symbols for operators
  * *
  * /
  * %
  * +
  * -
* Symbols for changing order of operation
  * Separators
    * (
    * )
    * Space
    * Operators
* Shunting yard algorithm
```java
public void tokenize() { /* ... */ }
public void reverseToken() { /* ... */ }
public void rnpToResult() { /* ... */ }
```

* Calculator code highlights
```java
public Double calculate(Double operand1, Double operand2, String operator) {
	Double result = 0.0;
	switch (operator) {
		case "+":
			result = operand2 + operand1;
			break;
		case "-":
			result = operand2 - operand1;
			break;
		case "*":
			result = operand2 * operand1;
			break;
		case "/":
			result = operand2 / operand1;
			break;
		case "%":
			result = operand2 % operand1;
			break;
		case "^":
			result = Math.pow(operand2, operand1);
	}
	return result;
}


private void rpnToResult() {
	// Stack used to hold calculation while process RPN
	Stack<Double> calculation = new Stack<Double>();

	// for loop to process RPN
	for(int i = 0; i < this.reverse_polish.size(); i++) {
		String currentToken = this.reverse_polish.get(i);
		// if token is an operator
		if(isOperator(currentToken)) {
			// get operator
			Double result;

			// get operands
			Double operand1 = calculation.pop();
			Double operand2 = calculation.pop();
			// calculate result
			result = this.calculate(operand1, operand2, currentToken);

			// push result to stack
			calculation.push(result);
		} else if (isFunction(currentToken)) {

			result = this.eval(calculation.pop(), currentToken);
			// push result to stack
			calculation.push(result);

		} else { // if a number
			// push number to stack
			calculation.push(Double.parseDouble(currentToken));
		}
		// debug
		// System.out.println("reverse plish: " + this.reverse_polish);
		System.out.println("calc: " + calculation);
	}

	this.result = calculation.pop();
}
```
