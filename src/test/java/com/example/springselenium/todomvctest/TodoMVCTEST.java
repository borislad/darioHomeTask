package com.example.springselenium.todomvctest;

import com.example.springselenium.SpringBaseTestNGTest;
import com.example.springselenium.page.todomvc.landingPage.LandingPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import utils.ExcelUtils;

import java.util.List;

public class TodoMVCTEST extends SpringBaseTestNGTest {

    @Value("${application.url}")
    private String homepage;
    @Autowired
    WebDriver webDriver;
    @Autowired
    private LandingPage landingPage;

    @AfterClass(alwaysRun = true)
    protected void tearDown() {
        this.webDriver.quit();
        this.webDriver = null;
    }

    @Test
    public void examTest() {
        landingPage.goTo(homepage);
        Assert.assertTrue(landingPage.isAt());
        landingPage.sendInput("Write");
        landingPage.sendInput("Read");
        Assert.assertEquals(landingPage.countTodo(), "2 items left");
        landingPage.completed.click();
        Assert.assertEquals(landingPage.todoList.size(), 0);
        landingPage.all.click();
        landingPage.toggleCheckbox.get(0).click(); //choose first element in checkbox
        Assert.assertEquals(landingPage.countTodo(), "1 item left");
        landingPage.completed.click();
        Assert.assertEquals(landingPage.todoList.get(0).getText(), "Write");
        landingPage.clearCompleted.click();
        Assert.assertEquals(landingPage.todoList.size(), 0);

        ExcelUtils excelUtils = new ExcelUtils();
        List<String> actionList = excelUtils.getCellData();

        int countTodo = Integer.parseInt(landingPage.countTodo().substring(0,1));
        for (String action : actionList) {
            landingPage.sendInput(action);
            countTodo++;
        }

        System.out.println("There are "+countTodo+" items left");
    }
}
