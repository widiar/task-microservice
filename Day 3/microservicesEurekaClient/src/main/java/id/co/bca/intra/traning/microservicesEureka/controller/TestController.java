package id.co.bca.intra.traning.microservicesEureka.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("coba")
    public String coba(){
        InstanceInfo instanceInfo = eurekaClient.getApplication(appName).getInstances().get(0);
        return "Hello from other: " + instanceInfo.getIPAddr() + " port: " + instanceInfo.getPort();
    }
}
