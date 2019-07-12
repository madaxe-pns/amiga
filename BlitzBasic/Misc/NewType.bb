;
; NewType Example
;

WBStartup

; NewType
NEWTYPE .teste
  x.w
  y.w
  tipo.b
End NEWTYPE

; Fills the Record
a.teste\x=300
a.teste\y=280
a.teste\tipo=1

; Prints the Record
NPrint a\x
NPrint a\y
NPrint a\tipo

MouseWait

End
