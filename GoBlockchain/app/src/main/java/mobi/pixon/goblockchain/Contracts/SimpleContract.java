package mobi.pixon.goblockchain.Contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class SimpleContract extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b6040516107b03803806107b08339810160405280805182019190602001805182019190602001805160008054600160a060020a03191633600160a060020a0316179055919091019050600283805161006b92916020019061009c565b50600382805161007f92916020019061009c565b50600181805161009392916020019061009c565b50505050610137565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100dd57805160ff191683800117855561010a565b8280016001018555821561010a579182015b8281111561010a5782518255916020019190600101906100ef565b5061011692915061011a565b5090565b61013491905b808211156101165760008155600101610120565b90565b61066a806101466000396000f30060606040526004361061008d5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde03811461009257806341c0e1b51461011c5780635353a2d8146101315780635fc5beaf146101825780637a0a3ac5146101d3578063820e93f5146101e65780638da5cb5b146101f95780639b528b1614610228575b600080fd5b341561009d57600080fd5b6100a561023b565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100e15780820151838201526020016100c9565b50505050905090810190601f16801561010e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561012757600080fd5b61012f6102d9565b005b341561013c57600080fd5b61012f60046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506102f895505050505050565b341561018d57600080fd5b61012f60046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506103e595505050505050565b34156101de57600080fd5b6100a5610495565b34156101f157600080fd5b6100a5610500565b341561020457600080fd5b61020c61056b565b604051600160a060020a03909116815260200160405180910390f35b341561023357600080fd5b61012f61057a565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102d15780601f106102a6576101008083540402835291602001916102d1565b820191906000526020600020905b8154815290600101906020018083116102b457829003601f168201915b505050505081565b60005433600160a060020a039081169116146102f457600080fd5b6000ff5b60005433600160a060020a0390811691161461031357600080fd5b60028180516103269291602001906105a3565b507f4288c41517abc26e9eab0540d3726b3431a4e90c2c21b500fbfbc84635d4379b600230604051600160a060020a0382166020820152604080825283546002600019610100600184161502019091160490820181905281906060820190859080156103d35780601f106103a8576101008083540402835291602001916103d3565b820191906000526020600020905b8154815290600101906020018083116103b657829003601f168201915b5050935050505060405180910390a150565b60005433600160a060020a0390811691161461040057600080fd5b60038180516104139291602001906105a3565b507f9db11307267c890ab25230a11fbb489c4fd763910b0b7b448e795a78faa8d5c6600330604051600160a060020a0382166020820152604080825283546002600019610100600184161502019091160490820181905281906060820190859080156103d35780601f106103a8576101008083540402835291602001916103d3565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102d15780601f106102a6576101008083540402835291602001916102d1565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102d15780601f106102a6576101008083540402835291602001916102d1565b600054600160a060020a031681565b60005433600160a060020a0390811691161461059557600080fd5b600054600160a060020a0316ff5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105e457805160ff1916838001178555610611565b82800160010185558215610611579182015b828111156106115782518255916020019190600101906105f6565b5061061d929150610621565b5090565b61063b91905b8082111561061d5760008155600101610627565b905600a165627a7a72305820d7f91d758f8672d25cbf970b7f4457adfa5299b3e7d8b1af54921e9736748a190029";

    protected SimpleContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogChangeNameEventResponse> getLogChangeNameEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogChangeName", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogChangeNameEventResponse> responses = new ArrayList<LogChangeNameEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogChangeNameEventResponse typedResponse = new LogChangeNameEventResponse();
            typedResponse._name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._contract = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogChangeNameEventResponse> logChangeNameEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogChangeName", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogChangeNameEventResponse>() {
            @Override
            public LogChangeNameEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogChangeNameEventResponse typedResponse = new LogChangeNameEventResponse();
                typedResponse._name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._contract = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<LogChangeDocumentEventResponse> getLogChangeDocumentEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogChangeDocument", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogChangeDocumentEventResponse> responses = new ArrayList<LogChangeDocumentEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogChangeDocumentEventResponse typedResponse = new LogChangeDocumentEventResponse();
            typedResponse._document = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._contract = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogChangeDocumentEventResponse> logChangeDocumentEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogChangeDocument", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogChangeDocumentEventResponse>() {
            @Override
            public LogChangeDocumentEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogChangeDocumentEventResponse typedResponse = new LogChangeDocumentEventResponse();
                typedResponse._document = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._contract = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> name() {
        Function function = new Function("name", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeName(String _newName) {
        Function function = new Function(
                "changeName", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_newName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeDocument(String _newDocument) {
        Function function = new Function(
                "changeDocument", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_newDocument)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> doc() {
        Function function = new Function("doc", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> email() {
        Function function = new Function("email", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> destroir() {
        Function function = new Function(
                "destroir", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<SimpleContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _name, String _doc, String _email) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_doc), 
                new org.web3j.abi.datatypes.Utf8String(_email)));
        return deployRemoteCall(SimpleContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<SimpleContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _name, String _doc, String _email) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_doc), 
                new org.web3j.abi.datatypes.Utf8String(_email)));
        return deployRemoteCall(SimpleContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static SimpleContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SimpleContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class LogChangeNameEventResponse {
        public String _name;

        public String _contract;
    }

    public static class LogChangeDocumentEventResponse {
        public String _document;

        public String _contract;
    }
}
