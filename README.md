  <h1 align="center"> PoC-BinaryParser </h1>

Parse Hex/Binary Strings according to a CPU. 
Only some instructions/memory addressing modes added, however, you could add more easily (and or customize the already added instructions.
The added instructions can be found in this table:
<br>
<img height="300px" src="https://i.imgur.com/XkynGKA.png">

It is basically using the parse string with radix to change from base 16 to 2 and then substrings are created using the instruction provided. It only creates the mnemonic in a fancy and readable way, plan is to reverse the proccess and create a programmer for your CPU.

Might expand this with one of my working CPUs, which can be found here: https://github.com/memoriasIT/PCBs-and-Circuits
