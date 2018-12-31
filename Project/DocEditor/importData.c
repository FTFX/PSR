#include <stdio.h>
#include <stdlib.h>
#include "dataio.h"

int importData(char *doc, const size_t doclen, char *path)
{
	FILE *fp_doc = NULL;
	fp_doc = fopen(path, "r");

	if (fp_doc == NULL)
	{
		printf("Faild to open %s", path);
		system("pause");
		return 1;
	}

	fread(doc, sizeof(char), doclen, fp_doc);

	return 0;
}
