package com.example.stewa.uniproject.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class UserAddressBook extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506106b9806100206000396000f3fe6080604052600436106100615763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416639deca1b08114610066578063a2af447a1461012b578063a591efde1461015e578063d84f55ee146101c3575b600080fd5b34801561007257600080fd5b506101296004803603604081101561008957600080fd5b600160a060020a0382351691908101906040810160208201356401000000008111156100b457600080fd5b8201836020820111156100c657600080fd5b803590602001918460018302840111640100000000831117156100e857600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061026b945050505050565b005b34801561013757600080fd5b506101296004803603602081101561014e57600080fd5b5035600160a060020a03166102d8565b34801561016a57600080fd5b5061017361046e565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156101af578181015183820152602001610197565b505050509050019250505060405180910390f35b3480156101cf57600080fd5b506101f6600480360360208110156101e657600080fd5b5035600160a060020a03166104d8565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610230578181015183820152602001610218565b50505050905090810190601f16801561025d5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3360008181526020818152604080832080546001808201835591855283852001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a038916908117909155948452825280832093835292815291902082516102d39284019061058a565b505050565b33600090815260208190526040812054905b818110156102d35733600090815260208190526040902080548290811061030d57fe5b600091825260209091200154600160a060020a03848116911614156104665733600090815260208190526040902054600110801561034d57506001820381105b156103d2573360009081526020819052604090208054600019840190811061037157fe5b60009182526020808320909101543383529082905260409091208054600160a060020a0390921691839081106103a357fe5b9060005260206000200160006101000a815481600160a060020a030219169083600160a060020a031602179055505b336000908152602081905260409020805460001984019081106103f157fe5b60009182526020808320909101805473ffffffffffffffffffffffffffffffffffffffff1916905533825281905260409020805490610434906000198301610608565b50336000908152600160209081526040808320600160a060020a038716845290915281206104619161062c565b6102d3565b6001016102ea565b33600090815260208181526040918290208054835181840281018401909452808452606093928301828280156104cd57602002820191906000526020600020905b8154600160a060020a031681526001909101906020018083116104af575b505050505090505b90565b336000908152600160208181526040808420600160a060020a038616855282529283902080548451600294821615610100026000190190911693909304601f8101839004830284018301909452838352606093909183018282801561057e5780601f106105535761010080835404028352916020019161057e565b820191906000526020600020905b81548152906001019060200180831161056157829003601f168201915b50505050509050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105cb57805160ff19168380011785556105f8565b828001600101855582156105f8579182015b828111156105f85782518255916020019190600101906105dd565b50610604929150610673565b5090565b8154818355818111156102d3576000838152602090206102d3918101908301610673565b50805460018160011615610100020316600290046000825580601f106106525750610670565b601f0160209004906000526020600020908101906106709190610673565b50565b6104d591905b80821115610604576000815560010161067956fea165627a7a72305820615e125d2447d488db346f799552a510422a90fce88f9d381bb5a8c5cf9790520029";

    public static final String FUNC_ADDUSERADDRESS = "addUserAddress";

    public static final String FUNC_REMOVEUSERADDRESS = "removeUserAddress";

    public static final String FUNC_GETUSERADDRESSES = "getUserAddresses";

    public static final String FUNC_GETUSERNAME = "getUserName";

    protected UserAddressBook(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserAddressBook(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> addUserAddress(String userAddress, String userName) {
        final Function function = new Function(
                FUNC_ADDUSERADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress), 
                new org.web3j.abi.datatypes.Utf8String(userName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeUserAddress(String userAddress) {
        final Function function = new Function(
                FUNC_REMOVEUSERADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getUserAddresses() {
        final Function function = new Function(FUNC_GETUSERADDRESSES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<String> getUserName(String userAddress) {
        final Function function = new Function(FUNC_GETUSERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<UserAddressBook> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserAddressBook.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<UserAddressBook> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserAddressBook.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static UserAddressBook load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserAddressBook(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static UserAddressBook load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserAddressBook(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
