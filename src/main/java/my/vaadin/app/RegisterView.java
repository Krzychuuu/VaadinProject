package my.vaadin.app;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;

public class RegisterView  extends CustomComponent implements View {

	public static final String NAME = null;
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	public RegisterView(){
		setSizeFull();
		
		PropertysetItem toRegister = new PropertysetItem();
		toRegister.addItemProperty("name", new ObjectProperty<String>("test1"));
		toRegister.addItemProperty("pass", new ObjectProperty<String>("test2"));
		
		// Have some layout and create the fields
		FormLayout form = new FormLayout();

		// Build and bind the fields using the default field factory
		final FieldGroup binder = new FieldGroup(toRegister);
		form.addComponent(binder.buildAndBind("name", "name"));
		form.addComponent(binder.buildAndBind("pass", "pass"));

		// Enable buffering (actually enabled by default)
		binder.setBuffered(true);

		// A button to commit the buffer
		form.addComponent(new Button("OK", new ClickListener() {
		    @Override
		    public void buttonClick(ClickEvent event) {
		        try {
		            binder.commit();
		            Notification.show("Thanks!");
		        } catch (CommitException e) {
		            Notification.show("You fail!");
		        }
		    }
		}));
	}
}
