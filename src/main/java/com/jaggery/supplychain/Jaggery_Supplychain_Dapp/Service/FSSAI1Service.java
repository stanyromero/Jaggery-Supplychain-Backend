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
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.InspectionDataByFSSAI1;

@Service
public class FSSAI1Service {
    private final SupplyChainPOS contract;
    @SuppressWarnings("unused")
    private final Web3j web3j;
    @SuppressWarnings("unused")
    private final Credentials credentials;

    public FSSAI1Service(
            @Qualifier("web3jFSSAI1") Web3j web3j,
            @Qualifier("fssai1Credentials") Credentials credentials,
            @Value("${blockchain.contractAddress}") String contractAddress) {
        this.web3j = web3j;
        this.credentials = credentials;
        this.contract = SupplyChainPOS.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }

    public String verifyBatch(InspectionDataByFSSAI1 data) throws Exception {
        String uuid = data.getUuid();

        if (uuid.startsWith("0x")) {
            uuid = uuid.substring(2);
        }

        if (uuid.length() != 64) {
            throw new IllegalArgumentException("UUID must be 64 characters long (32 bytes in hex)");
        }

        byte[] uuidBytes = hexStringToByteArray(uuid);

        if (data.getSampleTested() == null) {
            throw new IllegalArgumentException("sampleTested cannot be null");
        }

        TransactionReceipt receipt = contract.uploadInspectionByFSSAI1(
                uuidBytes,
                data.getInspectorId(),
                data.getDateOfInspection(),
                data.getModeOfInspection(),
                data.getSampleTested(),
                data.getApprovalStatus(),
                data.getRemarks()
        ).send();

        @SuppressWarnings("static-access")
        List<SupplyChainPOS.InspectionUploadedEventResponse> events = contract.getInspectionUploadedEvents(receipt);
        if (events.isEmpty()) {
            throw new Exception("No Batches Inspected event found in the transaction receipt.");
        }

        return "Uploaded inspected batch data successfully";
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
