;
; Dynamic Lists.
; Two Lists
;

WBStartup

NEWTYPE .listas
  n.w
  x.w
  y.w
End NEWTYPE

; Creates 2 Lists
Dim List lista1.listas(9)
Dim List lista2.listas(9)

cnt.w=0

; Fills the Lista
While AddItem(lista1())

  lista1()\n=cnt
  lista1()\x=Rnd(10)
  lista1()\y=Rnd(10)

  If AddItem (lista2())
    lista2()\n=cnt
    lista2()\x=Rnd(100)
    lista2()\y=Rnd(100)
  End If

  cnt+1

Wend

; Sets both List to the Beginning
ResetList lista1()
ResetList lista2()

; Prints the Lists
While NextItem(lista1())
  NPrint "Lista 1 : n-",lista1()\n," : x-",lista1()\x," , y-",lista1()\y
  NextItem lista2()
  NPrint "Lista 2 : n-",lista2()\n," : x-",lista2()\x," , y-",lista2()\y
Wend

While Joyb(0)<>2
Wend

End
