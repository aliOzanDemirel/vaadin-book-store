package app.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@UIScope
@SpringView
public class NoPageFound extends VerticalLayout implements View {

    @PostConstruct
    void init() {
        Label label = new Label("Page could not be found!");
        label.addStyleName(ValoTheme.LABEL_FAILURE);
        addComponent(label);
        setComponentAlignment(label, Alignment.MIDDLE_CENTER);
    }
}
