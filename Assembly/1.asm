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

		  ;���������е�ַ����
		  sub dh, 1                                   ;8-1=7 Ϊ�㷨��׼��
		  mov al, 0AH                                 ;Ϊ�㷨��׼��
		  mul dh                                      ;dh��al��� ������Ŀ��ε�ַ��ƫ���� ���Ž�ax
		  
		  add bx, ax                                  ;�ȰѼ�����ax��bx��B800��� �ó�Ŀ��ε�ַ
		  mov es, bx                                  ;��Ӻ�Ž�es��
		  
		  sub dl, 1                                   ;3-1=2 ��Ϊ�ڴ�����0��ͷ ��Ҫ��λ�������� �����Ϊ2
		  mov al, 2                                   ;��Ϊ���Դ��� ÿ�����ֽ�Ϊһ����ʾ��λ ����Ҫ��2
		  mul dl                                      ;2*2=4 ��Ϊһ���ַ�ռһ���� ���Եó���3�е�ƫ�Ƶ�ַ
		  mov bl, al                                  ;4����ΪĿ��ƫ�Ƶ�ַ ż�� ���Ž�bx�е�bl ׼����������
		  mov bh, 0                                   ;Ϊ��ֹ���� ��dh���� ׼����������
		  sub si, 2
		  
		  ;��������ʽ��ʼ��������
    oops: mov al, ds:[si]                             ;��ȡ����Դ���ݷ���al
		  mov es:[bx], al                             ;����al�Ž�Ŀ���ַ
		  mov es:[bx+1], cl                           ;������ֵ�Ž����ַ�λ��һλ������λ�� ����
		  
		  add bx, 2
		  mov cx, si
		  sub si, 1                                   ;����dtoc�洢ʱ������洢 ����-1
		  
		  jcxz return
		  jmp near ptr oops
		  
return: pop cx
        pop dx
        ret

code ends
end start