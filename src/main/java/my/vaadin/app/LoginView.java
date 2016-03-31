package my.vaadin.app;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class LoginView extends CustomComponent implements View,
        Button.ClickListener {
	
    @Inject
    private UserInfo userInfo;
    
    public static final String NAME = "login";

    private final TextField user;

    private final PasswordField password;

    private final Button loginButton;

    private final Button registerButton;

    public LoginView() {
        setSizeFull();
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInvalidAllowed(false);

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.setRequired(true);
        password.addValidator(new PasswordValidator());
        password.setValue("");
        password.setNullRepresentation("");

        loginButton = new Button("Login", this);
        registerButton = new Button("Register", this);
        

        VerticalLayout fields = new VerticalLayout(user, password, loginButton, registerButton);
        fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        user.focus();
    }

    // Validator for validating the passwords
    private static final class PasswordValidator extends AbstractValidator<String> {

        public PasswordValidator() {
            super("password invalid");
        }

        @Override
        protected boolean isValidValue(String value) {
            if (value != null && value.length() < 5) {
                return false;
            }
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    public void buttonClick(ClickEvent event) {
    	if (event.getButton() == loginButton){
    		if (!user.isValid() || !password.isValid()) {
	            return;
	        }
	
	        String username = user.getValue();
	        String password = this.password.getValue();

	        
	        		        boolean isValid = username.equals("test1")
	                && password.equals("test2");
	        if (isValid) {
	
	            getSession().setAttribute("user", username);
	            getUI().getNavigator().navigateTo(LoggedView.NAME);
	
	        } else {
	
	            // Wrong password clear the password field and refocuses it
	            this.password.setValue(null);
	            this.password.focus();
	        }
    	}
    	else if (event.getButton() == registerButton){
    		getUI().getNavigator().navigateTo("RegisterView");
    	}
    }
}