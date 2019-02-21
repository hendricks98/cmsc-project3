import java.util.EmptyStackException;

public class MyStack<E> implements StackInterface<E> {
	
	/******************************
	// private inner class for MyNode
	******************************/
	private class MyNode {
	  private E    data; // Entry in ADT
	  private MyNode next; // Link to next MyNode

	  	// constructor for MyNode passing only a data 
		private MyNode(E dataPortion)
		{
			this(dataPortion, null);	
		} 
		
		// constructor for MyNode passing data AND reference to next node
		private MyNode(E dataPortion, MyNode nextNode)
		{
			data = dataPortion;
			next = nextNode;	
		}

		// return data portion of node
		private E getData() {
			return data;
		}

		// set the data portion of node
		private void setData(E newData){
			data = newData;
		}

		// get the next referenced node
		private MyNode getNextNode(){
			return next;
		}

		// set the reference to the next node
		private void setNextNode(MyNode nextNode){
			next = nextNode;
		}
	}

	private MyNode head;
	private int stackSize = 0;

	private static final int MAX_CAPACITY = 10000;

	public MyStack() {
		// constructor
		head = null;
	}

	@Override
   /** Adds a new entry to the top of this stack.
       @param newEntry  An object to be added to the stack. */
   public void push(E newEntry){

   		// if stack is full do not push, return
   		if (stackSize >= 10000){
   			System.out.println("Stack is full. Could not push " + newEntry);
   			return;
   		}


   		MyNode newNode = new MyNode(newEntry);

   		// if stack is empty create new node and assign to head
   		if (isEmpty()){
   			newNode.setNextNode(null);
   			head = newNode;
   			stackSize++;
   		} else {
   			// if stack not empty, replace existing head with new node
   			MyNode palette = new MyNode(head.getData(), head.getNextNode());
   			newNode.setNextNode(palette);
   			head = newNode;
   			stackSize++;

   		}
   }
  
   /** Removes and returns this stack's top entry.
       @return  The object at the top of the stack. 
       @throws  EmptyStackException if the stack is empty before the operation. */
   public E pop() {

   		// if stack is empty, do not pop
   		if (isEmpty()){
   			throw new EmptyStackException();
   		}

   		// return the data of the head and reassign the head
   		// to the next node (effectively removing the old head)
   		E returnData = head.getData();
   		MyNode newHead = head.getNextNode();
   		head.setNextNode(null);
   		head = newHead;
   		stackSize = stackSize - 1;

   		return returnData;
   }
  
   /** Retrieves this stack's top entry.
       @return  The object at the top of the stack.
       @throws  EmptyStackException if the stack is empty. */
   public E peek(){

   		// if stack is empty do not peek
   		if (isEmpty()){
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

   public void display(){

   		// if stack is empty do not display
   		if (isEmpty()){
   			System.out.println("The stack is empty.");
   		} else {

   			MyNode current = head;

   			// iterate through each node starting from the head
   			// displaying the data of each as the program iterates down
   			for (int i = 0; i < stackSize; i++){

   				if (i == 0){
   					System.out.println("Top of Stack: " + current.getData());
   				} else if (i == stackSize - 1) {
   					System.out.println("Stack bottom: " + current.getData());
   				} else {
   					System.out.println("              " + current.getData());   					
   				}
   				
   				current = current.getNextNode();
   			}
   		}
   }

   public static void main(String[] args) {
   	MyStack<Integer> stack = new MyStack<Integer>();

   	stack.push(256);
   	stack.push(1);
   	stack.push(9);
   	stack.push(20);
   	stack.push(2018);
   	System.out.println("Pushed 256, 1, 9, 20, 2018");

   	stack.display();
   	System.out.println("");


   	stack.pop();
   	stack.pop();
   	System.out.println("Called pop twice:");

   	stack.display();
   	System.out.println("");

   	System.out.println("A call to peek returns " + stack.peek());

   	stack.display();
   	System.out.println("");

   	stack.pop();
   	stack.pop();
   	stack.pop();
   	System.out.println("Called pop three times:");

   	stack.display();
   	System.out.println("");

   	System.out.println("Call stack isEmpty(): " + stack.isEmpty());
   	System.out.println("");

   	stack.push(256);
   	stack.push(1);
   	stack.push(9);
   	stack.push(20);
   	stack.push(2018);
   	System.out.println("Pushed 256, 1, 9, 20, 2018");
   	System.out.println("");

   	stack.clear();
   	System.out.println("Called clear():");
   	stack.display();
   }


}