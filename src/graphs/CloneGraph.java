package graphs;

import java.util.*;

// cloneGraphDFS
// cloneGraphBFS
public class CloneGraph {
    HashMap<Integer, Node> map = new HashMap<>();

    public Node cloneGraphBFS(Node node) {
        if (node == null) {
            return null;
        }

        Queue<Node> que = new LinkedList<>();

        que.offer(node);
        map.put(node.val, new Node(node.val));

        while(!que.isEmpty()) {
            Node par = que.poll();

            for(Node neighbour: par.neighbors) {
                if (map.containsKey(neighbour.val)) {
                    // add neighbour to parent adjList
                    map.get(par.val).neighbors.add(map.get(neighbour.val));
                } else {
                    que.offer(neighbour);
                    map.put(neighbour.val, new Node(neighbour.val));
                }
            }
        }

        return map.get(node.val);
    }

    public Node cloneGraphDFS(Node src) {
        if (src == null) {
            return null;
        }

        map.put(src.val, new Node(src.val));

        for(Node neighbour: src.neighbors) {
            if (map.containsKey(neighbour.val)) {
                // add neighbour to parent adjList
                map.get(src.val).neighbors.add(map.get(neighbour));
            } else {
                // do dfs again with neighbour
                map.put(neighbour.val, cloneGraphDFS(neighbour));
            }
        }
        return map.get(src.val);
    }

}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}