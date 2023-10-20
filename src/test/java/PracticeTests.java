import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PracticeTests {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private static String expectedText = "";
    @BeforeAll
    public static void beforeOfAll(){
        System.out.println("------------------------");
    }
    @BeforeEach
    public void setup(){

        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void productNameCompareWhenAddToCard(){
        String rootPathsList = "//main/following-sibling::div[@class='sc-uhudcz-0 iZZGui']/div";
        int random_Index = (int)Math.floor(Math.random() * (16 - 1 + 1) + 1);
        String randProductRootPath = rootPathsList+"["+random_Index+"]";
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(rootPathsList)));
        WebElement nameProduct = driver.findElement(By.xpath(rootPathsList+"["+random_Index+"]/div[@alt]/following-sibling::p"));
        WebElement randAddToCart = driver.findElement(By.xpath(randProductRootPath+"//button"));
        expectedText = nameProduct.getText();
        randAddToCart.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sc-ebmerl-0 foenKb']/div[2]/div/div[2]/div/div[1]/p[1]")));
        WebElement nameProduct_Cart = driver.findElement(By.xpath("//div[@class='sc-ebmerl-0 foenKb']/div[2]/div/div[2]/div/div[1]/p[1]"));
        Assertions.assertEquals(expectedText,nameProduct_Cart.getText());
    }
    @Test
    public void compareProductListPriceAndThePriceInTheCart(){
        String rootPathsList = "//main/following-sibling::div[@class='sc-uhudcz-0 iZZGui']/div";
        int random_Index = (int)Math.floor(Math.random() * (16 - 1 + 1) + 1);
        String randProductRootPath = rootPathsList+"["+random_Index+"]";
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(rootPathsList)));
        WebElement priceProduct = driver.findElement(By.xpath(rootPathsList+"["+random_Index+"]//p[contains(@class,'sc-124al1g-6')]"));
        expectedText = priceProduct.getText();
        WebElement randAddToCart = driver.findElement(By.xpath(randProductRootPath+"//button"));
        randAddToCart.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sc-ebmerl-0 foenKb']/div[2]/div/div[2]/div/div[2]/p[1]")));
        WebElement priceProduct_Cart = driver.findElement(By.xpath("//div[@class='sc-ebmerl-0 foenKb']/div[2]/div/div[2]/div/div[2]/p[1]"));
        String actualTextPrice = priceProduct_Cart.getText();
        actualTextPrice = actualTextPrice.replace(" ","");
        Assertions.assertEquals(expectedText,actualTextPrice);
    }
    @Test
    public void clickPlusButtonInTheCart(){
        String rootPathsList = "//main/following-sibling::div[@class='sc-uhudcz-0 iZZGui']/div";
        int random_Index = (int)Math.floor(Math.random() * (16 - 1 + 1) + 1);
        int randomClickCount = (int)Math.floor(Math.random() * (4 - 2 + 2) + 2);
        float currentPrice = 0;
        int currentClickCount = 1;
        float priceProductFloat = 0;
        String totalPriceValue="";
        float totalPriceFloat=0;
        String randProductRootPath = rootPathsList+"["+random_Index+"]";
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(rootPathsList)));
        WebElement randAddToCart = driver.findElement(By.xpath(randProductRootPath+"//button"));
        randAddToCart.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sc-ebmerl-0 foenKb']/div[2]/div/div[2]/div/div[2]/p[1]")));
        WebElement priceProduct_Cart = driver.findElement(By.xpath("//div[@class='sc-ebmerl-0 foenKb']/div[2]/div/div[2]/div/div[2]/p[1]"));
        String currentTextPrice = priceProduct_Cart.getText();
        currentTextPrice = currentTextPrice.replace("$","");
        currentPrice = Float.parseFloat(currentTextPrice);
        WebElement plusButton = driver.findElement(By.xpath("//div[@class='sc-ebmerl-0 foenKb']/div[2]/div/div[2]/div/div[2]/div/button[2]"));
        WebElement totalPrice = driver.findElement(By.xpath("//div[@class='sc-ebmerl-0 foenKb']/div[2]/div/div[3]/div/p[1]"));
        while (currentClickCount<randomClickCount){
            plusButton.click();
            currentClickCount = currentClickCount+1;
        }
        currentPrice = currentPrice*currentClickCount;
        totalPriceValue = totalPrice.getText().replace("$ ","");
        totalPriceFloat = Float.parseFloat(totalPriceValue);
        Assertions.assertTrue(Math.abs(currentPrice-totalPriceFloat)<1);
        }
    @AfterEach
    public void tearDown() {
        System.out.println("Test sonlandı.");
        driver.quit();
    }
    @AfterAll
    public static void afterOfAll(){
        System.out.println("------------------------");
    }
}