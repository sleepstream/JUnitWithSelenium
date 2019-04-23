package tests;

import com.google.common.html.HtmlEscapers;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {


    private static boolean subMenuWWS = false;
    private static boolean MenuWWS = false;
    private static boolean MenuSub = false;
    private static boolean MenuAb = false;
    private static boolean SidePanel = false;
    private static boolean EducationHeader = false;
    private static boolean StudentTest3 = false;
    private static boolean backPressed = false;
    private static boolean StudentTest3LearnMore=false;
    private static boolean StudentTest3LinkStudents=false;
    public static String  MainLink = "https://www.wiley.com";
    private static boolean test5LogoToHomepage = false;
    private static boolean test6EmptySearch=false;
    private static boolean SuggestionsTest=false;
    private static boolean ProductsTest=false;
    private static boolean test6ShowSearchResult = false;
    private static boolean test8SearchResultPage = false;

    private static boolean test9SearchResultPage=false;
    
    

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
    }
/*
    @Test
    public void demo() {
        WebDriver driver = RunEnvironment.getWebDriver();
        driver.get("https://www.blazemeter.com/selenium");
        String homeUrl = driver.findElement(By.cssSelector("div#logo> a#logo_image ")).getAttribute("href");
        assertEquals(homeUrl, "https://www.blazemeter.com/");
    }
*/

    @Test
    public void CheckMainHeaderNavBar()
    {
        OpenHomePage();

        String[] navBar={"who we serve", "subjects", "about"};

        List<WebElement> listSubMenu = getListOfNavMenu(driver);
        int countMenu =0;
        for(WebElement item: listSubMenu)
        {
            WebElement link = item.findElement(By.className("collapsed"));
            String name = link.getText();

            if(link.isDisplayed()) {

                for(String str: navBar)
                {
                    if(str.equals(name.toLowerCase())) {
                        countMenu++;
                        break;
                    }
                }

            }
        }
        assertEquals((long)countMenu, (long)navBar.length);
    }
    @Test
    public void CheckMainHeaderNavBarMenuWWSItems()
    {
        OpenHomePage();

        List<WebElement> listSubMenu = getListOfNavMenu(driver);
        for(WebElement item: listSubMenu)
        {
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();
            wait.until(ExpectedConditions.visibilityOf(item));

            WebElement link = item.findElement(By.className("collapsed"));
            String name = link.getText();

            if(link.isDisplayed()) {

                if(name.toLowerCase().equals("who we serve")) {
                    String[] titles = {"Students", "Instructors", "Book Authors",
                            "Professionals", "Researchers", "Institutions",
                            "Librarians", "Corporations", "Societies", "Journal Editors",
                            "Bookstores",
                            "Government"};
                    List<WebElement> listSubMenuWWS = item.findElements(By.className("dropdown-item"));
                    if (listSubMenuWWS.size() == titles.length) {
                        int count = 0;

                        for (WebElement subItem : listSubMenuWWS) {
                            if (!subItem.findElement(By.tagName("a")).getAttribute("innerHTML").trim().equals(HtmlEscapers.htmlEscaper().escape(titles[count])))
                                break;
                            count++;
                        }
                        if(count==titles.length)
                            subMenuWWS = true;
                    }

                    break;
                }

            }
        }
        assertTrue(subMenuWWS);

    }
    @Test
    public void CheckMainHeaderNavBarMenuStudentSubMenu()
    {
        OpenHomePage();

        List<WebElement> listSubMenu = getListOfNavMenu(driver);
        for(WebElement item: listSubMenu)
        {



            WebElement link = item.findElement(By.className("collapsed"));
            String name = link.getText();

            if(link.isDisplayed()) {

                if(name.toLowerCase().equals("who we serve")) {
                Actions action = new Actions(driver);
                action.moveToElement(item).build().perform();
                wait.until(ExpectedConditions.visibilityOf(item));

                    List<WebElement> listSubMenuWWS = item.findElements(By.className("dropdown-item"));
                        for (WebElement subItem : listSubMenuWWS) {


                            WebElement tmp = subItem.findElement(By.tagName("a"));
                            //action.moveToElement(tmp).build().perform();
                            wait.until(ExpectedConditions.visibilityOf(tmp));
                            if (tmp.getAttribute("innerHTML").trim().equals("Students")) {
                                //((ChromeDriver) driver).executeScript("arguments[0].click();", tmp);
                                tmp.click();
                                try {
                                    wait.until(ExpectedConditions.titleContains("Students"));
                                    if(driver.getCurrentUrl().equals("https://www.wiley.com/en-us/students"))
                                        StudentTest3LinkStudents = true;
                                    StudentTest3 = true;
                                    WebElement learnMoreLink = driver.findElement(By.partialLinkText("Learn More"));
                                    String url = learnMoreLink.getAttribute("href");
                                    if(url != null && url.contains("wileyplus.com"))
                                        StudentTest3LearnMore = true;
                                }
                                catch (Exception ex )
                                {
                                    ex.printStackTrace();
                                }


                                driver.get(MainLink+"/en-us");
                                backPressed = true;
                                try {
                                    wait.until(ExpectedConditions.titleContains("Homepage"));
                                }
                                catch (Exception ex )
                                {
                                    ex.printStackTrace();
                                }

                                break;
                            }
                        }

                    break;
                }

            }
        }
        assertTrue(StudentTest3&StudentTest3LinkStudents&StudentTest3LearnMore);
    }
    @Test
    public void CheckMainHeaderNavBarMenuSubjects()
    {
        OpenHomePage();


        List<WebElement> listSubMenu = getListOfNavMenu(driver);
        for(WebElement item: listSubMenu)
        {



            WebElement link = item.findElement(By.className("collapsed"));
            String name = link.getText();

            if(link.isDisplayed()) {

                if(name.toLowerCase().equals("subjects")) {
                    Actions action = new Actions(driver);
                    action.moveToElement(item).build().perform();
                    wait.until(ExpectedConditions.visibilityOf(item));

                    String[] sidePanelLinks = {"Information & Library Science",
                            "Education & Public Policy",
                            "K-12 General",
                            "Higher Education General",
                            "Vocational Technology",
                            "Conflict Resolution & Mediation (School settings)",
                            "Curriculum Tools- General",
                            "Special Educational Needs",
                            "Theory of Education",
                            "Education Special Topics",
                            "Educational Research & Statistics",
                            "Literacy & Reading",
                            "Classroom Management"};

                    List<WebElement> ListSubMenuSub = item.findElements(By.cssSelector(".dropdown-item.dropdown-submenu"));

                    for(WebElement itemSubMenu : ListSubMenuSub)
                    {
                        action.moveToElement(itemSubMenu).build().perform();

                        WebElement SubMenuSub = itemSubMenu.findElement(By.tagName("a"));
                        String tmp = SubMenuSub.getAttribute("innerHTML").trim();
                        if(tmp.equals("Education"))
                        {
                            String linkEd = SubMenuSub.getAttribute("href");

                            WebDriver driverSub = new ChromeDriver();
                            driverSub.get(linkEd);
                            wait = new WebDriverWait(driverSub, 60);
                            wait.until(ExpectedConditions.elementToBeClickable(By.id("main-header-navbar")));

                            if(driverSub.getTitle().contains("Education"))
                                EducationHeader = true;

                            WebElement mainElementSub = driverSub.findElement(By.className("side-panel"));

                            List<WebElement> listMainElementSub = mainElementSub.findElements(By.tagName("li"));
                            int count =0;
                            for (WebElement itemPanel : listMainElementSub) {
                                String textLink = itemPanel.findElement(By.tagName("a")).getAttribute("innerHTML");

                                if (!textLink.equals(HtmlEscapers.htmlEscaper().escape(sidePanelLinks[count]))) {
                                    SidePanel = false;
                                    break;
                                } else
                                    SidePanel = true;

                                count++;
                            }

                            driverSub.quit();
                        }
                    }
                }

            }
        }
        assertTrue(SidePanel&EducationHeader);
    }
    @Test
    public  void chechLogo()
    {
        OpenHomePage();

        driver.findElement(By.className("simple-responsive-banner-component")).click();
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.titleContains("Homepage"));
            test5LogoToHomepage = true;
        }
        catch (Exception ex )
        {
            ex.printStackTrace();
        }

        assertTrue(test5LogoToHomepage);

    }
    @Test
    public  void checkEmptySearch()
    {
        OpenHomePage();
        driver.findElement(By.className("input-group")).findElement(By.tagName("button")).click();
        try {

            wait.until(ExpectedConditions.titleContains("Homepage"));
            test6EmptySearch = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            test6EmptySearch = false;
        }

        assertTrue(test6EmptySearch);
    }
    @Test
    public void checkQickLinkSearch()
    {
        OpenHomePage();

        try {
            driver.findElement(By.className("main-navigation-search")).findElement(By.className("input-group")).findElement(By.tagName("input")).sendKeys("java");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("main-navigation-search")).findElement(By.tagName("aside"))));
            test6ShowSearchResult = true;
            //WebElement item = driver.findElement(By.className("main-navigation-search")).findElement(By.className("search_form_SearchBox")).findElement(By.tagName("aside"));
            //Actions action = new Actions(driver);
            //action.moveToElement(item).build().perform();

            List<WebElement> sections = driver.findElement(By.className("main-navigation-search")).findElement(By.tagName("aside")).findElements(By.tagName("section"));

            for (WebElement elem : sections) {
                String title = elem.findElement(By.tagName("h3")).getAttribute("innerHTML").trim().toLowerCase();

                //Actions action = new Actions(driver);
                //action.moveToElement(elem).build().perform();
                if ("suggestions".equals(title)) {
                    List<WebElement> searchResult = elem.findElement(By.className("search-list")).findElements(By.tagName("div"));
                    if (searchResult.size() == 4) {
                        for (WebElement result : searchResult) {
                            String tmp = result.findElement(By.className("search-highlight")).getAttribute("innerHTML").trim().toLowerCase();
                            if (tmp.equals("java"))
                                SuggestionsTest = true;
                            else {
                                SuggestionsTest = false;
                                break;
                            }
                        }
                    } else {
                        SuggestionsTest = false;
                    }
                } else if ("products".equals(title)) {
                    List<WebElement> searchResult = elem.findElement(By.className("related-content-products")).findElements(By.tagName("div"));
                    if (searchResult.size() == 4) {
                        //in scenario 5 items in result but in fact have only 4
                        //if (searchResult.size() == 5) {
                        for (WebElement result : searchResult) {
                            String tmp = result.findElement(By.className("search-highlight")).getAttribute("innerHTML").trim().toLowerCase();
                            if (tmp.equals("java"))
                                ProductsTest = true;
                            else {
                                ProductsTest = false;
                                break;
                            }
                        }
                    } else {
                        ProductsTest = false;
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            test6ShowSearchResult = false;
        }

        assertTrue(ProductsTest&SuggestionsTest);
    }
    @Test
    public void checkResultPageSearch()
    {
        OpenHomePage();

        try {
            driver.findElement(By.className("main-navigation-search")).findElement(By.className("input-group")).findElement(By.tagName("input")).sendKeys("java");
            driver.findElement(By.className("main-navigation-search")).findElement(By.className("input-group")).findElement(By.tagName("button")).click();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("search-result-page"))));
            test8SearchResultPage = true;
            List<WebElement> productsList = driver.findElement(By.className("search-result-page")).findElements(By.className("product-item"));
            if(productsList.size() == 10)
            {
                int  tmpCount = 0;
                for(WebElement product : productsList)
                {
                    if(product.findElement(By.className("product-title")).findElement(By.className("search-highlight")).getAttribute("innerHTML").trim().toLowerCase().contains("java")
                            && product.findElements(By.className("product-button")).size() >0)
                    {
                        List<WebElement> tmp = product.findElements(By.className("product-button"));
                        int test =0;
                        for(WebElement element: tmp)
                        {
                            if(element.getAttribute("innerHTML").toLowerCase().contains("add to cart"))
                                test = 1;
                        }

                        tmpCount+=test;
                    }
                }

                if(tmpCount == 10)
                    test8SearchResultPage = true;
                else
                    test8SearchResultPage = false;


            }
            else
            {
                test8SearchResultPage = false;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            test6ShowSearchResult = false;
        }

        assertTrue(test8SearchResultPage);
    }
    @Test
    public  void checkRepeatSearch()
    {
        OpenHomePage();

        try {
            driver.findElement(By.className("main-navigation-search")).findElement(By.className("input-group")).findElement(By.tagName("input")).sendKeys("java");
            driver.findElement(By.className("main-navigation-search")).findElement(By.className("input-group")).findElement(By.tagName("button")).click();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("search-result-page"))));

            List<String> SearchResultItems = new ArrayList<String>();
            List<WebElement> productsList =  driver.findElement(By.className("search-result-page")).findElements(By.className("product-item"));
            for(WebElement product : productsList)
            {
                SearchResultItems.add(product.getAttribute("innerHTML"));
            }

            driver.findElement(By.className("main-navigation-search")).findElement(By.className("input-group")).findElement(By.tagName("input")).clear();
            driver.findElement(By.className("main-navigation-search")).findElement(By.className("input-group")).findElement(By.tagName("input")).sendKeys("java");
            driver.findElement(By.className("input-group")).findElement(By.tagName("button")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("search-result-page"))));
            productsList = driver.findElement(By.className("search-result-page")).findElements(By.className("product-item"));

            if (productsList.size() == SearchResultItems.size()) {

                int k = 0;
                for (WebElement product : productsList) {
                    if (product.getAttribute("innerHTML").equals(SearchResultItems.get(k))) {
                        test9SearchResultPage = true;
                    } else {
                        test9SearchResultPage = false;
                        break;
                    }
                    k++;
                }
            } else {
                test9SearchResultPage = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            test9SearchResultPage = false;
        }

        assertTrue(test9SearchResultPage);
    }

    @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }

    public static List<WebElement> getListOfNavMenu(WebDriver driver)
    {

        WebElement mainElement = driver.findElement(By.id("main-header-navbar"));

        List<WebElement> listSubMenu = mainElement.findElements(By.className("dropdown-submenu"));

        return listSubMenu;
    }

    public void OpenHomePage()
    {
        driver = RunEnvironment.getWebDriver();
        driver.get(MainLink+"/en-us");
        wait = new WebDriverWait(driver, 60);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("main-header-navbar")));

        driver.findElement(By.xpath("//*[@id=\"country-location-form\"]/div[3]/button[2]")).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(driver.findElement(By.className("modal-dialog")))));
    }
}
