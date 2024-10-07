import java.util.Scanner;

class PlayFairCipher {
    public static char[][] a = new char[5][5];

    // Method to compute and create the 5x5 Playfair cipher matrix
    public static void computeMatrix(String key) {
        int i = 0;
        boolean visited[] = new boolean[26];

        // Filling in the unique key alphabets
        for (char c : key.toCharArray()) {
            if (c == 'J') continue; // Skip 'J' (it is treated as 'I')
            if (!visited[c - 'A']) {
                a[i / 5][i % 5] = c;
                visited[c - 'A'] = true;
                i++;
            }
        }

        // Filling in the remaining alphabets
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J' || visited[c - 'A']) continue;
            a[i / 5][i % 5] = c;
            visited[c - 'A'] = true;
            i++;
        }
    }

    // Method to encrypt the plain text
    public static void encrypt(String s, String key) {
        computeMatrix(key);

        // Prepare digraphs
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            str.append(c == 'J' ? 'I' : c); // Replace 'J' with 'I'
            if (i < s.length() - 1 && c == s.charAt(i + 1)) {
                str.append('X'); // Insert 'X' between repeating characters
            }
        }
        if (str.length() % 2 != 0) {
            str.append('X'); // Append 'X' if the length is odd
        }

        // Actual encryption
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i += 2) {
            char a1 = str.charAt(i);
            char a2 = str.charAt(i + 1);
            int[] apos = getPosition(a1);
            int[] bpos = getPosition(a2);

            if (apos[0] == bpos[0]) {
                // Same row: Shift right
                encryptedText.append(a[apos[0]][(apos[1] + 1) % 5]);
                encryptedText.append(a[bpos[0]][(bpos[1] + 1) % 5]);
            } else if (apos[1] == bpos[1]) {
                // Same column: Shift down
                encryptedText.append(a[(apos[0] + 1) % 5][apos[1]]);
                encryptedText.append(a[(bpos[0] + 1) % 5][bpos[1]]);
            } else {
                // Rectangle case: Swap columns
                encryptedText.append(a[apos[0]][bpos[1]]);
                encryptedText.append(a[bpos[0]][apos[1]]);
            }
        }
        System.out.println("Encrypted text using PlayFair Cipher is: " + encryptedText);
    }

    // Method to decrypt the cipher text
    public static void decrypt(String str, String key) {
        computeMatrix(key);
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < str.length(); i += 2) {
            char a1 = str.charAt(i);
            char a2 = str.charAt(i + 1);
            int[] apos = getPosition(a1);
            int[] bpos = getPosition(a2);

            if (apos[0] == bpos[0]) {
                // Same row: Shift left
                decryptedText.append(a[apos[0]][(apos[1] + 4) % 5]);
                decryptedText.append(a[bpos[0]][(bpos[1] + 4) % 5]);
            } else if (apos[1] == bpos[1]) {
                // Same column: Shift up
                decryptedText.append(a[(apos[0] + 4) % 5][apos[1]]);
                decryptedText.append(a[(bpos[0] + 4) % 5][bpos[1]]);
            } else {
                // Rectangle case: Swap columns
                decryptedText.append(a[apos[0]][bpos[1]]);
                decryptedText.append(a[bpos[0]][apos[1]]);
            }
        }
        System.out.println("If the last character is 'X', it would likely be a filler used while creating diagram");
        System.out.println("Decrypted text using PlayFair Cipher is: " + decryptedText);
    }

    // Method to get the position of a character in the matrix
    public static int[] getPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (a[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \n 1 to encrypt plain text using PlayFair Cipher : \n 2 to decrypt cipher text using PlayFair Cipher:");
        int ch = sc.nextInt();
        sc.nextLine(); // Consume the newline

        if (ch == 1) {
            System.out.println("Enter plain text: ");
            String s = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", ""); // Remove non-letter characters
            System.out.println("Enter key to encrypt: ");
            String k = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
            encrypt(s, k);
        } else if (ch == 2) {
            System.out.println("Enter cipher text: ");
            String s = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
            System.out.println("Enter key to decrypt: ");
            String k = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
            decrypt(s, k);
        } else {
            System.out.println("Oops! Wrong choice");
        }
        sc.close();
    }
}
