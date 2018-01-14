import java.util.HashMap;

public class Best_Route {
	private TST<Integer> tst;
	private String map[];
	private HashMap<Integer,Integer> available;
	private int V;
	private int low_mark_no ;
	private int high_mark_no ;
	private WeightedDigraph G;
	private int distTo[];
	private Edge edgeTo[];
	private IndexedMinPQ<Integer> pq;
	private boolean marked[];
	public Best_Route(int V,int l, int h,String stations[]){
		// initialize the availability of each train to 0 or any suitable number
		this.low_mark_no = l;
		this.high_mark_no = h;
		this.V = V;
		tst = new TST<Integer>();
		map = new String[V];
		initialize_ds(stations);
		available = new HashMap<Integer,Integer>();
		for(int i = low_mark_no; i<= high_mark_no; i++){
			available.put(i,1);
		}
		G = new WeightedDigraph(V);
		
		
	}
	public void initialize_ds(String stations[]){
		
		for(int i = 0; i<V; i++){
			tst.put(stations[i], i);
			map[i] = stations[i];
		}
	}
	public void addTrain(int train_no, String v, String w,int time){
		G.addEdge(new Edge(train_no,tst.get(v),tst.get(w),time));
	}
	public void changeStatus(int train_no, int status){
		available.replace(train_no, status);
	}
	public int getStatus(int train_no){
		return available.get(train_no);
	}
	public void shortest_path(int s, int d){
		distTo = new int[V];
		edgeTo = new Edge[V];
		marked = new boolean[V];
		// initialize the distTo array to start with;
		for(int i=0; i<V;i++){
			distTo[i] = Integer.MAX_VALUE;
		}
		distTo[s] = 0;
		marked[s] = true;
		pq = new IndexedMinPQ<Integer>(V);
		pq.put(s,0);
		while(pq.isEmpty() == false){
			int v = pq.delMin();
			for(Edge e: G.adj(v)){
				relax(e);
			}
		}
		if(marked[d] == false){
			System.out.println("Sorry you are so late, no trains form S->D, try GENERAL COACH! ");
		}else{
			System.out.println("Total time for your journey is " + String.valueOf(distTo[d]));
			Stack<Edge> stack = new Stack<Edge>();
			for(int v = d; v != s ; v = edgeTo[v].from() ){
				stack.push(edgeTo[v]);
			}
			for(Edge e: stack){
				System.out.println(convert_ToString(e));
			}
		}
	}
	public String convert_ToString(Edge e){
		int v = e.from();
		int w = e.to();
		return map[v] + "--->" + map[w] + " via train number : " + String.valueOf(e.train_no()) ;
	}
	public void relax(Edge e){
		int v = e.from();
		int w = e.to();
		if(marked[w] == true){
			return;
		}
		if(available.get(e.train_no()) != 0 && distTo[w] > distTo[v] + e.time()){
			// update 
			distTo[w] = distTo[v]  + e.time();
			edgeTo[w] = e;
			marked[w] = true;
			if(pq.contains(w)){
				pq.decreaseKey(w,distTo[w]);
				
			}else{
				pq.put(w,distTo[w]);
			}
		}
		
	}
	
	public static void main(String a[]){
		String stations[] = {"A","B","C"};
		Best_Route r = new Best_Route(3,128,1229,stations);
		r.addTrain(1229, "A", "B", 30);
		r.addTrain(128, "B", "C", 30);
		r.addTrain(178, "A", "C", 40);
		r.changeStatus(178, 0);
		r.shortest_path(0, 2);
	}
}
