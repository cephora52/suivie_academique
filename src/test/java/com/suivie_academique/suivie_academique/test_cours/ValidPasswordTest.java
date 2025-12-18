package com.suivie_academique.suivie_academique.test_cours;


import com.suivie_academique.suivie_academique.utils.PassWordValidator;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidPasswordTest {

    static String password = "12345";

    @BeforeEach
    void beforeTestValid(){
        System.out.println("Travail avant le test");
        password = password+ "6";
    }

     @AfterEach
     void afterTestValid(){
        System.out.println("Fin du test"+ password);

     }

    @Test
    public void testPassword(){

        Assertions.assertEquals( true, PassWordValidator.isValid("123456"),"Verifier la longueur de votre mode de passe");

    }
}
