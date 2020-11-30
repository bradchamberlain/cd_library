package brad.chamberlain.library.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BooksControllerHttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void booksShouldReturnBooks() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/books",
                String.class)).contains("The Lord of the Rings:");
    }

    @Test
    public void bookShouldReturnBook() throws Exception
    {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/books/3",
                String.class)).contains("Good Night Moon");
    }
}