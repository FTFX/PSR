#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "words.h"
#include "dataio.h"
#include "display.h"
#define DOCLEN 500

int main(int argc, char *argv[])
{
	int i = 0;
	int contlen = 0;
	int user_action = 0;
	int state_save = 0;
	char doc[DOCLEN] = { 0 };
	struct words resault[DOCLEN];
	memset(resault, 0, sizeof(resault));

	// import document from file
	if (argc > 1)
	{
		importData(doc, DOCLEN, argv[1]);
	}

	// user interface part
	while (user_action != 4)
	{
		displayData(doc, DOCLEN);

		// reset resault array
		memset(resault, 0, sizeof(resault));
		analyzeData(doc, resault);

		// print resaults
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
			printf("%c", (i + 1) % 3 ? '\t' : '\n');
		}
		printf("%c", i % 3 ? '\n' : ' ');

		// user interaction part
		displayMenu();
		scanf("%d", &user_action);
		getchar();
		switch (user_action)
		{
		case 1:
			editData(doc, DOCLEN, 'w');
			state_save = 1;
			break;
		case 2:
			editData(doc, DOCLEN, 'd');
			state_save = 1;
			break;
		case 3:
			if (argc > 1)
			{
				saveData(doc, DOCLEN, argv[1]);
			}
			else
			{
				saveData(doc, DOCLEN, "document.txt");
			}
			state_save = 0;
			break;
		case 4:
			if (state_save)
			{
				printf("Do you want to save your document? (Y/N) ");
				scanf("%c", (char*)&user_action);
				if (user_action == 'Y' || user_action == 'y')
				{
					if (argc > 1)
					{
						saveData(doc, DOCLEN, argv[1]);
					}
					else
					{
						saveData(doc, DOCLEN, "document.txt");
					}
				}
				user_action = 4;
			}
			break;
		default:
			printf("Invalid input.");
			system("pause");
			break;
		}
		system("cls");
	}
    
    return 0;
}
