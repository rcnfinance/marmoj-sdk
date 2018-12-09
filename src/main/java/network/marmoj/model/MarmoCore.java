package network.marmoj.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
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
import org.web3j.tx.gas.ContractGasProvider;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class MarmoCore extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610d50806100206000396000f3fe608060405260043610610071577c010000000000000000000000000000000000000000000000000000000060003504630f694e26811461007357806361b69fde146100b957806366d38203146100f75780637747201d1461012a578063b8bb3eba14610293578063c4d252f5146104fc575b005b34801561007f57600080fd5b5061009d6004803603602081101561009657600080fd5b5035610526565b60408051600160a060020a039092168252519081900360200190f35b3480156100c557600080fd5b506100e3600480360360208110156100dc57600080fd5b5035610541565b604080519115158252519081900360200190f35b34801561010357600080fd5b506100716004803603602081101561011a57600080fd5b5035600160a060020a0316610556565b34801561013657600080fd5b50610281600480360360e081101561014d57600080fd5b81019060208101813564010000000081111561016857600080fd5b82018360208201111561017a57600080fd5b8035906020019184602083028401116401000000008311171561019c57600080fd5b9190808060200260200160405190810160405280939291908181526020018383602002808284376000920191909152509295600160a060020a038535169560208601359591945092506060810191506040013564010000000081111561020157600080fd5b82018360208201111561021357600080fd5b8035906020019184600183028401116401000000008311171561023557600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295505082359350505060208101359060400135610562565b60408051918252519081900360200190f35b34801561029f57600080fd5b5061047960048036036101008110156102b757600080fd5b8101906020810181356401000000008111156102d257600080fd5b8201836020820111156102e457600080fd5b8035906020019184602083028401116401000000008311171561030657600080fd5b9190808060200260200160405190810160405280939291908181526020018383602002808284376000920191909152509295600160a060020a038535169560208601359591945092506060810191506040013564010000000081111561036b57600080fd5b82018360208201111561037d57600080fd5b8035906020019184600183028401116401000000008311171561039f57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929584359560208601359560408101359550919350915060808101906060013564010000000081111561040457600080fd5b82018360208201111561041657600080fd5b8035906020019184600183028401116401000000008311171561043857600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061063d945050505050565b604051808315151515815260200180602001828103825283818151815260200191508051906020019080838360005b838110156104c05781810151838201526020016104a8565b50505050905090810190601f1680156104ed5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561050857600080fd5b506100716004803603602081101561051f57600080fd5b50356109d2565b600160205260009081526040902054600160a060020a031681565b60026020526000908152604090205460ff1681565b61055f81610ab1565b50565b6000308860405160200180828051906020019060200280838360005b8381101561059657818101518382015260200161057e565b50506040805193909501838103601f1901845280865283516020948501208e519e85019e909e20600160a060020a03998a166c0100000000000000000000000090810286840152603483019f909f529f909816909c02605488015250606886019b909b5250608884019a909a52505060a88101949094525060c883019190915260e88083019190915284518083039091018152610108909101909352508151910120919050565b6000606060006106528b8b8b8b8b8b8b610562565b90503a86101561066157600080fd5b60008181526002602052604090205460ff16156106c8576040805160e560020a62461bcd02815260206004820152601860248201527f5472616e73616374696f6e207761732063616e63656c65640000000000000000604482015290519081900360640190fd5b600081815260016020526040902054600160a060020a031615610735576040805160e560020a62461bcd02815260206004820152601b60248201527f5472616e73616374696f6e20616c72656164792072656c617965640000000000604482015290519081900360640190fd5b61073e8b610b9e565b1515610794576040805160e560020a62461bcd02815260206004820152601660248201527f506172656e742072656c6179206e6f7420666f756e6400000000000000000000604482015290519081900360640190fd5b61079e8185610c0e565b865a116107aa57600080fd5b89600160a060020a031689896040518082805190602001908083835b602083106107e55780518252601f1990920191602091820191016107c6565b6001836020036101000a03801982511681845116808217855250505050505090500191505060006040518083038185875af1925050503d8060008114610847576040519150601f19603f3d011682016040523d82523d6000602084013e61084c565b606091505b508093508194505050336001600083815260200190815260200160002060006101000a815481600160a060020a030219169083600160a060020a031602179055507fd610b46dae235a6112d33d972dfa88cb371146a053e13c3222aa14f07d576ea9818c8c8c8c8a338a604051808981526020018060200188600160a060020a0316600160a060020a031681526020018781526020018060200186815260200185600160a060020a0316600160a060020a031681526020018415151515815260200183810383528a818151815260200191508051906020019060200280838360005b8381101561094657818101518382015260200161092e565b50505050905001838103825287818151815260200191508051906020019080838360005b8381101561098257818101518382015260200161096a565b50505050905090810190601f1680156109af5780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a1509850989650505050505050565b333014610a29576040805160e560020a62461bcd02815260206004820152601a60248201527f4f6e6c792077616c6c65742063616e2063616e63656c20747873000000000000604482015290519081900360640190fd5b600081815260016020526040902054600160a060020a031615610a96576040805160e560020a62461bcd02815260206004820152601f60248201527f5472616e73616374696f6e2077617320616c72656164792072656c6179656400604482015290519081900360640190fd5b6000908152600260205260409020805460ff19166001179055565b600160a060020a0381161515610b37576040805160e560020a62461bcd02815260206004820152603d60248201527f496e76616c6964206f776e657220616464726573732070726f76696465642c2060448201527f7369676e657220616464726573732063616e6e6f74206265206e756c6c000000606482015290519081900360840190fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03838116919091179182905560408051929091168252517f82b74755d483f0688b80354268454667c377a5684e64a4dbb6820fc11a6276d4916020908290030190a150565b6000805b8251811015610c03576000600160a060020a0316600160008584815181101515610bc857fe5b6020908102909101810151825281019190915260400160002054600160a060020a03161415610bfb576000915050610c09565b600101610ba2565b50600190505b919050565b6000610c1a8383610c80565b9050610c2581610d10565b1515610c7b576040805160e560020a62461bcd02815260206004820181905260248201527f5369676e6174757265206e6f742070726f7669646564206279207369676e6572604482015290519081900360640190fd5b505050565b602081015160408201516041830151600092919060ff16601b811015610ca457601b015b6040805160008152602080820180845289905260ff8416828401526060820186905260808201859052915160019260a0808401939192601f1981019281900390910190855afa158015610cfb573d6000803e3d6000fd5b5050604051601f190151979650505050505050565b600054600160a060020a039182169116149056fea165627a7a72305820791822d9bb0641e7df1267328f372540c62a00002234e8d060089d1e520b65950029";

    public static final String FUNC_RELAYEROF = "relayerOf";

    public static final String FUNC_ISCANCELED = "isCanceled";

    public static final String FUNC_SETUP = "setup";

    public static final String FUNC_ENCODETRANSACTIONDATA = "encodeTransactionData";

    public static final String FUNC_RELAY = "relay";

    public static final String FUNC_CANCEL = "cancel";

    public static final Event RELAYED_EVENT = new Event("Relayed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event CANCELED_EVENT = new Event("Canceled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    public static final Event ADDEDSIGNER_EVENT = new Event("AddedSigner", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected MarmoCore(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MarmoCore(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MarmoCore(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MarmoCore(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> relayerOf(byte[] param0) {
        final Function function = new Function(FUNC_RELAYEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> isCanceled(byte[] param0) {
        final Function function = new Function(FUNC_ISCANCELED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> setup(String _signer) {
        final Function function = new Function(
                FUNC_SETUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_signer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> encodeTransactionData(List<byte[]> _dependencies, String _to, BigInteger _value, byte[] _data, BigInteger _minGasLimit, BigInteger _maxGasPrice, byte[] _salt) {
        final Function function = new Function(FUNC_ENCODETRANSACTIONDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_dependencies, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.DynamicBytes(_data), 
                new org.web3j.abi.datatypes.generated.Uint256(_minGasLimit), 
                new org.web3j.abi.datatypes.generated.Uint256(_maxGasPrice), 
                new org.web3j.abi.datatypes.generated.Bytes32(_salt)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> relay(List<byte[]> _dependencies, String _to, BigInteger _value, byte[] _data, BigInteger _minGasLimit, BigInteger _maxGasPrice, byte[] _salt, byte[] _signature) {
        final Function function = new Function(
                FUNC_RELAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_dependencies, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.DynamicBytes(_data), 
                new org.web3j.abi.datatypes.generated.Uint256(_minGasLimit), 
                new org.web3j.abi.datatypes.generated.Uint256(_maxGasPrice), 
                new org.web3j.abi.datatypes.generated.Bytes32(_salt), 
                new org.web3j.abi.datatypes.DynamicBytes(_signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> cancel(byte[] _hashTransaction) {
        final Function function = new Function(
                FUNC_CANCEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_hashTransaction)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<RelayedEventResponse> getRelayedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RELAYED_EVENT, transactionReceipt);
        ArrayList<RelayedEventResponse> responses = new ArrayList<RelayedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RelayedEventResponse typedResponse = new RelayedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._dependencies = (List<byte[]>) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._to = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._data = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._salt = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse._relayer = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse._success = (Boolean) eventValues.getNonIndexedValues().get(7).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RelayedEventResponse> relayedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, RelayedEventResponse>() {
            @Override
            public RelayedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RELAYED_EVENT, log);
                RelayedEventResponse typedResponse = new RelayedEventResponse();
                typedResponse.log = log;
                typedResponse._id = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._dependencies = (List<byte[]>) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._to = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._data = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse._salt = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse._relayer = (String) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse._success = (Boolean) eventValues.getNonIndexedValues().get(7).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<RelayedEventResponse> relayedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RELAYED_EVENT));
        return relayedEventObservable(filter);
    }

    public List<CanceledEventResponse> getCanceledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CANCELED_EVENT, transactionReceipt);
        ArrayList<CanceledEventResponse> responses = new ArrayList<CanceledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CanceledEventResponse typedResponse = new CanceledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CanceledEventResponse> canceledEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CanceledEventResponse>() {
            @Override
            public CanceledEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CANCELED_EVENT, log);
                CanceledEventResponse typedResponse = new CanceledEventResponse();
                typedResponse.log = log;
                typedResponse._id = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CanceledEventResponse> canceledEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CANCELED_EVENT));
        return canceledEventObservable(filter);
    }

    public List<AddedSignerEventResponse> getAddedSignerEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDEDSIGNER_EVENT, transactionReceipt);
        ArrayList<AddedSignerEventResponse> responses = new ArrayList<AddedSignerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddedSignerEventResponse typedResponse = new AddedSignerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.signer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AddedSignerEventResponse> addedSignerEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, AddedSignerEventResponse>() {
            @Override
            public AddedSignerEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDEDSIGNER_EVENT, log);
                AddedSignerEventResponse typedResponse = new AddedSignerEventResponse();
                typedResponse.log = log;
                typedResponse.signer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<AddedSignerEventResponse> addedSignerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDEDSIGNER_EVENT));
        return addedSignerEventObservable(filter);
    }

    public static RemoteCall<MarmoCore> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MarmoCore.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MarmoCore> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MarmoCore.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<MarmoCore> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MarmoCore.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MarmoCore> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MarmoCore.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static MarmoCore load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MarmoCore(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MarmoCore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MarmoCore(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MarmoCore load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MarmoCore(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MarmoCore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MarmoCore(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class RelayedEventResponse {
        public Log log;

        public byte[] _id;

        public List<byte[]> _dependencies;

        public String _to;

        public BigInteger _value;

        public byte[] _data;

        public byte[] _salt;

        public String _relayer;

        public Boolean _success;
    }

    public static class CanceledEventResponse {
        public Log log;

        public byte[] _id;
    }

    public static class AddedSignerEventResponse {
        public Log log;

        public String signer;
    }
}
