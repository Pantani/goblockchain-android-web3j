package mobi.pixon.goblockchain.Helpers.Accounts;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class Danilo {

    public Credentials CREDENTIALS;
    public String ADDRESS;
    static final String fileName = "keyfile.key";
    static final String password = "123";

    public Danilo(Context context) {
        try {
            //Credencial chave geth
            InputStream in = context.getAssets().open(fileName);
            ObjectMapper objectMapper = new ObjectMapper();
            WalletFile wFile = objectMapper.readValue(in, WalletFile.class);
            this.CREDENTIALS = Credentials.create(Wallet.decrypt(password, wFile));
            this.ADDRESS = CREDENTIALS.getAddress();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }

}
