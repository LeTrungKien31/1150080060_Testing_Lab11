package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver getDriver(String browser) {
        boolean isCI = System.getenv("CI") != null;
        WebDriver driver;
        // Kiểm tra xem có đang chạy trên GitHub Actions không

        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isCI) {
                options.addArguments("-headless"); // Firefox headless
            }
            driver = new FirefoxDriver(options);

        } else { // Mặc định là Chrome
            ChromeOptions options = new ChromeOptions();
            if (isCI) {
                options.addArguments("--headless=new"); // Chrome headless mới
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        return driver;
    }
}