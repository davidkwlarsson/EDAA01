package phonebook;


import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapPhoneBook implements PhoneBook, Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, HashSet<String>> phoneBook;

	public MapPhoneBook() {
		phoneBook = new HashMap<String, HashSet<String>>();

	}

	@Override
	public boolean put(String name, String number) {
		if (!phoneBook.containsKey(name)) {
			HashSet<String> numberList = new HashSet<String>();
			numberList.add(number);
			phoneBook.put(name, numberList);
		} else {
			HashSet<String> numbers = phoneBook.get(name);
			if (numbers.contains(number)) {
				return false;
			} else {
				numbers.add(number);
			}
		}
		return true;
	}

	@Override
	public boolean remove(String name) {
		HashSet<String> remove = phoneBook.remove(name);
		if (remove == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeNumber(String name, String number) {
		if (phoneBook.containsKey(name)) {
			return phoneBook.get(name).remove(number);
		}
		return false;
	}

	@Override
	public Set<String> findNumbers(String name) {
		if (phoneBook.containsKey(name)) {
			return new HashSet<String>(phoneBook.get(name));
		}
		return new HashSet<String>();
	}

	@Override
	public Set<String> findNames(String number) {
		HashSet<String> persons = new HashSet<String>();
		Set<String> names = names();

		Iterator<String> itr = names.iterator();
		while (itr.hasNext()) {
			String currentName = itr.next();
			Set<String> numbers = findNumbers(currentName);
			if (numbers != null && numbers.contains(number)) {
				persons.add(currentName);
			}

		}
		return persons;

	}

	@Override
	public Set<String> names() {
		Set<String> names = new TreeSet<String>(phoneBook.keySet());
		return names;
	}

	@Override
	public int size() {

		return phoneBook.size();
	}



}
