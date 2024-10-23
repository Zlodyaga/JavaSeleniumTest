package org.example.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    public HomePage() {}

    public void open() {
        Selenide.open("https://www.amazon.com/");
    }

    public void selectDropDown(String choose) {
        SelenideElement dropdownBook = $(By.cssSelector("[aria-describedby='searchDropdownDescription']"));
        dropdownBook.selectOptionContainingText(choose);
    }

    public void enterSearch(String text) {
        SelenideElement searchBox = $(By.id("twotabsearchtextbox"));
        searchBox.setValue(text).pressEnter();
    }
}
