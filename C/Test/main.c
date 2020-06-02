#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFFER_LEN 128;
#define NUM_P 100;

int main(int argc, char *argv[])
{
    char buffer[128];
    char* pS[100] = { NULL };
    char* pbuffer = buffer;
    int i = 0;

    printf("input");
    for (i = 0; i < 100; i++)
    {
        pbuffer = buffer;
        printf("enter:");

        while ((pbuffer - buffer < 128 - 1) && ((*pbuffer++ = getchar()) != '\n'));

        if ((pbuffer - buffer) < 2)
            break;


        pS[i] = (char*)malloc(pbuffer - buffer);
        strcpy(pS[i], buffer);
    }

    return 0;
}
