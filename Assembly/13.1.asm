assume cs:code

code segment
start:	mov ax, 4240h
		mov dx, 000Fh
		mov cx, 0Ah
		
		call divdw
		
		mov ax, 4c00h
		int 21h
		
divdw:	push ax
		div dx, cx
		mul 65536
		mul 65536
		mov ax, bx
		pop ax
		add dx, ax
		div dx, cx
		
		add dx, bx
		
		ret
		
code ends
end start