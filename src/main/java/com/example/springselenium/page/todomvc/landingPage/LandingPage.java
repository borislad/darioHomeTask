package com.example.springselenium.page.todomvc.landingPage;

import com.example.springselenium.page.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LandingPage extends Base {

    @FindBy(xpath = "/html/body/section/div/header/h1")
    public WebElement title;

    @FindBy(xpath = "/html/body/section/div/footer/ul/li[3]/a")
    public WebElement completed;

    @FindBy(xpath = "/html/body/section/div/footer/ul/li[1]/a")
    public WebElement all;

    @FindBy(xpath = "/html/body/section/div/header/input")
    public WebElement input;

    @FindBy(className = "todo-count")
    public List<WebElement> todoCountList;

    @FindBy(className = "toggle")
    public List<WebElement> toggleCheckbox;

    @FindBy(className = "view")
    public List<WebElement> todoList;

    @FindBy(className = "clear-completed")
    public WebElement clearCompleted;

    public void goTo(String url) {
        webDriver.get(url);
    }

    @Override
    public boolean isAt() {
        return wait.until((d) -> title.isDisplayed() && title.getText().equals("todos"));
    }

    public void sendInput(String text) {
        input.click();
        input.sendKeys(text);
        input.sendKeys(Keys.ENTER);
    }

    public String countTodo() {
        String countTodo = "";
        if (!todoCountList.isEmpty()) {
            for (int i = 0; i < todoCountList.size(); i++) {
                countTodo += todoCountList.get(i).getText();
            }
        }
        return countTodo;
    }
}
