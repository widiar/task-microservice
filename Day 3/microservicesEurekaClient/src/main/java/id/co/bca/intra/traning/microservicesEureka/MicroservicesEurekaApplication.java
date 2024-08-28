package id.co.bca.intra.traning.microservicesEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicesEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesEurekaApplication.class, args);
	}

}
