;
; Dual PlayFied OCS/ECS Example
;
; As each BitMap has 3 BitPlanes (8 Colours)
; the Copper must be initialized with 6 BitPlanes (3+3)
;


WBStartup

#COPPER = 0
#BIT0 = 0
#BIT1 = 1
#PAL = 0

; Creates Foreground BitMap
BitMap #BIT0,320,256,3

; Creates Background BitMap
BitMap #BIT1,320,256,3

; Initializes and Loads the Palette
InitPalette #PAL,16

LoadPalette #PAL,"data/palOCS01.pal",0
LoadPalette #PAL,"data/palOCS02.pal",8

PalRGB #PAL,0,0,0,0


COPPERTYPE.l=$6  ; 6 BitPlanes
COPPERTYPE+$1  0 ; Smooth Scrolling
COPPERTYPE+$20   ; Dual PlayField

; Initializes the Copper
InitCopList #COPPER,COPPERTYPE


BLITZ

; Draws Boxes in Foreground BitMap
Use BitMap #BIT0

For n=0 To 7
  Boxf n*15+10,10,n*15+20,20,n
Next

; Draws Boxes in Background BitMap
Use BitMap #BIT1

For n=0 To 7
  Boxf n*15+10,30,n*15+20,40,n+8
Next

; Creates the Display
CreateDisplay #COPPER

; Displays the Palette
DisplayPalette #COPPER,#PAL

; Displays the BitMaps
DisplayBitMap #COPPER,#BIT0,-1,0,#BIT1,-1,0

While Joyb(0)=0

Wend


End

