assume cs:code, ds:data

data segment
dw 2 dup(0)
data ends

code segment
start: mov ax, 21H
       mov ax, 4c00h
       int 21h
code ends

end start