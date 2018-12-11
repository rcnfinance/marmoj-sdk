package network.marmoj.service;

import network.marmoj.Application;
import network.marmoj.builder.IntentBuilder;
import network.marmoj.client.MarmoCoreClient;
import network.marmoj.model.core.Intent;
import network.marmoj.model.core.SignedIntent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.UnixIpcService;
import org.web3j.protocol.ipc.WindowsIpcService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarmoServiceImpl implements MarmoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private Web3j web3j;
    private Credentials credentials;
    private MarmoCoreClient marmoCoreClient;

    @Override
    public boolean setup(String nodeAddress, String contractAddress, String privateKey) {
        if (web3j == null) {
            Web3jService web3jService = buildService(nodeAddress);
            LOGGER.info(String.format("Building service for endpoint: %s", nodeAddress));
            web3j = Web3j.build(web3jService);
        }
        if (credentials == null) {
            credentials = Credentials.create(privateKey);
            LOGGER.info(String.format("Building credential for: %s", credentials.getAddress()));
        }
        if (marmoCoreClient == null) {
            marmoCoreClient = MarmoCoreClient.load(contractAddress, web3j, credentials, new DefaultGasProvider());
            LOGGER.info(String.format("Building contract for: %s", contractAddress));
        }
        return true;
    }

    @Override
    public Intent create(List<byte[]> dependencies, String to, BigInteger value, byte[] data, BigInteger minGasLimit,
                         BigInteger maxGasPrice, byte[] salt) {

        if (web3j == null || credentials == null ||  marmoCoreClient == null) {
            //FIXME up exception
            return null;
        }

        if (dependencies == null) {
            dependencies = new ArrayList<>();
        }
        if (data == null) {
            data = Numeric.toBytesPadded(BigInteger.ZERO, 32);
        }
        if (salt == null) {
            salt = Numeric.toBytesPadded(BigInteger.ZERO, 32);
        }

        Intent intent = IntentBuilder.anIntent()
                .withId(generateId(dependencies, to, value, data, minGasLimit, maxGasPrice, salt))
                .withData(data)
                .withFrom(credentials.getAddress())
                .withDependencies(dependencies)
                .withTo(to)
                .withMaxGasPrice(maxGasPrice)
                .withMinGasLimit(minGasLimit)
                .withSalt(salt)
                .withValue(BigInteger.ONE)
                .build();

        return intent;
    }

    @Override
    public SignedIntent sign(Intent intent) {
        return null;
    }

    /**
     * Private Metods
     */

    private String generateId(List<byte[]> dependencies, String to, BigInteger value, byte[] data, BigInteger minGasLimit, BigInteger maxGasPrice, byte[] salt)  {
        RemoteCall<byte[]> remoteCall = marmoCoreClient.encodeTransactionData(dependencies, to, value, data, minGasLimit, maxGasPrice, salt);
        try {
            return Numeric.toHexString(remoteCall.send());
        } catch (Exception e) {
            // TODO: UP RUNTIME EXCEPTION
            return null;
        }
    }

    private Web3jService buildService(String nodeAddress) {
        Web3jService web3jService;

        if (nodeAddress == null || "".equals(nodeAddress)) {
            web3jService = new HttpService(createOkHttpClient());
        } else if (nodeAddress.startsWith("http")) {
            web3jService = new HttpService(nodeAddress, createOkHttpClient(), false);
        } else if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            web3jService = new WindowsIpcService(nodeAddress);
        } else {
            web3jService = new UnixIpcService(nodeAddress);
        }

        return web3jService;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        configureLogging(builder);
        return builder.build();
    }

    private static void configureLogging(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(LOGGER::debug);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
    }

}
