
import org.junit.jupiter.api.Test;

import com.library.*;

import static org.junit.jupiter.api.Assertions.*;

class ISBNTest {

    @Test
    void shouldCreateISBNWithValidValue() {
        ISBN isbn = new ISBN("9780306406157");

        assertEquals("9780306406157", isbn.value());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> new ISBN(null)
        );
    }

    @Test
    void shouldRejectValueWith12Digits() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ISBN("978030640615")
        );
    }

    @Test
    void shouldRejectValueWith14Digits() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ISBN("97803064061578")
        );
    }

    @Test
    void shouldRejectValueContainingLetters() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ISBN("978030640615A")
        );
    }

    @Test
    void shouldRejectValueContainingHyphens() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ISBN("978-0306406157")
        );
    }

    @Test
    void shouldRejectValueContainingSpaces() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ISBN("978 030640615")
        );
    }

    @Test
    void shouldConsiderTwoISBNsWithSameValueEqual() {
        ISBN first = new ISBN("9780306406157");
        ISBN second = new ISBN("9780306406157");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldConsiderTwoISBNsWithDifferentValuesUnequal() {
        ISBN first = new ISBN("9780306406157");
        ISBN second = new ISBN("9780306406158");

        assertNotEquals(first, second);
    }
}
