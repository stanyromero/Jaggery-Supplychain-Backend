package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class FSSAI1Config {

    @Value("${blockchain.nodeUrl}")
    private String nodeUrl;

    @Value("${blockchain.fssai1.privateKey}")
    private String fssai1PrivateKey;

    @Bean(name = "web3jFSSAI1")
    public Web3j web3jFSSAI1() {
        return Web3j.build(new HttpService(nodeUrl));
    }

    @Bean(name = "fssai1Credentials")
    public Credentials fssai1Credentials() {
        return Credentials.create(fssai1PrivateKey);
    }
}
