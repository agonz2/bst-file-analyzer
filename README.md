# Overview
This Java program reads a text file (dracula.txt), processes its contents, and organizes the words into a binary search tree (BST) for analysis. 

# Features
The key tasks the program performs include:

**File Processing**: It reads the contents of the text file, extracts words by removing non-letter characters, and ensures no word is processed more than once.

**BST Construction**: Each unique word is inserted into a binary search tree. This structure organizes the words such that for any given node, words on the left subtree are alphabetically smaller, and words on the right are larger.

**Tree Analysis**: Once the tree is built, the program performs two main analyses:
- It counts the total number of nodes in the tree, representing the number of unique words.
- It calculates the depth of the tree, indicatating how balanced or unbalanced the tree is.
The analysis results are written to an output file (analysis.txt) when running DraculaBST.java

# Requirements
Java Development Kit (JDK) 8 or later.