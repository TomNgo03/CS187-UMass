package app;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void insertAtBeginning(T elem) throws NullPointerException {
      //TODO: Implement this method.
      Node<T> node = new Node<T>(elem, null);
      if (elem == null){
        throw new NullPointerException();
      }
      if (head == null){
        head = node;
      }
      else {
        node.setNext(head);
        head = node;
      }
      size ++;
  }

  @Override
  public void insertAtEnd(T elem) throws NullPointerException {
      //TODO: Implement this method.
      Node<T> node = new Node<T>(elem, null);
      if (elem == null){
        throw new NullPointerException();
      }
      if (head == null){
        head = node;
      }
      else {
        Node<T> lastNode = nodeHelper(0, size -1, head);
        lastNode.setNext(node);
      }
      size ++;
  }

  @Override
  public void insertAt(int index, T elem) throws IndexOutOfBoundsException{
      //TODO: Implement this method.
      Node<T> node = new Node<T>(elem, null);
      Node<T> sucNode = nodeHelper(0, index, head);
      if (elem == null){
        throw new NullPointerException();
      }
      if (index < 0 || index > size){
        throw new IndexOutOfBoundsException();
      }
      if (index == 0){
        insertAtBeginning(elem);
      }
      else if (index == size){
        insertAtEnd(elem);
      }
      else {
        Node<T> prevNode = nodeHelper(0, index-1, head);
        prevNode.setNext(node);
        node.setNext(sucNode);
        size++;
      }

  }

  private Node<T> nodeHelper(int i, int index, Node<T> node){
    if (i == index){
      return node;
    }
    else if (i >= size){
      return null;
    }
    else {
      i++;
      return nodeHelper(i, index, node.getNext());
    }
  }


  @Override
  public T removeFirst() throws IllegalStateException {
    T removedItem = null;
      //TODO: Implement this method.
      if (isEmpty()){
        throw new IllegalStateException();
      }
      else {
        removedItem = head.getData();
        head = head.getNext();
        size--;
      }
      return removedItem;
  }

  @Override
  public T removeLast() throws IllegalStateException {
    T removedItem = null;
      //TODO: Implement this method.
    if (isEmpty()){
      throw new IllegalStateException();
    }
    if (size == 1){
      removedItem = removeFirst();
    }
    else {
      Node<T> prevTail = nodeHelper(0, size - 2, head);
      removedItem = nodeHelper(0, size - 1, head).getData();
      prevTail.setNext(null);
      size--;
    }
    return removedItem;

  }

  @Override
  public T removeAt(int i) throws IndexOutOfBoundsException {
    T removedItem = null;
      //TODO: Implement this method.
      removedItem = getAt(i);
      Node<T> node = nodeHelper(0, i, head);
      if (i < 0 || i >= size){
        throw new IndexOutOfBoundsException();
      }
      else if (node == head){
        removedItem = removeFirst();
      }
      else if (node.getNext() == null) {
        removedItem = removeLast();
      }
      else {
        Node<T> prevNode = nodeHelper(0, i - 1, head);
        Node<T> sucNode = nodeHelper(0, i + 1, head);
        prevNode.setNext(sucNode);
        size--;
      }

    return removedItem;
  }

  @Override
  public T getFirst() throws IllegalStateException {
    T item = null;
      //TODO: Implement this method.
      if (isEmpty()){
        throw new IllegalStateException();
      }
      else {
        item = head.getData();
      }

    return item;
  }

  @Override
  public T getLast() throws IllegalStateException {
    T item = null;
      //TODO: Implement this method.
      if (isEmpty()){
        throw new IllegalStateException();
      }
      else {
        item = getHelper(0, size - 1, head);
      }

    return item;
  }

  @Override
  public T getAt(int i) throws IndexOutOfBoundsException {
    T item = null;
      //TODO: Implement this method.
      if (i < 0 || i >= size){
        throw new IndexOutOfBoundsException();
      }
      else {
        item = getHelper(0, i, head);
      }

    return item;
  }

  private T getHelper(int i, int index, Node<T> node){
    if (i == index){
      return node.getData();
    }
    i++;
    return getHelper(i, index, node.getNext());
  }

  @Override
  public void removeElement(T elem) throws ItemNotFoundException {
      //TODO: Implement this method.
      int k = indexOf(elem);

      if (elem == null){
        throw new NullPointerException();
      }
      if (k == -1){
        throw new ItemNotFoundException();
      }
      removeAt(k);

  }

  @Override
  public int indexOf(T elem) throws NullPointerException {
    int index = 0;
      //TODO: Implement this method.
      if (elem == null){
        throw new NullPointerException();
      }
      return indexOfHelper(elem, index, head);
  }

  private int indexOfHelper(T elem, int index, Node<T> node){
    if (elem == node.getData()){
      return index;
    }
    else if (index == size -1) {
      return -1;
    }
    else {
      index++;
      return indexOfHelper(elem, index, node.getNext());
    }
  }


  @Override
  public boolean isEmpty() {
    boolean empty = false;
      //TODO: Implement this method.
      if (head == null){
        empty = true;
      }

    return empty;
  }


  public Iterator<T> iterator() {
    Iterator<T> iter = null;
      //TODO: Implement this method.

      iter = new LinkedNodeIterator<T>(head);

   return iter;
  }
}
