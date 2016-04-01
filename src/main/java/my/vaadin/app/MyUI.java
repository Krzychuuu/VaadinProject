package my.vaadin.app;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@Widgetset("my.vaadin.app.MyAppWidgetset")
public class MyUI extends UI {

	private Button logout;
	private VerticalLayout layout;
	private BookService service = BookService.getInstance();
	private Grid grid = new Grid();
	private TextField filterText = new TextField();
	BookForm form = new BookForm(this);

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		layout = new VerticalLayout();
		// layout.setSizeUndefined();
		layout.setWidth("100%");
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		// MAIN
		BeanItemContainer<User> users = new BeanItemContainer<User>(User.class);

		logout = new Button("logout", (Button.ClickListener) (clickEvent) -> {
			getCurrentSession().removeAttribute("user");
			setContent(null);
			getUI().addWindow(new LoginPanelWindow());
		});

		layout.addComponent(logout);
		filterText.setInputPrompt("test");
		filterText.addTextChangeListener(e -> {
			grid.setContainerDataSource(new BeanItemContainer<>(Book.class, service.findAll(e.getText())));
		});

		Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
		clearFilterTextBtn.setDescription("Clear the current filter");
		clearFilterTextBtn.addClickListener(e -> {
			filterText.clear();
			updateList();
		});

		CssLayout filtering = new CssLayout();
		filtering.addComponents(filterText, clearFilterTextBtn);
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		Button addBookBtn = new Button("Add a book");
		addBookBtn.addClickListener(e -> {
			grid.select(null);
			form.setBook(new Book());
		});

		HorizontalLayout toolbar = new HorizontalLayout(filtering, addBookBtn);
		toolbar.setSpacing(true);

		grid.setColumns("title", "author");

		HorizontalLayout main = new HorizontalLayout(grid, form);
		main.setSpacing(true);
		main.setSizeFull();
		grid.setSizeFull();
		main.setExpandRatio(grid, 1);

		layout.addComponents(toolbar, main);

		updateList();

		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);

		form.setVisible(false);

		grid.addSelectionListener(event -> {
			if (event.getSelected().isEmpty()) {
				form.setVisible(false);
			} else {
				Book book = (Book) event.getSelected().iterator().next();
				form.setBook(book);
			}
		});

		if (!isRegistered()) {
			LoginPanelWindow registerPanelWindow = new LoginPanelWindow();
			getUI().addWindow(registerPanelWindow);
			setContent(null);
		} else if (!isLoggedIn()) {

			LoginPanelWindow loginPanelWindow = new LoginPanelWindow();
			getUI().addWindow(loginPanelWindow);
			setContent(null);
		} else {
			setContent(layout);
		}
	}

	public static boolean isRegistered() {
		WrappedSession currentSession = VaadinService.getCurrentRequest().getWrappedSession();
		return currentSession.getAttribute("register") != null;
	}

	public static boolean isLoggedIn() {
		WrappedSession currentSession = VaadinService.getCurrentRequest().getWrappedSession();
		return currentSession.getAttribute("user") != null;
	}

	public static WrappedSession getCurrentSession() {
		return VaadinService.getCurrentRequest().getWrappedSession();
	}

	public void updateList() {
		List<Book> books = service.findAll(filterText.getValue());
		grid.setContainerDataSource(new BeanItemContainer<>(Book.class, books));
	}

	public void setMainContent() {
		setContent(layout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
	public static class MyUIServlet extends VaadinServlet {
	}

}
