package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserProfilePageHelper extends PageBase {
    @FindBy (xpath = "//input[@name='initials']")
    WebElement initialsField;

    @FindBy (xpath = "//div[@title='lanaioffe (lanaioffe2)']/span")
    List <WebElement> listInitialsToVerify;

    public UserProfilePageHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(initialsField,30);
    }

    public String getInitialsFromInitialsField() {
        return initialsField.getAttribute("value");
    }


    public boolean verifyIfInitialsDisplayedCorrectly(String initials) {
        int counter = 0;
        if (listInitialsToVerify.size()==2) counter++;
        if (listInitialsToVerify.get(0).getText().equals(initials)) counter++;
        if (listInitialsToVerify.get(1).getText().equals(initials)) counter++;
        return counter == 3;
    }
}
