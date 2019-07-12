;
; Reading Data Example
; Gosub and Goto Example
;

WBStartup

.input
Print "Introduz um numero entre 1 e 9 : "

a=Edit(1)

If a<1 OR a>9 Goto input

If a<6 Gosub rotina1 Else Gosub rotina2

NPrint "Obrigado!!"

MouseWait

End

.rotina1

NPrint "Numero entre 1 e 5"

If a=1 Goto subrotina1 Else Goto fimrotina1

.subrotina1
NPrint "Primeiro numero"

.fimrotina1
Return


.rotina2

NPrint "Numero entre 6 e 9"

If a=9 Goto subrotina2 Else Goto fimrotina2

.subrotina2
NPrint "Ultimo numero"

.fimrotina2
Return


