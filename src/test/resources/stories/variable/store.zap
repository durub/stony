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
        ; Should print "10\n100\n-15000"
        print_num a

        new_line

        store 'a 100
        print_num a

        new_line

        store 'a -15000
        print_num a

        quit
 .END
