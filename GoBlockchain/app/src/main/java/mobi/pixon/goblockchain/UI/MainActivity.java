package mobi.pixon.goblockchain.UI;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobi.pixon.goblockchain.Helpers.AccountManager;
import mobi.pixon.goblockchain.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.act_transfer_edt_address)
    EditText etAddress;

    @BindView(R.id.act_transfer_edt_value)
    EditText etValue;

    @BindView(R.id.act_transfer_tv_balance)
    TextView tvBalance;

    private AccountManager conn;

    //================================================================================
    // Lifecycle
    //================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        conn = new AccountManager(this);
    }

    //================================================================================
    // Private Methods
    //================================================================================

    private void refreshBalance() {
        final ProgressDialog loading = ProgressDialog.show(this, getString(R.string.app_name),
                "Carregando...");
        conn.getBalance(new AccountManager.BalanceListener() {
            @Override
            public void didFetchBalance(final BigInteger balance, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loading != null) {
                            loading.cancel();
                        }
                        if (balance != null) {
                            tvBalance.setText((balance.doubleValue() / Math.pow(10,18)) + " wei");
                        }
                    }
                });
            }
        });
    }

    //================================================================================
    // Actions
    //================================================================================

    @OnClick(R.id.act_transfer_btn_transfer)
    public void onTransferTap() {

        String address = etAddress.getText().toString();
        String value = etValue.getText().toString();
        final ProgressDialog loading = ProgressDialog.show(this, getString(R.string.app_name),
                "Carregando...");

        conn.transferEth(address, Long.parseLong(value), new AccountManager.TransferListener() {
            @Override
            public void didTransfer(String hash, Exception e) {
                if (loading != null) {
                    loading.cancel();
                }

            }
        });
    }

    @OnClick(R.id.act_transfer_btn_refresh)
    public void onRefreshTap() {
        refreshBalance();
    }

    @OnClick(R.id.act_transfer_btn_contract)
    public void onContractTap() {
        startActivity(ContractActivity.getStartIntent(this));
    }
}
