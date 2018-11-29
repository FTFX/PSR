#include <stdio.h>
#include <stdlib.h>

typedef struct block {
	int data;
	struct block *last;
	struct block *next;
} block;

// Create a queue.
block *queue = NULL;

int printQueue();
int pushData(int target_data);
int popData();

int main()
{
	int choice = 0;
	int target_data = 0;
	int recive_data = 0;

	// Test info.
	//printf("%d %d %d %d %d", queue->data, queue->last, queue->next, head->data, bottom->data);
	//system("pause");

	// Print the menu.
	while (choice != 3)
	{
		printQueue(queue);                      // Print the queue first.
		printf("\n1.Push 2.Pop 3.Exit\n>> ");
		scanf_s("%d", &choice);
		switch (choice)
		{
		// Option Push.
		case 1:
			printf("Push: ");
			scanf_s("%d", &target_data);
			pushData(target_data);
			break;
		// Option Pop.
		case 2:
			recive_data = popData();
			printf("Pop: %d\n", recive_data);
			system("pause");
			break;
		// Option Exit.
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

int printQueue()
{
	int counter = 1;
	block *handle = queue;

	// Check out if queue is empty;
	if (queue == NULL)
	{
		printf("Empty Queue.");
		return 1;
	}

	// Print the current data;
	do
	{
		printf("%d%c", handle->data, counter % 5 == 0 ? '\n' : '\t');
		handle = handle->next;
		counter++;
	} while (handle != queue);

	return 0;
}

int pushData(int target_data)
{
	if (queue == NULL)
	{
		queue = (block*)malloc(sizeof(block));
		queue->data = target_data;
		queue->last = queue;
		queue->next = queue;
	}
	else
	{
		queue->last->next = (block*)malloc(sizeof(block));
		queue->last->next->last = queue->last;
		queue->last->next->data = target_data;
		queue->last->next->next = queue;
		queue->last = queue->last->next;
	}

	return 0;
}

int popData()
{
	int pop_data = 0;

	if (queue == NULL)
	{
		printf("The queue is empty.");
		return -1;
	}
	else if(queue->last == queue)
	{
		pop_data = queue->data;
		free(queue);
		queue = NULL;
	}
	else
	{
		pop_data = queue->data;
		queue = queue->next;
		queue->last = queue->last->last;
		free(queue->last->next);
		queue->last->next = queue;
	}

	return pop_data;
}