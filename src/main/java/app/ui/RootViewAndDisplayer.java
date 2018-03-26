package app.ui;

import app.common.Utils;
import app.ui.layout.header.Header;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringViewDisplay
@UIScope
public class RootViewAndDisplayer extends VerticalLayout implements ViewDisplay {

//    private SpringViewProvider viewProvider;
//    private SpringNavigator navigator;

    private Panel mainContentPanel;
    private Header header;

    @Autowired
    public RootViewAndDisplayer(Header header) {
        this.header = header;
    }

    @PostConstruct
    public void init() {

        setSizeFull();

        addComponent(header);
        setExpandRatio(header, 4.0f);

        mainContentPanel = new Panel();
        mainContentPanel.setSizeFull();
        addComponent(mainContentPanel);
        setExpandRatio(mainContentPanel, 50.0f);

        createFooter();
    }

    private void createFooter() {
        HorizontalLayout footer = Utils.horizontalLabel("Ali Ozan Demirel @2018 - Vaadin with Valo Theme",
                "10", ValoTheme.LABEL_TINY);

        addComponent(footer);
        setComponentAlignment(footer, Alignment.MIDDLE_CENTER);
        setExpandRatio(footer, 1.0f);
    }

    /**
     * called when navigateTo is performed by navigator
     */
    @Override
    public void showView(View view) {
        mainContentPanel.setContent((Component) view);
    }
}
