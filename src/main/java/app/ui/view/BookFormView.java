package app.ui.view;

import app.domain.Book;
import app.domain.form.BookFormFields;
import app.service.BookService;
import app.ui.layout.grid.BooksGrid;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@SpringView (name = BookFormView.VIEW_NAME)
public class BookFormView extends HorizontalLayout implements View {

    public static final String VIEW_NAME = "/book";
    private static final String BOOK_GRID_WIDTH_WITH_FORM = "840px";

    private Binder<Book> binder = new Binder<>(Book.class);
    private transient BookFormFields fields = new BookFormFields();
    private Button refreshButton = new Button("Refresh");
    private Button deleteButton = new Button("Delete");
    private Button addNewButton = new Button("Add New Book");

    private BooksGrid booksGrid;
    private transient BookService bookService;

    @Autowired
    public BookFormView(BooksGrid booksGrid, BookService bookService) {
        this.booksGrid = booksGrid;
        this.bookService = bookService;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        log.debug("\nBookFormView '{}', total components: {}", BookFormView.VIEW_NAME, getComponentCount());

        booksGrid.setSelectionAllowed(true);
        booksGrid.setGridDescription("Select a row to edit");
    }

    @PostConstruct
    public void init() {

        setSizeFull();

        binder.bindInstanceFields(fields);
        FormLayout formLayout = new FormLayout(fields.getOrderedFields());
        formLayout.setWidthUndefined();

        VerticalLayout listingGridWithButtons = prepareListingGrid();
        addComponents(listingGridWithButtons, formLayout);

        setExpandRatio(listingGridWithButtons, 2);
        setExpandRatio(formLayout, 1);

        fields.getSave().addClickListener(e -> validateBook(fields.bookRepresentation()).ifPresent(book -> {
            bookService.save(book);
            refreshButtonAction(e);
            Notification.show("Saved " + book.getName(), Notification.Type.HUMANIZED_MESSAGE);
        }));

        fields.getCancel().addClickListener(e -> booksGrid.clearSelection());

        booksGrid.getSelected().ifPresent(this::bindBookToForm);
        booksGrid.addValueChangeListener(valueChange -> bindBookToForm(valueChange.getValue()));
    }

    private VerticalLayout prepareListingGrid() {
        booksGrid.setMargin(false);
        booksGrid.setCompTreeWidth(BOOK_GRID_WIDTH_WITH_FORM);

        return new VerticalLayout(getButtons(), booksGrid);
    }

    private HorizontalLayout getButtons() {
        deleteButton.addClickListener(this::deleteButtonAction);
        refreshButton.addClickListener(this::refreshButtonAction);
        addNewButton.addClickListener(this::addNewButtonAction);

        HorizontalLayout leftMostButtons = new HorizontalLayout(deleteButton, addNewButton);

        HorizontalLayout layout = new HorizontalLayout(leftMostButtons, refreshButton);
        layout.setWidth(BOOK_GRID_WIDTH_WITH_FORM);
        layout.setComponentAlignment(leftMostButtons, Alignment.TOP_LEFT);
        layout.setComponentAlignment(refreshButton, Alignment.TOP_RIGHT);

        layout.setMargin(false);
        return layout;
    }

    private void deleteButtonAction(Button.ClickEvent event) {
        booksGrid.getSelected().ifPresent(book -> {
            bookService.deleteBook(book);
            refreshButtonAction(event);
            Notification.show("Deleted " + book.getName(), Notification.Type.HUMANIZED_MESSAGE);
        });
    }

    private void refreshButtonAction(Button.ClickEvent event) {
        booksGrid.refreshGrid(true);
    }

    private void addNewButtonAction(Button.ClickEvent event) {
        booksGrid.clearSelection();
        fields.getId().setValue("");
    }

    public void bindBookToForm(Book book) {
        binder.setBean(book);
        fields.getBookName().selectAll();
    }

    private Optional<Book> validateBook(Book book) {
        if (StringUtils.isEmpty(book.getName())) {
            warnUser("Book name cannot be empty!");
            return Optional.empty();
        } else if (StringUtils.isEmpty(book.getAuthor())) {
            warnUser("Author name should be provided!");
            return Optional.empty();
        } else {
            return Optional.of(book);
        }
    }

    private void warnUser(String message) {
        log.warn(message);
        Notification.show(message, Notification.Type.WARNING_MESSAGE);
    }

}