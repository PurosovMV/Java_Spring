package PMV.issue_service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component

public class BookProvider {
    private final WebClient webClient;
    // private EurekaClient client;

    public BookProvider(EurekaClient client, ReactorLoadBalancerExchangeFilterFunction reactorLoadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(reactorLoadBalancerExchangeFilterFunction)
                .build();

        //      this.client = client;
    }

    public UUID getRandomBookId() {
        Book randomBook = webClient.get()
                // .uri(getBookServiceIp() + "/book/random")
                .uri("http://book-service/book/random")
                .retrieve()
                .bodyToMono(Book.class)
                .block();
        // assert randomBook != null;
        return randomBook.getId();
    }

 /*   private String getBookServiceIp() {
        Application application = client.getApplication("BOOK-SERVICE");
        List<InstanceInfo> instanceInfos = application.getInstances();
        Random random = new Random();
        InstanceInfo randomInstance = instanceInfos.get(random.nextInt(instanceInfos.size()));
        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort();
    }
*/

}
