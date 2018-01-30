package mobi.pixon.goblockchain.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.AdminFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import mobi.pixon.goblockchain.Contracts.SimpleContract;
import mobi.pixon.goblockchain.Helpers.Accounts.Alice;
import mobi.pixon.goblockchain.Helpers.Accounts.Danilo;
import mobi.pixon.goblockchain.R;
import rx.Subscriber;

/**
 * Created by danilopantani on 20/01/18.
 */

public class ContractManager {

    // see https://www.reddit.com/r/ethereum/comments/5g8ia6/attention_miners_we_recommend_raising_gas_limit/
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);
//    private static final String HOST = "http://192.168.0.21:8545";
//    private static final String HOST = "http://10.255.130.245:8545";
    private static final String HOST = "https://ropsten.infura.io/XiYvA72TOUhpVgc21tyA";

    private Admin web3j;
    private SimpleContract contract;
    private Context context;

    //================================================================================
    // Lifecyle
    //================================================================================

    public ContractManager(Context context) {
        this.context = context;
        web3j = AdminFactory.build(new HttpService(HOST));
    }

    //================================================================================
    // Contract
    //================================================================================

    public synchronized void loadContract(final String address, final ContractListener listener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Danilo d = new Danilo(context);
                    SimpleContract contract = SimpleContract.load(address,
                            web3j,
                            d.CREDENTIALS,
                            GAS_PRICE,
                            GAS_LIMIT);
                    listener.didFetchContract(contract, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.didFetchContract(null, e);
                }
            }
        }).start();
    }

    public synchronized void deployContract(final String name, final String doc, final String email, final ContractListener listener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Danilo d = new Danilo(context);
                    SimpleContract contract = SimpleContract.deploy(web3j,
                            d.CREDENTIALS,
                            GAS_PRICE,
                            GAS_LIMIT,
                            name,
                            doc,
                            email).sendAsync().get();
                    listener.didFetchContract(contract, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.didFetchContract(null, e);
                }
            }
        }).start();
    }

    public void changeDocument(final SimpleContract contract, final String document) {
        if (contract == null) return;

        final ProgressDialog loading = ProgressDialog.show(context, context.getString(R.string.app_name),
                "Carregando...");

        new Thread(new Runnable() {
            public void run() {
                changeDoc(contract, document);
                if (loading != null) {
                    loading.cancel();
                }
            }
        }).start();
    }

    private void changeDoc(SimpleContract contract, String document) {
        try {
            TransactionReceipt receipt = contract.changeDocument(document).send();
            Log.e("GOBLOCKCHAIN", receipt.getBlockHash());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeName(final SimpleContract contract, final String name) {
        if (contract == null) return;

        final ProgressDialog loading = ProgressDialog.show(context, context.getString(R.string.app_name),
                "Carregando...");

        new Thread(new Runnable() {
            public void run() {
                changeNameBlock(contract, name);
                if (loading != null) {
                    loading.cancel();
                }
            }
        }).start();
    }

    private void changeNameBlock(SimpleContract contract, String name) {
        try {
            TransactionReceipt receipt = contract.changeName(name).send();
            Log.e("GOBLOCKCHAIN", receipt.getBlockHash());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenEmailEvents(final SimpleContract contract, final NameEventListener listener) {
        contract.logChangeNameEventObservable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST)
                .subscribe(new Subscriber<SimpleContract.LogChangeNameEventResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SimpleContract.LogChangeNameEventResponse logChangeEmailEventResponse) {
                        listener.didChangeName(logChangeEmailEventResponse._name);
                    }
                });
    }

    public void listenDocEvents(final SimpleContract contract, final DocEventListener listener) {
        contract.logChangeDocumentEventObservable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST)
                .subscribe(new Subscriber<SimpleContract.LogChangeDocumentEventResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SimpleContract.LogChangeDocumentEventResponse logChangeDocumentEventResponse) {
                        listener.didChangeDocument(logChangeDocumentEventResponse._document);
                    }
                });

    }

    //================================================================================
    // Interface
    //================================================================================

    public interface ContractListener {
        void didFetchContract(final SimpleContract contract, Exception e);
    }

    public interface DocEventListener {
        void didChangeDocument(final String text);
    }

    public interface NameEventListener {
        void didChangeName(final String text);
    }
}
