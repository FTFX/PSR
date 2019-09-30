assume cs:code

code segment
start:	mov ah, 15h
		mov bh, 20h
		cmp ah, bh
		je set1
		jne set2
		
		mov ax, 4c00h
		int 21h
		
set1:	add ah, ah
		ret
set2:	add ah, bh
		ret
		
code ends
end start