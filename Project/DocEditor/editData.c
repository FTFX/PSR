#include <stdio.h>
#include <stdlib.h>
#include <stdlib.h>
#include "dataio.h"
#include "words.h"

int editData(char *doc, const int doclen)
{
	int i = 0;
	system("cls");
	while (i < doclen)
	{
		doc[i] = getchar();
		if (doc[i] == 19)
		{
			doc[i] = 0;
			break;
		}
		i++;
	}
	return 0;
}