package com.example.stewa.uniproject;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

//makes synchronous requests
public class EthereumRequest {

    //address of the ganache test server, hosted on the wifi-address of my mobile hotspot
    private final static String GANACHE_ADDRESS = "http://192.168.43.231:8545";

    //these are the addresses and private keys of the first 2 pre-created addresses on my Ganache
    //test network, and cannot be changed
    private final static String ADDRESS_1 = "0x0088b08aBab996eF368FF7F4c6c1834B5F348C72";
    private final static String ADDRESS_1_PRIVATE_KEY = "23de68a8c5e9759e65dc5f1f4ca75a9a3e76c94eb8c0cb276fa1dc14b9936d9d";
    private final static String ADDRESS_2 = "0xC408093B7EbEe303d3f9D64be6DAC134bdfC2B7b";
    private final static String ADDRESS_2_PRIVATE_KEY = "fe95d8bee9ecc51ba648f9bfcceecbce03d47f15ffab25922328e9348cfc99a7";

    //gas is the cost to execute a transation on the network, GAS_PRICE is the price per unit of gas
    // and GAS_LIMIT is the maximum cost in gas of any 1 transaction
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(2000000000L);


    //establishes an instance of web3j that can be used to interact with the test Ethereum network
    private Web3j web3jInstance = Web3jFactory.build(new HttpService(GANACHE_ADDRESS));

    //gets the version of Ethereum being used by the server (e.g. test server uses EthereumJS TestRPC/v2.1.0/ethereum-js)
    public String getClientVersion() {

        Web3ClientVersion web3ClientVersion = null;
        try {
           web3ClientVersion = web3jInstance.web3ClientVersion().send();
           String clientVersionString = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("Client version is: "+clientVersionString);
            return clientVersionString;
        }catch(IOException ioException) {
            ioException.printStackTrace();
            return "Error";
        }
    }
    //sends the transactionValue quantity of Ether from Address 1 to Address 2 and returns the
    //transaction hash as a string
    public String executeAddress1toAddress2Transaction(double transactionValue){
    try {
        TransactionManager address1TransactionManager = prepareTransaction(ADDRESS_1_PRIVATE_KEY);
        TransactionReceipt transactionReceipt = executeTransaction(address1TransactionManager,ADDRESS_2,transactionValue);
        String transactionHash = transactionReceipt.getTransactionHash();
        System.out.println("Transaction Hash = "+ transactionHash);
        return  "Transaction successful: "+transactionValue+" Ether sent\n" +
                "Transaction Hash: "+transactionHash;
    }catch (Exception e){
        e.printStackTrace();
    }return "Error completing transaction";
    }


    //this method will only be used in the public Ethereum network, not the test server
    private Credentials getWalletCredentials() throws IOException, CipherException {

        Credentials credentials = WalletUtils.loadCredentials(
                "password",
                "walletPathString"
        );
        return credentials;
    }

    //creates credentials using private key of an address on the network, for the test server
    private Credentials getPrivateKeyCredentials(String addressPrivateKey){

        return Credentials.create(addressPrivateKey);
    }

    //sets up TransactionManager which includes sender's credentials
    private TransactionManager prepareTransaction(String addressPrivateKey){

        TransactionManager transactionManager = new RawTransactionManager(web3jInstance, getPrivateKeyCredentials(addressPrivateKey));
        return transactionManager;
    }

    //Creates a transaction and returns its receipt sends Ether from the private key address in the
    // TransactionManager to the recipient address
    private TransactionReceipt executeTransaction(TransactionManager transactionManager, String recipientAddress, double transactionValue){

        BigDecimal bigDecimalTransactionValue = BigDecimal.valueOf(transactionValue);
        Transfer transfer = new Transfer(web3jInstance,transactionManager);
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = transfer.sendFunds(
                    recipientAddress,
                    bigDecimalTransactionValue,
                    Convert.Unit.ETHER,
                    GAS_PRICE,
                    GAS_LIMIT).sendAsync().get();
            return transactionReceipt;
        }catch (Exception e){
            e.printStackTrace();
        }return transactionReceipt;
    }
}
