package mobi.pixon.goblockchain.Helpers;

import android.content.Context;

import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.AdminFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import mobi.pixon.goblockchain.Helpers.Accounts.Alice;

/**
 * Created by Alicepantani on 20/01/18.
 */

public class AccountManager {

    // http://ethereum.stackexchange.com/questions/1832/cant-send-transaction-exceeds-block-gas-limit-or-intrinsic-gas-too-low
    public static final BigInteger GAS_LIMIT_ETHER_TX = BigInteger.valueOf(21_000);
    public static final BigInteger GAS_LIMIT_GREETER_TX = BigInteger.valueOf(500_000L);

    // see https://www.reddit.com/r/ethereum/comments/5g8ia6/attention_miners_we_recommend_raising_gas_limit/
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);
    //        private static final String HOST = "http://10.0.2.1:8545";
    private static final String HOST = "https://ropsten.infura.io/XiYvA72TOUhpVgc21tyA";

    private Admin web3j;
    private Context context;

    //================================================================================
    // Lifecyle
    //================================================================================

    public AccountManager(Context context) {
        this.context = context;
        web3j = AdminFactory.build(new HttpService(HOST));
    }

    //================================================================================
    // Public Methods
    //================================================================================

    public void getBalance(final BalanceListener listener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    String address = Alice.ADDRESS;
                    android.util.Log.e("teste", address);
                    EthGetBalance ethGetBalance = web3j
                            .ethGetBalance(address, DefaultBlockParameterName.LATEST)
                            .sendAsync()
                            .get();

                    BigInteger wei = ethGetBalance.getBalance();
                    listener.didFetchBalance(wei, null);

                } catch (InterruptedException e) {
                    listener.didFetchBalance(null, e);
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    listener.didFetchBalance(null, e);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void transferEth(final String to, final long value, final TransferListener listener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    String address = Alice.ADDRESS;

                    //Nonce (falar sobre assync)
                    EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                            address, DefaultBlockParameterName.LATEST).send();
                    BigInteger nonce = ethGetTransactionCount.getTransactionCount();

                    //Transacionar
                    Transaction transaction = Transaction.createEtherTransaction(
                            address,
                            nonce,
                            GAS_PRICE,
                            GAS_LIMIT,
                            to,
                            BigInteger.valueOf(value));
                    EthSendTransaction sendTransaction = web3j.ethSendTransaction(transaction).send();
                    String hash = sendTransaction.getTransactionHash();

                    if (sendTransaction.getError() != null) {
                        listener.didTransfer(null, new Exception(sendTransaction.getError().getMessage()));
                    } else {
                        listener.didTransfer(hash, null);
                    }
                } catch (IOException e) {
                    listener.didTransfer(null, e);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // ================================================================================
    // Interface
    //================================================================================

    public interface TransferListener {
        void didTransfer(String hash, Exception e);
    }

    public interface BalanceListener {
        void didFetchBalance(BigInteger balance, Exception e);
    }
}
