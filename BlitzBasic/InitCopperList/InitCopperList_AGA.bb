;
; Creates 3 Coppers on AGA with 8 BitPlanes each : 256 Colours each
;
; Creates 3 BitMaps and displays each of them on their respective Copper
;

WBStartup

; Creates BitMap 0 - Top
BitMap 0,320,82,8

; Creates BitMap 1 - Middle
BitMap 1,320,82,8

; Creates BitMap 2 - Bottom
BitMap 2,320,82,8


; Loads BitMap 0 - Top
LoadBitMap 0,"data/iniaga_01.lbm"

; Loads BitMap 1 - Middle
LoadBitMap 1,"data/iniaga_02.lbm"

; Load BitMap 2 - Bottom
LoadBitMap 2,"data/iniaga_03.lbm"


; Initializes the Palettes

InitPalette 0,256
LoadPalette 0,"data/iniaga_01.lbm",0

InitPalette 1,256
LoadPalette 1,"data/iniaga_02.lbm",0

InitPalette 2,256
LoadPalette 2,"data/iniaga_03.lbm",0

; Initializes Copper 0 - Top
InitCopList 0,44,82,$10008,8,256,0

; Initializes Copper 1 - Middle
InitCopList 1,137,82,$10008,8,256,0

; Initializes Copper 2 - Bottom
InitCopList 2,230,82,$10008,8,256,0

BLITZ

; Creates the Displays from Top to Bottom
CreateDisplay 0,1,2

; Displays Palette 0 and BitMap 0 in Copper 0 - Top
DisplayPalette 0,0
DisplayBitMap 0,0
Use BitMap 0
Box 0,0,319,81,1

; Displays Palette 1 and BitMap 1 in Copper 1 - Middle
DisplayPalette 1,1
DisplayBitMap 1,1
Use BitMap 1
Box 0,0,319,81,1

; Displays Palette 2 and BitMap 2 in Copper 2 - Middle
DisplayPalette 2,2
DisplayBitMap 2,2
Use BitMap 2
Box 0,0,319,81,1

MouseWait

End
