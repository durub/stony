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
        ; Should print "0\n50\n50"
        mod 20 5 -> a
        print_num a

        new_line

        mod -120 70 -> a
        print_num a

        new_line

        mod 120 -70 -> a
        print_num a

        quit
 .END
