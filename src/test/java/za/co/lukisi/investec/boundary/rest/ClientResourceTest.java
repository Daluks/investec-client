package za.co.lukisi.investec.boundary.rest;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import za.co.lukisi.investec.control.ClientRegistry;

import java.io.IOException;


@RunWith(MockitoJUnitRunner.class)
public class ClientResourceTest extends TestCase {

    @InjectMocks
    ClientResource clientResource;

    @InjectMocks
    ClientRegistry clientRegistry;

    @Test
    public void test_pingPong() throws IOException {
    }


}