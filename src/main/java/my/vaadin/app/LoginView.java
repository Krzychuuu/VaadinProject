package my.vaadin.app;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
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

    private final TextField login;

    private final PasswordField password;

    private final Button loginButton;

    private final Button registerButton;
    private User user;
    private FormLayout formLayout;
    private FieldGroup binder;
    private BeanItem<User> item;
    private Notification errorNotification;
    private Button submit;
    public LoginView() {
        setSizeFull();
        user = new User();
        item = new BeanItem<>(user);
        binder = new BeanFieldGroup<>(User.class);
        binder.setItemDataSource(item);
        binder.setBuffered(true);
        formLayout.setMargin(true);
        login = binder.buildAndBind("login", "name", TextField.class);
        password = binder.buildAndBind("password", "pass", PasswordField.class);
        loginButton = new Button("loginButton", this);
        registerButton = new Button("Register", this);
        formLayout.addComponents(login, password, submit, registerButton);       
            
        setCompositionRoot(formLayout);
    }
        
    @Override
    public void enter(ViewChangeEvent event) {

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
//    		if (!user.isValid() || !password.isValid()) {
//	            return;
//	        }
//	
//	        String username = user.getValue();
//	        String password = this.password.getValue();
//
//	        
//	        		        boolean isValid = username.equals("test1")
//	                && password.equals("test2");
//	        if (isValid) {
    		try {
                binder.commit();

                //TODO: remove it to validation class after db integration
                if(!user.getName().equals("test1") && !user.getPass().equals("test1")){
                    return;
                }
                
            } catch (FieldGroup.CommitException e) {
                e.printStackTrace();
            }
	            getSession().setAttribute("user", login.getValue());
	            getUI().getNavigator().navigateTo(LoggedView.NAME);
	
//	        } else {
	
//	            // Wrong password clear the password field and refocuses it
//	            this.password.setValue(null);
//	            this.password.focus();
//	        }
    	}
    	else if (event.getButton() == registerButton){
    		getUI().getNavigator().navigateTo("RegisterView");
    	}
    }
}