package mayrevision;

class Entry<K, V> {
	final K key;
	V value;
	Entry<K, V> next;

	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
}

public class CustomHashMap<K, V> {

	private static final int DEFAULT_CAPACITY = 16;
	private Entry<K, V>[] table;

	// Array List of Entry objects
	@SuppressWarnings("unchecked")
	public CustomHashMap() {
		table = new Entry[DEFAULT_CAPACITY];
	}

	private int getHash(K key) {
		return (key == null) ? 0 : Math.abs(key.hashCode()) % table.length;
	}

	public void put(K key, V value) {
		int hash = getHash(key);
		Entry<K, V> newEntry = new Entry<>(key, value);

		// If no entry exists at the hash index, place the new entry there
		if (table[hash] == null) {
			table[hash] = newEntry;
		} else {
			Entry<K, V> current = table[hash];
			while (current != null) {
				if (current.key.equals(key)) {
					current.value = value; // Update existing key
					return;
				}
				if (current.next == null) {
					current.next = newEntry; // Add new entry at the end of the chain
					return;
				}
				current = current.next;
			}
		}

		// Add new node at the beginning of the chain
		table[hash] = newEntry;
	}

	// Method to retrieve value based on key
	public V get(K key) {
		int hash = getHash(key);
		Entry<K, V> current = table[hash];
		while (current != null) {
			if (current.key.equals(key)) {
				return current.value; // Return value if key is found
			}
			current = current.next; // Move to the next entry in the chain
		}
		return null;

	}

	public void remove(K key) {
		int hash = getHash(key);
		Entry<K, V> current = table[hash];
		Entry<K, V> previous = null;

		while (current != null) {
			if (current.key.equals(key)) {
				if (previous == null) {
					table[hash] = current.next; // Remove the first entry in the chain
				} else {
					previous.next = current.next; // Bypass the current
				}
			}
		}
	}

	public static void main(String[] args) {
		CustomHashMap<String, Integer> map = new CustomHashMap<>();
		map.put("One", 1);
		map.put("Two", 2);
		map.put("Three", 3);

		System.out.println(map.get("Two")); // Output: 2
		map.remove("Two");
		System.out.println(map.get("Two")); // Output: null
	}
}