package app.ui.layout.header;

import app.common.Utils;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
public class Header extends HorizontalLayout {

    private MenuButtons menuButtons;

    @Autowired
    public Header(MenuButtons menuButtons) {
        this.menuButtons = menuButtons;
    }

    @PostConstruct
    public void init() {

        addStyleNames(ValoTheme.LAYOUT_COMPONENT_GROUP);

        HorizontalLayout headerTitle = Utils.horizontalLabel("Simple Book Tracking", "40",
                ValoTheme.LABEL_BOLD, ValoTheme.LABEL_LARGE);

        setMargin(new MarginInfo(false, true));

        addComponentsAndExpand(headerTitle, menuButtons);

        setExpandRatio(headerTitle, 7.0f);
        setExpandRatio(menuButtons, 2.0f);
    }

}
