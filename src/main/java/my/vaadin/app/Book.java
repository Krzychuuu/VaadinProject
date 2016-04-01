package my.vaadin.app;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable, Cloneable {

	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	private String title = "";

	private String author = "";

	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof Book && obj.getClass().equals(getClass())) {
			return this.id.equals(((Book) obj).id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public Book clone() throws CloneNotSupportedException {
		return (Book) super.clone();
	}
	@Override
	public String toString() {
		return title + " " + author;
	}
}