package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.pages.HomePage;
import org.example.pages.ProductSearchedPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    private HomePage homePage;
    private ProductSearchedPage productSearchedPage;

    @BeforeEach
    public void setUp() {
        homePage = new HomePage();
        productSearchedPage = new ProductSearchedPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
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
            String priceWhole = productSearchedPage.getpriceWhole(book);
            String priceFraction = productSearchedPage.getpriceFraction(book);
            boolean isBestSeller = book.getText().contains("Best Seller");

            Book newBook = new Book(title, author, priceWhole, priceFraction, isBestSeller);
            bookDetails.add(newBook);
        }

        Book bookToFind = new Book("Head First Java: A Brain-Friendly Guide", "by Kathy Sierra, Bert Bates, et al.", "16", "97", true);

        // Шаг 5: Проверить наличие книги 'Head First Java'
        boolean found = bookDetails.stream()
                .anyMatch(book -> book.equals(bookToFind));

        // Assertions для проверки теста
        assertFalse(bookDetails.isEmpty(), "Список книг не должен быть пустым.");
        assertTrue(found, "Книга 'Head First Java: A Brain-Friendly Guide' должна быть найдена.");
    }
}
