package dev.sstol.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sergey Stol
 * 2024-07-13
 */
public class HttpClientService {
   private static final Logger log = Logger.getLogger(HttpClientService.class.getName());
   private static final String ADDRESS = "https://gist.githubusercontent.com/kissarat/bd30c324439cee668f0ac76732d6c825/raw/147eecc9a86ec7f97f6dd442c2eda0641ddd78dc/russian-mnemonic-words.txt";

   private Predicate<String> predicate = (line) -> line.length() > 5 && line.length() < 9;

   public HttpClientService(Predicate<String> predicate) {
      this.predicate = predicate;
   }

   public String[] fetch() {
      try (HttpClient httpClient = HttpClient.newHttpClient()) {
         HttpRequest httpRequest = HttpRequest.newBuilder()
           .uri(URI.create(ADDRESS))
           .build();
         HttpResponse<String[]> response = httpClient.send(httpRequest, customBodyHandler(predicate));
         return response.body();
      } catch (Exception e) {
         log.log(Level.SEVERE, "Exception occurred during HTTP fetch", e);
      }
      return new String[0];
   }

   private static HttpResponse.BodyHandler<String[]> customBodyHandler(Predicate<String> predicate) {
      return responseInfo -> HttpResponse.BodySubscribers.mapping(
        HttpResponse.BodySubscribers.ofInputStream(),
        inputStream -> new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
          .lines()
          .filter(predicate)
          .toArray(String[]::new)
      );
   }
}
