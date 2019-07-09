;
; Creates a Sin and Cos Table
; and saves it to a file
;

WBStartup

; Sin and Cos Table
NEWTYPE .sincos
  s.q[126]
  c.q[126]
End NEWTYPE

DEFTYPE .sincos sincos


; Fills the Table
ang.q=0
For n=0 To 125
  sincos\s[n]=Sin(ang)
  sincos\c[n]=Cos(ang)
  ang+0.05
Next n


; Prints the Table
For n=0 To 125
  NPrint sincos\s[n]," ",sincos\c[n]
Next n

; Prints the Size of the Table
NPrint SizeOf.sincos


; Writes the Table to a File
If WriteFile (0,"sincostab.dat")

  WriteMem 0,&sincos,SizeOf.sincos

  CloseFile 0

End If

MouseWait

End

