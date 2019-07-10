;
; Creates a BitMap in OCS/ECS - 5 BitPlanes : 32 Colours
;
; Get the Font Shapes from the BitMap and Displays it
;
; Get 4 Sprites from the BitMap ans Displays it
;
; Draw Lines
;

WBStartup

DEFTYPE .w

#COPPER = 0
#BIT = 0
#PAL = 0

; Creates the BitMap
BitMap #BIT,320,256,4

; Loads the BitMap
LoadBitMap #BIT,"data/fonte_16.lbm"

; Initializes and Loads the Palette
InitPalette #PAL,32

LoadPalette #PAL,"data/fonte_16.lbm",0  ;Load at a palette offset of 0
LoadPalette #PAL,"data/fonte_16.lbm",16 ;Load at a palette offset of 16

PalRGB #PAL,0,0,0,0

; Initializes 40 Shapes
For n=0 To 39
  InitShape n,15,9,4
Next n

; Gets the Shapes from the BitMap
For x=0 To 19
  GetaShape x,x*15,0,15,9
Next x

For x=20 To 39
  GetaShape x,(x-20)*15,10,15,9
Next x

; Gets the Sprites from the BitMap
GetaSprite 0,0
GetaSprite 1,1
GetaSprite 2,2
GetaSprite 3,3


; Initializes the Copper
COPPERTYPE.l=$5 ;5 bitplanes

InitCopList #COPPER,44,256,COPPERTYPE,8,32,0

DisplayPalette #COPPER,#PAL


BLITZ


; Draws the Lines
For y=0 To 32
  Line 0,y+150,319,y+150,y
Next y

; Displays the Shapes
For x=0 To 19
  Blit x,x*15,100
Next x

For x=20 To 39
  Blit x,(x-20)*15,110
Next x

; Displays the Sprites
DisplaySprite 0,0,10,200,0
DisplaySprite 0,1,25,200,2
DisplaySprite 0,2,40,200,4
DisplaySprite 0,3,55,200,6

; Creates the Display
CreateDisplay #COPPER

; Displays the BitMap
DisplayBitMap #COPPER,#BIT

MouseWait

End

