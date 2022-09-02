package graphs;

// T(logN)
public class DisjointSetUnionFind {
    int N;
    int parent[];
    int rank[];

    public DisjointSetUnionFind(int N) {
        this.N = N;
        this.parent = new int[N];
        this.rank = new int[N];         // {...0...}

        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    // T(logN)
    public int find(int u) {
        if (parent[u] == u) {
            return u;
        }

        // find parent and also compression path of nodes coming in from u ---> par(u): p
        return parent[u] = find(parent[u]);
    }

    // T(logN)
    public void union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u != v) {
            if (rank[u] < rank[v]) {
                // swap(u,v) ==> assuming (v --> u), u have higher rank than v
                int t = u;
                u = v ;
                v = t;
            }

            // v --> u
            parent[v] = u;

            if (rank[u] == rank[v]) {
                rank[u]++;
            }
        }
    }
}
