# PlayFairCipher
The Playfair Cipher is a manual symmetric encryption technique that encrypts digraphs (pairs of letters) instead of single letters, making it more secure than traditional substitution ciphers.
Key Features:
5x5 Matrix: The cipher uses a 5x5 matrix of letters generated from a keyword, where the letters 'I' and 'J' are usually combined to fit the alphabet.
#Encryption Rules:
Same row: Shift both letters to the right.
Same column: Shift both letters downward.
Rectangle: Swap the letters to form a rectangle.
Add a filler character (usually 'X') if letters in the same pair are identical or if there’s an odd number of letters.
Decryption follows similar rules but shifts left or upward instead of right or downward.
