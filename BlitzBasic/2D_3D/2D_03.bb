;
; Performs a simple rotation in 2D - Octagon
; using a Sin and Cos Table
; created previously
;

WBStartup

; Sin and Cos Table
NEWTYPE .sincos
  s.q[126]
  c.q[126]
End NEWTYPE

DEFTYPE .sincos sincos


; Dots Table
NEWTYPE .pxs
  x.w
  y.w
  r.w
End NEWTYPE

Dim px.pxs(7)


; Reads the Sin and Cos Table from a file
If ReadFile (0,"sincostab.dat")

  ReadMem 0,&sincos,SizeOf.sincos

  CloseFile 0

End If


; Dots
px(0)\x=0
px(0)\y=0
px(0)\r=0

px(1)\x=0
px(1)\y=0
px(1)\r=16

px(2)\x=0
px(2)\y=0
px(2)\r=32

px(3)\x=0
px(3)\y=0
px(3)\r=48

px(4)\x=0
px(4)\y=0
px(4)\r=64

px(5)\x=0
px(5)\y=0
px(5)\r=80

px(6)\x=0
px(6)\y=0
px(6)\r=96

px(7)\x=0
px(7)\y=0
px(7)\r=112


; Creates the BitMap
BitMap 0,320,256,1

; Initializes the Palette
InitPalette 0,2

PalRGB 0,0,0,0,0
PalRGB 0,1,15,15,15

; Initializes the Copper
InitCopList 0,$1

DisplayPalette 0,0


BLITZ


; Creates the Display
CreateDisplay 0

; Displays the BitMap
DisplayBitMap 0,0


While Joyb(0)=0

  c=0              ; erase figure
  Gosub drawfigure

  Gosub makemaths  ; do the maths

  c=1              ; draw figure
  Gosub drawfigure

  VWait

Wend

End


; Do the Maths
.makemaths

  For n=0 To 7

    px(n)\x=160+sincos\s[px(n)\r]*75
    px(n)\y=128+sincos\c[px(n)\r]*75

    px(n)\r+1
    If px(n)\r=126 px(n)\r=0

  Next n

Return


; Draw Figure
.drawfigure

  Line px(0)\x,px(0)\y,px(1)\x,px(1)\y,c
  Line px(1)\x,px(1)\y,px(2)\x,px(2)\y,c
  Line px(2)\x,px(2)\y,px(3)\x,px(3)\y,c
  Line px(3)\x,px(3)\y,px(4)\x,px(4)\y,c

  Line px(4)\x,px(4)\y,px(5)\x,px(5)\y,c
  Line px(5)\x,px(5)\y,px(6)\x,px(6)\y,c
  Line px(6)\x,px(6)\y,px(7)\x,px(7)\y,c
  Line px(7)\x,px(7)\y,px(0)\x,px(0)\y,c

  Line px(0)\x,px(0)\y,px(4)\x,px(4)\y,c
  Line px(1)\x,px(1)\y,px(5)\x,px(5)\y,c
  Line px(2)\x,px(2)\y,px(6)\x,px(6)\y,c
  Line px(3)\x,px(3)\y,px(7)\x,px(7)\y,c

Return
