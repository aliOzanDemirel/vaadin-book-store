package app.ui.layout.grid;

import app.domain.Book;
import app.service.BookService;
import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@SpringComponent
@UIScope
@Slf4j
public class BooksGrid extends VerticalLayout {

    public static final String DEFAULT_DESCRIPTION = "All Books In Store";
    public static final String DEFAULT_WIDTH = "1050px";
    private Grid<Book> bookGrid = new Grid<>(Book.class);
    private transient BookService bookService;
    private boolean gridIsEmpty = true;

    @Autowired
    public BooksGrid(BookService bookService) {
        this.bookService = bookService;
    }

    @PostConstruct
    public void init() {

        setCompTreeWidth(DEFAULT_WIDTH);
        setGridDescription(DEFAULT_DESCRIPTION);

        bookGrid.setColumns("id", "name", "author", "originalLanguage", "pageSize", "publishedDate", "awardWon");
        bookGrid.getColumns().forEach(it -> it.setExpandRatio(1));
//        bookGrid.setHeaderVisible(true);
//        bookGrid.setFrozenColumnCount(1);

        addComponent(bookGrid);
        refreshGrid(false);
    }

    public void addValueChangeListener(HasValue.ValueChangeListener<Book> listener) {
        bookGrid.asSingleSelect().addValueChangeListener(listener);
    }

    public void clearSelection() {
        bookGrid.asSingleSelect().clear();
        log.debug("Cleared grid selection");
    }

    public void setCompTreeWidth(String cssWidth) {
        setWidth(cssWidth);
        bookGrid.setWidth(cssWidth);
    }

    public void refreshGrid(boolean forceReload) {
        if (gridIsEmpty || forceReload) {
            List<Book> books = bookService.getAllBooks();
            log.debug("Retrieved {} books from db", books.size());

//            ListDataProvider<Book> dataProvider = DataProvider.ofCollection(books);
//            dataProvider.setSortOrder(Book::getId, SortDirection.ASCENDING);
//            bookGrid.setDataProvider(dataProvider);

            bookGrid.setItems(books);
            bookGrid.select(books.get(0));
            gridIsEmpty = false;
        }
    }

    public Optional<Book> getSelected() {
        return bookGrid.getSelectedItems().stream().findFirst();
    }

    public void setSelectionAllowed(boolean allowed) {
        bookGrid.getSelectionModel().setUserSelectionAllowed(allowed);
    }

    public void setGridDescription(String desc) {
        bookGrid.setDescription(desc);
    }

}
