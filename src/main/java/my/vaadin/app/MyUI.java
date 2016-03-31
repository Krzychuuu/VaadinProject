package my.vaadin.app;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@Widgetset("my.vaadin.app.MyAppWidgetset")
public class MyUI extends UI {
	

    private VerticalLayout layout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        layout.setSizeFull();
       

        if(!isLoggedIn()){
            LoginPanelWindow loginPanelWindow = new LoginPanelWindow();
            getUI().addWindow(loginPanelWindow);
            setContent(null);
        }else {
            setContent(layout);
        }
    }

    public static boolean isLoggedIn(){
        WrappedSession currentSession = VaadinService.getCurrentRequest().getWrappedSession();
        return currentSession.getAttribute("user") != null;
    }

    public static WrappedSession getCurrentSession(){
        return VaadinService.getCurrentRequest().getWrappedSession();
    }

    public void setMainContent(){
        setContent(layout);
    }
	
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
	public static class MyUIServlet extends VaadinServlet {
	}
}
