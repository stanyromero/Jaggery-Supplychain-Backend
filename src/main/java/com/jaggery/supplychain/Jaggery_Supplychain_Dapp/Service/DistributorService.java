package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service;

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
public class DistributorService {

    private final SupplyChainPOS contract;
    @SuppressWarnings("unused")
    private final Web3j web3j;
    @SuppressWarnings("unused")
    private final Credentials credentials;

    public DistributorService(
            @Qualifier("web3jDistributor") Web3j web3j,
            @Qualifier("distributorCredentials") Credentials credentials,
            @Value("${blockchain.contractAddress}") String contractAddress) {
        this.web3j = web3j;
        this.credentials = credentials;
        this.contract = SupplyChainPOS.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }

    /**
     * Updates the arrival date of goods at the distributor.
     *
     * @param uuid The UUID of the goods.
     * @param arrivalDate The arrival date as a Unix timestamp.
          * @param string 
          * @return A success message.
          * @throws Exception If the transaction fails.
          */
          public String updateArrivalDateToDistributor(String uuid, String arrivalDate) throws Exception {
            try {
                // Remove the "0x" prefix if present
                if (uuid.startsWith("0x")) {
                    uuid = uuid.substring(2);
                }
        
                // Ensure the UUID is 64 characters long (32 bytes in hex)
                if (uuid.length() != 64) {
                    throw new IllegalArgumentException("UUID must be 64 characters long (32 bytes in hex)");
                }
        
                // Convert the UUID from hex string to byte array
                byte[] uuidBytes = hexStringToByteArray(uuid);
        
                // Send the transaction to update the arrival date
                TransactionReceipt receipt = contract.enterArrivalDateToDistributor(
                    uuidBytes, // byte[] _uuid
                    arrivalDate // String _arrivalDate
                ).send();
        
                // Extract the DistributorArrivalDateEntry event from the transaction receipt
                @SuppressWarnings("static-access")
                List<SupplyChainPOS.DistributorArrivalDateEntryEventResponse> events = contract.getDistributorArrivalDateEntryEvents(receipt);
                if (events.isEmpty()) {
                    throw new Exception("No DistributorArrivalDateEntry event found in the transaction receipt.");
                }
        
                return "Arrival date updated successfully";
            } catch (Exception e) {
                // Extract the revert reason from the exception message
                String errorMessage = e.getMessage();
                if (errorMessage.contains("Goods must be verified by OFA before updating arrival date")) {
                    throw new Exception("Goods must be verified by OFA before updating arrival date");
                } else {
                    throw new Exception("Error updating arrival date: " + errorMessage);
                }
            }
        }
        
        private byte[] hexStringToByteArray(String s) {
            if (s.startsWith("0x")) {
                s = s.substring(2); // Remove the "0x" prefix
            }
            if (s.length() != 64) {
                throw new IllegalArgumentException("UUID must be 64 characters long (32 bytes in hex)");
            }
            byte[] data = new byte[32];
            for (int i = 0; i < 64; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            return data;
        }
}