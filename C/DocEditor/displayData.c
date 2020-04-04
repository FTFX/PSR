#include <stdio.h>
#include "display.h"

int displayData(char *doc, const size_t doclen)
{
	unsigned int i = 0;
	unsigned int counter = 0;

	if (*doc == 0)
	{
		printf("Empty.\n");
	}
	else
	{
		// print the docs char one by one
		while (doc[i] != '\0' || i >= doclen)
		{
			printf("%c", doc[i]);
			if (doc[i] != '\n')
			{
				counter++;
			}
			i++;
		}
		printf("\n\nTotal %u\n\n", counter);
	}

	return 0;
}
