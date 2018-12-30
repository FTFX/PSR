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
			if (resault[j].character == doc[i])
			{
				resault[j].frequence++;
				break;
			}
			if (resault[j].character == 0)
			{
				resault[j].character = doc[i];
				resault[j].frequence++;
				break;
			}
		}
	}
	printf("\n");
	for (i = 0; resault[i].character != 0; i++)
	{
		if (resault[i].character == '\n')
		{
			printf("\\n:%d", resault[i].frequence);
		}
		else
		{
			printf("%c:%d", resault[i].character, resault[i].frequence);
		}
		printf("%c", i % 3 ? '\t' : '\n');
	}
	return 0;
}