package app.ui.view;

import app.ui.layout.grid.BooksGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Slf4j
@UIScope
@SpringView (name = HomeView.VIEW_NAME)
public class HomeView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";
    private static final String SPLIT_PANEL_DESCRIPTION_WIDTH = "340px";
    private static final String SPLIT_PANEL_DESCRIPTION_HEIGHT = "500px";
    private BooksGrid listingGrid;

    @Autowired
    public HomeView(BooksGrid listingGrid) {
        this.listingGrid = listingGrid;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        log.debug("\nHomeView '{}', total components: {}", HomeView.VIEW_NAME, getComponentCount());

        HorizontalSplitPanel splitPanel = (HorizontalSplitPanel) getComponent(0);
        if (splitPanel.getSecondComponent() == null) {
            listingGrid.setCompTreeWidth(BooksGrid.DEFAULT_WIDTH);
            splitPanel.setSecondComponent(listingGrid);
        }

        listingGrid.clearSelection();
        listingGrid.setSelectionAllowed(false);
        listingGrid.setGridDescription(BooksGrid.DEFAULT_DESCRIPTION);
    }

    @PostConstruct
    public void init() {
        addComponent(mainContent());
        setSizeFull();
    }

    private HorizontalSplitPanel mainContent() {
        HorizontalSplitPanel horPanel = new HorizontalSplitPanel(getProjectDescription(), listingGrid);
        horPanel.setLocked(true);
        horPanel.setSplitPosition(350, Unit.PIXELS);
//        horPanel.setSplitPosition(20, Unit.PERCENTAGE);
        return horPanel;
    }

    private VerticalLayout getProjectDescription() {
        Label label = new Label("<p>This simple book store is for trying out Vaadin to " +
                "experience server-side frontend development with Java.</p>" +
                "<p>Used Spring Boot, Vaadin and Gradle. Undertow is serving the app. " +
                "H2 in-memory database to list, create, update or delete books. Vaadin " +
                "Spring Extensions can be used for further improvement.</p>",
                ContentMode.HTML
        );
        label.setWidth(SPLIT_PANEL_DESCRIPTION_WIDTH);

        VerticalLayout layout = new VerticalLayout(label);
        layout.setWidth(SPLIT_PANEL_DESCRIPTION_WIDTH);
        layout.setHeight(SPLIT_PANEL_DESCRIPTION_HEIGHT);
        layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        layout.setMargin(false);
        return layout;
    }

}
