package com.crovate.kitchensink;

import junit.framework.TestCase;

public class TestValidator extends TestCase {
    
    Validator validator;
    
    public TestValidator(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        validator = new Validator();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        validator = null;
    }
    
    
    public void testValidNames() {
        String[] validNames = new String[] { "alarm",
                                              "alarm:abc",
                                              "alarm:abc:cab",
                                              "alarm:abc4",
                                              "alarm:abc:cab:xyx:fgh1"};
        
        for (int i=0; i< validNames.length; i++) {
            assertTrue("This name should have been valid: " + validNames[i], 
                    validator.validate(validNames[i]));
        }  
    }
    
    public void testInvalidNames() {
        /**
         * An array of invalid names.
         */
        String[] validNames = new String[] { "alarm:",
                                              "alarm:4abc",
                                              "alarm:abc.cab",
                                              "alarm:abc:xyz:1",
                                              "alarm:abc:xyz:",
                                              "abc",
                                              "1",
                                              "alarm.com.somealarm"};
        
        for (int i=0; i< validNames.length; i++) {
            assertFalse("This name should have been invalid: " + validNames[i], 
                    validator.validate(validNames[i]));
        } 
        
        // Test length
        String longName = "alarm:abc";
        
        for (int i=0; i <= 256; i++) {
            longName += "a";
        }
        assertFalse("This name should have been invalid: " + longName, 
                    validator.validate(longName));
    }
}
