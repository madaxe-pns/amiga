;
; Creates 3 Coppers in OCS/ECS with 5 BitPlanes each - 32 Colours each
;
; Creates 3 BitMaps and displays each of them in their respective Copper
;

WBStartup

; Creates BitMap 0 - Top
BitMap 0,320,85,5  ;123

; Creates BitMap 1 - Middle
BitMap 1,320,95,5  ;179

; Creates BitMap 2 - Bottom
BitMap 2,320,85,5  ;137


; Loads BitMap 0 - Top
LoadBitMap 0,"data/init_01.lbm"

; Loads BitMap 1 - Middle
LoadBitMap 1,"data/init_02.lbm"

; Loads BitMap 2 - Bottom
LoadBitMap 2,"data/init_03.lbm"


; Initializes the Palettes

InitPalette 0,32
LoadPalette 0,"data/init_01.lbm",0

InitPalette 1,32
LoadPalette 1,"data/init_02.lbm",0

InitPalette 2,32
LoadPalette 2,"data/init_03.lbm",0

; Initializes Copper 0 (Top)
InitCopList 0,44,85,$5,8,32,0

; Initializes Copper 1 (Middle)
InitCopList 1,131,85,$5,8,32,0

; Initializes Copper 2 (Bottom)
InitCopList 2,218,85,$5,8,32,0

BLITZ

; Creates the Displays from Top to Bottom
CreateDisplay 0,1,2

; Displays Palette 0 and BitMap 0 in Copper 0 (Top)
DisplayPalette 0,0
DisplayBitMap 0,0

; Displays Palette 1 and BitMap 1 in Copper 1 (Middle)
DisplayPalette 1,1
DisplayBitMap 1,1

; Displays Palette 2 and BitMap 2 in Copper 2 (Bottom)
DisplayPalette 2,2
DisplayBitMap 2,2

MouseWait

End
