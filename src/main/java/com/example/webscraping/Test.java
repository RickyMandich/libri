package com.example.webscraping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://my.zanichelli.it/");
            Thread.sleep(5000);
        }catch (Exception e){
            
        }finally {
            driver.quit();
        }

    }
}