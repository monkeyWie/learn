package hashmap;

import java.util.HashMap;

public class MyHashMap<K,V> {
	
	private Entry<K,V>[] entries = null;
	private int size = 0;
	
	public MyHashMap(){
		entries = (Entry<K,V>[])new Entry<?,?>[10];
	}
	
	public V put(K k,V v){
		int i = getIndex(k.hashCode(),entries.length);
		System.out.println("index:"+i);
		if(entries[i]==null){
			entries[i] = new Entry<K, V>(k,v,null,k.hashCode());
		}else{
			entries[i] = new Entry<K, V>(k,v,entries[i],k.hashCode());
		}
		size++;
		return v;
	}
	
	public V get(K k){
		int i = getIndex(k.hashCode(),entries.length);
		for (Entry<K, V> entry = entries[i]; entry!=null; entry = entry.next) {
			if(k==entry.getKey()||k.equals(entry.getKey())){
				return entry.getValue();
			}
		}
		return null;
	}
	
	private int getIndex(int hash,int length){
		return hash%length;
	}
	
	static class Entry<K,V>{
		K key;
		V value;
		Entry<K,V> next;
		int hash;
		
		public Entry(K key, V value, Entry<K, V> next, int hash) {
			this.key = key;
			this.value = value;
			this.next = next;
			this.hash = hash;
		}
		
		public K getKey() {
			return key;
		}


		public void setKey(K key) {
			this.key = key;
		}


		public V getValue() {
			return value;
		}


		public void setValue(V value) {
			this.value = value;
		}


		public Entry<K, V> getNext() {
			return next;
		}


		public void setNext(Entry<K, V> next) {
			this.next = next;
		}


		public int getHash() {
			return hash;
		}


		public void setHash(int hash) {
			this.hash = hash;
		}

	}
	
	public static void main(String[] args) {
		MyHashMap<String,String> map = new MyHashMap<String, String>();
		map.put("aa","123");
		map.put("b","234");
		map.put("c","123");
		map.put("d","123");
		map.put("e","123");
		map.put("f","123");
		map.put("g","123");
		map.put("h","123");
		map.put("i","123");
		map.put("j","123");
		map.put("k","123");
		map.put("l","123");
		System.out.println(map.get("aa"));
		System.out.println(map.get("b"));
		System.out.println(map.get("c"));
		System.out.println(map.get("l"));
		System.out.println(5139%10);
	}
}
