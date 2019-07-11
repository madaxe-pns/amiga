;
; Joystick Example in AGA - 8 BitPlanes : 256 Colours
;

WBStartup

DEFTYPE .w

#COPPER = 0
#BIT = 0
#PAL = 0

; Creates the BitMap
BitMap #BIT,320,256,8

; Loads the BitMap
LoadBitMap #BIT,"data/spr_256.lbm"

; Initializes and Loads the Palette
InitPalette #PAL,256
LoadPalette #PAL,"data/spr_256.lbm",0

AGAPalRGB #PAL,0,0,0,0

; Gets the Shape from the BitMap
GetaShape 0,0,24,16,16

; Initializes the Copper
COPPERTYPE.l=$8   ;8 bitplanes
COPPERTYPE+$10000 ; AGA colours

InitCopList #COPPER,44,256,COPPERTYPE,8,256,0

DisplayPalette #COPPER,#PAL


BLITZ

; Creates the Display
CreateDisplay #COPPER

; Displays the BitMap
DisplayBitMap #COPPER,#BIT

; Uses Buffer Blits - Don't Damage the Background
Buffer 0,1024

x=160
y=100

While Joyb(0)=0

  VWait

  x+Joyx(1)*2
  y+Joyy(1)*2

  x=QLimit(x,0,304)
  y=QLimit(y,0,239)

  UnBuffer 0
  BBlit 0,0,x,y

Wend

End

