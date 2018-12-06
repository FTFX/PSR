#include <stdio.h>
#include <stdlib.h>

// Each node has 2 pointers which is *last and *next to point its parent and child.
//                          > [node A]
//                        /
//                       |    <- *last points to the last
//                        \
//                         [node B]
//                                 \
//   *next points to the next ->    |
//                                 /
//                      [node C] <
typedef struct stack
{
	struct stack *last;
	struct stack *next;
	int content;
}stack;

int printStack(stack *handle);
int pushData(stack *handle, int tData);
int popData(stack *handle);

int main()
{
	// Init stack;
	stack obj;
	obj.last = NULL;
	obj.next = NULL;
	obj.content = 0;

	int target_data = 0;
	int recive_data = 0;
	int choice = 0;
	// Draw the menu;
	while (choice != 3)
	{
		printStack(&obj);                      // Print the stack first;
		printf("\n1.Push 2.Pop 3.Exit\n>> ");
		scanf_s("%d", &choice);
		switch (choice)
		{
		// Option Push;
		case 1:
			printf("Push: ");
			scanf_s("%d", &target_data);
			pushData(&obj, target_data);
			break;
		// Option Pop;
		case 2:
			recive_data = popData(&obj);
			printf("Pop: %d\n", recive_data);
			system("pause");
			break;
        // Option Exit;
		case 3:
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

int printStack(stack *handle)
{
	int counter = 1;

	// Check out if stack is empty;
	if (handle->next == NULL)
	{
		printf("Empty Stack.");
		return 1;
	}

	// Print the current data;
	do
	{
		handle = handle->next;      // 1. The first node is a flag - move on while first iteration  2. Move on each iteration time;
		printf("%d%c", handle->content, counter % 5 == 0 ? '\n' : '\t');
		counter++;
	} while (handle->next != NULL);

	return 0;
}

int pushData(stack *handle, int tData)
{
	// Move to the top of the stack;
	while (handle->next != NULL)
	{
		handle = handle->next;
	}

	handle->next = (stack*)malloc(sizeof(stack));      // *next points to this new one;
	handle->next->last = handle;                       // Init new one - new.last points to current node;
	handle = handle->next;                             // handle points to the new one;
	handle->next = NULL;                               // Init new one - new.next points to NULL;
	handle->content = tData;                           // Init new one - new.content = user's input;

	return 0;
}

int popData(stack *handle)
{
	int data = 0;

	// Check out if stack is empty;
	if (handle->next == NULL)
	{
		return 0;
	}
	// Move to the top of the stack;
	while (handle->next != NULL)
	{
		handle = handle->next;
	}

	data = handle->content;        // Save the top to data;
	handle = handle->last;         // Move up;
	free(handle->next);            // Clean up - release mem;
	handle->next = NULL;           // Clean up - make the current node become the top;

	return data;
}