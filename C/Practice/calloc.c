#include <stdio.h>
#include <stdlib.h>

int main()
{
    int *p = (int *)calloc(100, sizeof(int));

    for(int i = 0; i < 10; i++)
    {
        *(p + i) = i + 1;
    }
    printf("%p\n%p\n", p, p + 1);

    for(int i = 0; i < 10; i++)
    {
        printf("%d\t", *(p+i));
    }
    printf("\n");

    free(p);

    return 0;
}
