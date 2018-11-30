package com.example.stewa.uniproject;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

//makes synchronous requests
public class SmartContractRequest {

    private final static String ganacheDefaultAddress = "http://192.168.43.231:8545";


    public String getClientVersion() {
        Web3j web3 = Web3jFactory.build(new HttpService(ganacheDefaultAddress));
        Web3ClientVersion web3ClientVersion = null;
        try {
           web3ClientVersion = web3.web3ClientVersion().send();
           String clientVersionString = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("Client version is: "+clientVersionString);
            return clientVersionString;
        }catch(IOException ioException) {
            ioException.printStackTrace();
            return "Error";
        }
    }









}
