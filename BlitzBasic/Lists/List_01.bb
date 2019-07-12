;
; Dynamic Lists
;

WBStartup


NEWTYPE .listas
  n.w
  x.w
  y.w
End NEWTYPE

; Creates the List
Dim List lista.listas(9)

cnt.w=0

; Fills the List
While AddItem(lista())
  lista()\n=cnt
  lista()\x=Rnd(10)
  lista()\y=Rnd(10)
  cnt+1
Wend

; Sets the List to the Beginning
ResetList lista()

; Prints the List
While NextItem(lista())
  NPrint "n-"+Str$(lista()\n)+" : x-"+Str$(lista()\x)+" , y-"+Str$(lista()\y)
Wend

; Sets the List to the Beginning
ResetList lista()

; Remove some itens from the List
While NextItem(lista())
  If lista()\x<5 KillItem lista()
Wend

NPrint""

; Sets the List to the Beginnig
ResetList lista()

; Prints the new List
While NextItem(lista())
  NPrint "n-"+Str$(lista()\n)+" : x-"+Str$(lista()\x)+" , y-"+Str$(lista()\y)
Wend

While Joyb(0)<>2
Wend

End
