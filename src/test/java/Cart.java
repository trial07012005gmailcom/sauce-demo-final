import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart extends BaseTest {

    @Test
    public void removeItemClearCartBadge() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
        Thread.sleep(1000);

        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        Assertions.assertEquals("1", cartBadge.getText());
        Thread.sleep(1000);

        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_contents_container")));
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-backpack"))).click();
        Thread.sleep(1000);

        int items = driver.findElements(By.className("cart_item")).size();
        Assertions.assertEquals(0, items);

        int badges = driver.findElements(By.className("shopping_cart_badge")).size();
        Assertions.assertEquals(0, badges);

        Thread.sleep(1000);
    }
}