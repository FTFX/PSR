ASSUME CS:CODE,DS:DATA,SS:STACK

STACK SEGMENT
DW 0,0,0,0,0,0,0,0
STACK ENDS

DATA SEGMENT
DB 'aaaaaaaaaaaaaaaa'
DB 'aaaaaaaaaaaaaaaa'
DB 'aaaaaaaaaaaaaaaa'
DB 'aaaaaaaaaaaaaaaa'
DATA ENDS

CODE SEGMENT
START: MOV AX,STACK
       MOV SS,AX
	   MOV SP,16
	   MOV AX,DATA
	   MOV DS,AX
	   MOV BX,0
	   
	   MOV CX,4
S0:    PUSH CX
       MOV SI,0
	   
	   MOV CX,4
	   MOV AH,0
S:     MOV AL,[BX+SI+3]
       AND AL,11011111B
	   MOV [BX+SI+3],AL
	   MOV AX,0
	   INC SI
	   LOOP S
	   
	   ADD BX,16
	   POP CX
	   LOOP S0
	   
	   MOV AX,4C00H
	   INT 21H
CODE ENDS
END START