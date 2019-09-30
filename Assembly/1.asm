assume cs:code, ds:data

data segment
dw 10 dup(0)
data ends

code segment
start: mov bx, data
       mov ds, bx
	   mov si, 0
	   mov ax, 12666
	   call dtoc
	   
	   mov dh, 8
	   mov dl, 3
	   mov cl, 2
	   call show_str
	   
	   mov ax, 4c00h
       int 21h
	   
dtoc: push dx
      push cx
  s1: mov dx, 0
      mov cx, 0010
      div cx
	  mov cx, dx
	  add dl, 30H
	  mov ds:[si], dl
	  inc si
	  jcxz return
	  jmp near ptr s1
	   
show_str: push dx
          push cx
          mov bx, 0B800H

		  ;接下来进行地址运算
		  sub dh, 1                                   ;8-1=7 为算法做准备
		  mov al, 0AH                                 ;为算法做准备
		  mul dh                                      ;dh和al相乘 正好是目标段地址的偏移量 并放进ax
		  
		  add bx, ax                                  ;先把计算结果ax和bx的B800相加 得出目标段地址
		  mov es, bx                                  ;相加后放进es中
		  
		  sub dl, 1                                   ;3-1=2 因为内存中以0开头 若要定位到第三组 则必须为2
		  mov al, 2                                   ;因为在显存中 每两个字节为一个显示单位 所以要乘2
		  mul dl                                      ;2*2=4 因为一个字符占一个字 所以得出第3列的偏移地址
		  mov bl, al                                  ;4正好为目标偏移地址 偶数 并放进bx中的bl 准备输入数据
		  mov bh, 0                                   ;为防止意外 将dh清零 准备输入数据
		  sub si, 2
		  
		  ;接下来正式开始数据输入
    oops: mov al, ds:[si]                             ;提取到的源数据放入al
		  mov es:[bx], al                             ;再由al放进目标地址
		  mov es:[bx+1], cl                           ;把属性值放进比字符位高一位的属性位里 奇数
		  
		  add bx, 2
		  mov cx, si
		  sub si, 1                                   ;由于dtoc存储时是逆序存储 所以-1
		  
		  jcxz return
		  jmp near ptr oops
		  
return: pop cx
        pop dx
        ret

code ends
end start