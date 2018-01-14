

public class TST<Value> {
	private static final int R = 26;
	private Node root = new Node();
	private static class Node{
		Object v;
		Node next[] = new Node[R];
	}
	public void put(String key,Value v){
		root = put_wrap(root,key.toLowerCase(),v,0);
	}
	public Node put_wrap(Node x, String key, Value v, int d){
		
		if(x == null){
			x = new Node();
		}
		if(d == key.length()){
			x.v = v;
			return x;
		}
		char c = key.charAt(d);
		x.next[c - 'a'] = put_wrap(x.next[c - 'a'],key,v,d+1);
		return x;
	}
	
	
	public Value get(String key){
		Node x = get(root,key.toLowerCase(),0);
		if(x == null){
			return null;
		}
		return (Value)x.v;
	}
	public Node get(Node x, String key, int d){
		if(x == null){
			return null;
		}
		if(d == key.length()){
			return x;
		}
		char c = key.charAt(d);
		return get(x.next[c - 'a'],key,d+1);
	}

	
}
