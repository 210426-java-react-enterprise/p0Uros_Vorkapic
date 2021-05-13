package com.revature.p0.util;

/**
 * A simple implementation of a doubly linked-list structure that
 * does not accept null data.
 *
 * Current implementation heavily borrowed from Wezley
 *
 * @param <T>
 */
public class LinkedList<T> implements List<T>, Queue<T> {

	private int size;
	private Node<T> head;
	private Node<T> tail;

	/**
	 * Appends data to tail of list
	 * @param data to be inserted
	 * @throws IllegalArgumentException if null value is inserted
	 */
	@Override
	public void add(T data) throws IllegalArgumentException {

		if (data == null) {
			throw new IllegalArgumentException("This linked list does not accept null values");
		}

		Node<T> newNode = new Node<T>(data);
		if (head == null) {
			tail = head = newNode; // sets both the head and tail equal to the new node
		} else {
			tail.nextNode = newNode;
			newNode.prevNode = tail;
			tail = newNode;
		}

		size++;

	}

	/**
	 * Returns and removes the head node's data or else returns null.
	 * @return head node or null
	 */
	@Override
	public T poll() {

		if (head == null) {
			return null;
		}

		T soughtData = head.data;
		head = head.nextNode;

		if (head != null) {
			head.prevNode = null;
		} else {
			tail = null;
		}

		size--;

		return soughtData;

	}

	/**
	 * Returns the head node's data without removing it, else returns null
	 * @return head node or null
	 */
	@Override
	public T peek() {
		if (head != null) {
			return head.data;
		} else {
			return null;
		}
	}

	/**
	 * Removes first instance of parameter found in LinkedList and returns it, otherwise returns null
	 * @param data information to be removed from LinkedList
	 * @return removed data or null
	 */
	public T remove(T data) {
		Node<T> listReader = head;

		while (listReader != null) {
			if (listReader.data.equals(data)) {
				if (listReader.prevNode != null) {
					listReader.prevNode.nextNode = listReader.nextNode;
				}
				if (listReader.nextNode != null) {
					listReader.nextNode.prevNode = listReader.prevNode;
				}
				return data;
			} else {
				listReader = listReader.nextNode;
			}
		}
		return null;
	}

	/**
	 * retrieves data from specified index
	 * @param index being searched
	 * @return data found at index, null if none found
	 */
	@Override
	public T get(int index) {

		if (index < 0 || index > size) {
			throw new IllegalArgumentException("The provided index would be out of bounds.");
		}

		Node<T> runner = head;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				return runner.data;
			}
			runner = runner.nextNode;
		}

		return null;
	}

	/**
	 * Checks whether data exists in list
	 * @param data to be checked
	 * @return true if found, false otherwise
	 */
	@Override
	public boolean contains(T data) {
		return false;
	}

	/**
	 * @return size of list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * local node class for maintaining the linked list
	 * @param <T>
	 */
	private static class Node<T> {

		T data;
		Node<T> nextNode; // defaults to null
		Node<T> prevNode; // defaults to null

		Node(T data) {
			this.data = data;
		}

		Node(T data, Node<T> nextNode, Node<T> prevNode) {
			this.data = data;
			this.nextNode = nextNode;
			this.prevNode = prevNode;
		}

	}


}
