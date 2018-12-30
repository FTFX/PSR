#include <stdio.h>
#include "display.h"

int displayData(char *doc)
{
	if (*doc == 0)
	{
		printf("Empty.");
	}
	else
	{
		printf("%s", doc);
	}

	return 0;
}