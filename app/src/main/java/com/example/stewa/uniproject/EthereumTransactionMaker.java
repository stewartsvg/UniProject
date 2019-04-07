package com.example.stewa.uniproject;

/** web3j android framework source available from <a href="https://github.com/web3j/web3j/tree/android"></a>*/
import android.os.AsyncTask;

import com.example.stewa.uniproject.contracts.UserAddressBook;

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
public class EthereumTransactionMaker {

    TransactionDetails transactionDetails;

    public EthereumTransactionMaker(TransactionDetails transactionDetails){
        this.transactionDetails = transactionDetails;
    }



    //address of the ganache test server, hosted on the wifi-address of mobile hotspot
    private final static String GANACHE_ADDRESS = "http://192.168.43.231:8545";

    //these are the addresses and private keys of the first 2 pre-created addresses on my Ganache
    //test network, and cannot be changed
    private final static String ADDRESS_1 = "0x0088b08aBab996eF368FF7F4c6c1834B5F348C72";
    private final static String ADDRESS_1_PRIVATE_KEY = "23de68a8c5e9759e65dc5f1f4ca75a9a3e76c94eb8c0cb276fa1dc14b9936d9d";
    private final static String ADDRESS_2 = "0xC408093B7EbEe303d3f9D64be6DAC134bdfC2B7b";
    private final static String ADDRESS_2_PRIVATE_KEY = "fe95d8bee9ecc51ba648f9bfcceecbce03d47f15ffab25922328e9348cfc99a7";
    private final static String ADDRESS_3 = "0x4Ff3847eD1511F69ce20e3b09c626871D0FEaEa1";
    private final static String ADDRESS_3_PRIVATE_KEY = "37a70f9c305dc666c4f9a77eaa97da6803a9fe1912e32b3570c4480b5508f131";

    private static String CONTRACT_ADDRESS;
    private boolean contractIsLoaded = false;

    //gas is the cost to execute a transaction on the Ethereum blockchain network, GAS_PRICE is the price per unit of gas
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
            return "Error getting client version";
        }
    }

    //returns true is successful transaction is completed
    public boolean successfulTransaction(){
        String transactionHashString = executeTransactionBetweenBuyerAndSeller();
        if(!transactionHashString.equals("Error completing transaction")){
            return true;
        }else return false;
    }

    //sends the transactionValue quantity of Ether from sender's wallet to receiver's wallet
    //and returns the transaction hash as a string
    public String executeTransactionBetweenBuyerAndSeller(){
    try {
        TransactionManager senderTransactionManager = prepareTransaction(transactionDetails.getSenderPrivateKey());
        TransactionReceipt transactionReceipt = executeTransaction(senderTransactionManager,transactionDetails.getReceiverWalletAddress(),transactionDetails.getTransactionValueTotal());
        String transactionHash = transactionReceipt.getTransactionHash();

        System.out.println("Transaction Hash = "+ transactionHash);
        return  "Transaction successful: "+transactionDetails.getTransactionValueTotal()+
                " Ether sent from "+transactionDetails.getSenderWalletAddress()+
                " to "+transactionDetails.getReceiverWalletAddress()+"\n" +
                "Transaction Hash: "+transactionHash;
    }catch (Exception e){
        e.printStackTrace();
    }return "Error completing transaction";
    }

    //adds addresses to User, addresses hardcoded for purposes of the demonstration, then outputs from
    //the data created on the blockchain by the contract
    public String addTransactionDetailsToTransactionHash(String transactionReceiptHash){
        try {
//            ProductOrderDetailsContract productOrderDetailsContract = getUserAddressBookFromCredentials(getPrivateKeyCredentials(transactionDetails.getSenderPrivateKey()));
//            productOrderDetailsContract.addProductOrderDetails(transactionReceiptHash, productOrderDetailsString).sendAsync().get();
//
//            for(Object productOrderDetails : userAddressBook.getUserAddresses().sendAsync().get()){
//                String userAddressString = userAddress.toString();
//                String userName = userAddressBook.getUserName(userAddressString).sendAsync().get();
//                System.out.println("Address : "+userAddressString+" Username : "+userName);
//            }
            return "Successfully added users";
        }catch (Exception e){
            e.printStackTrace();
        }return "Error adding users to address book";
    }


    //adds addresses to User, addresses hardcoded for purposes of the demonstration, then outputs from
    //the data created on the blockchain by the contract
    public String addUsersToUserAddressBook(String user){
        try {
            UserAddressBook userAddressBook = getUserAddressBookFromCredentials(getPrivateKeyCredentials(ADDRESS_1_PRIVATE_KEY));
            userAddressBook.addUserAddress(ADDRESS_1, "Stewart").sendAsync().get();
            userAddressBook.addUserAddress(ADDRESS_2, "User2").sendAsync().get();
            userAddressBook.addUserAddress(ADDRESS_3,user).sendAsync().get();

            for(Object userAddress : userAddressBook.getUserAddresses().sendAsync().get()){
                String userAddressString = userAddress.toString();
                String userName = userAddressBook.getUserName(userAddressString).sendAsync().get();
                System.out.println("Address : "+userAddressString+" Username : "+userName);
            }
            return "Successfully added users";
        }catch (Exception e){
            e.printStackTrace();
        }return "Error adding users to address book";
    }

//TRANSACTION METHODS
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
                    GAS_LIMIT)
                    .sendAsync()
                    .get();
            return transactionReceipt;
        }catch (Exception e){
            e.printStackTrace();
        }return transactionReceipt;
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



//CONTRACT METHODS
    //deploys the UserAddressBook contract using the provided address credentials and returns its contract address
    private String deployUserAddressBookContract(Credentials credentials){
        try {
            String UserAddressBookContractAddress = UserAddressBook.deploy(web3jInstance, credentials, GAS_PRICE, GAS_LIMIT).sendAsync()
                    .get()
                    .getContractAddress();
            return UserAddressBookContractAddress;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private UserAddressBook loadUserAddressBookContract(String userAddressBookContractAddress,Credentials credentials) {
        try {
            UserAddressBook userAddressBook = UserAddressBook.load(userAddressBookContractAddress, web3jInstance, credentials, GAS_PRICE, GAS_LIMIT);
            return userAddressBook;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //returns a UserAddressBook from the contract, which can be interacted with to add or remove addresses
    private UserAddressBook getUserAddressBookFromCredentials(Credentials credentials){
        String contractAddress = getContractAddress();
        UserAddressBook userAddressBook = loadUserAddressBookContract(contractAddress,credentials);
        return userAddressBook;
    }

    //gets the contract address. Deploys contract if it has not already been deployed
    //only needs to be deployed once.
    //after it is deployed it can be loaded and interacted with
    private String getContractAddress(){
        if(contractIsLoaded) {
            return CONTRACT_ADDRESS;
        }else
            CONTRACT_ADDRESS = deployUserAddressBookContract(getPrivateKeyCredentials(ADDRESS_1_PRIVATE_KEY));
        contractIsLoaded = true;
        return CONTRACT_ADDRESS;
    }

    private class EthereumNetworkTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Web3j web3jInstance = Web3jFactory.build(new HttpService(GANACHE_ADDRESS));
            return null;
        }
    }

}
