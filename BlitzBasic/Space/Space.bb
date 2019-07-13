;
; Simple Kind of Demo
;
;

WBStartup

NEWTYPE .rod
  x.q
  y.q
  dx.q
  dy.q
  r.q
End NEWTYPE

Dim rodas.rod(3)

rodas(0)\x=0
rodas(0)\y=128
rodas(0)\dx=1
rodas(0)\dy=-1
rodas(0)\r=0

rodas(1)\x=160
rodas(1)\y=0
rodas(1)\dx=1
rodas(1)\dy=1
rodas(1)\r=Pi/2

rodas(2)\x=306
rodas(2)\y=128
rodas(2)\dx=-1
rodas(2)\dy=1
rodas(2)\r=Pi

rodas(3)\x=160
rodas(3)\y=242
rodas(3)\dx=-1
rodas(3)\dy=-1
rodas(3)\r=3*Pi/2

bx=0
by=0
bdx=1
bdy=-1

#COPPER = 0
#BIT = 0
#BIT2 = 1
#PAL = 0

NPrint "Loading Background BitMap, please wait."

; Creates the Background BitMap
BitMap #BIT,640,480,5

; Loads the Background BitMap
LoadBitMap #BIT,"space_16.lbm"

NPrint "Loading Sprites Bitmap, please wait."

; Creates the Sprites BitMap
BitMap #BIT2,60,14,4

; Loads the Sprites BitMap
LoadBitMap #BIT2,"sprite_16.lbm"

; Initializes the Palette
InitPalette #PAL,32

; Gets the Shapes from the Sprites BitMap
Use BitMap #BIT2
GetaShape 0,0,0,14,14
GetaShape 1,15,0,14,14
GetaShape 2,30,0,14,14
GetaShape 3,45,0,14,14

; Gets the Sprites from the Shapes
GetaSprite 0,0
GetaSprite 1,1
GetaSprite 2,2
GetaSprite 3,3

; Frees the Shapes
Free Shape 0
Free Shape 1
Free Shape 2
Free Shape 3

; Frees the Sprites BitMap
Free BitMap #BIT2

NPrint "Loading Palettes, please wait"

; Loads the Palettes
LoadPalette #PAL,"sprite_16.lbm",16
LoadPalette #PAL,"space_16.lbm",0 ;Load at a palette offset of 0

PalRGB #PAL,0,0,0,0

; Initializes the Copper

COPPERTYPE.l=$5 ;5 bitplanes
COPPERTYPE+$10 ;Smooth scrolling

InitCopList #COPPER,44,256,COPPERTYPE,8,32,0

NPrint "Loading Music, please wait."

; Loads the Protraker Module
LoadModule 0,"musica.mod"

NPrint "Empty Drive Caches, please wait"

VWait 5*50

BLITZ

; Plays the Module
PlayModule 0

; Creates the Display
CreateDisplay #COPPER

; Displays the Palette
DisplayPalette #COPPER,#PAL

While Joyb(0)=0

  ; Moves the Background BitMap
  If bx<321 AND bdx=1
      bx+0.5 Else bdx=-1
  End If

  If bx>0 AND bdx=-1
      bx-0.5 Else bdx=1
  End If

  If by<224 AND bdy=1
      by+0.5 Else bdy=-1
  End If

  If by>0 AND bdy=-1
      by-0.5 Else bdy=1
  End If

  ; Displays the Background BitMap
  DisplayBitMap #COPPER,#BIT,bx-1,by

  ; Rotates the Sprites
  For n=0 To 3

    rodas(n)\r+0.05

    x=160+Sin(rodas(n)\r)*100
    y=128+Cos(rodas(n)\r)*100

    ; Displays the Sprites
    DisplaySprite 0,n,x,y,n*2

  Next n

  VWait

Wend

; Stops Playing the Module
StopModule

End

