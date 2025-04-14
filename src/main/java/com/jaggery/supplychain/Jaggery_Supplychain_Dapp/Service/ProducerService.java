package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Contract.SupplyChainPOS;

@Service
public class ProducerService {
    private final SupplyChainPOS contract;
    @SuppressWarnings("unused")
    private final Web3j web3j;
    @SuppressWarnings("unused")
    private final Credentials credentials;

    public ProducerService(
            @Qualifier("web3jProducer") Web3j web3j,
            @Qualifier("producerCredentials") Credentials credentials,
            @Value("${blockchain.contractAddress}") String contractAddress) {
        this.web3j = web3j;
        this.credentials = credentials;
        this.contract = SupplyChainPOS.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }

    /**
     * Registers batch in the supply chain.
     *
     * @param dateOfProduction The production date.
     * @param quantityProduced The quantity produced.
     * @return The UUID of the registered batch.
     * @throws Exception If the registration fails or no event is emitted.
     */
    public String registerBatch(String dateOfProduction, BigInteger quantityProduced) throws Exception {
        // Send the transaction to register batch
        TransactionReceipt receipt = contract.registerBatch(dateOfProduction, quantityProduced).send();

        // Extract the GoodsRegistered event from the transaction receipt
        @SuppressWarnings("static-access")
        List<SupplyChainPOS.BatchRegisteredEventResponse> events = contract.getBatchRegisteredEvents(receipt);
        if (events.isEmpty()) {
            throw new Exception("No GoodsBatch event found in the transaction receipt.");
        }

        // Convert the UUID to a hexadecimal string and return it
        return "0x" + new BigInteger(1, events.get(0).uuid).toString(16);
    }

    /**
     * Fetches all registered goods from the supply chain.
     *
     * @return A list of maps containing goods details.
     * @throws Exception If fetching goods fails.
     */
}
