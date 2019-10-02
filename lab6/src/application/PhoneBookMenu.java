package application;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import phonebook.MapPhoneBook;
import phonebook.PhoneBook;

public class PhoneBookMenu extends MenuBar {
	private PhoneBook phoneBook;
	private NameListView nameListView;

	/**
	 * Creates the menu for the phone book application.
	 * 
	 * @param phoneBook
	 *            the phone book with names and numbers
	 * @param nameListView
	 *            handles the list view for the names
	 */
	public PhoneBookMenu(PhoneBook phoneBook, NameListView nameListView) {
		this.phoneBook = phoneBook;
		this.nameListView = nameListView;

		final Menu menuPhoneBook = new Menu("PhoneBook");
		final MenuItem menuQuit = new MenuItem("Quit");
		menuQuit.setOnAction(e -> Platform.exit());
		menuPhoneBook.getItems().addAll(menuQuit);

		final Menu menuFind = new Menu("Find");

		final MenuItem menuShowAll = new MenuItem("Show All");
		final MenuItem menuFindNumbers = new MenuItem("Find Numbers");
		final MenuItem menuFindNames = new MenuItem("Find Names");
		final MenuItem menuFindPerson = new MenuItem("Find Persons");
		menuFindNumbers.setOnAction(e -> findNumbers());
		menuFindNames.setOnAction(e -> findNames());
		menuFindPerson.setOnAction(e -> findPerson());
		menuShowAll.setOnAction(e -> showAll());
		menuFind.getItems().addAll(menuShowAll, menuFindNumbers, menuFindNames, menuFindPerson);

		getMenus().addAll(menuPhoneBook, menuFind);
		// setUseSystemMenuBar(true); // if you want operating system rendered
		// menus, uncomment this line
	}

	private void showAll() {
		nameListView.fillList(phoneBook.names());
		nameListView.clearSelection();
	}

	private void findNumbers() {
		PhoneBook tempPhoneBook = new MapPhoneBook();
		String tempNbr;
		Optional<String> name = Dialogs.oneInputDialog(" ", " ", "Enter the name for the number you wanna find ");
		// System.out.println(name.get().toString());
		if (name.isPresent()) {
			Iterator<String> itr = phoneBook.findNumbers(name.get().toString()).iterator();
			while (itr.hasNext()) {
				tempNbr = itr.next();
				tempPhoneBook.put(name.get(), tempNbr);
			}
			if (tempPhoneBook.isEmpty()) {
				Dialogs.alert("", "", "there are no such names ");
			} else {
				nameListView.fillList(tempPhoneBook.names());
				nameListView.select(name.get());
			}
		}
	}

	private void findNames() {
		PhoneBook tempPhoneBook = new MapPhoneBook();
		String tempName;
		Optional<String> number = Dialogs.oneInputDialog(" ", " ", "Enter the number of the name you wanna find ");
		if (number.isPresent()) {
			Iterator<String> itr = phoneBook.findNames(number.get().toString()).iterator();
			while (itr.hasNext()) {
				tempName = itr.next().toString();
				tempPhoneBook.put(tempName, number.get());
				System.out.println(tempName);
			}
			if (tempPhoneBook.isEmpty()) {
				Dialogs.alert("", "", "there are no such names ");
			} else {
				nameListView.fillList(tempPhoneBook.names());
			}
		}
	}

	private void findPerson() {
		PhoneBook tempPhoneBook = new MapPhoneBook();
		Optional<String> names = Dialogs.oneInputDialog(" ", " ", "Enter the name of the person you wanna find ");
		if (names.isPresent()) {
			Set<String> pb = phoneBook.names();
			Iterator<String> itr = pb.iterator();
			while (itr.hasNext()) {
				String tempName = itr.next().toString();
				if (tempName.contains(names.get().toString())) { // what???
					Set<String> numbers = phoneBook.findNumbers(tempName);
					Iterator<String> nbrItr = numbers.iterator();
					while (nbrItr.hasNext()) {
						tempPhoneBook.put(tempName, nbrItr.next());
					}
				}
			}
			if (tempPhoneBook.isEmpty()) {
				Dialogs.alert("", "", "there are no such names ");
			} else {
				nameListView.fillList(tempPhoneBook.names());
			}
		}

	}

}
