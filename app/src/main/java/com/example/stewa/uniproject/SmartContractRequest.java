package com.example.stewa.uniproject;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

//makes synchronous requests
public class SmartContractRequest {

    private final static String ganacheDefaultAddress = "http://localhost:7545";


    public void makeRequest() {
        try {
            Web3j web3 = Web3jFactory.build(new HttpService(ganacheDefaultAddress));
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        }catch(IOException ioException){
            System.out.println(ioException);
        }
    }









}
