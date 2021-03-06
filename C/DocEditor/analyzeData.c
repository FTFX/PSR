#include <stdio.h>
#include "dataio.h"
#include "words.h"


int analyzeData(char *doc, struct words *resault)
{
	int i = 0;
	int j = 0;
	
	for (i = 0; doc[i]; i++)
	{
		for (j = 0; j < 100; j++)
		{
			// existing character
			if (resault[j].character == doc[i])
			{
				resault[j].frequence++;
				break;
			}
			// new character
			if (resault[j].character == 0)
			{
				resault[j].character = doc[i];
				resault[j].frequence++;
				break;
			}
		}
	}

	return 0;
}
