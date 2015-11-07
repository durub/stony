; Assembled using the Zapf assembler
GLOBAL::
        .GVAR a =10

OBJECT::
IMPURE:: 

VOCAB::
        .BYTE 0
        .BYTE 4
        .WORD 0

WORDS::

ENDLOD::

        .FUNCT GO
START::
        ; Should print "18662\n-900\n50"
        mul 602 31 -> a
        print_num a

        new_line

        mul 30 -30 -> a
        print_num a

        new_line

        mul -50 -1 -> a
        print_num a

        quit
 .END
