;
; Opens a Binary File and Reads Data from it
;

WBStartup

; Data
NEWTYPE .la
  a.b[100]
End NEWTYPE

DEFTYPE .la la

; Prints the Size of Data
NPrint SizeOf.la

; Opens and Reads Data from the File
If ReadFile (0,"inimigos.dat")

  ReadMem 0,&la,SizeOf.la

  CloseFile 0

End If

; Prints the Data
For n=0 To 99
  NPrint la\a[n]
Next n

MouseWait

End

