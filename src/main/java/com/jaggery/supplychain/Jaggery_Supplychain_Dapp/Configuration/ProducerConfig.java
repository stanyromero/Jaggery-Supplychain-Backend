package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class ProducerConfig {

    @Value("${blockchain.nodeUrl}")
    private String nodeUrl;

    @Value("${blockchain.producer.privateKey}")
    private String producerPrivateKey;

    @Bean(name = "web3jProducer")
    public Web3j web3jProducer() {
        return Web3j.build(new HttpService(nodeUrl));
    }

    @Bean(name = "producerCredentials")
    public Credentials producerCredentials() {
        return Credentials.create(producerPrivateKey);
    }
}
