package com.example.feature1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.google.android.gms.maps.GoogleMap;

import org.junit.Test;
import org.mockito.Mock;

public class Feature1WhiteBoxTests {
    String userID = "R4Gtu8xdrDbbriUrMvaoONYRYRh2";
    GoogleMap mapTest; // google map to compare with
    @Mock
    LoginActivity loginActivity = mock(LoginActivity.class);

    @Mock
    MapPage mapPage = mock(MapPage.class);

    @Test
    public void checkEmail() // makes sure that the email was correctly set
    {
        assertEquals("Bell@usc.edu", loginActivity.inputName);
    }

    @Test
    public void checkPassword() // makes sure that the password was correctly set
    {
        assertEquals("JonBell", loginActivity.inputPassword);
    }

    @Test
    public void userAndPassNotNull() // checks that the username and password are not empty
    {
        assertTrue(loginActivity.emailAndPasswordSet(loginActivity.inputName, loginActivity.inputPassword));
    }

    @Test
    public void userOrPassNull() // checks that the username or password is empty
    {
        assertFalse(loginActivity.emailAndPasswordSet(loginActivity.invalid, loginActivity.inputPassword));
    }

    @Test
    public void checkMapIsCreated() // at the beginning the string should be empty
    {
        assertEquals(mapPage.map, mapTest);
    }
}
