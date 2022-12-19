package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonsPage extends AbstractPage {
    @FindBy(css = ".edit-button")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-balloon-button")
    private WebElement addBalloonButton;

    @FindBy(css = ".delete-balloon-button")
    private WebElement deleteBalloonButton;

    public BalloonsPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public void assertElemts(int editButtonsNumber, int addButtonsNumber, int deleteButtonsNumber) {
        Assert.assertEquals("rows do not match", editButtonsNumber, this.getEditButtons().size());
        Assert.assertEquals("Add element not present", addBalloonButton);
        Assert.assertEquals("Delete element not present", deleteBalloonButton);
    }





}
