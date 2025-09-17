package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {
    WebDriver driver;

    @FindBy(className = "app_logo")
    WebElement sauceDemoTitle;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(className = "product_sort_container")
    WebElement filterDropdown;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean sauceDemoTitleIsDisplayed() {
        if(sauceDemoTitle.isDisplayed()){
            return true;
        }
        return false;
    }

    public boolean productIsDisplayed(String expectedProductName){
        String actualProductName = "";
        for (WebElement element: productNames){
            actualProductName = element.getText();
            if(actualProductName.equalsIgnoreCase(expectedProductName)){
                return true;
            }
        }
        return false;
    }

    public void addProductToCart(String productName){
        String productId = "add-to-cart-"+productName.replace(" ", "-").toLowerCase();
        WebElement addToCartButton = driver.findElement(By.id(productId));
        addToCartButton.click();
    }

    public void clickOnCartIcon(){
        cartIcon.click();
    }

    public void removeProductToCart(String productName){
        String productId = "remove-"+productName.replace(" ", "-").toLowerCase();
        WebElement removeToCartButton = driver.findElement(By.id(productId));
        removeToCartButton.click();
    }

    public void selectFilterOption(String filterText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(filterDropdown));

        Select filterSelect = new Select(filterDropdown);

        switch (filterText) {
            case "Price (high to low)":
                filterSelect.selectByValue("hilo");
                break;
            case "Price (low to high)":
                filterSelect.selectByValue("lohi");
                break;
            case "Name (Z to A)":
                filterSelect.selectByValue("za");
                break;
            default:
                throw new IllegalArgumentException("Opción de filtro no válida: " + filterText);
        }
    }

    public List<Double> getProductPrices() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        List<WebElement> currentPriceElements = driver.findElements(By.className("inventory_item_price"));
        wait.until(ExpectedConditions.visibilityOfAllElements(currentPriceElements));

        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : currentPriceElements) {
            String priceText = priceElement.getText().replace("$", "").trim();
            try {
                prices.add(Double.parseDouble(priceText));
            } catch (NumberFormatException e) {
                System.out.println("Error parsing price: " + priceText);
                prices.add(0.0);
            }
        }
        return prices;
    }

    public List<Double> sortPricesDescending(List<Double> prices) {
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        return sorted;
    }

    public Double getHighestPrice(List<Double> prices) {
        return Collections.max(prices);
    }

    public Double getLowestPrice(List<Double> prices) {
        return Collections.min(prices);
    }

    public List<String> getProductNames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        List<WebElement> nameElements = driver.findElements(By.className("inventory_item_name"));
        wait.until(ExpectedConditions.visibilityOfAllElements(nameElements));

        List<String> names = new ArrayList<>();

        for (WebElement nameElement : nameElements) {
            names.add(nameElement.getText().trim());
        }

        return names;
    }

    public List<String> sortNamesDescending(List<String> names) {
        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted, Collections.reverseOrder());
        return sorted;
    }

    public String getFirstName(List<String> names) {
        return names.get(0);
    }

    public String getLastName(List<String> names) {
        return names.get(names.size() - 1);
    }

    public boolean areProductsSortedZA() {
        List<String> actualNames = getProductNames();
        List<String> expectedNames = sortNamesDescending(actualNames);
        return actualNames.equals(expectedNames);
    }
}