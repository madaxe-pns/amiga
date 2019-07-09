;
; Opens a Binary File and Writes Data to it
;

WBStartup

; Data
NEWTYPE .la
  a.b[100]
End NEWTYPE

DEFTYPE .la la

; Prints the Data
For n=0 To 99
  la\a[n]=n
Next n

; Prints The Size of Data
NPrint SizeOf.la

; Opens and Writes the Data to a File
If WriteFile (0,"inimigos.dat")

  WriteMem 0,&la,SizeOf.la

  CloseFile 0

End If

MouseWait

End

