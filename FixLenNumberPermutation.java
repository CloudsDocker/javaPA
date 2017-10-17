import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.BaseNCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {


    private static final String SALT = "DUUUUMMMMYYY";
    private static final String ENCODING_TYPE = "UTF-8";
//    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        // write your code here
        Main inst = new Main();
        System.out.println("test:" + inst.encode("61234567", SALT));
        List<List<Integer>> rtn = inst.permutateV2(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 7);
        StringBuilder sb = new StringBuilder();
        Writer writer = Files.newBufferedWriter(Paths.get("C:\\aaa.csv"), Charset.defaultCharset());

        System.out.println("--- there are {} items" + rtn.size());
        rtn.parallelStream().forEach(item -> {
            try {
                String line = "6" + Joiner.on("").join(item);
                line += "," + inst.encode(line, SALT) + System.lineSeparator();
                writer.write(line);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        });

        writer.flush();
        writer.close();
        System.out.println("--- Done");

    }

    private List<List<Integer>> permutateV2(int[] numbers, int lenth) {
        List<List<Integer>> allLists = new ArrayList<List<Integer>>();

        for (int i = 0; i < lenth; i++) {

            if (allLists.size() == 0) {
                // init
                for (int j = 0; j < numbers.length; j++) {
                    allLists.add(Arrays.asList(numbers[j]));
                }
            } else {
                List<List<Integer>> current = new ArrayList<List<Integer>>();
                for (List<Integer> item : allLists) {
                    for (int j = 0; j < numbers.length; j++) {
                        List<Integer> temp = Lists.newArrayList(item);
                        temp.add(numbers[j]);
                        current.add(temp);
                    }
                }
                allLists = Lists.newArrayList(current);
            }


        }
        return allLists;
    }

    private String encode(final String subject, final String salt) {

        String result = "";

        if ((null == subject) || (null == salt)) {
            return result;
        }

        try {

            final SHA1Hash hasher = new SHA1Hash(salt.getBytes(ENCODING_TYPE), ENCODING_TYPE);

            final byte[] hashValue = hasher.getHashedValue(subject.getBytes(ENCODING_TYPE));

            final BaseNCodec codec = new Base32();

            result = codec.encodeAsString(hashValue);

        } catch (Exception e) {
//            logger.warn("Error attempting to encode users access code for analytics",e);
        }
        return result;
    }

    private class SHA1Hash {

        private static final String DIGEST_ALGORITHM = "SHA-1";
        private final byte[] salt;
        private MessageDigest md;

        public SHA1Hash(final byte[] salt, final String encodingType) throws Exception, IOException {
            this.salt = Arrays.copyOf(salt, salt.length);
            initMessageDigest(DIGEST_ALGORITHM);
        }

        private synchronized void initMessageDigest(final String digestAlgorithm) throws Exception {
            try {
                md = MessageDigest.getInstance(digestAlgorithm);
            } catch (final NoSuchAlgorithmException e) {
                throw new Exception("Could not initialise message digest using " + digestAlgorithm, e);
            }
        }

        public synchronized byte[] getHashedValue(final byte[] textToHash) throws Exception {
            md.reset();
            md.update(salt);
            md.update(textToHash);
            return md.digest();
        }
    }
}
