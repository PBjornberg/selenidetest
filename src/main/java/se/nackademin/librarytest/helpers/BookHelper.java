/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;

import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditBookPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 * @author testautomatisering
 */
public class BookHelper {

    public static void addNewBook(Book book) {

    }

    public static Book fetchBook(String searchQuery) {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(searchQuery);
        browseBooksPage.clickSearchBooksButton();
        browseBooksPage.clickFirstResultTitle();

        BookPage bookPage = page(BookPage.class);
        Book book = new Book();
        book.setTitle(bookPage.getTitle());
        book.setAuthor(bookPage.getAuthor());
        book.setDescription(bookPage.getDescription());
        book.setDatePublished(bookPage.getDatePublished());
        return book;
    }
    
    /**
     * Updates book with given title. 
     * Parameters with non-null values will be updated in database
     * 
     * @param searchTitle The book title to search for
     * @param newTitle The new title for this book
     * @param description
     * @param numberOfPages
     * @param isbn
     * @param numberInInventory
     * @param datePublished
     */
    public static void editBook(String searchTitle,
            String newTitle,
            String description, 
            String numberOfPages, 
            String isbn,
            String numberInInventory,
            String datePublished) {
        
        
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(searchTitle);
        browseBooksPage.clickSearchBooksButton();
        browseBooksPage.clickFirstResultTitle();

        BookPage bookPage = page(BookPage.class);
        bookPage.clickEditBookButton();
        
        EditBookPage editBookPage = page(EditBookPage.class);
        
        if (newTitle!=null) { editBookPage.setTitle(newTitle); }
        if (description!=null) { editBookPage.setDescription(description); }        
        if (datePublished!=null) { editBookPage.setDatePublished(datePublished); } 
        // Other properties omitted for simplicity
        
        editBookPage.clickSaveBookButton();        
        
    }
}
