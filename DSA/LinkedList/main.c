#include <stdio.h>
#include <stdlib.h>

typedef struct element
{
	struct element *next;
	int data;
}node;

node* head = NULL;

int main()
{
	// Init
	int choice = 0;
	int target_obj = 0;
	int target_data = 0;

	head = (node*)malloc(sizeof(node));
	head->next = NULL;
	head->data = 0;
	while (choice != 4)
	{
		printList(head);
		printf("\n1.Add 2.Modify 3.Delete 4.Exit\n");
		scanf_s("%d", &choice);
		switch (choice)
		{
		case 1:
			printf("add: ");
			scanf_s("%d", &target_data);
			addData(target_data, head);
			break;

		case 2:
			printf("mod: ");
			scanf_s("%d%d", &target_obj, &target_data);
			modifyData(target_obj, target_data, head);
			break;

		case 3:
			printf("del: ");
			scanf_s("%d", &target_obj);
			deleteData(target_obj, head);
			break;

		case 4:
			break;

		default:
			printf("Invalidate input.\n");
			system("pause");
			break;
		}
		system("cls");
	}
	return 0;
}

int printList(node *head)
{
	int i = 0;
	node *handle = NULL;

	if (head == NULL)
	{
		printf("ERROR: Head is null.");
		return 1;
	}

	handle = head;
	printf("%d", handle->data);
	while (handle->next != NULL)
	{
		handle = handle->next;
		printf("\t%d", handle->data);
		i++;
		if (i % 10 == 0)
		{
			printf("\n");
		}
	}

	return 0;
}

int addData(int tdata, node *head)
{
	node *handle = NULL;

	if (head == NULL)
	{
		printf("ERROR: Head is null.");
		return 1;
	}

	handle = head;
	while (handle->next != NULL)
	{
		handle = handle->next;
	}
	handle->next = (node*)malloc(sizeof(node));
	handle = handle->next;
	handle->data = tdata;
	handle->next = NULL;

	return 0;
}

int modifyData(int tobj, int tdata, node *head)
{
	int i = 0;
	node *handle = NULL;

	if (head == NULL)
	{
		printf("ERROR: Head is null.");
		return 1;
	}
	handle = head;

	while (i + 1 < tobj)
	{
		handle = handle->next;
		i++;
	}
	handle->data = tdata;

	return 0;
}

int deleteData(int tobj, node *head)
{
	int i = 0;
	node *handle1 = NULL;
	node *handle2 = NULL;

	if (head == NULL)
	{
		printf("ERROR: Head is null.");
		return 1;
	}
	handle1 = head;
	if (tobj == 1)
	{
		handle2 = head;
		head = head->next;
		free(handle2);
	}
	else
	{
		while (i + 1 < tobj - 1)
		{
			if (handle1->next == NULL)
			{
				printf("No data found.");
				return 1;
			}
			handle1 = handle1->next;
			i++;
		}
		handle2 = (handle1->next)->next;
		free(handle1->next);
		handle1->next = handle2;
	}
	return 0;
}