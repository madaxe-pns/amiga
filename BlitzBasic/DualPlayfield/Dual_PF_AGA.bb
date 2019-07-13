;
; Dual PlayField AGA Example
;

WBStartup

#COPPER = 0
#BIT0 = 0
#BIT1 = 1
#PAL = 0

; Creates the Foreground BitMap
BitMap #BIT0,320,256,4

; Creates the Background BitMap
BitMap #BIT1,320,256,4

; Initializes and Loads the Palette
InitPalette #PAL,32

LoadPalette #PAL,"data/palAGA01.pal",16
LoadPalette #PAL,"data/palAGA02.pal",0

AGAPalRGB #PAL,0,0,0,0


COPPERTYPE.l=$8   ;8 BitPlanes
COPPERTYPE+$10    ;Smooth Scrolling
COPPERTYPE+$20    ;Dual PlayField
COPPERTYPE+$10000 ;AGA Colours

; Initializes The Copper
InitCopList #COPPER,COPPERTYPE

; Fix AGA Colours
DisplayControls #COPPER,0,$1C00,0

BLITZ

; Draws Boxes in Foreground BitMap
Use BitMap #BIT0

For n=0 To 15
  Boxf n*15+10,10,n*15+20,20,n
Next

; Draws Boxes in Background BitMap
Use BitMap #BIT1

For n=0 To 15
  Boxf n*15+10,30+16,n*15+20,40+16,n+16
Next

; Creates the Display
CreateDisplay #COPPER

; Displays the Palette
DisplayPalette #COPPER,#PAL

; Displays the BitMaps
DisplayBitMap #COPPER,#BIT0,-1,0,#BIT1,-1,0

MouseWait

End

