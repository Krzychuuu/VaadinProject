package my.vaadin.app;

import java.awt.List;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class RegisterView extends CustomComponent implements View {

    @Inject
    private UserInfo userInfo;

    
	public static final String NAME = "";
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	public RegisterView(){
	 		
		PropertysetItem toRegister = new PropertysetItem();
		toRegister.addItemProperty("name", new ObjectProperty<String>(""));
		toRegister.addItemProperty("pass", new ObjectProperty<String>(""));
		
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
		VerticalLayout viewLayout = new VerticalLayout(form);
		viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		setCompositionRoot(viewLayout);
	}
}
