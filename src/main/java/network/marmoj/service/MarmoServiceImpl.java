package network.marmoj.service;

import network.marmoj.Application;
import network.marmoj.client.MarmoCoreClient;
import network.marmoj.model.core.Intent;
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
import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class MarmoServiceImpl implements MarmoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private Web3j web3j;
    private Credentials credentials;
    private MarmoCoreClient marmoCoreClient;

    @Override
    public boolean setup(String nodeAddress, String privateKey) {
        if (web3j == null) {
            Web3jService web3jService = buildService(nodeAddress);
            LOGGER.info(String.format("Building service for endpoint: %s", nodeAddress));
            web3j = Web3j.build(web3jService);
        }
        if (credentials == null) {
            credentials = Credentials.create(privateKey);
            LOGGER.info(String.format("Building credential for: %s", credentials.getAddress()));

        }
        return true;
    }

    @Override
    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public byte[] encodeTransactionData(String contractAddress, Intent intent) throws Exception {

        if (web3j == null || credentials == null) {
            //FIXME up exception
            return null;
        }
        if (marmoCoreClient == null) {
            marmoCoreClient = MarmoCoreClient.load(contractAddress, web3j, credentials, new DefaultGasProvider());
        }

        RemoteCall<byte[]> remoteCall = marmoCoreClient.encodeTransactionData(intent.getDependencies(),
                    intent.getTo(), intent.getValue(), intent.getData(), intent.getMinGasLimit(),
                    intent.getMaxGasPrice(), intent.getSalt());

        return remoteCall.send();
    }

    /**
     * Private Metods
     */

    private Web3jService buildService(String nodeAddress) {
        Web3jService web3jService;

        if (nodeAddress == null || nodeAddress.equals("")) {
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
