package network.marmoj.client;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.List;

public interface MarmoCoreClient {

    static MarmoCoreClient load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MarmoCoreClientImpl(contractAddress, web3j, credentials, contractGasProvider);
    }

    RemoteCall<byte[]> encodeTransactionData(List<byte[]> _dependencies, String _to, BigInteger _value, byte[] _data, BigInteger _minGasLimit, BigInteger _maxGasPrice, byte[] _salt);
}
