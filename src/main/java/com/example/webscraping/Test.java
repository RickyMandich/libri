package com.example.webscraping;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Test {
    public static void main(String[] args){
        /*dati presi in input
        String mail = getString("inserisci la mail dell'account myZanichelly.it");
        String password = getString("inserisci la password dell'account myZanichelly.it");
        /*dati inseriti da codice*/
        String mail = "ricky.mandich@gmail.com";
        String password = "Minecraft35";
        /**/
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://my.zanichelli.it/");
            WebElement inputMail = driver.findElement(By.id("inline-username-input"));
            WebElement inputPassword = driver.findElement(By.id("inline-password-input"));
            inputMail.sendKeys(mail);
            inputPassword.sendKeys(password);
            driver.findElement(By.cssSelector(".z-button--container.z-button--has-text.sc-idp-login-form.sc-z-button.sc-idp-login-inline.sc-z-button-s")).click();
            // Prima della ricerca degli elementi, aggiungi un'attesa esplicita per il container
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Aspetta che il contenitore principale sia presente e visibile
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sc-jzJRlG.kRflIG")));

            List<WebElement> libri = driver.findElements(By.cssSelector(".sc-jzJRlG.kRflIG>z-book-card"));
            for(int i=0; i<libri.size(); i++){
                System.out.println(i+")\t"+libri.get(i).getAttribute("opera-title")+"\t"+libri.get(i).getAttribute("volume-title"));
            }
            System.out.println("inserisci il numero del libro che vuoi scaricare");
            WebElement libro = libri.get(getInt(0, libri.size()-1));
            libro = libro.findElement(By.cssSelector("button"));
            System.out.println(libro.getAttribute("outerHTML"));
            libro.click();
            Thread.sleep(5000);
            System.out.println(driver.getCurrentUrl());
            // Prima della ricerca degli elementi, aggiungi un'attesa esplicita per il container
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Aspetta che il contenitore principale sia presente e visibile
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table tr td mat-icon")));
            driver.findElement(By.cssSelector("table tr td mat-icon")).click();
            driver.findElement(By.cssSelector(".deletebtn")).click();

            System.out.println("ciao");
            System.out.println(driver.getCurrentUrl());
            Thread.sleep(5000);
        }catch (InterruptedException e){
        }finally {
            //close(driver);
        }
    }

    public static void close(WebDriver driver){
        try {
            driver.quit();
        }catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println(driver.getCurrentUrl());
        }
    }

    /**
     * funzione che ritorna un intero preso in input dall'utente e lo filtra nell'intervallo inserito
     * @param min il numero minimo che può essere ritornato
     * @param max il numero massimo che può essere ritornato
     * @return il numero preso in input, se l'intervallo non è possibile min>max ritorna 0
     */
    public static int getInt(int min, int max){
        System.out.println("getInt("+min+", "+max+")");
        if(min < max){
            int n;
            if((n = getInt(min))<=max){
                return n;
            }else{
                System.out.println("il numero è troppo grande");
                return getInt(min, max);
            }
        }else if(min == max){
            System.out.println("min e max sono uguali");
            return min;
        }else{
            System.out.println("ritorno 0 perchè min è maggiore di max");
            return 0;
        }
    }

    /**
     * funzione che ritorna un intero preso in input dall'utente e controlla che non sia più piccolo di un certo valore
     * @param min il numero più piccolo che può essere ritornato
     * @return il numero inserito in input
     */
    public static int getInt(int min){
        //System.out.println("getInt("+min+")");
        int n;
        if((n=getInt())>min){
            return n;
        }else{
            System.out.println("il numero è troppo piccolo");
            return getInt(min);
        }
    }

    public static int getInt(){
        //System.out.println("getInt()");
        try{
            return new java.util.Scanner(System.in).nextInt();
        }catch (java.util.IllegalFormatException e){
            System.out.println("devi inserire un intero");
            return getInt();
        }
    }

    public static String getString(String output){
        System.out.println(output);
        return getString();
    }

    public static String getString(){
        try{
            return new java.util.Scanner(System.in).nextLine();
        }catch (java.util.IllegalFormatException e){
            System.out.println("devi inserire una stringa");
            return getString();
        }

    }
}