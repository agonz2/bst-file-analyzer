# Overview
This Java project reads a text file and generates an analysis of those words using a Binary Search Tree (BST). The project ensures that each word in the BST is unique, meaning duplicate words are not added to the tree. The program allows for additional tree-based operations to analyze the word set.

### File Processing 
Reads the contents of the text file, extracts words by removing non-letter characters, and ensures no word is processed more than once.

### Binary Search Tree Construction 
Each unique word is inserted into a binary search tree. This structure organizes the words such that for any given node, words on the left subtree are alphabetically smaller, and words on the right are larger.

### Tree Analysis
Once the tree is built, the program performs two main analyses:
- It counts the total number of nodes in the tree, representing the number of unique words.
- It calculates the depth of the tree, indicatating how balanced or unbalanced the tree is.
The analysis results are written to an output file (analysis.txt) when running BstAnalyzer.java

## Prerequisites
- Requires Java 8 or higher to run.