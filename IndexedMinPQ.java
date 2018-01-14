
public class IndexedMinPQ<Key extends Comparable>{
	private int V;
	private Key keys[];
	private int pq[];
	private int size = 0;
	private int qp[];
	public IndexedMinPQ(int V){
		this.V = V;
		pq = new int[V];
		qp = new int[V];
		keys = (Key[])(new Comparable[V]);
		// intialize the qp to -1 so that contains becomes easier operation
		for(int i=0; i<V;i++){
			qp[i] = -1;
		}
		
	}
	public void put(int i, Key k){
		keys[i] = k;
		qp[i] = size;
		pq[size] = i;
		size++;
		
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public boolean contains(int i){
		return qp[i] != -1;
	}
	public int delMin(){
		int min = pq[0];
		swap(0,size-1);
		size--;
		sink(0);
		return min;
	}
	public void decreaseKey(int i,Key k){
		keys[i] = k;
		swim(qp[i]);
	}
	private void swap(int i, int j){
		int temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	private void swim(int k){
		while(less(k,k/2) == true){
			swap(k,k/2);
		}
	}
	private boolean less(int i, int j){
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}
	private void sink(int k){
		while(true){
			int min = k;
			if(2*k + 1 < size && less(2*k+1,min)){
				min = 2*k + 1;
			}
			if(2*k + 2 < size && less(2*k + 1, min)){
				min = 2*k + 1;
			}
			if(min != k){
				swap(min,k);
			}else{
				break;
			}
		}
	}
	
}
