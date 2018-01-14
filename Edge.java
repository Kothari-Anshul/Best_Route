
public class Edge {
	private int v;
	private int w;
	private int train_no;
	private int time; // time in  minutes to reach from v to w;
	public Edge(int train_no, int v, int w, int time){
		this.v = v;
		this.w  = w;
		this.train_no = train_no;
		this.time = time;
	}
	public int to(){
		return w;
	}
	public int from(){
		return v;
	}
	public int time(){
		return time;
	}
	public int train_no(){
		return train_no;
	}
	public String toString(){
		return String.valueOf(v)  + " --> " + String.valueOf(w) + " via train_no. : " + String.valueOf(train_no);
	}
	
	
	
}
