package project.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = 611069276690962829L;
    private final LibraryMember libraryMember;
    private final List<CheckoutEntry> checkoutEntries = new ArrayList<>();

    public CheckoutRecord(LibraryMember libraryMember, CheckoutEntry checkoutEntry){
        this.libraryMember = libraryMember;
        this.checkoutEntries.add(checkoutEntry);
    }

    public List<CheckoutEntry> getCheckoutEntry() {
        return checkoutEntries;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

}
