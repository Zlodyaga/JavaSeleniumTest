package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.Book;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TestBookPage {
    public TestBookPage() {
    }

    public void open() {
        Selenide.open("https://www.amazon.com/Head-First-Java-Brain-Friendly-Guide/dp/1491910771");
    }

    public Book getBook() { //
        String title = $(By.xpath(".//span[@id='productTitle']")).getText();
        ElementsCollection authorCollection = $$(By.xpath(".//div[@class='a-section a-spacing-micro bylineHidden feature']//span[@class='author notFaded']//a[@class='a-link-normal']"));
        String author = getFromCollectionAuthor(authorCollection);
        String[] prices = $(By.xpath(".//a[@id='a-autoid-0-announce']//span[@class='slot-price']//span[@class='a-size-base a-color-secondary']")).getText().split(" - ");
        String priceRent = prices[0].trim();
        String priceFull = prices[1].trim();
        boolean isBestSeller = $(By.xpath(".//div[@id='centerAttributesLeftColumn']")).getText().contains("Best Seller");

        return new Book(title, author, priceFull, priceRent, isBestSeller);
    }

    private String getFromCollectionAuthor(ElementsCollection authorAll) {
        StringBuilder authorsText = new StringBuilder();
        int countElements = authorAll.size(), i = 1;
        for (SelenideElement authorElement : authorAll) {
            authorsText.append(authorElement.getText().trim());
            if(i != countElements) {authorsText.append(",");}
            i++;
        }

        return authorsText.toString().trim();
    }
}
