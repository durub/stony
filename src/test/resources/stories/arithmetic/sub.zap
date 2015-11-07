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
        ; Should print "-88\n50"
        sub 212 300 -> a
        print_num a

        new_line

        sub 20 -30 -> a
        print_num a

        quit
 .END
