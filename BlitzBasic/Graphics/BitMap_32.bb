;
; Creates and Loads a BitMap on OCS/ECS - 5 BitPlanes : 32 Colours
; Draws Lines
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

; Initializes the Copper
COPPERTYPE.l=$5 ;5 bitplanes

InitCopList #COPPER,44,256,COPPERTYPE,8,32,0

DisplayPalette #COPPER,#PAL


BLITZ


; Draw the Lines
For y=0 To 31
    Line 0,y+150,319,y+150,y
Next y

; Creates the Display
CreateDisplay #COPPER

; Displays the BitMap
DisplayBitMap #COPPER,#BIT


MouseWait

End

