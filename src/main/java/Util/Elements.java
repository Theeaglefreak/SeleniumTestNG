package main.java.Util;

import test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Elements {

    public static WebElement getElement(String identifier, String identifierValue) {
        switch (identifier) {
            case "id":
                return BaseTest.driver.findElement(By.id(identifierValue));
            case "Xpath":
                return BaseTest.driver.findElement(By.xpath(identifierValue));
            case "className":
                return BaseTest.driver.findElement(By.className(identifierValue));
            case "Name":
                return BaseTest.driver.findElement(By.name(identifierValue));
            default:
                return null;
        }

    }

    public static List<WebElement> getElements(String identifier, String identifierValue) {
        switch (identifier) {
            case "id":
                return BaseTest.driver.findElements(By.id(identifierValue));
            case "Xpath":
                return BaseTest.driver.findElements(By.xpath(identifierValue));
            case "className":
                return BaseTest.driver.findElements(By.className(identifierValue));
            case "Name":
                return BaseTest.driver.findElements(By.name(identifierValue));
            default:
                return null;
        }

    }
}
