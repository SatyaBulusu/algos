import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyUrl {
    private final static int RANGE = 1000000;
    private static final String CHAR_POOL = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
    private static final int POOL_LEN = CHAR_POOL.length();
    private static final String BASE_URL = "http://tinyurl.com/";
    Map<String, String> map = new HashMap<>();
    Random r = new Random();

    // Encodes a URL to a shortened URL.
    public String encode(final String longUrl) {
        final int key = r.nextInt(RANGE);
        System.out.println(key);

        final String encodedKey = encodeKey(key);
        map.put(encodedKey, longUrl);
        return BASE_URL + encodedKey;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(final String shortUrl) {
        return map.get(shortUrl.replace(BASE_URL, ""));
    }

    private String encodeKey(int num) {
        final StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.insert(0, CHAR_POOL.charAt(num % POOL_LEN));
            num = num / POOL_LEN;
        }
        return sb.toString();
    }

    public static void main(final String args[]) {
        final TinyUrl tu = new TinyUrl();
        System.out.println(tu.encode("http://www.google.com"));
    }
}
