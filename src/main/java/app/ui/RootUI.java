package app.ui;

import app.ui.view.NoPageFound;
import app.ui.view.HomeView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Theme ("valo")
@SpringUI (path = "/")
@Title ("Simple Book Store")
@Slf4j
//@Viewport ("width=device-width, initial-scale=1.0, user-scalable=no")
//@PushStateNavigation
public class RootUI extends UI {

    private transient ApplicationContext context;

    @Autowired
    public RootUI(ApplicationContext context) {
        this.context = context;
    }

    @Override
    protected void init(VaadinRequest request) {

        setErrorHandler(event -> log.error("Error during request!",
                DefaultErrorHandler.findRelevantThrowable(event.getThrowable())));

        getUI().getNavigator().setErrorView(NoPageFound.class);

        setContent(context.getBean(RootViewAndDisplayer.class));

        getUI().getNavigator().navigateTo(HomeView.VIEW_NAME);
    }

}
