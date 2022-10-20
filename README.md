# Sock Pattern Generator

### A generator for sock knitting patterns.
One of my favorite stress-relieving hobbies is knitting, 
especially knitting something relatively quick like a pair of socks. 
After knitting 5 pairs back to back during a particularly stressful exam period, I made three observations:
* My patterns generally have the same structure, just the number of stitches differs
* I often refer back to previous patterns when planning my next pair of socks
* The random text files and pieces of paper I used for patterns are easy to misplace

So I decided to create a small desktop application that allows me to configure a sock, 
generate a knitting pattern based on that configuration and save the configuration for later.
Currently, only _plain, cuff-to-toe socks with a German short row heel_ are supported, 
but I do intend on expanding that in the future.

### Main goals
* Display all the relevant parameters a sock has in a user-friendly way
* Calculate the number of rounds needed for the foot, based on shoe size
* Generate a text file containing the knitting pattern
* Save the sock configuration as a JSON file
* Reach and maintain a high level of test coverage

### Planned features
* Ability to add notes to the sock JSON file (like the yarn used for that sock)
* Loading a sock JSON file and displaying the parameters
* Support for striped and ribbed socks
* More heel types and their specific parameters
* Ability to set a custom directory for patterns and socks (as opposed to the static MyPatterns and MySocks directories)
* Support for toe-up socks