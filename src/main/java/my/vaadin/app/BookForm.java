package my.vaadin.app;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;

public class BookForm extends BookFormDesign {

	BookService service = BookService.getInstance();
	private Book book;
	private MyUI myUI;

	public BookForm(MyUI myUI) {
		this.myUI = myUI;
		save.setClickShortcut(KeyCode.ENTER);
		save.addClickListener(e -> this.save());
		delete.addClickListener(e -> this.delete());
	}

	public void setBook(Book book) {
		this.book = book;
		BeanFieldGroup.bindFieldsUnbuffered(book, this);

		delete.setVisible(book.isPersisted());
		setVisible(true);
		title.selectAll();
	}

	private void delete() {
		service.delete(book);
		 Broadcaster.broadcast("");
		setVisible(false);
	}

	private void save() {
		service.save(book);
		 Broadcaster.broadcast("");
		setVisible(false);
	}
}
