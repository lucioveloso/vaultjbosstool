package br.lucio.jboss.vaultjbosstool;

import org.jboss.security.plugins.PBEUtils;
import org.picketbox.plugins.vault.PicketBoxSecurityVault;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("WARNING: This tool will convert JKS to JCEKS if your keystore is not a JCEKS.");
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



        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEwithMD5andDES");
        char[] password = "somearbitrarycrazystringthatdoesnotmatter".toCharArray();
        PBEParameterSpec cipherSpec = new PBEParameterSpec(((String) data.get("SALT")).getBytes(), Integer.valueOf((String) data.get("ITERATION_COUNT")));
        PBEKeySpec keySpec = new PBEKeySpec(password);
        SecretKey cipherKey = factory.generateSecret(keySpec);
        String decodedValue = PBEUtils.decode64(((String) data.get("KEYSTORE_PASSWORD")).substring("MASK-".length()), "PBEwithMD5andDES", cipherKey, cipherSpec);
        System.out.println("Keystore password: " + decodedValue);

        PicketBoxSecurityVault meuPick = new PicketBoxSecurityVault();
        meuPick.init(data);
        //Insert: meuPick.store("TOTO", "TEST", "test".toCharArray(), null);
        System.out.println("List of vault attributes:");
        for (String s : meuPick.keyList()) {
            String block = s.split(":")[0];
            String attribute = s.split(":")[2];
            char[] senha = meuPick.retrieve(block, attribute, null);
            System.out.println("Block: " + block + " Attribute: " + attribute + " Pass: " + String.valueOf(senha));
        }
    }
}
