package com.test.devops;

import com.test.devops.security.OAuth2Constants;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenerateKeyPairApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(OAuth2Constants.ALGORITHM);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        byte[] publicKey = keyPair.getPublic().getEncoded();
        byte[] privateKey = keyPair.getPrivate().getEncoded();
        generateKeyFile(publicKey, OAuth2Constants.PUB_KEY, OAuth2Constants.PUB_KEY_FILENAME);
        generateKeyFile(privateKey, OAuth2Constants.PRI_KEY, OAuth2Constants.PRI_KEY_FILENAME);
    }

    private static void generateKeyFile(byte[] privateKey, String header, String fileName) throws IOException {
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        PemObject pemObject = new PemObject(header, privateKey);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
    }

}
