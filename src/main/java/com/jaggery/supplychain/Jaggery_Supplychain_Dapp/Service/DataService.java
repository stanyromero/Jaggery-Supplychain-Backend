package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple12;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.gas.DefaultGasProvider;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Contract.SupplyChainPOS;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.BatchMetaDataDTO;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.InspectionDataDTO;


@Service
public class DataService {
    private final SupplyChainPOS contract;
    @SuppressWarnings("unused")
    private final Web3j web3j;
    @SuppressWarnings("unused")
    private final Credentials credentials;

    public DataService(
            @Qualifier("web3jDistributor") Web3j web3j,
            @Qualifier("distributorCredentials") Credentials credentials,
            @Value("${blockchain.contractAddress}") String contractAddress) {
        this.web3j = web3j;
        this.credentials = credentials;
        this.contract = SupplyChainPOS.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }

    /**
     * Fetches goods details by UUID.
     *
     * @param uuid The UUID of the batch.
     * @return A GoodsDetailsDTO containing goods details.
     * @throws Exception If fetching goods details fails.
     */
    @SuppressWarnings("deprecation")
    public BatchMetaDataDTO getBatchDataByID(String uuid) throws Exception {
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

            // Fetch goods details from the smart contract
            Tuple6<String, BigInteger, String, String, String, Boolean> batchDetails1 = contract.getBatchMeta(uuidBytes).send();
            Tuple12<String, String, String, Boolean, String, String, String, String, String, Boolean, String, String> batchDetails2 = contract.getInspectionDetails(uuidBytes).send();

            // Map details to DTO
            BatchMetaDataDTO batchDetails = new BatchMetaDataDTO();

            batchDetails.setUuid("0x" + uuid);
            batchDetails.setDateOfProduction(batchDetails1.getValue1());
            batchDetails.setQuantityProduced(batchDetails1.getValue2());
            batchDetails.setArrivalDateToDistributor(batchDetails1.getValue3());
            batchDetails.setProducer(batchDetails1.getValue4());
            batchDetails.setDistributor(batchDetails1.getValue5());
            batchDetails.setIsArrivedToDistributor(batchDetails1.getValue6());

            // Map inspection 1
            InspectionDataDTO inspection1 = new InspectionDataDTO();
            inspection1.setInspectorId(batchDetails2.getValue1());
            inspection1.setDateOfInspection(batchDetails2.getValue2());
            inspection1.setModeOfInspection(batchDetails2.getValue3());
            inspection1.setSampleTested(batchDetails2.getValue4());
            inspection1.setApprovalStatus(batchDetails2.getValue5());
            inspection1.setRemarks(batchDetails2.getValue6());
            batchDetails.setInspectionByFSSAI1(inspection1);

            // Map inspection 2
            InspectionDataDTO inspection2 = new InspectionDataDTO();
            inspection2.setInspectorId(batchDetails2.getValue7());
            inspection2.setDateOfInspection(batchDetails2.getValue8());
            inspection2.setModeOfInspection(batchDetails2.getValue9());
            inspection2.setSampleTested(batchDetails2.getValue10());
            inspection2.setApprovalStatus(batchDetails2.getValue11());
            inspection2.setRemarks(batchDetails2.getValue12());
            batchDetails.setInspectionByFSSAI2(inspection2);

            return batchDetails;

        } catch (Exception e) {
            throw new Exception("Error fetching goods details: " + e.getMessage(), e);
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
