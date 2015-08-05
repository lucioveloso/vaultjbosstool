package br.lucio.jboss.vaultjbosstool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.jboss.security.vault.SecurityVaultException;
import org.picketbox.plugins.vault.PicketBoxSecurityVault;

public class Main {

	public static void main(String[] args) throws SecurityVaultException, IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Keystore URL: (Ex: /tmp/new/a.keystore)");
		data.put("KEYSTORE_URL", in.readLine());
		System.out.println("Keystore Password: (Ex: MASK-2CnDY1FriorSpKmoIGU5WR)");
		data.put("KEYSTORE_PASSWORD", in.readLine());
		System.out.println("Salt: (Ex: 12345678)");
		data.put("SALT", in.readLine());
		System.out.println("Interation: (Ex: 44)");
		data.put("ITERATION_COUNT", in.readLine());
		System.out.println("Alias: (Ex: vault)");
		data.put("KEYSTORE_ALIAS", in.readLine());
		System.out.println("Enc file directory: (Ex: /tmp/new)");
		data.put("ENC_FILE_DIR", in.readLine());
		
		PicketBoxSecurityVault meuPick = new PicketBoxSecurityVault();
		meuPick.init(data);
		System.out.println("List of vault attributes:");
		for (String s : meuPick.keyList()) {
		    String block = s.split(":")[0];
		    String attribute = s.split(":")[2];
		    char[] senha = meuPick.retrieve(block, attribute,null);
		    System.out.println("Block: " + block + " Attribute: " + attribute + " Pass: " + String.valueOf(senha));
		}
	}
}
