package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private static final double loadfactor = 0.75;
	private Entry<K, V>[] map;
	private int capacity;
	private int size;
	private boolean rehashing;

	public SimpleHashMap() {
		this.capacity = 16;
		map = (Entry<K, V>[]) new Entry[capacity];

	}

	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		map = (Entry<K, V>[]) new Entry[capacity];
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		private Entry(K key, V value) {
			this.key = key;
			this.value = value;

		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}

		public String toString() {
			return key + " = " + value;
		}

	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			sb.append(i + "\t");
			if (map[i] != null) {
				Entry<K, V> e = map[i];
				while (e != null) {
					sb.append(e.toString() + "\t");
					e = e.next;
				}
			} else {
				sb.append("empty");
			}
			sb.append("\n");
		}
		return sb.toString();

	}

	@Override
	public V get(Object obj) {
		K key = (K) obj;
		Entry<K, V> temp = find(index(key), key);
		if (temp != null) {
			return temp.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);
		Entry<K, V> e = find(index, key);
		if (e != null) {
			return e.setValue(value);
		} else {
			Entry<K, V> newE = new Entry<K, V>(key, value);
			if (map[index] != null) {
				newE.next = map[index];
				map[index] = newE;
			} else {
				map[index] = newE;
			}
			if (!rehashing) {
				size++;
			}
			if (overload()) {
				rehash();
			}
			return null;
		}
	}

	@Override
	public V remove(Object arg0) {
		if (!isEmpty()) {
			K key = (K) arg0;
			int index = index(key);
			Entry<K, V> remove = find(index, key);
			if (remove != null) {
				Entry<K, V> e = map[index];
				if (e.key.equals(key)) {
					map[index] = e.next;
					size--;
					return e.value;
				}
				while (e.next != null) {
					if (e.next.key.equals(key)) {
						Entry<K, V> temp = e.next;
						e.next = e.next.next;
						size--;
						return temp.value;
					}
					e = e.next;
				}
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	private int index(K key) {
		int index = key.hashCode() % capacity;
		index = Math.abs(index);
		return index;
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> e = map[index];
		while (e != null) {
			if (e.key.equals(key)) {
				return e;
			}
			e = e.next;
		}
		return null;
	}

	private void rehash() {
		rehashing = true;
		capacity = capacity * 2;
		Entry<K, V>[] old = map;
		map = (Entry<K, V>[]) new Entry[capacity];
		for (int i = 0; i < old.length; i++) {
			Entry<K, V> first = old[i];
			while (first != null) {
				put(first.key, first.value);
				first = first.next;
			}
		}
		rehashing = false;
	}

	private boolean overload() {
		return ((double) size > (loadfactor * capacity));
	}

}
