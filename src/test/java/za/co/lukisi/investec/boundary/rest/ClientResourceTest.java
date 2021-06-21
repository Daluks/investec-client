package za.co.lukisi.investec.boundary.rest;

import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ClientResourceTest extends TestCase {

    @Test
    public void test_pingPong() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost/ping");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
    }

}