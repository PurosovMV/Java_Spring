package PMV.issue_service;

import com.netflix.discovery.EurekaClient;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component

public class BookProvider {
    private final WebClient webClient;
    Book randomBook;


    public BookProvider(ReactorLoadBalancerExchangeFilterFunction reactorLoadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(reactorLoadBalancerExchangeFilterFunction)
                .build();


    }

    public UUID getRandomBookId() {
        randomBook = webClient.get()
                .uri("http://book-service/book/random")
                .retrieve()
                .bodyToMono(Book.class)
                .block();

        return randomBook.getId();
    }

    public String getRandomBookName() {
        return randomBook.getName();
    }


}