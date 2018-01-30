package mobi.pixon.goblockchain.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobi.pixon.goblockchain.Contracts.SimpleContract;
import mobi.pixon.goblockchain.Helpers.ContractManager;
import mobi.pixon.goblockchain.R;

public class ContractActivity extends AppCompatActivity implements ContractManager.NameEventListener, ContractManager.DocEventListener {

    @BindView(R.id.act_contract_edt_address)
    EditText etAddress;

    @BindView(R.id.act_contract_edt_name)
    EditText etName;

    @BindView(R.id.act_contract_edt_email)
    EditText etEmail;

    @BindView(R.id.act_contract_edt_doc)
    EditText etDoc;

    private ContractManager conn;
    private SimpleContract contract;

    //================================================================================
    // Lifecycle
    //================================================================================

    public static Intent getStartIntent(@NonNull Context context) {
        Intent intent = new Intent(context, ContractActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        ButterKnife.bind(this);
        conn = new ContractManager(this);
    }

    //================================================================================
    // Listeners
    //================================================================================

    @Override
    public void didChangeDocument(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("teste", text);
                etDoc.setText(text);
            }
        });
    }

    @Override
    public void didChangeName(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("teste", text);
                etName.setText(text);
            }
        });
    }

    //================================================================================
    // Actions
    //================================================================================

    @OnClick(R.id.act_contract_btn_load_contract)
    public void onLoadContractTap() {
        final ProgressDialog loading = ProgressDialog.show(this, getString(R.string.app_name),
                "Carregando...");

        String address = etAddress.getText().toString();

        conn.loadContract(address, new ContractManager.ContractListener() {
            @Override
            public void didFetchContract(final SimpleContract newContract, Exception e) {
                try {

                    conn.listenDocEvents(newContract, ContractActivity.this);
                    conn.listenEmailEvents(newContract, ContractActivity.this);

                    final String name = newContract.name().send();
                    final String email = newContract.email().send();
                    final String doc = newContract.doc().send();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contract = newContract;
                            etName.setText(name);
                            etEmail.setText(email);
                            etDoc.setText(doc);


                            if (loading != null) {
                                loading.cancel();
                            }
                        }
                    });
                } catch (Exception ex) {
                    if (loading != null) {
                        loading.cancel();
                    }

                    ex.printStackTrace();
                }
            }
        });
    }

    @OnClick(R.id.act_contract_btn_new_contract)
    public void onDeployContractTap() {
        final ProgressDialog loading = ProgressDialog.show(this, getString(R.string.app_name),
                "Carregando...");

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String doc = etDoc.getText().toString();

        conn.deployContract(name, doc, email, new ContractManager.ContractListener() {
            @Override
            public void didFetchContract(final SimpleContract newContract, Exception e) {
                try {
//                    conn.listenDocEvents(newContract, ContractActivity.this);
//                    conn.listenEmailEvents(newContract, ContractActivity.this);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (loading != null) {
                                loading.cancel();
                            }

                            contract = newContract;
                            Log.w("DEBUG", "address: " + newContract.getContractAddress());
                            etAddress.setText(newContract.getContractAddress());
                        }
                    });
                } catch (Exception ex) {
                    if (loading != null) {
                        loading.cancel();
                    }

                    ex.printStackTrace();
                }
            }
        });
    }

    @OnClick(R.id.act_contract_btn_update_doc)
    public void onUpdateDocTap() {
        String doc = etDoc.getText().toString();
        conn.changeDocument(contract, doc);
    }

    @OnClick(R.id.act_contract_btn_update_name)
    public void onUpdateNameTap() {
        String name = etName.getText().toString();
        conn.changeName(contract, name);
    }
}
