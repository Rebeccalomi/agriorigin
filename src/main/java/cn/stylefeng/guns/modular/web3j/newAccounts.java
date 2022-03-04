package cn.stylefeng.guns.modular.web3j;

import com.mysql.cj.jdbc.ha.LoadBalancedConnectionProxy;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class newAccounts {



    public Map<String, Object> newAccounts(String walletPwd) throws Exception {
        Map<String, Object> maps = new HashMap<>();
        ETHAccounts ethAccounts = new ETHAccounts();

        Bip39Wallet wallet;
        try {
            //本地环境
            wallet = WalletUtils.generateBip39Wallet(walletPwd, new File("/root/privatechain/data/keystore/"));

        } catch (Exception e) {
            throw new Exception("创建以太坊钱包失败");
        }

        //通过钱包密码与助记词获得钱包地址、公钥及私钥信息
        Credentials credentials = WalletUtils.loadBip39Credentials(walletPwd,
                wallet.getMnemonic());
        //钱包地址
        ethAccounts.setWalletAddress(credentials.getAddress());
        //钱包私钥16进制字符串表示
        ethAccounts.setEthPrivateKey(credentials.getEcKeyPair().getPrivateKey().toString(16));
        //钱包公钥16进制字符串表示
        ethAccounts.setEthPublicKey(credentials.getEcKeyPair().getPublicKey().toString(16));
        //保存文件名
        ethAccounts.setKeyStoreKey(wallet.getFilename());
        //12个单词的助记词
        ethAccounts.setMemorizingWords(wallet.getMnemonic());

        maps.put("ethAccounts",ethAccounts);

        return maps;
    }

    public Map<String, Object> accountsBanlance(String address) throws Exception {

        Web3j web3j = Web3JClient.getClient();
        DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(web3j.ethBlockNumber().send().getBlockNumber());
        System.out.println(defaultBlockParameter);
        Map<String, Object> maps = new HashMap<>();
        try {
            EthGetBalance ethGetBalance = web3j.ethGetBalance(address, defaultBlockParameter).send();
            if (ethGetBalance != null) {
                maps.put("address",address);
                System.out.println("账号地址：" + address);
                // 打印账户余额
                System.out.println("账号余额：" + ethGetBalance.getBalance());
                // 将单位转为以太
                System.out.println("账号余额：" + Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER)+"ETH");
            }
        }
        catch (ConnectException e){
            throw new ConnectException("################连接失败，客户端挂了");
        } catch (SocketTimeoutException exception){
            throw new SocketTimeoutException("###############连接超时，钱包地址有问题");
        }
        return maps;
    }

    /** 创建交易
     *
     */

    public Map<String, Object> newTransaction(String from, BigInteger value, String passWord,
                                              String to, String keyStoreKey, String input) throws Exception {

        Web3j web3j = Web3JClient.getClient();
        Logger logger=null;
        Map<String, Object> maps = new HashMap<>();
        try {
            //获取账户余额
            EthGetBalance ethGetBalance = web3j.ethGetBalance(from, DefaultBlockParameterName.LATEST).send();
            if (ethGetBalance != null&&ethGetBalance.getBalance().compareTo(value) == 1) {
                // 将单位转为以太，方便查看
                System.out.println("账号余额：" + Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
                // 第一个变量填入账户的密码，第二个变量填入账户文件的 path,可以在私链数据文件夹中的 keystore 文件夹中找到，是一个UTC开头的文件
                Credentials credentials = WalletUtils.loadCredentials(passWord, keyStoreKey);
                /*也可以通过私钥的方式*/

                //创建交易
                BigInteger nonce;
                EthGetTransactionCount ethGetTransactionCount = null;
                try {
                    ethGetTransactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nonce = ethGetTransactionCount.getTransactionCount();
                RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, Contract.GAS_PRICE,Contract.GAS_LIMIT,
                        to,  new BigInteger("1"), input);

                //签名
                byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
                String hexValue = Numeric.toHexString(signedMessage);
                //发起交易
                EthSendTransaction ethSendTransaction =
                        web3j.ethSendRawTransaction(hexValue).send();
                String transactionHash = ethSendTransaction.getTransactionHash();

                maps.put("transactionHash",transactionHash);
                System.out.println("地址"+transactionHash);
                //logger.info("transactionHash" + transactionHash);
            }else {
                throw new Exception("钱包账户余额不足");
            }
        }catch (ConnectException e){
            throw new ConnectException("################连接失败，客户端挂了");
        }catch (SocketTimeoutException exception){
            throw new SocketTimeoutException("###############连接超时，钱包地址有问题");
        }
        return maps;
    }

    public Map<String,Object> getTransactionByHash(String transactionHash) throws IOException{
        Map<String,Object> map = new HashMap<>();
        Web3j web3j = Web3JClient.getClient();
        Optional<Transaction> et = web3j.ethGetTransactionByHash(transactionHash).send().getTransaction();
        Transaction transaction =  et.get();
        System.out.println(transaction.getTransactionIndex());
        map.put("transaction",transaction);
        return map;
    }


    //通过交易hash获取区块信息

    public EthBlock getBlockByHash(String transactionHash) throws IOException{
        Web3j web3j = Web3JClient.getClient();
        //为true返回完整区块信息，false只返回交易hash
        EthBlock ethBlock = web3j.ethGetBlockByHash(transactionHash,true).send();
        List<EthBlock.TransactionResult> txs = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock().getTransactions();
        txs.forEach(tx -> {
            EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) tx.get();
            System.out.println("dqwdwq"+transaction.getFrom());
        });
        return ethBlock;
    }


    public static void main(String args[]) throws Exception {
        newAccounts abc=new newAccounts();
        BigInteger a=new BigInteger("0");
        abc.newAccounts(generatePassword.generatePassword(10));
        abc.accountsBanlance("0xf5e079ac84cf6567faffc6b1ba96ac3abdca6835");
        //abc.newTransaction("0xf6581b9d1d7e8d58b392035cb4cbc467b4953c86",a,"123456","0xf5e079ac84cf6567faffc6b1ba96ac3abdca6835","/root/privatechain/data/keystore/UTC--2020-03-30T04-00-58.928940570Z--f6581b9d1d7e8d58b392035cb4cbc467b4953c86.json","测试test");
        abc.getBlockByHash("0xf0db96508cabb3b4219abc701c79cebf8dab1caeb3762cce00e5ca3f8bce9ceb");
        //abc.getBlockByHash("0x65495678468b1410cfab2720ba8ef5b32d6d16a7e06e94bd9193f1bfd7198e79");

    }

}
