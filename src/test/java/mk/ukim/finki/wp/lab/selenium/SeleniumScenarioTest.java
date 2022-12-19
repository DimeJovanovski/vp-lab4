package mk.ukim.finki.wp.lab.selenium;


import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    BalloonService balloonService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    UserService userService;

    private static boolean dataInitialized = false;

    private static String user = "user";
    private static String admin = "admin";

    private HtmlUnitDriver driver;

    private static User regularUser;
    private static User adminUser;


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    private void initData() {
        if (!dataInitialized) {
            regularUser = userService.findById(1L);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElemts(0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        // se pravi LOGIN na USER
        balloonsPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");
        balloonsPage.assertElemts(1,1,1);

        // se pravi LOGOUT na admin i se logira USER
        loginPage = LoginPage.logout(this.driver);
        balloonsPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);
        balloonsPage.assertElemts(0,0,0);

        balloonsPage = LoginPage.doLogin(this.driver, loginPage, "dimitar.jovanovski", "dj");
        balloonsPage.assertElemts(0,0,0);

    }






}
