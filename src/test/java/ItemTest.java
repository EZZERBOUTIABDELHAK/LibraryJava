
import org.junit.jupiter.api.Test;

import com.library.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    // Concrete implementation used only for testing Item.
    private static class TestItem extends Item {

        public TestItem(String id, String title) {
            super(id, title);
        }

        @Override
        public int loanPeriodDays() {
            return 14;
        }
    }

    @Test
    void constructor_shouldInitializeFieldsCorrectly() {
        Item item = new TestItem("B01", "Clean Code");

        assertEquals("B01", item.getId());
        assertEquals("Clean Code", item.getTitle());
        assertTrue(item.isAvailable());
        assertTrue(item.canBeBorrowed());
    }

    @Test
    void constructor_shouldRejectNullId() {
        assertThrows(
            NullPointerException.class,
            () -> new TestItem(null, "Clean Code")
        );
    }

    @Test
    void constructor_shouldRejectNullTitle() {
        assertThrows(
            NullPointerException.class,
            () -> new TestItem("B01", null)
        );
    }

    @Test
    void constructor_shouldRejectBlankId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new TestItem("   ", "Clean Code")
        );
    }

    @Test
    void constructor_shouldRejectBlankTitle() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new TestItem("B01", "   ")
        );
    }

    @Test
    void markBorrowed_shouldMakeItemUnavailable() {
        Item item = new TestItem("B01", "Clean Code");

        item.markBorrowed();

        assertFalse(item.isAvailable());
        assertFalse(item.canBeBorrowed());
    }

    @Test
    void markBorrowed_shouldThrowWhenAlreadyBorrowed() {
        Item item = new TestItem("B01", "Clean Code");

        item.markBorrowed();

        assertThrows(
            IllegalStateException.class,
            item::markBorrowed
        );
    }

    @Test
    void markReturned_shouldMakeItemAvailableAgain() {
        Item item = new TestItem("B01", "Clean Code");
        item.markBorrowed();

        item.markReturned();

        assertTrue(item.isAvailable());
        assertTrue(item.canBeBorrowed());
    }

    @Test
    void markReturned_shouldThrowWhenItemWasNotBorrowed() {
        Item item = new TestItem("B01", "Clean Code");

        assertThrows(
            IllegalStateException.class,
            item::markReturned
        );
    }

    @Test
    void loanPeriodDays_shouldReturnPositiveValue() {
        Item item = new TestItem("B01", "Clean Code");

        assertTrue(item.loanPeriodDays() > 0);
    }
}
