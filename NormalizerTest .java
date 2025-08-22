import org.junit.Test;
import static org.junit.Assert.*;

public class NormalizerTest {

    @Test
    public void testFullUrl() {
        String result = Normalizer.normalize("https://example.com:8888/a/b/c");
        assertEquals("https://example.com:8888/a/b/c", result);
    }

    @Test
    public void testOnlyDomain() {
        String result = Normalizer.normalize("example.com");
        assertEquals("http://example.com:80/", result);
    }
  
    @Test
    public void testNoDomain() {
        String result = Normalizer.normalize("http:///abc");
        assertEquals("http://localhost:80/abc", result);
    }
  
    @Test
    public void testEmptyInput() {
        String result = Normalizer.normalize("");
        assertEquals("http://localhost:80/", result);
    }

    @Test
    public void testNoPath() {
        String result = Normalizer.normalize("https://example.com:8080");
        assertEquals("https://example.com:8080/", result);
    }

    @Test
    public void testNoPort() {
        String result = Normalizer.normalize("https://example.com/abc");
        assertEquals("https://example.com:80/abc", result);
    }

    @Test
    public void testNoProtocol() {
        String result = Normalizer.normalize("example.com:8080/xyz");
        assertEquals("http://example.com:8080/xyz", result);
    }

    @Test
    public void testOnlyDomainAndPath() {
        String result = Normalizer.normalize("example.com/test");
        assertEquals("http://example.com:80/test", result);
    }

    @Test
    public void testOnlyLocalhost() {
        String result = Normalizer.normalize("localhost");
        assertEquals("http://localhost:80/", result);
    }
}
