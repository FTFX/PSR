#include <stdio.h>
#include <stdlib.h>
#include <stdlib.h>
#include "dataio.h"
#include "words.h"

int editData(char *doc, const size_t doclen, char mode)
{
	int i = 0;
	int row = 0;
	int col = 0;

	if (mode == 'w')
	{
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
	}
	else if (mode == 'd')
	{
		printf("Row: ");
		scanf("%d", &row);
		printf("Col: ");
		scanf("%d", &col);

		row--;
		while (doc[i] != 0 && i < doclen && row != 0)
		{
			if (doc[i] == '\n')
			{
				row--;
			}
			i++;
		}

		col--;
		while (doc[i] != 0 && i < doclen && col != 0)
		{
			i++;
			col--;
		}
		
		while (i + 1 < doclen)
		{
			doc[i] = doc[i + 1];
			i++;
		}
	}
	
	// make sure it ends with a terminator
	doc[doclen - 1] = 0;

	return i - 1;
}
