assume cs:code

code segment
start:	mov ax, 0020H
		mov bx, 1000H
		add bx, 0F000H
		adc ax, 001EH
		
		mov ax, 4c00h
		int 21h
code ends
end start
	   