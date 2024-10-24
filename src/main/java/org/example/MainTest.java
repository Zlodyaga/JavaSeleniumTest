package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.pages.HomePage;
import org.example.pages.ProductSearchedPage;
import org.example.pages.TestBookPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    private HomePage homePage;
    private ProductSearchedPage productSearchedPage;
    private TestBookPage testBookPage;

    @BeforeEach
    public void setUp() {
        // Открыть браузер Chrome
        Configuration.browser = "firefox";
        Configuration.headless = false;

        homePage = new HomePage();
        productSearchedPage = new ProductSearchedPage();
        testBookPage = new TestBookPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    public void testCorrectAlways() {
        assertTrue(true);
    }

    @Test
    public void testSearchForJavaBooksAndCheckHeadFirstJava() {
        // Шаг 1: Перейти на Amazon
        homePage.open();

        // Шаг 2: Установить фильтр "Books"
        homePage.selectDropDown("Books");

        // Шаг 3: Ввести поисковое слово "Java"
        homePage.enterSearch("Java");

        // Шаг 4: Получить информацию с первой страницы
        ElementsCollection books = productSearchedPage.getProducts();
        List<Book> bookDetails = new ArrayList<>();

        for (SelenideElement book : books) {
            String title = productSearchedPage.getTitle(book);
            String author = productSearchedPage.getAuthorName(book);
            String priceFull = "0", priceRent = null;
            ElementsCollection pricesKindle = productSearchedPage.getPricesKindle(book);

            if(pricesKindle.size() == 2) {
                priceRent = pricesKindle.first().getText();
                priceFull = pricesKindle.last().getText();

                priceRent = priceRent.isEmpty() ? "0" : priceRent;
            } else if (pricesKindle.size() == 1) {
                priceFull = pricesKindle.first().getText();
            }
            priceFull = priceFull.isEmpty() ? "0" : priceFull;

            boolean isBestSeller = book.getText().contains("Best Seller");

            Book newBook = new Book(title, author, priceFull, priceRent, isBestSeller);
            bookDetails.add(newBook);
        }

        testBookPage.open();

        //Book bookToFind = new Book("Head First Java: A Brain-Friendly Guide", "by Kathy Sierra, Bert Bates, et al.", "16", "97", true);
        Book bookToFind = testBookPage.getBook();

        // Шаг 5: Проверить наличие книги 'Head First Java'
        boolean found = bookDetails.stream()
                .anyMatch(book -> book.equals(bookToFind));

        // Assertions для проверки теста

        assertFalse(bookDetails.isEmpty(), "Список книг не должен быть пустым.");
        assertTrue(found, "Книга 'Head First Java: A Brain-Friendly Guide' должна быть найдена.");
    }
}
