package trees;

// Diameter is the number of branches between 2 leaf nodes.
public class DiameterBTree {
	static class Node 
	{  
	    int data;  
	    Node left, right;  
	}
	static class A 
	{ 
	    int ans = Integer.MIN_VALUE; 
	}
	static Node newNode(int data)  
	{  
	    Node node = new Node();  
	    node.data = data;  
	    node.left = null; 
	    node.right = null;  
	  
	    return (node);  
	}
	
	public static void main(String ...args) {
		Node root = newNode(1);  
	    root.left = newNode(2);
	    root.right = newNode(3);
	     root.left.left = newNode(4);
	     root.left.right = newNode(5);
	    root.left.left.left = newNode(4);
	    root.left.left.right = newNode(5);
	    root.left.left.right.left = newNode(6);
	    root.left.left.right.left.right = newNode(7);

	    root.left.right.right = newNode(9);
	    root.left.right.right.left = newNode(10);
	    root.left.right.right.right = newNode(11);
	    root.left.right.right.right.left = newNode(12);

	    System.out.println("Diameter is " + diameter(root));  
	}
	
	static int diameter(Node root) {
		if (root == null) {
			return 0;
		}		
		A ans =new A();
		height(root, ans);
		return ans.ans;	
	}
	
	
	static int height(Node root, A ans)  
	{  
	    if (root == null)  
	        return 0;  
	  
	    int left_height = height(root.left, ans);   
	    int right_height = height(root.right, ans);  

	    // for leaf to leaf don't consider max(l,r) + node(1) > as it can has any-path max(result)  > wrong answer
		// either_path as max can give me any path max resulting in wrong answer
	    ans.ans = Math.max(ans.ans, 							// max diameter so far
				1 + left_height + right_height); 				// either via-root or

	    return 1 + Math.max(left_height, right_height);  	    // max either path height including this node
	} 
}
