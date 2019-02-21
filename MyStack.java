import java.util.EmptyStackException;

public class MyStack<E> implements StackInterface<E> {
	
	/******************************
	// private inner class for node
	******************************/
	private class Node {
	  private E    data; // Entry in ADT
	  private Node next; // Link to next node

		private Node(E dataPortion)
		{
			this(dataPortion, null);	
		} 
		
		private Node(E dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;	
		}

		private E getData() {
			return data;
		}

		private void setData(E newData){
			data = newData;
		}

		private Node getNextNode(){
			return next;
		}

		private void setNextNode(Node nextNode){
			next = nextNode;
		}
	}

	private Node head;
	private int stackSize;

	private static final int DEFAULT_SIZE = 50;
	private static final int MAX_CAPACITY = 10000;

	public MyStack() {
		// constructor
		head = null;
	}

	@Override
   /** Adds a new entry to the top of this stack.
       @param newEntry  An object to be added to the stack. */
   public void push(E newEntry){

   		Node newNode = new Node(newEntry);

   		if (head == null){
   			newNode.setNextNode(null);
   			head = newNode;
   			stackSize++;
   		} else {
   			Node palette = new Node(head.getData(), head.getNextNode());
   			newNode.setNextNode(palette);
   			head = newNode;
   			stackSize++;

   		}
   }
  
   /** Removes and returns this stack's top entry.
       @return  The object at the top of the stack. 
       @throws  EmptyStackException if the stack is empty before the operation. */
   public E pop() {

   		E returnData = head.getData();
   		Node newHead = head.getNextNode();
   		head.setNextNode(null);
   		head = newHead;
   		stackSize = stackSize - 1;

   		return returnData;
   }
  
   /** Retrieves this stack's top entry.
       @return  The object at the top of the stack.
       @throws  EmptyStackException if the stack is empty. */
   public E peek(){
   		if (head == null){
   			throw new EmptyStackException();
   		}
   		return head.getData();
   }
  
   /** Detects whether this stack is empty.
       @return  True if the stack is empty. */
   public boolean isEmpty(){
   		if (stackSize > 0){
   			return false;
   		}

   		return true;
   }
  
   /** Removes all entries from this stack. */
   public void clear(){
   		// recursively pop all stack entries
   		if (stackSize > 0){
   			pop();
   			clear();
   		}
   }


}