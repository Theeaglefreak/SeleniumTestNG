package main.java.PageEvents;

import main.java.Util.Elements;
import org.openqa.selenium.WebElement;

public class HomePage {
 String profileXpath = "//*[@Class='desktop-userTitle']";
    public void login()
    {
      WebElement profile=  Elements.getElement("Xpath",profileXpath);
      profile.click();
    }
}
