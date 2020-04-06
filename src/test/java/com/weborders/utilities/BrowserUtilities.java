package com.weborders.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BrowserUtilities {
    public static void wait(int second){

        try {
            Thread.sleep(1000*second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public static List<String> getTextFromWebElements(List<WebElement> elements){
        List<String> textValues = new ArrayList<String>();
        for (WebElement element: elements){
            if (!element.getText().isEmpty()){
                textValues.add(element.getText());
            }
            textValues.add(element.getText());

        }
        return textValues;
    }
    public static void waitForPageToLoad(long timeoutInSeconds){
        ExpectedCondition<Boolean> expectation = driver ->((JavascriptExecutor)driver).
                executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(),timeoutInSeconds);


        }catch (Throwable error){
            error.printStackTrace();
        }
    }
    public static void clickWithJS(WebElement element){
        ((JavascriptExecutor)Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true); ",element);
        ((JavascriptExecutor)Driver.getDriver()).executeScript("arguments[0].click(); ",element);
    }
    public static void scrollTo(WebElement element){
        ((JavascriptExecutor)Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",element);

    }
    public static String getScreenshot(String name){
        name = LocalDate.now() +"_"+name;
        String path = System.getProperty("user.dir")+"/test-output/screenshots/"+name+".png";
        System.out.println("Screenshot is here:"+path);
        TakesScreenshot takesScreenshot = (TakesScreenshot)Driver.getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE );
        File destination = new File(path);
        try {
            FileUtils.copyFile(source,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return path;
    }


}
