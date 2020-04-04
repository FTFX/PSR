#include <stdio.h>
#include "dataio.h"

int saveData(char *doc, const size_t doclen, const char *path)
{
	FILE *fp_doc = NULL;
	fp_doc = fopen(path, "w");

	if (fp_doc == NULL)
	{
		printf("Faild to save file.");
		return 1;
	}

	fwrite(doc, sizeof(char), doclen, fp_doc);

	fclose(fp_doc);

	return 0;
}
