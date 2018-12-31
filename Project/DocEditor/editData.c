#include <stdio.h>
#include <stdlib.h>
#include <stdlib.h>
#include "dataio.h"
#include "words.h"

int editData(char *doc, const size_t doclen)
{
	int i = 0;
	system("cls");
	while (i < doclen)
	{
		doc[i] = getchar();
		// if recive "ctrl+s" - assign terminator to the end of array
		if (doc[i] == 19)
		{
			doc[i] = 0;
			break;
		}
		i++;
	}
	// make sure it ends with a terminator
	doc[doclen - 1] = 0;

	return i - 1;
}
