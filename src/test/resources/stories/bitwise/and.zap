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
        ; Should print "0\768\n0"
        and 240 15 -> a
        print_num a

        new_line

        and 10000 5000 -> a
        print_num a

        new_line

        and 1 2 -> a
        print_num a

        quit
 .END
