;
; Performs a simple rotation in 2D - Square
;

WBStartup

; Dots Table
NEWTYPE .pxs
  x.w
  y.w
  r.q
End NEWTYPE

Dim px.pxs(3)

; Dots
px(0)\x=0
px(0)\y=0
px(0)\r=0

px(1)\x=0
px(1)\y=0
px(1)\r=Pi/2

px(2)\x=0
px(2)\y=0
px(2)\r=Pi

px(3)\x=0
px(3)\y=0
px(3)\r=Pi*3/2

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

  Gosub drawfigure

  VWait

Wend

End


; Do the Maths
.makemaths

  For n=0 To 3

    px(n)\r+0.05

    px(n)\x=160+Sin(px(n)\r)*50
    px(n)\y=128+Cos(px(n)\r)*50

  Next n

Return


; Draw Figure
.drawfigure

; Erase the last figure
  Line px(0)\x,px(0)\y,px(1)\x,px(1)\y,0
  Line px(1)\x,px(1)\y,px(2)\x,px(2)\y,0
  Line px(2)\x,px(2)\y,px(3)\x,px(3)\y,0
  Line px(3)\x,px(3)\y,px(0)\x,px(0)\y,0

  Gosub makemaths

; Draw the new figure
  Line px(0)\x,px(0)\y,px(1)\x,px(1)\y,1
  Line px(1)\x,px(1)\y,px(2)\x,px(2)\y,1
  Line px(2)\x,px(2)\y,px(3)\x,px(3)\y,1
  Line px(3)\x,px(3)\y,px(0)\x,px(0)\y,1

Return
