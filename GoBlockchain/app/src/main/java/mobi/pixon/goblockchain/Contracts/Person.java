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
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Int16;
import org.web3j.abi.datatypes.generated.Int8;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class Person extends Contract {
    private static final String BINARY = "606060405234156200001057600080fd5b60405162000f1b38038062000f1b833981016040528080518201919060200180518201919060200180519150600390508380516200005392916020019062000308565b5060048280516200006992916020019062000308565b506006805460ff191660ff600084810b9190911691909117918290558054600160a060020a03191633600160a060020a031617815542600155436002819055620000c59230920b906401000000006200018f8102620009be1704565b620001863060048054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015620001645780601f10620001385761010080835404028352916020019162000164565b820191906000526020600020905b8154815290600101906020018083116200014657829003601f168201915b50505050506002546200021c6401000000000262000a4a176401000000009004565b505050620003ad565b60005433600160a060020a03908116911614620001ab57600080fd5b6000547fb8b72ae829f67f88bd5426c37c378f363684da50252b0ed879fcd649ed66fc4f90600160a060020a0316848484604051600160a060020a039485168152929093166020830152600090810b900b6040808301919091526060820192909252608001905180910390a1505050565b60005433600160a060020a039081169116146200023857600080fd5b6000547ffbd53c8f634bf7d11e127dc53fed174593a729e5ee7bdfaae02aad7a4ca1937b90600160a060020a0316848484604051600160a060020a038086168252841660208201526060810182905260806040820181815290820184818151815260200191508051906020019080838360005b83811015620002c5578082015183820152602001620002ab565b50505050905090810190601f168015620002f35780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a1505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200034b57805160ff19168380011785556200037b565b828001600101855582156200037b579182015b828111156200037b5782518255916020019190600101906200035e565b50620003899291506200038d565b5090565b620003aa91905b8082111562000389576000815560010162000394565b90565b610b5e80620003bd6000396000f3006060604052600436106100e25763ffffffff60e060020a60003504166306fdde0381146100e75780632d7734651461017157806334e7dc31146101a35780633d5f71ca146101d657806341c0e1b5146102025780634cf88750146102175780634d238c8e1461023c5780634f11229c1461025b5780636fd993581461026e578063820e93f514610281578063893fe85b146102945780638a00d190146102b85780638da5cb5b146102d75780639284f497146102ea5780639b528b1614610316578063c3cf537614610329578063d553357d1461033f578063fbb75f7314610390575b600080fd5b34156100f257600080fd5b6100fa6103b4565b60405160208082528190810183818151815260200191508051906020019080838360005b8381101561013657808201518382015260200161011e565b50505050905090810190601f1680156101635780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561017c57600080fd5b610187600435610452565b604051600160a060020a03909116815260200160405180910390f35b34156101ae57600080fd5b6101c2600160a060020a036004351661047a565b604051901515815260200160405180910390f35b34156101e157600080fd5b6101e961048f565b604051600191820b90910b815260200160405180910390f35b341561020d57600080fd5b61021561049d565b005b341561022257600080fd5b61022a6104bc565b60405190815260200160405180910390f35b341561024757600080fd5b610215600160a060020a03600435166104c2565b341561026657600080fd5b6101c2610528565b341561027957600080fd5b61022a610538565b341561028c57600080fd5b6100fa61053e565b341561029f57600080fd5b610215600160a060020a036004351660243515156105a9565b34156102c357600080fd5b6101c2600160a060020a0360043516610636565b34156102e257600080fd5b61018761064b565b34156102f557600080fd5b6102fd61065a565b604051600091820b90910b815260200160405180910390f35b341561032157600080fd5b610215610663565b341561033457600080fd5b6101c260043561068c565b341561034a57600080fd5b61021560046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506106a195505050505050565b341561039b57600080fd5b610215600160a060020a0360043516602435151561078e565b60038054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561044a5780601f1061041f5761010080835404028352916020019161044a565b820191906000526020600020905b81548152906001019060200180831161042d57829003601f168201915b505050505081565b600980548290811061046057fe5b600091825260209091200154600160a060020a0316905081565b600a6020526000908152604090205460ff1681565b600654610100900460010b81565b60005433600160a060020a039081169116146104b857600080fd5b6000ff5b60075481565b60005433600160a060020a039081169116146104dd57600080fd5b600160a060020a0381166000908152600b602052604090205460ff16151561050457600080fd5b600160a060020a03166000908152600b60205260409020805460ff19166001179055565b6006546301000000900460ff1681565b60015481565b60048054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561044a5780601f1061041f5761010080835404028352916020019161044a565b6000805433600160a060020a039081169116146105c557600080fd5b5081600160a060020a03811663fbb75f73308460405160e060020a63ffffffff8516028152600160a060020a03909216600483015215156024820152604401600060405180830381600087803b151561061d57600080fd5b6102c65a03f1151561062e57600080fd5b505050505050565b60086020526000908152604090205460ff1681565b600054600160a060020a031681565b60065460000b81565b60005433600160a060020a0390811691161461067e57600080fd5b600054600160a060020a0316ff5b60056020526000908152604090205460ff1681565b60005433600160a060020a039081169116146106bc57600080fd5b60048180516106cf929160200190610923565b507fa79e0bb5b2e79f7ddeca9530b41e5b4cca973196b9c0c73537101a3d8560d79f306004604051600160a060020a03831681526040602082018181528354600260001961010060018416150201909116049183018290529060608301908490801561077c5780601f106107515761010080835404028352916020019161077c565b820191906000526020600020905b81548152906001019060200180831161075f57829003601f168201915b5050935050505060405180910390a150565b6000805432600160a060020a03908116911614156107ab57600080fd5b30600160a060020a031633600160a060020a0316141515156107cc57600080fd5b5081600160a060020a038116634f11229c6000604051602001526040518163ffffffff1660e060020a028152600401602060405180830381600087803b151561081457600080fd5b6102c65a03f1151561082557600080fd5b50505060405180519050151561083a57600080fd5b6006546101009004600190810b900b600160a060020a038216633d5f71ca6000604051602001526040518163ffffffff1660e060020a028152600401602060405180830381600087803b151561088f57600080fd5b6102c65a03f115156108a057600080fd5b5050506040518051905060010b141515156108ba57600080fd5b81156108ce57600c805460010190556108d8565b600d805460010190555b7fce1999d813c8f8f86decb86dfff5abe046627678996d958a3993af85cfa3fcbc8383604051600160a060020a039092168252151560208201526040908101905180910390a1505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061096457805160ff1916838001178555610991565b82800160010185558215610991579182015b82811115610991578251825591602001919060010190610976565b5061099d9291506109a1565b5090565b6109bb91905b8082111561099d57600081556001016109a7565b90565b60005433600160a060020a039081169116146109d957600080fd5b6000547fb8b72ae829f67f88bd5426c37c378f363684da50252b0ed879fcd649ed66fc4f90600160a060020a0316848484604051600160a060020a039485168152929093166020830152600090810b900b6040808301919091526060820192909252608001905180910390a1505050565b60005433600160a060020a03908116911614610a6557600080fd5b6000547ffbd53c8f634bf7d11e127dc53fed174593a729e5ee7bdfaae02aad7a4ca1937b90600160a060020a0316848484604051600160a060020a038086168252841660208201526060810182905260806040820181815290820184818151815260200191508051906020019080838360005b83811015610af0578082015183820152602001610ad8565b50505050905090810190601f168015610b1d5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15050505600a165627a7a72305820415d87535688ad142233e770f0b07e8f0fa008e9b2f3ea0d9353829ed436735a0029";

    protected Person(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Person(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogPersonValidatorEventResponse> getLogPersonValidatorEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogPersonValidator", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogPersonValidatorEventResponse> responses = new ArrayList<LogPersonValidatorEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogPersonValidatorEventResponse typedResponse = new LogPersonValidatorEventResponse();
            typedResponse._contractPersonValidator = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._isValid = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogPersonValidatorEventResponse> logPersonValidatorEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogPersonValidator", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogPersonValidatorEventResponse>() {
            @Override
            public LogPersonValidatorEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogPersonValidatorEventResponse typedResponse = new LogPersonValidatorEventResponse();
                typedResponse._contractPersonValidator = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._isValid = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<LogUpdateEmailEventResponse> getLogUpdateEmailEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogUpdateEmail", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogUpdateEmailEventResponse> responses = new ArrayList<LogUpdateEmailEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogUpdateEmailEventResponse typedResponse = new LogUpdateEmailEventResponse();
            typedResponse._addressPerson = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._newEmail = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogUpdateEmailEventResponse> logUpdateEmailEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogUpdateEmail", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogUpdateEmailEventResponse>() {
            @Override
            public LogUpdateEmailEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogUpdateEmailEventResponse typedResponse = new LogUpdateEmailEventResponse();
                typedResponse._addressPerson = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._newEmail = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<LogUpdatePhotoEventResponse> getLogUpdatePhotoEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogUpdatePhoto", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogUpdatePhotoEventResponse> responses = new ArrayList<LogUpdatePhotoEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogUpdatePhotoEventResponse typedResponse = new LogUpdatePhotoEventResponse();
            typedResponse._addressPerson = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._newEmail = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogUpdatePhotoEventResponse> logUpdatePhotoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogUpdatePhoto", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogUpdatePhotoEventResponse>() {
            @Override
            public LogUpdatePhotoEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogUpdatePhotoEventResponse typedResponse = new LogUpdatePhotoEventResponse();
                typedResponse._addressPerson = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._newEmail = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<LogValidateCellPhoneEventResponse> getLogValidateCellPhoneEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogValidateCellPhone", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Int8>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogValidateCellPhoneEventResponse> responses = new ArrayList<LogValidateCellPhoneEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogValidateCellPhoneEventResponse typedResponse = new LogValidateCellPhoneEventResponse();
            typedResponse._owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._contractIdentity = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._phone = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._identificationPerson = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogValidateCellPhoneEventResponse> logValidateCellPhoneEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogValidateCellPhone", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Int8>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogValidateCellPhoneEventResponse>() {
            @Override
            public LogValidateCellPhoneEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogValidateCellPhoneEventResponse typedResponse = new LogValidateCellPhoneEventResponse();
                typedResponse._owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._contractIdentity = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._phone = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._identificationPerson = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public List<LogValidateEmailEventResponse> getLogValidateEmailEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogValidateEmail", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogValidateEmailEventResponse> responses = new ArrayList<LogValidateEmailEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogValidateEmailEventResponse typedResponse = new LogValidateEmailEventResponse();
            typedResponse._owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._contractIdentity = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._email = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._identificationPerson = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogValidateEmailEventResponse> logValidateEmailEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogValidateEmail", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogValidateEmailEventResponse>() {
            @Override
            public LogValidateEmailEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogValidateEmailEventResponse typedResponse = new LogValidateEmailEventResponse();
                typedResponse._owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._contractIdentity = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._email = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._identificationPerson = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
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

    public RemoteCall<String> listCellPhoneValidator(BigInteger param0) {
        Function function = new Function("listCellPhoneValidator", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> mapCellPhoneValidator(String param0) {
        Function function = new Function("mapCellPhoneValidator", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<BigInteger> identificationCellPhone() {
        Function function = new Function("identificationCellPhone", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int16>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> addressIPFSPhoto() {
        Function function = new Function("addressIPFSPhoto", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> addValidator(String _validator) {
        Function function = new Function(
                "addValidator", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_validator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> isValidPhone() {
        Function function = new Function("isValidPhone", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<BigInteger> dateCreate() {
        Function function = new Function("dateCreate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> email() {
        Function function = new Function("email", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> sendValidationIdentity(String _addressPerson, Boolean _isValid) {
        Function function = new Function(
                "sendValidationIdentity", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addressPerson), 
                new org.web3j.abi.datatypes.Bool(_isValid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> isValidAddressIPFSPhoto(String param0) {
        Function function = new Function("isValidAddressIPFSPhoto", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> phone() {
        Function function = new Function("phone", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> destroir() {
        Function function = new Function(
                "destroir", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> isValidEmail(byte[] param0) {
        Function function = new Function("isValidEmail", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> changeEmail(String _newMail) {
        Function function = new Function(
                "changeEmail", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_newMail)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> validatorIdentity(String _contractPersonValidator, Boolean isValid) {
        Function function = new Function(
                "validatorIdentity", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_contractPersonValidator), 
                new org.web3j.abi.datatypes.Bool(isValid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Person> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _name, String _email, BigInteger _phone) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_email), 
                new org.web3j.abi.datatypes.generated.Int8(_phone)));
        return deployRemoteCall(Person.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Person> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _name, String _email, BigInteger _phone) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_email), 
                new org.web3j.abi.datatypes.generated.Int8(_phone)));
        return deployRemoteCall(Person.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Person load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Person(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Person load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Person(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class LogPersonValidatorEventResponse {
        public String _contractPersonValidator;

        public Boolean _isValid;
    }

    public static class LogUpdateEmailEventResponse {
        public String _addressPerson;

        public String _newEmail;
    }

    public static class LogUpdatePhotoEventResponse {
        public String _addressPerson;

        public String _newEmail;
    }

    public static class LogValidateCellPhoneEventResponse {
        public String _owner;

        public String _contractIdentity;

        public BigInteger _phone;

        public BigInteger _identificationPerson;
    }

    public static class LogValidateEmailEventResponse {
        public String _owner;

        public String _contractIdentity;

        public String _email;

        public BigInteger _identificationPerson;
    }
}
