
package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.utils.AuthUtil;

public class AuthTest extends BaseTest {

    @Test
    public void verifyTokenGeneration() {

        String token = AuthUtil.generateToken(
                requestSpec, username, password);

        System.out.println("Generated Token: " + token);

        Assert.assertNotNull(token);
        Assert.assertFalse(token.isEmpty());
    }
}
