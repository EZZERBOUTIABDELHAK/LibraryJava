

import org.junit.jupiter.api.Test;

import com.library.DVD;

import static org.junit.jupiter.api.Assertions.*;

class DVDTest {

    @Test
    void shouldCreateDVDWithValidValues() {
        DVD dvd = new DVD("D001", "Interstellar", 169);

        assertEquals("D001", dvd.getId());
        assertEquals("Interstellar", dvd.getTitle());
        assertEquals(169, dvd.getDurationMinutes());
    }

    @Test
    void shouldBeAvailableWhenCreated() {
        DVD dvd = new DVD("D001", "Interstellar", 169);

        assertTrue(dvd.isAvailable());
    }

    @Test
    void shouldAcceptDurationOfOneMinute() {
        DVD dvd = new DVD("D001", "Short Film", 1);

        assertEquals(1, dvd.getDurationMinutes());
    }

    @Test
    void shouldRejectZeroDuration() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DVD("D001", "Interstellar", 0)
        );
    }

    @Test
    void shouldRejectNegativeDuration() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DVD("D001", "Interstellar", -10)
        );
    }

    @Test
    void shouldHaveLoanPeriodOfSevenDays() {
        DVD dvd = new DVD("D001", "Interstellar", 169);

        assertEquals(7, dvd.loanPeriodDays());
    }

    @Test
    void shouldRejectNullId() {
        assertThrows(
            NullPointerException.class,
            () -> new DVD(null, "Interstellar", 169)
        );
    }

    @Test
    void shouldRejectBlankId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DVD("   ", "Interstellar", 169)
        );
    }

    @Test
    void shouldRejectNullTitle() {
        assertThrows(
            NullPointerException.class,
            () -> new DVD("D001", null, 169)
        );
    }

    @Test
    void shouldRejectBlankTitle() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DVD("D001", "   ", 169)
        );
    }
}
