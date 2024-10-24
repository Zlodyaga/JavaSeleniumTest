package org.example.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    public HomePage() {}

    @Step("Перейти на https://www.amazon.com/")
    public void open() {
        Selenide.open("https://www.amazon.com/");
    }

    @Step("Встановити фільтр Book")
    public void selectDropDown(String choose) {
        SelenideElement dropdownBook = $(By.cssSelector("[aria-describedby='searchDropdownDescription']"));
        dropdownBook.selectOptionContainingText(choose);
    }

    @Step("Ввести пошукове слово Java")
    public void enterSearch(String text) {
        SelenideElement searchBox = $(By.id("twotabsearchtextbox"));
        searchBox.setValue(text).pressEnter();
    }
}
