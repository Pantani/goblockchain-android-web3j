package mobi.pixon.goblockchain.Helpers.Accounts;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.utils.Numeric;

public class Alice {
	static final String PRIVATE_KEY = "0x69081defa229bfb75c5915bb36ad065673493ffce0c72efb5528c549a1b387c9";
//	static final String PRIVATE_KEY = "0x69081d333229bfb75c5915bb36ad065673493ffce0c72efb5528c549a1b387c9";
    static final ECKeyPair KEY_PAIR = ECKeyPair.create(Numeric.toBigInt(PRIVATE_KEY));

	public static final Credentials CREDENTIALS = Credentials.create(KEY_PAIR);
	public static final String ADDRESS = CREDENTIALS.getAddress();
}
