package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.pages.HomePage;
import org.example.pages.ProductSearchedPage;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HomePage homePage = new HomePage();
        // Крок 1: Перейти на Amazon
        homePage.open();

        // Крок 2: Встановити фільтр Book
        homePage.selectDropDown("Books");

        // Крок 3: Ввести пошукове слово Java
        homePage.enterSearch("Java");

        ProductSearchedPage productSearchedPage = new ProductSearchedPage();

        // Крок 4: Зберегти інформацію з першої сторінки
        ElementsCollection books = productSearchedPage.getProducts();
        List<Book> bookDetails = new ArrayList<>();

        for (SelenideElement book : books) {
            String title = productSearchedPage.getTitle(book);
            String author = productSearchedPage.getAuthorName(book);
            String priceWhole = productSearchedPage.getpriceWhole(book);
            String priceFraction = productSearchedPage.getpriceFraction(book);
            boolean isBestSeller = book.getText().contains("Best Seller");

            Book newBook = new Book(title, author, priceWhole, priceFraction, isBestSeller);
            bookDetails.add(newBook);
        }

        // Крок 5: Перевірити наявність книги 'Head First Java'
        boolean found = bookDetails.stream()
                .anyMatch(book -> book.getTitle().equals("Head First Java: A Brain-Friendly Guide"));

        System.out.println("Book details:");
        bookDetails.forEach(System.out::println);
        System.out.println("Found 'Head First Java': " + found);
    }
}
