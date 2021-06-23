package za.co.lukisi.investec.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IdNumberValidatorTest {

    IdNumberValidator idNumberValidator = new IdNumberValidator();

    public IdNumberValidatorTest(){}

    @Test
    public void when_valid_id_is_given_return_true(){

        boolean validIdNumber = idNumberValidator.isValidIdNumber("8409205652089");
        assertTrue(validIdNumber);

    }

}