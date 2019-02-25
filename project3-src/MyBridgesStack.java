/**
	A BRIDGES API implementation of the generic stack inteface using 
	the bridges SLelement object instead of a traditional node class
   Jamel Hendricks
   02/23/2019
   CMSC 256 Spring '19
*/

import bridges.connect.Bridges;
import java.io.IOException;
import java.util.EmptyStackException;
import bridges.base.SLelement;
import bridges.validation.*;

public class MyBridgesStack<E> implements StackInterface<E> {
	
	SLelement head = new SLelement();
	private int stackSize = 0;
	private static final int MAX_CAPACITY = 1000;
	
	public MyBridgesStack() {
		head = null;
	}
	
	@Override
	public void push(E newEntry) {
		
		// check if full
		if (stackSize >= MAX_CAPACITY) {
			System.out.println("Stack is full.");
			return;
		}
		
		SLelement<E> newElement = new SLelement<E>(newEntry);
		
   		// if stack is empty create new node and assign to head
   		if (isEmpty()){
   			newElement.setNext(null);
   			head = newElement;
   			stackSize++;
   		} else {
   			// if stack not empty, replace existing head with new node
   			@SuppressWarnings("unchecked")
   			SLelement<E> palette = new SLelement<E>((E) head.getValue(), head.getNext());
   			newElement.setNext(palette);
   			head = newElement;
   			
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
			@SuppressWarnings("unchecked")
			E returnData = (E) head.getValue();
			SLelement<E> newHead = head.getNext();
			head.setNext(null);
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
			
			@SuppressWarnings("unchecked")
			E value = (E) head.getValue();
			return value;
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

   @SuppressWarnings("unchecked")
   public void display(){

  		// if stack is empty do not display
  		if (isEmpty()){
  			System.out.println("The stack is empty.");
  		} else {

  			SLelement<E> current = head;
  			
  			if (stackSize == 1){
  				System.out.println("Top & bottom of Stack: " + current.getValue());
  				return;
  			}
  			
  			// iterate through each node starting from the head
  			// displaying the data of each as the program iterates down
  			for (int i = 0; i < stackSize; i++){
  		  				
  				if (i == 0){
  					System.out.println("Top of Stack: " + current.getValue());
  				} else if (i == stackSize - 1) {
  					System.out.println("Stack bottom: " + current.getValue());
  				} else {
  					System.out.println("              " + current.getValue());   					
  				}
  				
  				current = current.getNext();
  			}
  			
  		}
  }

	public static void main(String[] args) {
	    
	   	MyBridgesStack<Integer> stack = new MyBridgesStack<Integer>();

	   	stack.push(256);
	   	stack.push(1);
	   	stack.push(9);
	   	stack.push(20);
	   	stack.push(2018);

	   		   	
	   	stack.display();
		
	   	Bridges bridges = new Bridges(0, "[YOUR BRIDGES USER ID]", "[YOUR BRIDGES API CODE]");
  		
		stack.head.setLabel( stack.head.getValue().toString());
		bridges.setDataStructure(stack.head);
		bridges.setTitle("CMSC Project 3 Jamel Hendricks");

		@SuppressWarnings("unchecked")
		SLelement<Integer> current = stack.head;
		for (int i = 0; i < stack.stackSize; i++) {
			current.setLabel( current.getValue().toString());
			current = current.getNext();
		}
		
		try {
			bridges.visualize();
		} catch (IOException | RateLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
