package heaps;

import java.util.ArrayList;

public class MinHeap {
	
	int capacity;
	// Array representation of minHeap
	ArrayList<Integer> nodes;
	int currSize;

	public MinHeap(int capacity) {
		this.capacity = capacity;
		this.nodes = new ArrayList<Integer>(capacity);
	}
	
	// minHeap
	public void offer(int val) {
		if(currSize == this.capacity) {
			System.out.println("Capacity full");
			return;
		}
		nodes.add(this.currSize, val);
		int currIndex = this.currSize;
		while(currIndex!=0 && this.nodes.get(currIndex) < this.nodes.get(this.parent(currIndex))) {
			this.swap(currIndex, this.parent(currIndex));
			currIndex = this.parent(currIndex);
		}
		this.currSize++;
	}
	
	public Integer peek() {
		if (this.currSize == 0) {
			System.out.println("No elements to show");
			return -1;
		}
		return this.nodes.get(0);
	}
	
	public Integer poll() {
		if (this.currSize == 0) {
			System.out.println("No elements to show");
			return -1;
		}
		int val = this.nodes.get(0);
		// we're swapping the values of 0th and last-index, & then call heapify(0)
		if(this.nodes.size() > 1) {
			this.nodes.set(0, this.nodes.get(this.currSize - 1));
			this.nodes.remove(this.currSize - 1);
			this.currSize--;
			this.minHeapify(0);
		}
		return val;
	}
	
	public void show() {
		int index = this.currSize;
		for (int i = 0; i < (index / 2); i++) {
			System.out.print("Parent : " + this.nodes.get(i));
			if (left(i) < index)
				System.out.print(" Left : " + this.nodes.get(this.left(i)));
			if (right(i) < index)
				System.out.print(" Right :" + this.nodes.get(this.right(i)));
			System.out.println();
		}
	}
	
	private void swap(int i,int j) {
		int vali = nodes.get(i);
		int valj = nodes.get(j);
		nodes.set(i, valj);
		nodes.set(j, vali);
	}
	
	// 0 based indexing, #bubbling down
	public void minHeapify(int i) {
		int left = this.left(i);
		int right = this.right(i);
		int smallest = i;
		if (left < this.currSize && this.nodes.get(left) < this.nodes.get(smallest)) {
			smallest = left;
		}
		if (right < this.currSize && this.nodes.get(right) < this.nodes.get(smallest)) {
			smallest = right;
		}
		if (i!=smallest) {
			this.swap(i,smallest);
			this.minHeapify(smallest);
		}
	}
	
	// 0 based indexing
	public void maxHeapify(int i) {
		int left = this.left(i);
		int right = this.right(i);
		int largest = i;
		if (left < this.currSize && this.nodes.get(left) > this.nodes.get(largest)) {
			largest = left;
		}
		if (right < this.currSize && this.nodes.get(right) > this.nodes.get(largest)) {
			largest = right;
		}
		if (i!=largest) {
			this.swap(i,largest);
			this.minHeapify(largest);
		}
	}
	
	// 0 based indexing
	public void buildHeap() {
		for(int i = this.parent(this.currSize); i>=0 ;i--) {
			this.minHeapify(i);
		}
	}
	
	// 0 based indexing
	public Integer parent(int i) {
		return (i-1)/2;
	}
	
	// 0 based indexing
	public boolean isLeaf(int i) {
		if (left(i) >= this.currSize || right(i) >= this.currSize) {
			return true;
		}
		return false;
	}
	
	private Integer left(int i) {
		return (2*i+1);
	}
	
	private Integer right(int i) {
		return (2*i+2);
	}
	
	public static void main(String ...strings) {
	    MinHeap minHeap = new MinHeap(7);
	    minHeap.offer(16);
	    minHeap.offer(3);
	    minHeap.offer(13);
	    minHeap.offer(7);        
        minHeap.offer(9);
	    minHeap.offer(21);
	    minHeap.offer(12);
	    System.out.println("The Min Heap is : " + minHeap.nodes.toString());	    
	    minHeap.buildHeap();
	    System.out.println(minHeap.poll());
	    System.out.println(minHeap.poll());
	    System.out.println(minHeap.poll());
		minHeap.show();
	}
}