package org.example;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String priceFull;
    private String priceRent;
    private boolean isBestSeller;

    // Конструктор
    public Book(String title, String author, String priceFull, String priceRent, boolean isBestSeller) {
        this.title = title;
        this.author = formatAuthors(author); // Форматируем автора при создании объекта
        this.priceFull = priceFull;
        this.priceRent = priceRent;
        this.isBestSeller = isBestSeller;
    }

    // Перегрузка equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isBestSeller == book.isBestSeller &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(priceFull, book.priceFull) &&
                Objects.equals(priceRent, book.priceRent);
    }

    // Перегрузка hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(title, author, priceFull, priceRent, isBestSeller);
    }

    // Геттер для назви книги
    public String getTitle() {
        return title;
    }

    // Сеттер для назви книги
    public void setTitle(String title) {
        this.title = title;
    }

    // Геттер для автора
    public String getAuthor() {
        return author;
    }

    // Сеттер для автора
    public void setAuthor(String author) {
        this.author = formatAuthors(author);
    }

    // Геттер для цілої частини ціни
    public String getPriceFull() {
        return priceFull;
    }

    // Сеттер для цілої частини ціни
    public void setPriceFull(String priceFull) {
        this.priceFull = priceFull;
    }

    // Геттер для дробової частини ціни
    public String getPriceRent() {
        return priceRent;
    }

    // Сеттер для дробової частини ціни
    public void setPriceRent(String priceRent) {
        this.priceRent = priceRent;
    }

    // Геттер для статусу бестселера
    public boolean isBestSeller() {
        return isBestSeller;
    }

    // Сеттер для статусу бестселера
    public void setBestSeller(boolean bestSeller) {
        isBestSeller = bestSeller;
    }

    // Метод для отримання повної ціни
    public String getFullPrice() {
        if(priceRent == null || priceRent.isEmpty()) return priceFull;
        else return priceFull + " For rent:" + priceRent;
    }

    // Метод для форматування авторов
    private String formatAuthors(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Возвращаем исходную строку, если она пустая или null
        }

        // Добавляем пробел после "by", если он отсутствует
        String formatted = input.replaceAll("by(\\S)", "$1");

        // Добавляем пробел после запятых, если его нет
        formatted = formatted.replaceAll(",(\\S)", ", $1");

        return formatted;
    }

    // Перевизначений метод toString()
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + getFullPrice() +
                ", isBestSeller=" + isBestSeller +
                '}';
    }
}
