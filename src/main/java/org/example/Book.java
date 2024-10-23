package org.example;

public class Book {
    private String title;
    private String author;
    private String priceWhole;
    private String priceFraction;
    private boolean isBestSeller;

    // Конструктор
    public Book(String title, String author, String priceWhole, String priceFraction, boolean isBestSeller) {
        this.title = title;
        this.author = formatAuthors(author); // Форматируем автора при создании объекта
        this.priceWhole = priceWhole;
        this.priceFraction = priceFraction;
        this.isBestSeller = isBestSeller;
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
    public String getPriceWhole() {
        return priceWhole;
    }

    // Сеттер для цілої частини ціни
    public void setPriceWhole(String priceWhole) {
        this.priceWhole = priceWhole;
    }

    // Геттер для дробової частини ціни
    public String getPriceFraction() {
        return priceFraction;
    }

    // Сеттер для дробової частини ціни
    public void setPriceFraction(String priceFraction) {
        this.priceFraction = priceFraction;
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
        return priceWhole + "." + priceFraction;
    }

    // Метод для форматування авторов
    private String formatAuthors(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Возвращаем исходную строку, если она пустая или null
        }

        // Добавляем пробел после "by", если он отсутствует
        String formatted = input.replaceAll("by(\\S)", "by $1");

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
