import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindDuplicateFiles {
    private static MessageDigest messageDigest;
    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException("cannot initialize SHA-512 hash function", e);
        }
    }

    public static void findDuplicateFiles(final Map<String, List<String>> filesList, final File directory) {
        for (final File dirChild : directory.listFiles()) {
            // Iterate all file sub directories recursively
            if (dirChild.isDirectory()) {
                findDuplicateFiles(filesList, dirChild);
            } else {
                try {
                    // Read file as bytes
                    final FileInputStream fileInput = new FileInputStream(dirChild);
                    final byte fileData[] = new byte[(int) dirChild.length()];
                    fileInput.read(fileData);
                    fileInput.close();
                    // Create unique hash for current file
                    final String uniqueFileHash = new BigInteger(1, messageDigest.digest(fileData)).toString(16);
                    List<String> identicalList = filesList.get(uniqueFileHash);
                    if (identicalList == null) {
                        identicalList = new LinkedList<>();
                    }
                    // Add path to list
                    identicalList.add(dirChild.getAbsolutePath());
                    // push updated list to Hash table
                    filesList.put(uniqueFileHash, identicalList);
                } catch (final IOException e) {
                    throw new RuntimeException("cannot read file " + dirChild.getAbsolutePath(), e);
                }
            }
        }
    }

    public static void main(final String[] args) {

        final File dir = new File("/Users/bulusus/workspace/platform/");
        if (!dir.isDirectory()) {
            System.out.println("Supplied directory does not exist.");
            return;
        }
        final Map<String, List<String>> lists = new HashMap<>();
        final long start = System.currentTimeMillis();
        FindDuplicateFiles.findDuplicateFiles(lists, dir);
        System.out.println("Time lapse : " + (System.currentTimeMillis() - start));
        for (final List<String> list : lists.values()) {
            if (list.size() > 1) {
                System.out.println("\n");
                for (final String file : list) {
                    System.out.println(file);
                }
            }
        }
        System.out.println("\n");
    }

}
