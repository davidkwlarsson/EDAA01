package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import phonebook.MapPhoneBook;
import phonebook.PhoneBook;

public class PhoneBookApplication extends Application {
	private PhoneBook phoneBook;
	private NameListView nameListView;
	private File selectedFile;

	/**
	 * The entry point for the Java program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// launch() do the following:
		// - creates an instance of the Main class
		// - calls Main.init()
		// - create and start the javaFX application thread
		// - waits for the javaFX application to finish (close all windows)
		// the javaFX application thread do:
		// - calls Main.start(Stage s)
		// - runs the event handling loop
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		phoneBook = new MapPhoneBook();
		if (Dialogs.confirmDialog("", "Hello", "Do you wanna use the saved catalog?")) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
			selectedFile = fileChooser.showOpenDialog(primaryStage);

			if (selectedFile.isFile()) {
				read(selectedFile);
			}

		}

		// set default locale English
		Locale.setDefault(Locale.ENGLISH);
		BorderPane root = new BorderPane();
		nameListView = new NameListView(phoneBook);
		root.setTop(new PhoneBookMenu(phoneBook, nameListView));
		root.setCenter(nameListView);
		Scene scene = new Scene(root);
		primaryStage.setTitle("PhoneBook");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void stop() {

		boolean choice = Dialogs.confirmDialog("", "", "Do you wanna save over the old one?");
		if (choice) {
			if (selectedFile.isFile()) {
				if (Dialogs.confirmDialog("", "Hello", "Do you wanna save?")) {
					write(selectedFile);
				}
			}
		}
		if (!choice) {

			if (Dialogs.confirmDialog("", "Hello", "Do you create a new file?")) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
				selectedFile = fileChooser.showSaveDialog(null);

				if (selectedFile.isFile()) {
					write(selectedFile);
				}
			}
		}

		// Here you can place any action to be done when the application is
		// closed, i.e. save phone book to file.

	}

	private void write(File file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(phoneBook);
			out.close();
		} catch (EOFException e) {

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void read(File file) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			phoneBook = (MapPhoneBook) in.readObject();
			in.close();
		} catch (EOFException e) {

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
