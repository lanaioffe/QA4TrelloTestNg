package tel_ran.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tel_ran.helpers.BoardsPageHelper;
import tel_ran.helpers.HomePageHelper;
import tel_ran.helpers.LoginPageHelper;

public class LoginPageTests extends TestBase {

    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
    }

    @Test
    public void loginToTrelloPositive() throws InterruptedException {

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD);
//        loginPage.enterAtlLogin(LOGIN);
//        loginPage.clickLoginWithAtlassian();
//        loginPage.clickContinueButton();
//        loginPage.enterAtlPasswordAndLogin(PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertTrue(boardsPage.verifyIfBoardsIconIsDisplayed());
        Assert.assertTrue(boardsPage.verifyIfPersonalBoardsHeaderIsDisplayed());
    }


    @Test
    public void loginIncorrectPassNegative() throws InterruptedException {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD + "1");
        loginPage.waitUntilPasswordError();

        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(), "Error message is not correct");

    }

    @Test
    public void loginIncorrectLoginNegative() {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
//        loginPage.loginToTrello(LOGIN + "1", PASSWORD);
        loginPage.loginToTrello("abc@mai.com", "hjdhj");
        loginPage.waitUntilLoginError();
        Assert.assertTrue(loginPage.verifyIfLoginErrorIsCorrect(), "Error login message is not correct");
    }


}