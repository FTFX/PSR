assume cs:code

code segment
start:	mov ax, 0
		mov bx, 1
		shl bx, 1
		add ax, bx
		mov bx, 1
		mov cl, 3
		shl bx, cl
		add ax, bx
		
		mov ax, 4c00h
		int 21h
code ends
end start