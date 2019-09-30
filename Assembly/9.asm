assume cs:code, ds:data, ss:stack

data segment
db 'welcome to masm!'
db 0b, 01110001b, 00100100b, 00000010b
data ends

stack segment
dw 0,0
stack ends

code segment
start: mov ax, data
       mov ds, ax
	   mov ax, stack
	   mov ss, ax
	   mov sp, 4
	   mov ax, 0B884h
	   mov es, ax
	   mov cx, 3
       push ax
	   
oops:  mov di, 20h
	   pop ax
	   add ax, 0Ah
	   mov es, ax
	   push ax
	   push cx
	   mov bx, cx
	   mov ah, [bx+16]
	   mov bx, 0
	   mov cx, 16
	   
input: mov al, [bx]
       mov es:[di], ax
	   inc bx
	   add di, 2
	   loop input
	   
	   pop cx
	   loop oops
	   
	   mov ax, 4c00h
	   int 21h
	   
code ends
end start