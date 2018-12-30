#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "words.h"
#include "dataio.h"
#include "display.h"
#define DOCLEN 100

int main(int argc, char *argv[])
{
	int i = 0;
	int user_action = 0;
	int state_save = 0;
	char doc[DOCLEN] = { 0 };
	struct words resault[DOCLEN];
	memset(resault, 0, sizeof(resault));

	if (argc > 1)
	{
		while (i < argc)
		{
			printf("%s", argv[i]);
		}

		assimilateData(doc, DOCLEN);
	}

	while (user_action != 4)
	{
		displayData(doc);
		memset(resault, 0, sizeof(resault));
		analyzeData(doc, resault);
		displayMenu();
		scanf("%d", &user_action);
		getchar();
		switch (user_action)
		{
		case 1:
			editData(doc, DOCLEN);
			state_save = 1;
			break;
		case 2:
			memset(resault, 0, sizeof(resault));
			analyzeData(doc, resault);
			break;
		case 3:
			saveData(doc);
			state_save = 0;
			break;
		case 4:
			if (state_save)
			{
				printf("Do you want to save your document? (Y/N) ");
				scanf("%c", &user_action);
				if (user_action == 'Y' || user_action == 'y')
				{
					saveData(doc);
				}
				user_action = 4;
			}
			break;
		default:
			printf("Invalid input.");
			break;
		}
		system("cls");
	}

    
    return 0;
}
