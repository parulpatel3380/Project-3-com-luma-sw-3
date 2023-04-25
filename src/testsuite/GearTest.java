package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

/**5.Write down the following test into ‘GearTest’ class
 1. userShouldAddProductSuccessFullyTo
 ShoppinCart()
 * Mouse Hover on Gear Menu
 * Click on Bags
 * Click on Product Name ‘Overnight Duffle’
 * Verify the text ‘Overnight Duffle’
 * Change Qty 3
 * Click on ‘Add to Cart’ Button.
 * Verify the text
 ‘You added Overnight Duffle to your shopping cart.’
 * Click on ‘shopping cart’ Link into
 message
 * Verify the product name ‘Cronus Yoga Pant’

 * Verify the Qty is ‘3’
 * Verify the product price ‘$135.00’
 * Change Qty to ‘5’
 * Click on ‘Update Shopping Cart’ button
 * Verify the product price ‘$225.00’
 *
 */
public class GearTest extends Utility {
    String baseUrl="https://magento.softwaretestingboard.com/";
    @Before
    public void setBaseUrl()
    {
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart()
    {
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-6']//span[normalize-space()='Gear']"));
        mouseHoverAndClickOnElement(By.xpath("//span[normalize-space()='Bags']"));
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));

        String expectedText = "Overnight Duffle";
        WebElement actualText = driver.findElement(By.xpath("//span[@class='base']"));
        String actualMessageText= actualText.getText();
        driver.findElement(By.xpath("//input[@id='qty']")).clear();
        sendTextToElement(By.xpath("//input[@id='qty']"),"3");
        clickOnElement(By.xpath("//span[normalize-space()='Add to Cart']"));

        String expectedMessageAdded = "You added Overnight Duffle to your shopping cart.";
        WebElement actualMessageAdded = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        String actualMessageAdded1 = actualMessageAdded.getText();
        verifyElementMethod(expectedMessageAdded,actualMessageAdded1);

        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        String expectedMessageBag = "Overnight Duffle";
        WebElement actualMessageBag = driver.findElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']"));
        String actualMessageBag1 = actualMessageBag.getText();
        verifyElementMethod(expectedMessageBag,actualMessageBag1);

//        String expectedMessageQTY = "3";
//        WebElement actualMessageQTY = driver.findElement(By.xpath("//td[@class='col qty']//label[@for='cart-102361-qty']//input[@id='cart-102361-qty']"));
//        String actualMessageQTY1 = actualMessageQTY.getText();
//        verifyElementMethod(expectedMessageQTY,actualMessageQTY1);

        WebElement element12 = driver.findElement(By.cssSelector(".input-text.qty"));
        String actualQty=  element12.getAttribute("value");
        String expectedQty= "3";
        verifyElementMethod(expectedQty,actualQty);

        String expectedMessagePrice = "$135.00";
        WebElement actualMessagePrice = driver.findElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$135.00']"));
        String actualMessagePrice1 = actualMessagePrice.getText();
        verifyElementMethod(expectedMessagePrice,actualMessagePrice1);

//        driver.findElement(By.xpath("//input[@id='cart-102383-qty']")).clear();
//        sendTextToElement(By.xpath("//input[@id='cart-102383-qty']"),"5");

        WebElement element2 = driver.findElement(By.cssSelector(".input-text.qty"));
        element2.clear();
        // sendTextToElement(By.xpath("//input[@id='cart-101283-qty'])[1]"),"5");
        sendTextToElement(By.cssSelector(".input-text.qty"),"5");

        clickOnElement(By.xpath("//span[normalize-space()='Update Shopping Cart']"));


        //  * Verify the product price ‘$225.00’

        String expectedPrice1= "$225.00";
        String actualPrice1=getTextFromElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$225.00']"));
        verifyElementMethod(expectedPrice1,actualPrice1);





    }

    @After
    public void tearDown() {
        driver.close();
    }

}
