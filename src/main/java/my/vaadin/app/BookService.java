package my.vaadin.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookService {

	private static BookService instance;
	private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());

	private final HashMap<Long, Book> abouts = new HashMap<>();
	private long nextId = 0;

	private BookService() {
	}

	static BookService getInstance() {
		if (instance == null) {
			instance = new BookService();
			instance.ensureTestData();
		}
		return instance;
	}

	public synchronized List<Book> findAll() {
		return findAll(null);
	}

	public synchronized List<Book> findAll(String stringFilter) {
		ArrayList<Book> arrayList = new ArrayList<>();
		for (Book about : abouts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| about.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(about.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}
	public synchronized List<Book> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Book> arrayList = new ArrayList<>();
		for (Book about : abouts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| about.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(about.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}
	public synchronized long count() {
		return abouts.size();
	}

	public synchronized void delete(Book value) {
		abouts.remove(value.getId());
	}
	public synchronized void save(Book entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Book not found");
			return;
		}
		if (entry.getId() == null){
			entry.setId(nextId++);
		}
		try {
			entry = (Book) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		abouts.put(entry.getId(), entry);
	}

	/**
	 * Sample data generation
	 */
	public void ensureTestData() {
		if (findAll().isEmpty()) {
			final String[] names = new String[] {"Hobbit Tolkien", "Cruzoe Defoe",
					"Potter Rowling", "Cooking Amaro", "Yurem Jackson", "Kelly Gustavsson",
					"Johnnie Walker", "Guliver Nilsen" };
			Random r = new Random(0);
			for (String name : names) {
				String[] split = name.split(" ");
				Book b = new Book();
				b.setTitle(split[0]);
				b.setAuthor(split[1]);
				save(b);
			}
		}
	}

}