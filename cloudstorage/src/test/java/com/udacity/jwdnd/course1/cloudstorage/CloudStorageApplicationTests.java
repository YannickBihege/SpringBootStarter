package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.SpringVersion;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CloudStorageApplicationTests {

	@BeforeAll
	static void beforeAll() {
		assertEquals("11.0.17", System.getProperty("java.version"));
		       System.out.println( SpringVersion.getVersion());

	}

	@Test
	public void myTest() {
		System.out.println("This is a test message");
		// Your test code here
	}

	@Test
	public void springVersion(){
		assertEquals("5.2.3.RELEASE", SpringVersion.getVersion());
	}

	@Test
	public void springSecurityVersion(){
		assertEquals("5.2.1.RELEASE", SpringSecurityCoreVersion.getVersion());
	}
}
