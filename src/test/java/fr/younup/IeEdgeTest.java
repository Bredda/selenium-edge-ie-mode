package fr.younup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import static org.junit.Assert.*;

public class IeEdgeTest {

    private WebDriver webDriver;

    @Before
    public void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        String ieDriverPath = classLoader.getResource("IEDriverServer.exe").getPath();
        System.setProperty("webdriver.ie.driver", ieDriverPath);


        String edgeDriverPath = classLoader.getResource("msedgedriver.exe").getPath();
        System.setProperty("webdriver.edge.driver", edgeDriverPath);
    }

    @Test
    public void classicMode() {
        this.webDriver = new EdgeDriver();

        testSteps();
    }

    @Test
    public void ieMode() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.attachToEdgeChrome();
        ieOptions.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
        this.webDriver = new InternetExplorerDriver(ieOptions);

        testSteps();
    }

    void testSteps() {

        webDriver.get("http://the-internet.herokuapp.com/login");

        webDriver.findElement(By.id("username")).sendKeys("tomsmith");
        webDriver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        webDriver.findElement(By.cssSelector("button[type='submit']")).click();

        String flashText = webDriver.findElement(By.id("flash")).getText().trim();

        assertEquals( "You logged into a secure area! ×", flashText);

    }

    @After
    public void tearDown() {
        if (null != webDriver)
            webDriver.close();
    }


}
