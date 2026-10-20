

import org.junit.jupiter.api.Test;

import com.library.Magazine;

import static org.junit.jupiter.api.Assertions.*;

class MagazineTest {

    @Test
    void shouldCreateMagazineWithValidValues() {
        Magazine magazine = new Magazine("M001", "Science Weekly", 12);

        assertEquals("M001", magazine.getId());
        assertEquals("Science Weekly", magazine.getTitle());
        assertEquals(12, magazine.getIssueNumber());
    }

    @Test
    void shouldBeAvailableWhenCreated() {
        Magazine magazine = new Magazine("M001", "Science Weekly", 12);

        assertTrue(magazine.isAvailable());
    }

    @Test
    void shouldRejectZeroIssueNumber() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Magazine("M001", "Science Weekly", 0)
        );
    }

    @Test
    void shouldRejectNegativeIssueNumber() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Magazine("M001", "Science Weekly", -1)
        );
    }

    @Test
    void shouldAcceptIssueNumberOne() {
        Magazine magazine = new Magazine("M001", "Science Weekly", 1);

        assertEquals(1, magazine.getIssueNumber());
    }

    @Test
    void shouldHaveLoanPeriodOfThreeDays() {
        Magazine magazine = new Magazine("M001", "Science Weekly", 12);

        assertEquals(3, magazine.loanPeriodDays());
    }

    @Test
    void shouldRejectNullId() {
        assertThrows(
            NullPointerException.class,
            () -> new Magazine(null, "Science Weekly", 12)
        );
    }

    @Test
    void shouldRejectBlankId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Magazine("   ", "Science Weekly", 12)
        );
    }

    @Test
    void shouldRejectNullTitle() {
        assertThrows(
            NullPointerException.class,
            () -> new Magazine("M001", null, 12)
        );
    }

    @Test
    void shouldRejectBlankTitle() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Magazine("M001", "   ", 12)
        );
    }
}
