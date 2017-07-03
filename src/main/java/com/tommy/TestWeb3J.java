package com.tommy;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tommy on 2017/6/28.
 */
public class TestWeb3J {
    public static void main(String[] args) throws ExecutionException, InterruptedException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        Web3j web3 = Web3j.build(new HttpService("http://172.104.73.26:8543"));  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println(clientVersion);
        File file=new File("target");
        if(!file.exists()){
            file.createNewFile();
        }
        WalletUtils.generateFullNewWalletFile("test",file);
    }
}
