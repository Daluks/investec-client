package za.co.lukisi.investec.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDNumberValidatorTest {

    IDNumberValidator idNumberValidator = new IDNumberValidator();

    @Test
    public void when_valid_id_is_given_return_true(){

        boolean validIdNumber = idNumberValidator.isValidIdNumber("8409205652089");
        assertTrue(validIdNumber);

    }

}