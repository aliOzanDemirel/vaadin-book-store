package app.domain.form;

import app.common.Awards;
import app.domain.Book;
import com.vaadin.annotations.PropertyId;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import lombok.Getter;

@Getter
public class BookFormFields {

    private TextField id = new TextField("ID");

    @PropertyId ("name")
    private TextField bookName = new TextField("Book Name");

    @PropertyId ("author")
    private TextField authorName = new TextField("Author Name");

    private TextField originalLanguage = new TextField("Original Language");
    private TextField pageSize = new TextField("Total Pages");

    private DateField publishedDate = new DateField("Published Date");
    private NativeSelect<Awards> awardWon = new NativeSelect<>("Award Won");

    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");

    public Component[] getOrderedFields() {

        id.setReadOnly(true);
        awardWon.setItems(Awards.values());
        awardWon.setEmptySelectionAllowed(true);
        save.addStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        return new Component[]{
                new HorizontalLayout(id),
                new HorizontalLayout(bookName, authorName),
                new HorizontalLayout(originalLanguage, pageSize),
                new HorizontalLayout(publishedDate, awardWon),
                new HorizontalLayout(save, cancel)
        };
    }

    public Book bookRepresentation() {
        return new Book(id.getValue(), bookName.getValue(), authorName.getValue(), originalLanguage.getValue(),
                pageSize.getValue(), publishedDate.getValue(), awardWon.getValue());
    }

}
