package mobi.pixon.goblockchain.Helpers.Accounts;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.utils.Numeric;

public class Bob {
	private static final String PRIVATE_KEY = "0106bbdbe23c67be4ed666f5c863158417d05f9ac357d4d0fee46726f3f1d8f6";
	static final ECKeyPair KEY_PAIR = ECKeyPair.create(Numeric.toBigInt(PRIVATE_KEY));

	public static final Credentials CREDENTIALS = Credentials.create(KEY_PAIR);
	public static final String ADDRESS = CREDENTIALS.getAddress();
}
