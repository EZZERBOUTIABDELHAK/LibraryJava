
import org.junit.jupiter.api.Test;

import com.library.Book;
import com.library.DVD;
import com.library.ISBN;
import com.library.Item;
import com.library.Magazine;

import static org.junit.jupiter.api.Assertions.*;

class ItemLiskovTest {

    private void borrowItem(Item item) {
        assertTrue(item.isAvailable());

        item.markBorrowed();

        assertFalse(item.isAvailable());
    }

    @Test
    void bookShouldRespectItemContract() {
        Item book =new Book(
                    "B001",
                    "Clean Code",
                    "Robert C. Martin",
                    new ISBN("9780132350884")
);

        assertDoesNotThrow(() -> borrowItem(book));
    }

    @Test
    void dvdShouldRespectItemContract() {
        Item dvd = new DVD("D001", "Interstellar", 169);

        assertDoesNotThrow(() -> borrowItem(dvd));
    }

    @Test
    void magazineShouldRespectItemContract() {
        Item magazine = new Magazine("M001", "Science Weekly", 12);

        assertDoesNotThrow(() -> borrowItem(magazine));
    }
}