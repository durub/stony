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
        ; Should print "2\n-1\n10000"
        div 600 300 -> a
        print_num a

        new_line

        div 30 -30 -> a
        print_num a

        new_line

        div -10000 -1 -> a
        print_num a

        quit
 .END
