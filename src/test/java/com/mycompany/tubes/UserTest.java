package com.mycompany.tubes;

import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void signUpShouldAddNewUser() {
        HashMap<Integer, User> userMap = new HashMap<>();
        User user = new User(1, "rade", "rade@example.com", "rade12");

        boolean result = user.signUp(userMap);

        assertTrue(result);
        assertEquals(1, userMap.size());
        assertSame(user, userMap.get(1));
    }

    @Test
    void signUpShouldRejectDuplicateUsername() {
        HashMap<Integer, User> userMap = new HashMap<>();
        User firstUser = new User(1, "rade", "rade@example.com", "rade12");
        User duplicateUser = new User(2, "rade", "rade2@example.com", "pass2");
        firstUser.signUp(userMap);

        boolean result = duplicateUser.signUp(userMap);

        assertFalse(result);
        assertEquals(1, userMap.size());
    }

    @Test
    void loginShouldReturnUserForValidCredential() {
        HashMap<Integer, User> userMap = new HashMap<>();
        User user = new User(1, "rade", "rade@example.com", "rade12");
        userMap.put(user.getId(), user);
        Scanner scanner = new Scanner("rade\nrade12\n");

        User loggedIn = User.login(userMap, scanner);

        assertNotNull(loggedIn);
        assertEquals("Aldo", loggedIn.getUsername());
    }

    @Test
    void loginShouldReturnNullForInvalidCredential() {
        HashMap<Integer, User> userMap = new HashMap<>();
        userMap.put(1, new User(1, "rade", "rade@example.com", "rade12"));
        Scanner scanner = new Scanner("rade\nsalah\n");

        User loggedIn = User.login(userMap, scanner);

        assertNull(loggedIn);
    }
}
