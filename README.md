# LoseRAR 📁
## An Implementation of Huffman Coding
A Huffman code is a particular type of optimal prefix code that is commonly used for lossless data compression. 

The process of finding or using such a code proceeds by means of Huffman coding, an algorithm developed by *David A. Huffman*.

**LoseRAR** (*inspired by the popular/indigent **WinRAR**!*) as an assignment for my *Data Structures and Algorithms* course, compresses strings/text files while it enables them to be extracted as well.

### Usage
Run `src/Main.java` and go along with the instrunctions on the CLI.

The program looks for input files in `files/input` and generates its results in the `files/output` directory.

Based on that functionality, any asked path for input is relative to the `files/input` directory.

### Process
We have the `Objects/Compressor.java` agent which uses the Huffman algorithm to produce a `Objects/Zip.java` object containing the final encoded result along with a map to indicate the encoding dictionary for future extraction.

To delineate the effecacy of the algorithm on any file, another file with a `.losehuff` is generated which contains the pure encoded result. **BUT** the dictionary map is required for the extracting process; therefore, this file solely is of no use.

The `Objects/Extractor.java` agent reads a serialized `.losezip` file written by the `Compressor` agent and using the included encoding dictionary, decodes the content to its original form.

*Using java collections was not allowed for this project by the instructor, thus all the required collections were reimplemented and are at the `Collections` package.*
