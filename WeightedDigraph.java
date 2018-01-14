import java.util.HashSet;

public class WeightedDigraph {
	private int V;
	private HashSet<Edge>[] adj;
	public WeightedDigraph(int V){
		this.V = V;
		adj = (HashSet<Edge>[])new HashSet[V];
		for(int i=0; i<V; i++){
			adj[i] = new HashSet<Edge>();
		}
	}
	public void addEdge(Edge e){
		int v = e.from();
		adj[v].add(e);
	}
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
}
