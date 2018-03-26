package app.common;

import com.vaadin.ui.*;

public class Utils {

    private Utils() {
    }

    public static HorizontalLayout horizontalLabel(String labelStr, String verticalHeight, String... labelStyles) {
        Label label = new Label(labelStr);
        label.addStyleNames(labelStyles);

        HorizontalLayout layout = new HorizontalLayout(label);
        layout.setHeight(verticalHeight);
        layout.setMargin(false);

        return layout;
    }

}
