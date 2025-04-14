package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class DistributorConfig {

    @Value("${blockchain.nodeUrl}")
    private String nodeUrl;

    @Value("${blockchain.distributor.privateKey}")
    private String distributorPrivateKey;

    @Bean(name = "web3jDistributor")
    public Web3j web3jDistributor() {
        return Web3j.build(new HttpService(nodeUrl));
    }

    @Bean(name = "distributorCredentials")
    public Credentials distributorCredentials() {
        return Credentials.create(distributorPrivateKey);
    }
}