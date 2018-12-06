#include <stdio.h>
#include <stdlib.h>
#define LISTSIZE 100

int printList(int *lp);
int addData(int tdata, int *lp);
int modifyData(int tobj, int tdata, int *lp);
int searchData(int tobj, int *lp);
int deleteData(int tobj, int *lp);
int adjustStructure(int *lp);

int main()
{
	int choice = 0;
	int target_obj = 0;
	int target_data = 0;
	int list[LISTSIZE] = { 0 };

	while (choice != 5)
	{
		printList(list);
		printf("What you want to do?\n1.Add 2.Modify 3.Delete 4.Search 5.Exit\n> ");
		scanf_s("%d", &choice);
		switch (choice)
		{
		case 1:
			printf("add: ");
			scanf_s("%d", &target_data);
			addData(target_data, list);
			break;

		case 2:
			printf("mod: ");
			scanf_s("%d%d", &target_obj, &target_data);
			modifyData(target_obj, target_data, list);
			break;

		case 3:
			printf("del: ");
			scanf_s("%d", &target_obj);
			deleteData(target_obj, list);
			break;

		case 4:
			printf("sch: ");
			scanf_s("%d", &target_obj);
			searchData(target_obj, list);
			system("pause");
			break;

		case 5:
			break;

		default:
			printf("Invalidate input.\n");
			system("pause");
			break;
		}
		adjustStructure(list);
		system("cls");
	}

	return 0;
}

int printList(int *lp)
{
	int i = 0;
	printf("Total %d\n", LISTSIZE);
	while (i < LISTSIZE)
	{
		printf("%d", *(lp + i));
		if (i + 1 != LISTSIZE)
		{
			printf("\t");
		}
		if ((i + 1) % 10 == 0)
		{
			printf("\n");
		}
		i++;
	}

	return 0;
}

int addData(int tdata, int *lp)
{
	int i = 0;
	while (i < LISTSIZE)
	{
		if (*(lp + i) == 0)
		{
			*(lp + i) = tdata;
			break;
		}
		i++;
	}
	return 0;
}

int modifyData(int tobj, int tdata, int *lp)
{
	*(lp + tobj - 1) = tdata;

	return 0;
}

int searchData(int tobj, int *lp)
{
	printf("Student\tScore\n%d\t%d\n", tobj, *(lp + tobj - 1));
	
	return 0;
}
int deleteData(int tobj, int *lp)
{
	*(lp + tobj - 1) = 0;

	return 0;
}

int adjustStructure(int *lp)
{
	int i = 0;
	int flag = 1;
	while (flag)
	{
		while (i < LISTSIZE)
		{
			if (*(lp + i) == 0)
			{
				flag = 0;
			}
			else
			{
				if (!flag)
				{
					i--;
					if (i != LISTSIZE - 1)
					{
						*(lp + i) = *(lp + i + 1);
						*(lp + i + 1) = 0;
					}
					flag = 1;
				}
			}
			i++;
		}
	}

	return 0;
}