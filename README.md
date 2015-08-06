# About vaultjbosstool

Features of this tool:
- Convert a JKS to JCEKS (Useful when migrating a JBoss EAP version 6.0 to a version 6.3 or higher one.
- Reveal the password of the keystore.
- Reveal the attributes stored in a vault and passwords.

Esta ferramenta tem como funcionalidade:
- Converter um JKS em JCEKS (Útil ao migrar de uma versão de JBoss EAP 6.0 para uma versão superior que 6.3.
- Revelar a senha do Keystore.
- Revelar os atributos armazenados em um Vault e suas senhas.

# Howto use:

shell# java -jar vaultjbosstool.jar

WARNING: This tool will convert JKS to JCEKS if your keystore is not a JCEKS.

Enter Keystore URL: (Ex: /tmp/new/a.keystore)

/tmp/new/a.keystore

Enter Keystore Password: (Ex: MASK-2CnDY1FriorSpKmoIGU5WR)

MASK-2CnDY1FriorSpKmoIGU5WR

Enter Salt: (Ex: 12345678)
12345678
Enter Interation: (Ex: 44)
44
Enter Alias: (Ex: vault)
vault
Enter Enc file directory: (Ex: /tmp/new)
/tmp/new

RESULT:

Keystore password: minhasenha

List of vault attributes:

Block: blocoa Attribute: atributodecuehrola Pass: otherpassword

Block: blocob Attribute: atributo Pass: securedpass

# Notes
Older versions of PicketBox Vault use JKS keystore and ENC.dat and Shared.dat files.
Newer versions of PicketBox consist of JCEKS keystore and VAULT.dat file.
