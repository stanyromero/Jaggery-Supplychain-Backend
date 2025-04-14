package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class FSSAI2Config {

    @Value("${blockchain.nodeUrl}")
    private String nodeUrl;

    @Value("${blockchain.fssai2.privateKey}")
    private String fssai2PrivateKey;

    @Bean(name = "web3jFSSAI2")
    public Web3j web3jFSSAI2() {
        return Web3j.build(new HttpService(nodeUrl));
    }

    @Bean(name = "fssai2Credentials")
    public Credentials fssai2Credentials() {
        return Credentials.create(fssai2PrivateKey);
    }
}
