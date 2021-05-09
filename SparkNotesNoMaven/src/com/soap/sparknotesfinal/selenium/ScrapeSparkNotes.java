package com.soap.sparknotesfinal.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ScrapeSparkNotes {







    public static void main(String[] args) {

        ScrapeSparkNotes fetcher = new ScrapeSparkNotes();
        fetcher.getSummary(args[0]);


    }

    public String getSummary(String search) {
//        String search = Input.getInput("What is the name of the book?", DATATYPE.STRING);
        search.replaceAll(" ", "%20");
        search = search.toLowerCase();
        String baseUrl = "https://www.sparknotes.com/search?q=" + search;

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\arche\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        // launch Chrome and direct it to the Base URL
        driver.get(baseUrl);
        String sub = "";
        System.out.println("Running...(Wait 10 seconds before closing if nothing happens)");
        try {
            WebElement topsearchresult = driver.findElement(By.cssSelector("body > div.content > div.content-container.search-results > div:nth-child(3) > div"));
            topsearchresult.click();

            String newurl = driver.getCurrentUrl();
            newurl += "summary/";
            String summary;
            driver.get(newurl);
            if(driver.findElements(By.cssSelector("body > div.layout-wrapper-2018 > div.layout-wrapper-2018__column.layout-wrapper-2018__column--main.mainTextContent > div.mainTextContent")).size() > 0) {
                 summary = driver.findElement(By.cssSelector("body > div.layout-wrapper-2018 > div.layout-wrapper-2018__column.layout-wrapper-2018__column--main.mainTextContent > div.mainTextContent")).getText();
            }else {
                 summary = driver.findElement(By.cssSelector("#plotoverview")).getText();
            }
            System.out.println(summary.indexOf("Featured on Sparknotes"));
            System.out.println(summary.indexOf("Powered By"));
            sub = summary.substring(0, summary.indexOf("Featured on Sparknotes")) + summary.substring(summary.indexOf("Powered By") + 10, summary.length());

            System.out.println(sub);
            try {
                search = search.replaceAll(" ", "");
                FileOutputStream out = new FileOutputStream("C:\\Users\\arche\\OneDrive\\Documents\\Desktop\\" + search + "summary.txt");
                System.out.println("The txt file with your summary is:" + "C:\\Users\\arche\\OneDrive\\Documents\\Desktop\\" + search + "summary.txt");
                byte contents[] = sub.getBytes(StandardCharsets.UTF_8);
                out.write(contents);

            } catch (IOException e) {
                e.printStackTrace();
            }

    }catch(NoSuchElementException e){
        System.out.println("The book title that you entered does not seem to be getting any good matches. Make sure that the book exists on SparkNotes and try again");
    }
        return sub;
    }
}
