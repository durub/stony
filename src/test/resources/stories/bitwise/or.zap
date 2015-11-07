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
        ; Should print "255\n14232\n3"
        or 240 15 -> a
        print_num a

        new_line

        or 10000 5000 -> a
        print_num a

        new_line

        or 1 2 -> a
        print_num a

        quit
 .END
