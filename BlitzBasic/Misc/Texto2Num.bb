;
; Converts a String to ASCII code
;

WBStartup

a$="ABCDEFGHIJKLMNOPQRSTUVWXYZ :!-0123456789"

NPrint ""
NPrint "Primeiro imprime o valor em ASCII"
NPrint ""

For n=1 To Len(a$)
  Print Asc(Mid$(a$,n,1))," "
Next n

NPrint ""
NPrint "Depois imprime a String de volta, LOL"
NPrint ""

For n=1 To Len(a$)
  Print Chr$(Asc(Mid$(a$,n,1)))
Next n

NPrint ""

MouseWait

End
