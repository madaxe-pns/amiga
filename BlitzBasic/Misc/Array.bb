;
; Array and NewType Example
;

WBStartup

; NewType
NEWTYPE .teste
  x.w
  y.w
  tipo.b
End NEWTYPE

; Array
Dim a.teste(5)

; Fills the Array: 0-2
a(0)\x=0
a(0)\y=10
a(0)\tipo=0

a(1)\x=100
a(1)\y=110
a(1)\tipo=1

a(2)\x=200
a(2)\y=210
a(2)\tipo=2

; Fills the Array: 3-5
For n=3 To 5
 a(n)\x=Rnd(320),Rnd(200)
 a(n)\tipo=n
Next n

; Prints the Array
For n=0 To 5
  NPrint "Record : ",n
  NPrint a(n)\x
  NPrint a(n)\y
  NPrint a(n)\tipo
  NPrint ""
Next n

MouseWait

End
