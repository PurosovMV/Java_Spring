package PMV.issue_service;

import PMV.timer.Timer;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component

public class ReaderProvider {
    private  WebClient webClient;
    Reader randomReader;



    public ReaderProvider(ReactorLoadBalancerExchangeFilterFunction reactorLoadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(reactorLoadBalancerExchangeFilterFunction)
                .build();


    }
    @Timer
    public UUID getRandomReaderId() {
         randomReader = webClient.get()
                .uri("http://reader-service/reader/random")
                .retrieve()
                .bodyToMono(Reader.class)
                .block();
        return randomReader.getIdReader();
    }

    @Timer
    public String getRandomReaderFirstName() {

        return randomReader.getFirstName();
    }
    @Timer
    public String getRandomReaderLastName() {

        return randomReader.getLastName();
    }
}
