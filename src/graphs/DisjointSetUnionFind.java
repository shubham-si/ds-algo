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
        int pu = find(u);
        int pv = find(v);

        if(pu != pv) {
            if (rank[pu] < rank[pv]) {
                // swap(pu,pv) ==> assuming (pv --> pu), pu has higher rank than pv
                int t = pu;
                pu = pv ;
                pv = t;
            }

            // v --> u
            parent[pv] = pu;

            if (rank[pu] == rank[pv]) {
                rank[pu]++;
            }
        }
    }
}
