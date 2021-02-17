# CPU-Emulator
 a CPU emulator software that support a basic instruction set (15 instructions).
 Computer has 256 bytes of available memory (M) initially set to zero.
 Emulator loads a program code from a text file.
 
 
 **Units**
 
 M[X] : Memory
 
 PC : Program Counter
 
 AC : Accumulator
 
 F : Flag
 
 
 **Construction List**
 
 START : Starts the program execution.
 
 LOAD X : Load immediate value X to AC. Ex: LOAD 25 means AC=25
 
 LOADM M[X] : Load memory value stored at M[X] to AC. Ex: LOADM 25 means AC=M[25]
 
 STORE X : Store value in AC to memory location M[X]. Ex: STORE 10 means M[10]=AC
 
 CMPM M[X] : Compare, 
 
    If the value in AC is greater than value in M[X] then set F flag to 1 

    If the value in AC is less than value in M[X] then set F flag to -1  

    If the value in AC is equal to value in M[X] then set F flag to 0
             
 CJMP X : Conditional Jump, Update the PC with X if the F flag value is positive.
 
 JMP X : Unconditional Jump, Update the PC value with X. Ex: JMP 114 means PC=114
 
 ADD X : Add immediate value of X to AC. Ex: ADD 67 means AC=AC+67
 
 ADDM M[X] : Add Memory value of M[X] to AC. Ex: ADDM 180 means AC=AC+M[180]
 
 SUB X : Subtract immediate value of X from AC. Ex: SUB 75 means AC=AC-75
 
 SUBM M[X] : Subtract Memory value of M[X] from AC. Ex: SUBM 150 means AC=AC-M[150]
 
 MUL X : Multiply AC with immediate value of X. Ex: MUL 4 means AC=AC×4
 
 MULM X : Multiply AC with M[X]. Ex: MULM 4 means AC=AC×M[4]
 
 DISP : Display the value in AC on screen.
 
 HALT : Stop execution.
 
 
 **Program Code Files**
 
  1. program.txt : An app that can compute the sum of the numbers between 0 and 20.
  
  2. program02.txt : an application that prints n×m matrix indices in row major order. The value of m and n variables initialized in the beginning of code.
  
    If n=3 and m=4 then it should print values 1 1 1 2 1 3 1 4 2 1 2 2 2 3 2 4 3 1 3 2 3 3 3 4 4 1 4 2 4 3 4 4(or vertically).
