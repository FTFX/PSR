assume cs:code

data segment
db "MASM$"
data ends

code segment
start:	mov ah, 2
		mov bh, 0
		mov dh, 5
		mov dl, 12
		int 10h
		
		mov bx, 0
		mov dx, 0
		
		mov ax, data
		mov ds, ax
		mov ax, 0
		mov dx, ax
		mov ax, 0900
		int 21h
		
		mov ax, 4c00h
		int 21h
code ends
end start