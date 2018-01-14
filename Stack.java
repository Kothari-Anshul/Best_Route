import java.util.Iterator;

public class Stack<Value> implements Iterable<Value>{
	private int maxsize;
	private int size;
	private Value a[];
	public Stack(){
		maxsize = 1;
		size = 0;
		a = (Value[])new Object[maxsize];
	}
	public void push(Value v){
		if(size == maxsize){
			resize(2*maxsize);
		}
		a[size++] = v;
		
	}
	public Value pop(){
		Value v = a[--size];
		if(size == (maxsize/4)){
			resize(maxsize/2);
		}
		
		return v;
	}
	public void resize(int capacity){
		Value b[] = (Value[])new Object[capacity];
		for(int i=0; i<maxsize; i++){
			b[i] = a[i];
		}
		a = b;
		maxsize = capacity;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	@Override
	public Iterator<Value> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Value>{
		private int x = size;
		@Override
		
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return x != 0;
		}

		@Override
		public Value next() {
			// TODO Auto-generated method stub
			Value v = a[--x];
			return v;
		}
		
	}
	public int maxsize(){
		return maxsize;
	}
	
}
