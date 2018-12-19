package network.marmoj.utils;

import network.marmoj.model.core.Intent;
import network.marmoj.model.core.SignedIntent;
import org.junit.Assert;
import org.junit.Test;
import org.web3j.utils.Numeric;

import java.security.SignatureException;

import static network.marmoj.utils.MarmoUtils.keccak256;
import static network.marmoj.utils.MarmoUtils.sign;
import static org.junit.Assert.assertEquals;
import static org.web3j.crypto.Sign.*;
import static org.web3j.utils.Numeric.hexStringToByteArray;

public class MarmoUtilsTest {


    @Test
    public void testKeccak256() {
        assertEquals(keccak256(""), "c5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470");
        assertEquals(keccak256("cc"), "eead6dbfc7340a56caedc044696a168870549a6a7f6f56961e84a54bd9970b8a");
        assertEquals(keccak256("41fb"), "a8eaceda4d47b3281a795ad9e1ea2122b407baf9aabcb9e18b5717b7873537d2");
        assertEquals(keccak256("1f877c"), "627d7bc1491b2ab127282827b8de2d276b13d7d70fb4c5957fdf20655bc7ac30");
        assertEquals(keccak256("c1ecfdfc"), "b149e766d7612eaf7d55f74e1a4fdd63709a8115b14f61fcd22aa4abc8b8e122");
        assertEquals(keccak256("21f134ac57"), "67f05544dbe97d5d6417c1b1ea9bc0e3a99a541381d1cd9b08a9765687eb5bb4");
        assertEquals(keccak256("c6f50bb74e29"), "923062c4e6f057597220d182dbb10e81cd25f60b54005b2a75dd33d6dac518d0");
        assertEquals(keccak256("119713cc83eeef"), "feb8405dcd315d48c6cbf7a3504996de8e25cc22566efec67433712eda99894f");
        assertEquals(keccak256("4a4f202484512526"), "e620d8f2982b24fedaaa3baa9b46c3f9ce204ee356666553ecb35e15c3ff9bf9");
        assertEquals(keccak256("1f66ab4185ed9b6375"), "9e03f7c9a3d055eca1d786ed6fb624d93f1cf0ac27f9c2b6c05e509fac9e7fca");
        assertEquals(keccak256("eed7422227613b6f53c9"), "caad8e1ed546630748a12f5351b518a9a431cda6ba56cbfc3ccbdd8aae5092f7");
        assertEquals(keccak256("eaeed5cdffd89dece455f1"), "d61708bdb3211a9aab28d4df01dfa4b29ed40285844d841042257e97488617b0");
        assertEquals(keccak256("5be43c90f22902e4fe8ed2d3"), "0f53be55990780b3fad9870f04f7d8153c3ae605c057c85abb5d71765043aaa8");
        assertEquals(keccak256("a746273228122f381c3b46e4f1"), "32215ae88204a782b62d1810d945de49948de458600f5e1e3896ceca2ed3292b");
        assertEquals(keccak256("3c5871cd619c69a63b540eb5a625"), "9510da68e58ebb8d2ab9de8485bb408e358299a9c011ae8544b0d0faf9d4a4ea");
        assertEquals(keccak256("fa22874bcc068879e8ef11a69f0722"), "f20b3bcf743aa6fa084038520791c364cb6d3d1dd75841f8d7021cd98322bd8f");
        assertEquals(keccak256("52a608ab21ccdd8a4457a57ede782176"), "0e32defa2071f0b5ac0e6a108b842ed0f1d3249712f58ee0ddf956fe332a5f95");
        assertEquals(keccak256("82e192e4043ddcd12ecf52969d0f807eed"), "9204550677b9aa770e6e93e319b9958540d54ff4dccb063c8561302cd8aff676");
        assertEquals(keccak256("75683dcb556140c522543bb6e9098b21a21e"), "a6d5444cb7aa61f5106cdedb39d5e1dd7d608f102798d7e818ac87289123a1db");
        assertEquals(keccak256("06e4efe45035e61faaf4287b4d8d1f12ca97e5"), "5796b993d0bd1257cf26782b4e58fafb22b0986d88684ab5a2e6cec6706275f9");
        assertEquals(keccak256("e26193989d06568fe688e75540aea06747d9f851"), "cfbe73c6585be6204dd473abe356b539477174c4b770bfc91e9fdbcbc57086e6");
        assertEquals(keccak256("d8dc8fdefbdce9d44e4cbafe78447bae3b5436102a"), "31c8006b0ec35e690674297cb27476db6066b5fa9825c60728e9e0bb338fb7c3");
        assertEquals(keccak256("57085fd7e14216ab102d8317b0cb338a786d5fc32d8f"), "3b8fa3904fe1b837565a50d0fbf03e487d6d72fc3cea41adcce33df1b835d247");
        assertEquals(keccak256("a05404df5dbb57697e2c16fa29defac8ab3560d6126fa0"), "37febc4df9d50daeabd0caa6578812a687e55f1ac0b109d2512810d00548c85b");
        assertEquals(keccak256("aecbb02759f7433d6fcb06963c74061cd83b5b3ffa6f13c6"), "2329810b5a4735bcd49c10e6456c0b1ded5eac258af47cbb797ca162ab6d1ba8");
        assertEquals(keccak256("aafdc9243d3d4a096558a360cc27c8d862f0be73db5e88aa55"), "6fffa070b865be3ee766dc2db49b6aa55c369f7de3703ada2612d754145c01e6");
        assertEquals(keccak256("7bc84867f6f9e9fdc3e1046cae3a52c77ed485860ee260e30b15"), "b30761c053e926f150b9dce7e005b4d87811ccfb9e3b6edb0221022f01711cf0");
        assertEquals(keccak256("fac523575a99ec48279a7a459e98ff901918a475034327efb55843"), "04f1b3c1e25ba5d012e22ad144e5a8719d94322d05ad9ef61e7db49b59959b3a");
        assertEquals(keccak256("0f8b2d8fcfd9d68cffc17ccfb117709b53d26462a3f346fb7c79b85e"), "aeef4b4da420834ffced26db291248fb2d01e765e2b0564057f8e6c2030ac37f");
        assertEquals(keccak256("a963c3e895ff5a0be4824400518d81412f875fa50521e26e85eac90c04"), "03d26aeeb4a7bdddbff7cff667198c425941a2776922df2bec545f5304e2c61c");
        assertEquals(keccak256("03a18688b10cc0edf83adf0a84808a9718383c4070c6c4f295098699ac2c"), "435cfc0d1afd8d5509a9ccbf49706575038685bf08db549d9714548240463ee9");
        assertEquals(keccak256("84fb51b517df6c5accb5d022f8f28da09b10232d42320ffc32dbecc3835b29"), "d477fb02caaa95b3280ec8ee882c29d9e8a654b21ef178e0f97571bf9d4d3c1c");
        assertEquals(keccak256("9f2fcc7c90de090d6b87cd7e9718c1ea6cb21118fc2d5de9f97e5db6ac1e9c10"), "24dd2ee02482144f539f810d2caa8a7b75d0fa33657e47932122d273c3f6f6d1");
        assertEquals(keccak256("de8f1b3faa4b7040ed4563c3b8e598253178e87e4d0df75e4ff2f2dedd5a0be046"), "e78c421e6213aff8de1f025759a4f2c943db62bbde359c8737e19b3776ed2dd2");
        assertEquals(keccak256("62f154ec394d0bc757d045c798c8b87a00e0655d0481a7d2d9fb58d93aedc676b5a0"), "cce3e3d498328a4d9c5b4dbf9a1209628ab82621ad1a0d0a18680362889e6164");
        assertEquals(keccak256("b2dcfe9ff19e2b23ce7da2a4207d3e5ec7c6112a8a22aec9675a886378e14e5bfbad4e"), "f871db93c5c92ecd65d4edb96fcb12e4729bc2a1899f7fb029f50bff431cbb72");
        assertEquals(keccak256("47f5697ac8c31409c0868827347a613a3562041c633cf1f1f86865a576e02835ed2c2492"), "4eb143477431df019311aed936cab91a912ec1e6868b71e9eddb777408d4af34");
        assertEquals(keccak256("512a6d292e67ecb2fe486bfe92660953a75484ff4c4f2eca2b0af0edcdd4339c6b2ee4e542"), "9a0c1d50a59dbf657f6713c795ed14e1f23b4eaa137c5540aacdb0a7e32c29fc");
        assertEquals(keccak256("973cf2b4dcf0bfa872b41194cb05bb4e16760a1840d8343301802576197ec19e2a1493d8f4fb"), "ba062e5d370216d11985c4ca7a2658ddc7328b4be4b40a52dd8fa3ca662f09d1");
        assertEquals(keccak256("80beebcd2e3f8a9451d4499961c9731ae667cdc24ea020ce3b9aa4bbc0a7f79e30a934467da4b0"), "3a083ae163df42bd51b9c664bee9dc4362f16e63383df16473df71be6dd40c1c");
        assertEquals(keccak256("7abaa12ec2a7347674e444140ae0fb659d08e1c66decd8d6eae925fa451d65f3c0308e29446b8ed3"), "4876e273ac00942576d9608d5b63ecc9a3e75d5e0c42c6abdbcde037785af9a7");
        assertEquals(keccak256("c88dee9927679b8af422abcbacf283b904ff31e1cac58c7819809f65d5807d46723b20f67ba610c2b7"), "4797ba1c7ab7197050d6b2e506f2df4550e4b673df78f18c465424e48df5e997");
        assertEquals(keccak256("01e43fe350fcec450ec9b102053e6b5d56e09896e0ddd9074fe138e6038210270c834ce6eadc2bb86bf6"), "41c91be98c5813a4c5d8ae7c29b9919c1cc95b4a05f82433948cb99d9a6d039c");
        assertEquals(keccak256("337023370a48b62ee43546f17c4ef2bf8d7ecd1d49f90bab604b839c2e6e5bd21540d29ba27ab8e309a4b7"), "ee354290e3f9ce9123c49ba616e1a2684a90f3ddd84e73a1d2c232f740412b18");
        assertEquals(keccak256("6892540f964c8c74bd2db02c0ad884510cb38afd4438af31fc912756f3efec6b32b58ebc38fc2a6b913596a8"), "fbec0b6d71696eede900b77aa6d7d25f4ab45df8961ca9c8b3f4f9b51af983ab");
        assertEquals(keccak256("f5961dfd2b1ffffda4ffbf30560c165bfedab8ce0be525845deb8dc61004b7db38467205f5dcfb34a2acfe96c0"), "9d24aeea08f9a4b5fb8b6de85a2296f5f4108ddd1eea4f8ee58819cf84edb765");
        assertEquals(keccak256("ca061a2eb6ceed8881ce2057172d869d73a1951e63d57261384b80ceb5451e77b06cf0f5a0ea15ca907ee1c27eba"), "732034cae3ff1116f07fc18b5a26ef8faf3fe75d3dbca05e48795365e0a17c40");
        assertEquals(keccak256("1743a77251d69242750c4f1140532cd3c33f9b5ccdf7514e8584d4a5f9fbd730bcf84d0d4726364b9bf95ab251d9bb"), "deac521805bc6a97c0870e9e225d1c4b2fd8f3a9a7f6b39e357c26414821e2dd");
        assertEquals(keccak256("d8faba1f5194c4db5f176fabfff856924ef627a37cd08cf55608bba8f1e324d7c7f157298eabc4dce7d89ce5162499f9"), "ad55537347b20d9fca02683e6de1032ec10eb84da4cbd501e49744a666292edf");
        assertEquals(keccak256("be9684be70340860373c9c482ba517e899fc81baaa12e5c6d7727975d1d41ba8bef788cdb5cf4606c9c1c7f61aed59f97d"), "b1f990204bf630569a3edc634864274786f40ce1c57165ee32d0e29f5d0c6851");
        assertEquals(keccak256("7e15d2b9ea74ca60f66c8dfab377d9198b7b16deb6a1ba0ea3c7ee2042f89d3786e779cf053c77785aa9e692f821f14a7f51"), "fa460cd51bc611786d364fcabe39052bcd5f009edfa81f4701c5b22b729b0016");
        assertEquals(keccak256("9a219be43713bd578015e9fda66c0f2d83cac563b776ab9f38f3e4f7ef229cb443304fba401efb2bdbd7ece939102298651c86"), "f7b0fe5a69ff44060d4f6ad2486e6cde9ed679af9aa1ada613e4cc392442beb5");
        assertEquals(keccak256("c8f2b693bd0d75ef99caebdc22adf4088a95a3542f637203e283bbc3268780e787d68d28cc3897452f6a22aa8573ccebf245972a"), "24204d491f202534859fc0a208237184471a2d801fb3b934d0968d0d843d0345");
        assertEquals(keccak256("ec0f99711016c6a2a07ad80d16427506ce6f441059fd269442baaa28c6ca037b22eeac49d5d894c0bf66219f2c08e9d0e8ab21de52"), "81147cba0647eee78c4784874c0557621a138ca781fb6f5dcd0d9c609af56f35");
        assertEquals(keccak256("0dc45181337ca32a8222fe7a3bf42fc9f89744259cff653504d6051fe84b1a7ffd20cb47d4696ce212a686bb9be9a8ab1c697b6d6a33"), "5b6d7eda559574fae882e6266f4c2be362133e44b5a947ecb6e75db9fc8567e0");
    }

    private static final byte[] TEST_MESSAGE = "A test message".getBytes();

    @Test
    public void testSignMessage() {

        Intent intent = new Intent();
        intent.setId(TEST_MESSAGE);

        SignedIntent signedIntent = sign(intent, SampleKeys.CREDENTIALS);

        SignatureData expected = new SignatureData(
                (byte) 27,
                hexStringToByteArray("0xbefef6d19dc2da2d5e394d2d2bc68b5cd1c4f869a19b2b2d6dbccce84702edd6"),
                hexStringToByteArray("0x7d83911011b94392fcf701b6ddee49e4f50d3ce92b306dae538007e4228fa9a1")
        );

        Assert.assertEquals(signedIntent.getSignature(), expected);
    }

    @Test
    public void testPublicKeyFromPrivateKey() {
        Assert.assertEquals(publicKeyFromPrivate(SampleKeys.PRIVATE_KEY), SampleKeys.PUBLIC_KEY);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidSignature() throws SignatureException {
        signedMessageToKey(
                TEST_MESSAGE, new SignatureData((byte) 27, new byte[]{1}, new byte[]{0}));
    }

    @Test
    public void create2Test() {
        byte[] from = Numeric.hexStringToByteArray("ca35b7d915458ef540ade6068dfe2f44e8fa733c");
        byte[] salt = Numeric.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000001");
        byte[] code = Numeric.hexStringToByteArray("6080604052348015600f57600080fd5b50606780601d6000396000f3fe6080604052366000803760008036600073000000d781bcca1b13eba4fc04f1a8fdb12f69825af43d6000803e8015156036573d6000fd5b3d6000f3fea165627a7a7230582033b260661546dd9894b994173484da72335f9efc37248d27e6da483f15afc1350029");

        Assert.assertEquals("0x90ce4b17b425167341ac5d1e716b233e64728c26", MarmoUtils.create2(from, code, salt));
    }

}