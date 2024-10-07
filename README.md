# PlayFairCipher

The **Playfair Cipher** is a manual symmetric encryption technique that operates on digraphs (pairs of letters) rather than individual characters. This feature makes it more secure than traditional substitution ciphers.

## Key Features:
1. **5x5 Matrix**:  
   A 5x5 matrix is created using a keyword, where each letter of the alphabet (except 'J') is placed in the matrix. The letters 'I' and 'J' are typically combined to ensure the matrix fits 25 letters.
   
2. **Encryption Rules**:
   - **Same Row**: If both letters in the digraph are in the same row of the matrix, shift both letters to the right. If one of them is the last letter of the row, wrap around to the first letter.
   - **Same Column**: If both letters are in the same column, shift them downward. If one of them is the bottom letter, wrap around to the top of the column.
   - **Rectangle Rule**: If the letters form a rectangle, swap them by replacing each with the letter in the opposite corner of the rectangle.
   - **Filler Character**: A filler character (usually 'X') is added between repeating letters in a digraph or appended if the plaintext has an odd number of characters.

3. **Decryption**:  
   The decryption process follows the reverse of the encryption rules, shifting letters to the left or upward instead of right or downward, and applying the rectangle rule as well.



This technique is more complex than simple monoalphabetic ciphers, making frequency analysis more difficult for attackers. However, it remains vulnerable to known plaintext attacks if not used carefully.

---

This project provides an implementation of the Playfair Cipher encryption and decryption in Java.
