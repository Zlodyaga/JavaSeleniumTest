package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductSearchedPage {

    public ProductSearchedPage() {
    }

    public ElementsCollection getProducts() {
        return $$(By.xpath(".//div[@data-component-type='s-search-result']"))
                .shouldHave(CollectionCondition.sizeGreaterThan(15));
    }

    public String getTitle(SelenideElement product) {
        return product.$(By.cssSelector(".a-size-medium.a-color-base.a-text-normal")).getText();
    }

    public ElementsCollection getPricesKindle(SelenideElement product) { //На жаль, не витягує ціну.
        return product.$$(By.xpath(".//div[@class='a-row a-spacing-mini']//div[@class='a-row']" +
                "//a[@class='a-link-normal s-no-hover s-underline-text s-underline-link-text s-link-style a-text-normal']" +
                "//span[@class='a-price']/span[@class='a-offscreen']"));
    }

    public String getAuthorName(SelenideElement product) {
        // Отримати текст з елементів
        StringBuilder authorsText = new StringBuilder();

        // Отримуємо всі елементи
        ElementsCollection authorAll = product.$$(By.xpath(
                ".//div[@class='a-row']//span[@class='a-size-base' and not(contains(@class, 'a-color-secondary'))] " +
                        "| .//div[@class='a-row']//a[@class='a-size-base a-link-normal s-underline-text s-underline-link-text s-link-style' and not(contains(@class, 'a-text-bold'))]"
        ));

        for (SelenideElement authorElement : authorAll) {
            authorsText.append(authorElement.getText().trim());
        }

        return authorsText.toString().trim();
    }
}
