import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    Library library;
    Book book;
    DVD dvd;
    Member member;

    @BeforeEach
    void setUp() {
        library = new Library();
        book = new Book("B1", "Java Programming", "Author X", new ISBN("1234567890123"));
        dvd = new DVD("D1", "Inception", 148);
        member = new Member("M1", "Abdel Hak", 2);
    }

    @Test
    void testInitialLibraryIsEmpty() {
        assertTrue(library.getItems().isEmpty());
        assertTrue(library.getMembers().isEmpty());
        assertTrue(library.getActiveLoans().isEmpty());
    }

    @Test
    void testAddItemSuccess() {
        library.addItem(book);
        assertEquals(1, library.getItems().size());
        assertEquals(book, library.getItems().get(0));
    }

    @Test
    void testAddItemNullThrowsException() {
        assertThrows(NullPointerException.class, () -> library.addItem(null));
    }

    @Test
    void testAddDuplicateItemThrowsException() {
        library.addItem(book);
        Book duplicateBook = new Book("B1", "Another Book", "Author Y", new ISBN("9876543210987"));
        
        assertThrows(IllegalArgumentException.class, () -> library.addItem(duplicateBook));
    }

    @Test
    void testRegisterMemberSuccess() {
        library.registerMember(member);
        assertEquals(1, library.getMembers().size());
        assertEquals(member, library.getMembers().get(0));
    }

    @Test
    void testRegisterMemberNullThrowsException() {
        assertThrows(NullPointerException.class, () -> library.registerMember(null));
    }

    @Test
    void testRegisterDuplicateMemberThrowsException() {
        library.registerMember(member);
        Member duplicateMember = new Member("M1", "Someone Else", 1);
        
        assertThrows(IllegalArgumentException.class, () -> library.registerMember(duplicateMember));
    }

    @Test
    void testLendSuccess() {
        library.addItem(book);
        library.registerMember(member);

        Loan loan = library.lend("M1", "B1");

        assertNotNull(loan);
        assertFalse(book.isAvailable());
        assertEquals(1, library.getActiveLoans().size());
        assertEquals(book, loan.item());
        assertEquals(member, loan.member());
        assertEquals(LocalDate.now(), loan.borrowedOn());
        assertEquals(LocalDate.now().plusDays(21), loan.dueDate()); // Book loan period is 21 days
    }

    @Test
    void testLendNullIdsThrowsException() {
        assertThrows(NullPointerException.class, () -> library.lend(null, "B1"));
        assertThrows(NullPointerException.class, () -> library.lend("M1", null));
    }

    @Test
    void testLendUnknownMemberOrItemThrowsException() {
        library.addItem(book);
        library.registerMember(member);

        // Unknown member ID
        assertThrows(IllegalArgumentException.class, () -> library.lend("UNKNOWN", "B1"));
        
        // Unknown item ID
        assertThrows(IllegalArgumentException.class, () -> library.lend("M1", "UNKNOWN"));
    }

    @Test
    void testLendUnavailableItemThrowsIllegalState() {
        library.addItem(book);
        library.registerMember(member);

        library.lend("M1", "B1"); // First borrow succeeds

        // Second borrow of the same item should fail
        assertThrows(IllegalStateException.class, () -> library.lend("M1", "B1"));
    }

    @Test
    void testLendExceedingMaxLoansThrowsIllegalState() {
        // Member has maxLoans = 1
        Member restrictedMember = new Member("M2", "Fares", 1);
        Book book2 = new Book("B2", "Advanced Java", "Author Z", new ISBN("1111222233334"));
        
        library.addItem(book);
        library.addItem(book2);
        library.registerMember(restrictedMember);

        library.lend("M2", "B1"); // First loan (reaches limit of 1)

        // Second loan should fail because maxLoans is reached
        assertThrows(IllegalStateException.class, () -> library.lend("M2", "B2"));
    }

    @Test
    void testReceiveSuccess() {
        library.addItem(book);
        library.registerMember(member);

        Loan loan = library.lend("M1", "B1");
        assertEquals(1, library.getActiveLoans().size());
        assertFalse(book.isAvailable());

        library.receive(loan);

        assertTrue(library.getActiveLoans().isEmpty());
        assertTrue(book.isAvailable());
    }

    @Test
    void testReceiveNullOrInactiveLoanThrowsException() {
        assertThrows(NullPointerException.class, () -> library.receive(null));

        library.addItem(book);
        library.registerMember(member);
        Loan foreignLoan = new Loan(book, member, LocalDate.now(), LocalDate.now().plusDays(5));

        // Trying to return a loan that was never added to activeLoans
        assertThrows(IllegalArgumentException.class, () -> library.receive(foreignLoan));
    }
}