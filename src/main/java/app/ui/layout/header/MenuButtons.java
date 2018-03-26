package app.ui.layout.header;

import app.ui.view.BookFormView;
import app.ui.view.HomeView;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@SpringComponent
public class MenuButtons extends CssLayout {

    @PostConstruct
    public void init() {

        addStyleNames(ValoTheme.LAYOUT_COMPONENT_GROUP);

        addComponent(createNavigationButton("Home View", HomeView.VIEW_NAME));
        addComponent(createNavigationButton("Form View", BookFormView.VIEW_NAME));
        addComponent(createNavigationButton("Nowhere", "nowhere"));

    }

    private Button createNavigationButton(String caption, String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }
}
