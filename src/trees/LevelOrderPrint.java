package trees;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderPrint {
	static class Node { 
	    int data; 
	    Node left; 
	    Node right; 
	  
	    Node(int data) { 
	      this.data = data; 
	      left = null; 
	      right = null; 
	    } 
	  } 
	
	private static void levelOrderWithDelim(Node root) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(root);
		// null will acts as delimiter as end of current level
		q.offer(null);
		while(!q.isEmpty()) {
			Node node = q.poll();
			// delimiter <end of current level>
			if (node == null) {	
				// still child nodes are present to process
				if (!q.isEmpty()) {
					// indicates > current level is finished
					q.add(null);
					System.out.print("\n"); 
				}
			}else {
				if(node.left != null) {
					q.offer(node.left);
				}
				if(node.right != null) {
					q.offer(node.right);
				}
				System.out.print(node.data + " ");
			}
		}
	}
	
	private static void levelOrderWithSize(Node root) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(root);
		while(!q.isEmpty()) {
			int currSizeOfLevel = q.size();
			while(currSizeOfLevel > 0) {
				Node node = q.poll();
				if(node.left != null) {
					q.offer(node.left);
				}
				if(node.right != null) {
					q.offer(node.right);
				}
				System.out.print(node.data + " ");	
				currSizeOfLevel--;
			}
			System.out.println(); 
		}
	}
	
	public static void main(String ...args) {
		Node root = new Node(1); 
	   // root.left = new Node(2); 
	    root.right = new Node(3); 
	    root.right.left = new Node(4); 
	    root.right.right = new Node(5); 
	    root.right.right.left = new Node(6); 
	  
	    levelOrderWithDelim(root); 
	    System.out.println(); 
	    levelOrderWithSize(root);
	}
}
