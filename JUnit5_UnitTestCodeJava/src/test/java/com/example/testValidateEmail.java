package com.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class testValidateEmail {
    @Test
    public void test1(){
        validateEmail test1 = new validateEmail("nhatm@gmail.com");
        assertTrue(test1.isValid());
    }
    @Test
    public void test2(){
        validateEmail test2 = new validateEmail("nhatm@");
        assertFalse(test2.isValid());
    }
    @Test
    public void test3(){
        validateEmail test3 = new validateEmail("nhatm.com");
        assertFalse(test3.isValid());
    }
    @Test
    public void test4(){
        validateEmail test4 = new validateEmail("nhatm$@gmail.com");
        assertFalse(test4.isValid());
    }
    @Test
    public void test5(){
        validateName test5 = new validateName(" nhatm@gmail.com");
        assertFalse(test5.isValid());
    }
    @Test
    public void test6(){
        validateName test5 = new validateName("nhatm$@gmail.com ");
        assertFalse(test5.isValid());
    }
    @Test
    public void test7(){
        validateName test5 = new validateName("nhatm123.@gmail.com");
        assertFalse(test5.isValid());
    }
    @Test
    public void test8(){
        validateName test5 = new validateName("nhatm(aa@gmail.com");
        assertFalse(test5.isValid());
    }
    @Test
    public void test9(){
        validateName test5 = new validateName("nhatm@gmail.com$");
        assertFalse(test5.isValid());
    }
    @Test
    public void test10(){
        validateName test5 = new validateName("nhatm@gmail.com..");
        assertFalse(test5.isValid());
    }
    @Test
    public void test11(){
        validateName test5 = new validateName("nhatm@gmail.com+");
        assertFalse(test5.isValid());
    }
    @Test
    public void test12(){
        validateName test5 = new validateName("nhatm*@gmail.com");
        assertFalse(test5.isValid());
    }

}
