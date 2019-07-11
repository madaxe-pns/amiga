;
; Joystick Example in OCS/ECS - 5 BitPlanes : 32 Colours
;

WBStartup

DEFTYPE .w

#COPPER = 0
#BIT = 0
#PAL = 0

; Creates the BitMap
BitMap #BIT,320,256,5

; Loads the BitMap
LoadBitMap #BIT,"data/spr_32.lbm"

; Initializes and Loads the Palette
InitPalette #PAL,32
LoadPalette #PAL,"data/spr_32.lbm",0

PalRGB #PAL,0,0,0,0

; Gets the Shape from BitMap
GetaShape 0,0,24,16,16

; Initializes the Copper
COPPERTYPE.l=$5 ;5 bitplanes

InitCopList #COPPER,44,256,COPPERTYPE,8,32,0

DisplayPalette #COPPER,#PAL


BLITZ

; Creates the Display
CreateDisplay #COPPER

; Displays the BitMap
DisplayBitMap #COPPER,#BIT

; Uses Buffer Blits - Don't Damage the Background
Buffer 0,512

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

