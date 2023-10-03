#include <stdio.h>
#include "sharex.h"

int main(int argc, char **argv)
{
    printf("Hello word, this is \"%s\"\n", argv[0]);

    lib_hello();

    return 0;
}

