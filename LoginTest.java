/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userinputout;

/**
 *
 * @author Student
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
    }

    // ---------------- USERNAME TESTS ----------------
    @Test
    void testValidUsername() {
        assertTrue(login.checkUserName("user_"));
    }

    @Test
    void testInvalidUsername_NoUnderscore() {
        assertFalse(login.checkUserName("user"));
    }

    @Test
    void testInvalidUsername_TooLong() {
        assertFalse(login.checkUserName("user_name"));
    }

    // ---------------- PASSWORD TESTS ----------------
    @Test
    void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Pass@123"));
    }

    @Test
    void testInvalidPassword_NoUppercase() {
        assertFalse(login.checkPasswordComplexity("pass@123"));
    }

    @Test
    void testInvalidPassword_NoDigit() {
        assertFalse(login.checkPasswordComplexity("Password@"));
    }

    @Test
    void testInvalidPassword_NoSpecialChar() {
        assertFalse(login.checkPasswordComplexity("Password1"));
    }

    @Test
    void testInvalidPassword_TooShort() {
        assertFalse(login.checkPasswordComplexity("P@1a"));
    }

    // ---------------- PHONE NUMBER TESTS ----------------
    @Test
    void testValidPhoneNumber() {
        assertTrue(login.checkPhoneNumber("+27831234567"));
    }

    @Test
    void testInvalidPhoneNumber_NoCountryCode() {
        assertFalse(login.checkPhoneNumber("0645891125"));
    }

    @Test
    void testInvalidPhoneNumber_WrongLength() {
        assertFalse(login.checkPhoneNumber("+2783123456"));
    }

    // ---------------- REGISTRATION TESTS ----------------
    @Test
    void testSuccessfulRegistration() {
        String result = login.registerUser("user_", "Pass@123", "John", "Doe", "+27831234567");
        assertEquals("The two above conditions have been met and the user has been registered successfully.", result);
    }

    @Test
    void testRegistrationFails_InvalidUsername() {
        String result = login.registerUser("user", "Pass@123", "John", "Doe", "+27831234567");
        assertEquals("Username is not correctly formatted...", result);
    }

    @Test
    void testRegistrationFails_InvalidPassword() {
        String result = login.registerUser("user_", "pass", "John", "Doe", "+27831234567");
        assertEquals("Password is not correctly formatted...", result);
    }

    @Test
    void testRegistrationFails_InvalidPhone() {
        String result = login.registerUser("user_", "Pass@123", "John", "Doe", "0831234567");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    // ---------------- LOGIN TESTS ----------------
    @Test
    void testSuccessfulLogin() {
        login.registerUser("user_", "Pass@123", "John", "Doe", "+27831234567");
        assertTrue(login.loginUser("user_", "Pass@123"));
    }

    @Test
    void testFailedLogin() {
        login.registerUser("user_", "Pass@123", "John", "Doe", "+27831234567");
        assertFalse(login.loginUser("user_", "wrongPass"));
    }

    // ---------------- LOGIN STATUS TESTS ----------------
    @Test
    void testReturnLoginStatus_Success() {
        login.registerUser("user_", "Pass@123", "John", "Doe", "+27831234567");
        String message = login.returnLoginStatus(true);
        assertEquals("Welcome John, Doe it is great to see you again.", message);
    }

    @Test
    void testReturnLoginStatus_Failure() {
        String message = login.returnLoginStatus(false);
        assertEquals("Username or password incorrect, please try again.", message);
    }
}
