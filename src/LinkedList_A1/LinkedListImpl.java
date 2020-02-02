/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node headCell; // this will be the entry point to your linked list (the head)
	Node lastCell; // this is the Node at the end of the list... the starting place
					// if you wanted to traverse the list backwards
	boolean isEmpty; // Tells if list is empty or not
	int numElts = 0; // Keeps track of items in a list

	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
		headCell = null; // Note that the root's data is not a true part of your data set!
		lastCell = null;
	}

	// implement all methods in interface, and include the getRoot method we made
	// for testing
	// purposes. Feel free to implement private helper methods!

	// add the fields you need to add to make it work... like a

	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
		return headCell;
	}

	public Node getLast() { // leave this method as is, used by the grader to grab your linkedList easily.
		return lastCell;
	}

	@Override
	public boolean insert(double elt, int index) {
		if (index > numElts || index < 0) {
			return false;
		}
		if (numElts == 0 && index == 0) {
			headCell = new Node(elt);
			headCell.next = null;
			headCell.prev = null;
			lastCell = headCell;
		} else if (index == 0) {
			Node newNode = new Node(elt);
			newNode.next = headCell;
			newNode.prev = null;
			headCell.prev = newNode;
			headCell = newNode;
		} else if (index == numElts) {
			Node newNode = new Node(elt);
			Node lastCell = headCell;
			newNode.next = null;
			while (lastCell.next != null) {
				lastCell = lastCell.next;
			}
			lastCell.next = newNode;
			newNode.prev = lastCell;
		} else {
			Node curr = headCell;
			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
			Node inst = new Node(elt);
			Node prev = curr.prev;
			inst.next = prev.next;// WHEN INSERTING at 0, there is not prev.next quantity dum dum
			prev.next = inst;
			inst.prev = prev;
			if (inst.next != null) {
				inst.next.prev = inst;
			}
		}
		// Following code; repositions last cell:
		lastCell = headCell;
		while (lastCell.next != null) {
			lastCell = lastCell.next;
		}
		numElts++;// Need to add to numElts when item is added
		return true;
	}

	@Override
	public boolean insort(double elt) {
		// TODO Auto-generated method stub
		if (headCell.data >= elt) {
			this.insert(elt, 0);
		}
		else {
			Node curr = headCell;
			while (curr.next != null) {
				if(curr.next.data >= elt && curr.data < elt) {
					Node inst = new Node(elt);
					Node prev = curr;
					inst.next = curr.next;
					prev.next = inst;
					inst.prev = prev;
					if (inst.next != null) {
						inst.next.prev = inst;
					}
					lastCell = headCell;
					while (lastCell.next != null) {
						lastCell = lastCell.next;
					}
					numElts++;
					return true;
				}
				curr = curr.next;
			}
			this.insert(elt, numElts);
		}
		// Need to add to numElts when item is added
		return true;
	}

	@Override
	public boolean remove(int index) {
		if (index >= numElts || index < 0) {
			return false;
		}
		// TODO add condition for removing from end and from front
		if (index == 0) {
			headCell = headCell.next;
			headCell.prev = null;
		} else if (index + 1 == numElts) {
			lastCell =lastCell.prev;
			lastCell.next = null;
		} else {
			Node curr = headCell;
			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;

		}
		numElts--;// Need to remove from numElts when item is removed
		return true;
	}

	@Override
	public double get(int index) {
		if (index >= numElts || index < 0) {
			return Double.NaN;
		}
		Node curr = headCell;
		int count =0;
		while(curr != null) {
			if(count == index) {
				return curr.data;
			}
			count++;
			curr = curr.next;
		}
		return 0;
	}

	@Override
	public int size() {
		return numElts;
	}

	@Override
	public boolean isEmpty() {
		return (size() == 0);
	}

	@Override
	public void clear() {
		headCell = null;
		lastCell = null;
		numElts = 0;
	}
}